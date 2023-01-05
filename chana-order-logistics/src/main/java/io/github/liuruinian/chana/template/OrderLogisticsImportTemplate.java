package io.github.liuruinian.chana.template;

import com.alibaba.excel.annotation.ExcelProperty;
import org.springframework.util.StringUtils;

import java.io.Serializable;

public class OrderLogisticsImportTemplate implements Serializable {
    @ExcelProperty(value = "订单编号", index = 0)
    private String      orderId;

    @ExcelProperty(value = "快递公司", index = 1)
    private String      logisticsName;

    @ExcelProperty(value = "物流编号", index = 2)
    private String      logisticsId;

    public void checkRequired() {
        if (!StringUtils.hasLength(orderId)) {
            throw new IllegalArgumentException("订单编号有空记录!");
        }
        if (!StringUtils.hasLength(logisticsName)) {
            throw new IllegalArgumentException("快递公司有空记录!");
        }
        if (!StringUtils.hasLength(logisticsId)) {
            throw new IllegalArgumentException("物流编号有空记录!");
        }
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getLogisticsName() {
        return logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
        this.logisticsName = logisticsName;
    }

    public String getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(String logisticsId) {
        this.logisticsId = logisticsId;
    }
}
