package io.github.liuruinian.im.server.listener.accountmanage;

import io.github.liuruinian.im.server.events.accountmanage.AccountDeleteSuccessEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author liuruinian
 * @version 2022-02-06
 * <p>
 *     账号删除成功事件监听器
 * </p>
 */
public interface AccountDeleteSuccessListener extends ApplicationListener<AccountDeleteSuccessEvent> {
}
