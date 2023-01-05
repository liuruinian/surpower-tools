package io.github.liuruinian.chana.processor;

import io.github.liuruinian.chana.template.OrderLogisticsImportTemplate;

public interface OrderLogisticsProcessor {
    /**
     * @param data excel data
     */
    void preProcessBeforeImport(OrderLogisticsImportTemplate data);

    /**
     * @param data imported data
     */
    void postProcessBeforeImport(OrderLogisticsImportTemplate data);
}
