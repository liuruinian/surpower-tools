package io.github.liuruinian.ccr.server.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "baidu.ccr")
public class BaiduCcrProperties {

    private String apiKey;

    private String secretKey;

    private String tokenUri = "https://aip.baidubce.com/oauth/2.0/token";

    private String tokenKeyPrefix = "baidu-ccr-token-";

    private Long   tokenExpire = 30L;

    private String endpoint = "https://aip.baidubce.com/rest/2.0";

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getTokenKeyPrefix() {
        return tokenKeyPrefix;
    }

    public void setTokenKeyPrefix(String tokenKeyPrefix) {
        this.tokenKeyPrefix = tokenKeyPrefix;
    }

    public Long getTokenExpire() {
        return tokenExpire;
    }

    public void setTokenExpire(Long tokenExpire) {
        this.tokenExpire = tokenExpire;
    }

    public String getTokenUri() {
        return tokenUri;
    }

    public void setTokenUri(String tokenUri) {
        this.tokenUri = tokenUri;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
