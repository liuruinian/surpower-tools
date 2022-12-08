package io.github.liuruinian.ccr.server.ccrimpl.imgcensor;

import com.alibaba.fastjson.JSONObject;
import io.github.liuruinian.ccr.core.param.ImgCensorParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DefaultImgCensorApi extends AbstractImgCensorApi {


    @Override
    protected JSONObject handleImgCensor(ImgCensorParam param) {
        return null;
    }
}
