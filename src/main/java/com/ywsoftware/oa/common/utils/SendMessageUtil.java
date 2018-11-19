package com.ywsoftware.oa.common.utils;

import com.ywsoftware.oa.common.partner.emay.demo.SingletonClient;
import java.rmi.RemoteException;

/**
 * 发送消息Util类
 *
 * @author yux 2018年7月17日
 */
public class SendMessageUtil {

    /**
     * 亿美短信发送Util 发送带有信息ID的短信（可查状态报告）
     * @author yux 2018年7月17日
     * @param phoneNo    手机号码组 String 使用，分割 eg: "15558098706,18239900040,18565711060"
     * @param content    短信内容
     * @param smsId      信息ID，此ID可以与查询查询状态报告的方法中对照发送信息的状态（成功，失败）使用随机数
     * @param srcCharset 字符编码 默认传"UTF-8"
     * @return 0 成功 ；其他 失败
     */
    public static Integer sendPhoneMsgByEmay(String phoneNo, String content, long smsId,
                                             String srcCharset) throws RemoteException {
        int count = SingletonClient.getClient().sendSMSEx(phoneNo.split(","), "【红猫】" + content, "",
                srcCharset, 5, smsId);
        return count;
    }

}
