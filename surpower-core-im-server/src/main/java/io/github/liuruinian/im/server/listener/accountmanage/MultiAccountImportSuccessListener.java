package io.github.liuruinian.im.server.listener.accountmanage;

import io.github.liuruinian.im.server.events.accountmanage.MultiAccountImportSuccessEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author liuruinian
 * @version 2022-02-06
 * <p>
 *     导入多个账号成功事件监听器
 * </p>
 */
public interface MultiAccountImportSuccessListener extends ApplicationListener<MultiAccountImportSuccessEvent> {
}
