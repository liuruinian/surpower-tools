package io.github.liuruinian.fanco.server.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * fanco properties
 *
 * @author liuruinian
 * @version 2022-12-26
 */
@ConfigurationProperties(prefix = "fanco")
public class FancoProperties {

    /** aid */
    private Integer aid = 28140240;

    /** client id */
    private String clientId = "fk-hd-open-client-121";

    /** client secret */
    private String clientSecret = "72ad4fc0d02f438e95c55af068517f3e";

    /** authorization_code_url */
    private String authorizationCodeUrl = "http://127.0.0.1:8080/fanco/oauth/authorization_code";

    /** access token uri */
    private String accessTokenUri = "https://openauth-hd.fkw.com/oauth/partner/token?auth_type=partner_client_credential";

    /** access token key prefix */
    private String accessTokenKeyPrefix = "fanco-access-token-";

    /** access token expire , default 7200 seconds */
    private Long    authTokenExpire = 7100L;

    public String getAuthorizationCodeUrl() {
        return authorizationCodeUrl;
    }

    public void setAuthorizationCodeUrl(String authorizationCodeUrl) {
        this.authorizationCodeUrl = authorizationCodeUrl;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Long getAuthTokenExpire() {
        return authTokenExpire;
    }

    public void setAuthTokenExpire(Long authTokenExpire) {
        this.authTokenExpire = authTokenExpire;
    }

    public String getAccessTokenKeyPrefix() {
        return accessTokenKeyPrefix;
    }

    public void setAccessTokenKeyPrefix(String accessTokenKeyPrefix) {
        this.accessTokenKeyPrefix = accessTokenKeyPrefix;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getAccessTokenUri() {
        return accessTokenUri;
    }

    public void setAccessTokenUri(String accessTokenUri) {
        this.accessTokenUri = accessTokenUri;
    }
}
