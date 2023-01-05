package io.github.liuruinian.chana.controller;

import io.github.liuruinian.chana.service.OrderLogisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@Api(tags = "历史订单需发货API")
public class OrderLogisticsController {

    private OrderLogisticsService orderLogisticsService;

    @Autowired
    @Lazy
    public void setOrderLogisticsService(OrderLogisticsService orderLogisticsService) {
        this.orderLogisticsService = orderLogisticsService;
    }

    @ApiOperation("导入需要发货的历史订单")
    public Map<String, Object> importOrderLogistics(@RequestParam("file") MultipartFile file) throws IOException {
       return orderLogisticsService.importOrderLogistics(file.getInputStream());
    }
}
