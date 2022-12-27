package io.github.liuruinian.fanco.core.constants;

public interface FancoConstants {

    String BASE_PATH = "/fanco";

    /**
     * 获取活动列表接口地址
     */
    String ACTIVITIES_LIST_API_URL = "https://open-hd.fkw.com/open-api/activities/list-page";

    /**
     * 获取活动详情接口地址
     */
    String ACTIVITIES_DETAIL_API_URL = "https://open-hd.fkw.com/open-api/activities/one";

    /**
     * 授权码请求地址
     */
    String OAUTH_AUTHORIZE_URL = "https://openauth-hd.fkw.com/oauth/authorize";

    /**
     * 令牌请求地址
     */
    String OAUTH_TOKEN_URL = "https://openauth-hd.fkw.com/oauth/token";
}
