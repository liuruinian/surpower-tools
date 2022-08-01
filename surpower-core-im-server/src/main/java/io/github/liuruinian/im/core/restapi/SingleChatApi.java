package io.github.liuruinian.im.core.restapi;

import io.github.liuruinian.im.core.params.singlechat.*;

/**
 * @author liuruinian
 * @version 2022-02-06
 * <p>
 *     单聊API
 * </p>
 */
public interface SingleChatApi {

    /**
     * 单发单聊消息
     *
     * @param singleIssueSingleChatParam 单发单聊请求体
     * <p>
     *      1.管理员向帐号发消息，接收方看到消息发送者是管理员.
     *      2.管理员指定某一帐号向其他帐号发消息，接收方看到发送者不是管理员，而是管理员指定的帐号.
     *      3.该接口不会检查发送者和接收者的好友关系（包括黑名单），同时不会检查接收者是否被禁言.
     *      4.该接口默认不会检查接收者对发送者是否设置了免打扰，如需检查，请在"SendMsgControl"字段填上"WithMuteNotifications".
     *      5.单聊消息 MsgSeq 字段的作用及说明：该字段在发送消息时由用户自行指定，该值可以重复，非后台生成，非全局唯一。
     *        与群聊消息的 MsgSeq 字段不同，群聊消息的 MsgSeq 由后台生成，每个群都维护一个 MsgSeq，从1开始严格递增。
     *        单聊消息历史记录对同一个会话的消息先以时间戳排序，同秒内的消息再以 MsgSeq 排序.
     * </p>
     */
    String sendMsg(SingleIssueSingleChatParam singleIssueSingleChatParam);

    /**
     * 批量发单聊消息
     *
     * @param batchSingleChatParam 批量发单聊消息请求体
     * <p>
     *      1.支持一次对最多500个用户进行单发消息.
     *      2.该接口不会检查发送者和接收者的好友关系（包括黑名单），同时不会检查接收者是否被禁言.
     *      3.管理员指定某一帐号向目标帐号批量发消息，接收方看到发送者不是管理员，而是管理员指定的帐号.
     *      4.该接口默认不会检查接收者对发送者是否设置了免打扰，如需检查，请在"SendMsgControl"字段填上"WithMuteNotifications".
     * </p>
     */
    String batchSendMsg(BatchSingleChatParam batchSingleChatParam);

    /**
     * 导入单聊消息
     *
     * @param importSingleChatParam 批量发单聊请求体
     * <p>
     *     1.平滑过渡期间，将原有即时通信实时单聊消息导入到即时通信 IM.
     *     2.该接口会更新会话，该接口不会触发回调.
     *     3.对于同一个单聊会话的消息，该接口会根据 MsgSeq ， MsgRandom ， MsgTimeStamp 字段的值对导入的消息进行去重。
     *       仅当这三个字段的值都对应相同时，才判定消息是重复的，消息是否重复与消息内容本身无关。
     *       另外，若两条消息的 MsgSeq ， MsgRandom ， MsgTimeStamp 字段对应相同，
     *       而 from_account 和 to_account 相反，则这两条消息也认为是重复的。
     *     4.重复导入的消息不会覆盖之前已导入的消息（即消息内容以首次导入的为准）.
     * </p>
     */
    String importMsg(ImportSingleChatParam importSingleChatParam);

    /**
     * 查询单聊消息
     *
     * @param querySingleChatParam 查询单聊消息请求体
     * <p>
     *      1.管理员按照时间范围查询某单聊会话的消息记录.
     *      2.查询的单聊会话由请求中的 From_Account 和 To_Account 指定。
     *        查询结果包含会话双方互相发送的消息，具体每条消息的发送方和接收方由每条消息里的 From_Account 和 To_Account 指定.
     *      3.一般情况下，请求中的 From_Account 和 To_Account 字段值互换，查询结果不变。
     *        但通过 单发单聊消息 或 批量发单聊消息 接口发送的消息，
     *        如果指定 SyncOtherMachine 值为2，则需要指定正确的 From_Account 和 To_Account 字段值才能查询到该消息。
     *      4.查询结果包含被撤回的消息，由消息里的 MsgFlagBits 字段标识.
     *      5.若想通过 REST API 撤回单聊消息 接口撤回某条消息，可先用本接口查询出该消息的 MsgKey，然后再调用撤回接口进行撤回.
     * </p>
     */
    String admin_getroammsg(QuerySingleChatParam querySingleChatParam);

    /**
     * 撤回单聊消息
     *
     * @param withdrawSingleChatParam 撤回单聊消息请求体
     * <p>
     *      1.管理员撤回单聊消息
     *      2.该接口可以撤回所有单聊消息，包括客户端发出的单聊消息，由 REST API 单发 和 批量发 接口发出的单聊消息.
     *      3.若需要撤回由客户端发出的单聊消息，您可以开通 发单聊消息之前回调 或 发单聊消息之后回调 ，
     *        通过该回调接口记录每条单聊消息的 MsgKey ，然后填在本接口的 MsgKey 字段进行撤回。
     *        您也可以通过 查询单聊消息 查询出待撤回的单聊消息的 MsgKey 后，填在本接口的 MsgKey 字段进行撤回。
     *      4.若需要撤回由 REST API 单发 和 批量发 接口发出的单聊消息，需要记录这些接口回包里的 MsgKey 字段以进行撤回。
     *      5.调用该接口撤回消息后，该条消息的离线、漫游存储，以及消息发送方和接收方的客户端的本地缓存都会被撤回。
     *      6.该接口可撤回的单聊消息没有时间限制，即可以撤回任何时间的单聊消息。
     * </p>
     */
    String admin_msgwithdraw(WithdrawSingleChatParam withdrawSingleChatParam);

    /**
     * 设置单聊消息已读
     *
     * @param readSingleChatParam 设置单聊消息已读请求体
     * <p>
     *     设置用户的某个单聊会话的消息已读。
     * </p>
     */
    String admin_set_msg_read(ReadSingleChatParam readSingleChatParam);

    /**
     * 查询单聊未读消息计数
     *
     * @param c2CUnreadMsgNumParam 查询单聊未读消息计数参数
     * <p>
     *     App 后台可以通过该接口查询特定帐号的单聊总未读数（包含所有的单聊会话）或者单个单聊会话的未读数。
     * </p>
     */
    String get_c2c_unread_msg_num(C2CUnreadMsgNumParam c2CUnreadMsgNumParam);
}
