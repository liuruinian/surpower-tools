package io.github.liuruinian.chana.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @author liuruinian
 * @version 2023-01-05
 */
public interface OrderLogisticsService {

    /**
     * @param inputStream input stream
     * @return import result
     */
    Map<String, Object> importOrderLogistics(InputStream inputStream) throws IOException;
}
