package com.ywsoftware.oa.modules.sys.service.impl;

import com.ywsoftware.oa.common.exception.InvalidParameterAppException;
import com.ywsoftware.oa.common.utils.CookieHelper;
import com.ywsoftware.oa.common.utils.DrawImg;
import com.ywsoftware.oa.common.utils.MD5;
import com.ywsoftware.oa.common.utils.SendMessageUtil;
import com.ywsoftware.oa.modules.sys.domain.entity.PhoneVerifyCode;
import com.ywsoftware.oa.modules.sys.service.mapper.PhoneVerifyCodeMapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yx
 */
@Service
public class SecurityService {

    /**
     * Cookie中存入的图片验证码值
     */
    private static final String PIC_CODE = "SecCode";

    /**
     * Cookie中存入手机验证码值
     */
    private static final String TEL_CODE = "TelCode";

    /**
     * 短信存储mapper
     */
    private final PhoneVerifyCodeMapper phoneVerifyCodeMapper;

    @Autowired
    public SecurityService(PhoneVerifyCodeMapper phoneVerifyCodeMapper) {
        this.phoneVerifyCodeMapper = phoneVerifyCodeMapper;
    }

    /**
     * 校验图片验证码（只用于校验不会清session）
     *
     * @param code 传入输入图片验证码
     */
    public void verPicCode(String code) throws NoSuchAlgorithmException {
        String cookieCode = CookieHelper.getCookie(PIC_CODE);
        if (!MD5.md5(code.trim().toUpperCase()).equals(cookieCode)) {
            throw new InvalidParameterAppException("图片验证码输入错误");
        }
    }

    /**
     * 发送手机验证码，并存入cookie
     *
     * @param msg      发送短信信息
     * @param picCode  发送短信时校验图片验证码
     * @param phoneNum 手机号
     */
    public void sendPhoneCode(String msg, String picCode, String phoneNum, String type) throws RemoteException, NoSuchAlgorithmException {
        //校验图片验证码
        verPicCode(picCode);
        //构建短信
        long sendTelCode = RandomUtils.nextInt(1000, 9999);
        String message = String.format("，验证码为:%d 请不要把验证码泄露给其他人。如非本人操作，可不用理会！", sendTelCode);
        message = msg + message;
        //发送短信
        //亿美短信需要单号
        long orderNum = RandomUtils.nextLong(100000, 900000);
        SendMessageUtil.sendPhoneMsgByEmay(phoneNum, message, orderNum, "UTF-8");
        //将手机验证码和手机存入cookie
        String telCode = MD5.md5(String.valueOf(sendTelCode).toUpperCase().concat(phoneNum));
        CookieHelper.addCookie(TEL_CODE + type, telCode);
        //存入数据库
        PhoneVerifyCode phoneVerifyCode = new PhoneVerifyCode(phoneNum, type, String.valueOf(sendTelCode));
        phoneVerifyCodeMapper.insert(phoneVerifyCode);
    }

    /**
     * 创建验证码
     */
    public String generatePicCode() throws NoSuchAlgorithmException, IOException {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            //图片输出流写入
            String code = DrawImg.drawImg(output);
            //写入cookie
            CookieHelper.addCookie(PIC_CODE, MD5.md5(code.toUpperCase()));
            //写出base64编码
            return Base64.getEncoder().encodeToString(output.toByteArray());
        } catch (IOException ex) {
            throw ex;
        }
    }

    /**
     * 校验手机验证码
     *
     * @param phoneCode 传入手机验证码
     */
    public void verPhoneCode(String type, String phoneCode, String phoneNum) throws NoSuchAlgorithmException {
        //校验
        if (phoneCode != null && MD5.md5(phoneCode.trim().toUpperCase().concat(phoneNum)).equals(CookieHelper.getCookie(TEL_CODE + type))) {
            //清除短信cookie
            //CookieHelper.delCookie(TEL_CODE + type);
        } else {
            throw new InvalidParameterAppException("手机验证码输入错误");
        }
    }
}
