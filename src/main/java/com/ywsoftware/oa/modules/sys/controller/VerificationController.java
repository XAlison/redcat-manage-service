package com.ywsoftware.oa.modules.sys.controller;

import com.ywsoftware.oa.modules.sys.model.VerifyCodeModel;
import com.ywsoftware.oa.modules.sys.service.impl.SecurityService;
import java.io.IOException;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 图片、短信验证码发送、校验相关
 *
 * @author yx
 */
@RestController
@RequestMapping("/api/verifyCode")
public class VerificationController {

    private final SecurityService securityService;

    @Autowired
    public VerificationController(SecurityService securityService) {
        this.securityService = securityService;
    }

    /**
     * 生成验证码并存入session
     *
     */
    @GetMapping("/generatePicCode")
    public String generatePicCode() throws IOException, NoSuchAlgorithmException {
        return securityService.generatePicCode();
    }

    /**
     * 发送短信并清除图片验证码
     *
     * @param model 图片验证码
     */
    @PostMapping("/sendPhoneCode")
    public ResponseEntity sendPhoneCode(@RequestBody VerifyCodeModel model)
            throws RemoteException, NoSuchAlgorithmException {
        securityService.sendPhoneCode(model.getMessage(), model.getPicCode(), model.getPhoneNum(), model.getType());
        return new ResponseEntity<>("发送短信成功", HttpStatus.OK);
    }

    /**
     * 短信验证，验证成功清除session
     *
     * @param model 手机验证码
     */
    @PostMapping("/verPhoneCode")
    public ResponseEntity verPhoneCode(@RequestBody VerifyCodeModel model)
            throws NoSuchAlgorithmException {
        securityService.verPhoneCode(model.getType(), model.getPhoneCode(), model.getPhoneNum());
        return new ResponseEntity<>("手机验证码校验通过", HttpStatus.OK);
    }

    /**
     * 图片验证码，验证成功清除session
     *
     * @param model 校验码
     */
    @PostMapping("/verPicCode")
    public ResponseEntity verPicCode(@RequestBody VerifyCodeModel model) throws NoSuchAlgorithmException {
        securityService.verPicCode(model.getPicCode());
        return new ResponseEntity<>("图片验证码校验通过", HttpStatus.OK);
    }

}
