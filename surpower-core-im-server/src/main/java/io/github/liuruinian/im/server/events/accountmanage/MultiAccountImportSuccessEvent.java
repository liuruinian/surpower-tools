package io.github.liuruinian.im.server.events.accountmanage;

import io.github.liuruinian.im.core.restapi.AccountManageApi;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

import java.util.List;

/**
 * @author liuruinian
 * @version 2022-02-06
 * <p>
 *     导入多个账号成功事件
 * </p>
 */
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class MultiAccountImportSuccessEvent extends ApplicationEvent {

    private final List<String>  accounts;

    private final String        result;

    public MultiAccountImportSuccessEvent(AccountManageApi source, List<String> accounts, String result) {
        super(source);
        this.accounts = accounts;
        this.result = result;
    }
}
