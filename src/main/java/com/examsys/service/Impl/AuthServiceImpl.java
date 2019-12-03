package com.examsys.service.Impl;

import com.examsys.model.entity.ResponseEntity;
import com.examsys.util.EncryptUtil;
import com.examsys.util.error.ErrorMsgEnum;
import jxl.write.DateTime;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.krb5.internal.crypto.Aes256;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.crypto.Data;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Service
public class AuthServiceImpl {


    private static long expireTime = 1000 * 60 * 1;

    public ResponseEntity CheckAuth(Map<String,Object> map) {
        try {
            int auth_stat = CheckAuthStat(String.valueOf(map.get("auth_token")));
            if(auth_stat == ErrorMsgEnum.TOKEN_EXPIRE_ERROR.getCode()) {
                return new ResponseEntity(ErrorMsgEnum.TOKEN_EXPIRE_ERROR);
            }
            else if(auth_stat == ErrorMsgEnum.NO_TOKEN_ERROR.getCode()) {
                return new ResponseEntity(ErrorMsgEnum.NO_TOKEN_ERROR);
            }
            else {
                return new ResponseEntity(200,"登录授权已校验");
            }
        }
        catch (Exception ex) {
            return new ResponseEntity(ErrorMsgEnum.TOKEN_WRONG_ERROR);
        }
    }

    int CheckAuthStat(String auth_token) {
        //无token
        if(auth_token == null || auth_token.equals("") || auth_token.equals("null")) {
            return ErrorMsgEnum.NO_TOKEN_ERROR.getCode();
        }
        String msg = EncryptUtil.Decrypt(auth_token);
        String token_time = msg.split("#")[0];
        //token过期
        long cur_time = new Date().getTime();
        long time_pass = cur_time - Long.valueOf(token_time);
        if(time_pass > expireTime) {
            return ErrorMsgEnum.TOKEN_EXPIRE_ERROR.getCode();
        }
        return 0;
    }



}
