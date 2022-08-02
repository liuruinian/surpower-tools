package io.github.liuruinian.im.server.listener.accountmanage;

import io.github.liuruinian.im.server.events.accountmanage.AccountCheckFailEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author liuruinian
 * @version 2022-02-06
 * <p>
 *     检查账号导入失败事件监听器
 * </p>
 */
public interface AccountCheckFailListener extends ApplicationListener<AccountCheckFailEvent> {
}
