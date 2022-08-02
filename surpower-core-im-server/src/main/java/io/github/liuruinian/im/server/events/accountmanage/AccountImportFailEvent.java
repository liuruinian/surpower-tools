package io.github.liuruinian.im.server.events.accountmanage;

import io.github.liuruinian.im.core.restapi.AccountManageApi;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

/**
 * @author liuruinian
 * @version 2022-02-06
 * <p>
 *     导入单个账号失败事件
 * </p>
 */
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class AccountImportFailEvent extends ApplicationEvent {

    private final String    userId;

    private final String    nick;

    private final String    faceUrl;

    private final String    result;

    private final Throwable throwable;

    public AccountImportFailEvent(AccountManageApi source, String userId, String nick, String faceUrl, String result, Throwable throwable) {
        super(source);
        this.userId = userId;
        this.nick = nick;
        this.faceUrl = faceUrl;
        this.result = result;
        this.throwable = throwable;
    }
}
