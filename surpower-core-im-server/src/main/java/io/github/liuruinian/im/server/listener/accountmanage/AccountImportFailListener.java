package io.github.liuruinian.im.server.listener.accountmanage;

import io.github.liuruinian.im.server.events.accountmanage.AccountImportFailEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author liuruinian
 * @version 2022-02-06
 * <p>
 *     导入单个账号失败事件监听器
 * </p>
 */
public interface AccountImportFailListener extends ApplicationListener<AccountImportFailEvent> {
}
