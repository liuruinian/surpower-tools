package io.github.liuruinian.chana.impl;

import cn.hutool.core.util.IdUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import io.github.liuruinian.chana.processor.OrderLogisticsProcessor;
import io.github.liuruinian.chana.service.OrderLogisticsService;
import io.github.liuruinian.chana.template.OrderLogisticsImportTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class DefaultOrderLogisticsService implements OrderLogisticsService, ApplicationContextAware {

    private final JdbcTemplate jdbcTemplate;

    private ApplicationContext applicationContext;

    private OrderLogisticsProcessor processor = null;

    public DefaultOrderLogisticsService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 批处理阈值
     */
    private static final int BATCH_COUNT = 100;

    @Override
    public Map<String, Object> importOrderLogistics(InputStream inputStream) throws IOException {
        Map<String, Object> result = new HashMap<>();

        try {
            processor = applicationContext.getBean(OrderLogisticsProcessor.class);
        } catch (BeansException e) {
            if (log.isInfoEnabled()) {
                log.info("application context can't find OrderLogisticsProcessor!");
            }
        }

        List<String> success = new ArrayList<>();
        AtomicInteger failed = new AtomicInteger(0);

        EasyExcel.read(inputStream, OrderLogisticsImportTemplate.class, new AnalysisEventListener<OrderLogisticsImportTemplate>() {

            final List<OrderLogisticsImportTemplate> list = new ArrayList<>(BATCH_COUNT);

            @Override
            public void invoke(OrderLogisticsImportTemplate orderLogisticsImportTemplate, AnalysisContext analysisContext) {
                list.add(orderLogisticsImportTemplate);
                if (list.size() >= BATCH_COUNT) {
                    saveData();
                    list.clear();
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                saveData();
            }

            private void saveData() {
                for (OrderLogisticsImportTemplate data : list) {
                    try {
                        data.checkRequired();
                        if (processor != null) {
                            processor.preProcessBeforeImport(data);
                        }

                        String orderId = data.getOrderId();
                        String logisticsName = data.getLogisticsName();
                        String logisticsId = data.getLogisticsId();

                        String sql = "INSERT INTO `order_logistics` (`id`, `order_id`, `logistics_id`, `logistics_name`, `is_wx`) " +
                                "VALUES (?, ?, ?, ?, ?) " +
                                "ON DUPLICATE KEY UPDATE " +
                                "`order_id` = VALUES(`order_id`), `logistics_id` = VALUES(`logistics_id`), `logistics_name` = VALUES(`logistics_name`), `is_wx` = 1";
                        int update = jdbcTemplate.update(sql, preparedStatement -> {
                            preparedStatement.setString(1, IdUtil.fastSimpleUUID());
                            preparedStatement.setString(2, orderId);
                            preparedStatement.setString(3, logisticsId);
                            preparedStatement.setString(4, logisticsName);
                            preparedStatement.setInt(5, 1);
                        });

                        if (update > 0) {
                            success.add(orderId);
                            if (processor != null) {
                                processor.postProcessBeforeImport(data);
                            }
                        }
                    } catch (Exception e) {
                        failed.incrementAndGet();
                    }
                }
            }
        }).sheet().headRowNumber(1).doReadSync();

        result.put("success", success);
        result.put("failed", failed);

        return result;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
