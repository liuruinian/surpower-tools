package io.github.liuruinian.im.server.events.accountmanage;

import io.github.liuruinian.im.core.restapi.AccountManageApi;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

import java.util.Set;

/**
 * @author liuruinian
 * @version 2022-02-06
 * <p>
 *     账号删除成功事件
 * </p>
 */
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class AccountDeleteSuccessEvent extends ApplicationEvent {

    private final Set<String>   userIds;

    private final String        result;

    public AccountDeleteSuccessEvent(AccountManageApi source, Set<String> userIds, String result) {
        super(source);
        this.userIds = userIds;
        this.result = result;
    }
}
