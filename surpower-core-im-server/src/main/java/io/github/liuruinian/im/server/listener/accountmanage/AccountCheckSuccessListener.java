package io.github.liuruinian.im.server.listener.accountmanage;

import io.github.liuruinian.im.server.events.accountmanage.AccountCheckSuccessEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author liuruinian
 * @version 2022-02-06
 * <p>
 *     检查账号导入成功事件监听器
 * </p>
 */
public interface AccountCheckSuccessListener extends ApplicationListener<AccountCheckSuccessEvent> {
}
