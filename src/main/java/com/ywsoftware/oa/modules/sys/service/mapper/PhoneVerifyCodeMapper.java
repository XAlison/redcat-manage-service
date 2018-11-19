package com.ywsoftware.oa.modules.sys.service.mapper;

import com.ywsoftware.oa.modules.sys.domain.entity.PhoneVerifyCode;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PhoneVerifyCodeMapper {

    @Insert("INSERT INTO phone_verify_code (id, phone, code, type) VALUES (#{id}, #{phone}, #{code}, #{type})")
    void insert(PhoneVerifyCode phoneVerifyCode);
}
