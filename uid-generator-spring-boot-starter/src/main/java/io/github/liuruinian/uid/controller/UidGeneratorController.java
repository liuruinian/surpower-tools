package io.github.liuruinian.uid.controller;

import io.github.liuruinian.uid.impl.CachedUidGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hk417
 * @version Created in 2022/1/16 2:59
 * <p>
 *     UidGeneratorController
 * </p>
 */
@Api(tags = "百度UID生成器接口")
@RestController
public class UidGeneratorController {

    private CachedUidGenerator cachedUidGenerator;

    @Autowired
    public void setCachedUidGenerator(CachedUidGenerator cachedUidGenerator) {
        this.cachedUidGenerator = cachedUidGenerator;
    }

    @ApiOperation("生成UID")
    public String uidGenerate() {
        return String.valueOf(cachedUidGenerator.getUID());
    }
}
