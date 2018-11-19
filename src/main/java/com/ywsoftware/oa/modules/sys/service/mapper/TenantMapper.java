package com.ywsoftware.oa.modules.sys.service.mapper;

import com.ywsoftware.oa.modules.sys.domain.entity.SysUploadFile;
import com.ywsoftware.oa.modules.sys.domain.entity.UserTenant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 注意这里的sql查询都不会被过滤 tenantid不会自动追加
 */
@Mapper
public interface TenantMapper {

    @Update("UPDATE `user_tenant` SET "
            + "`tenantName` = #{tenantName}, "
            + "`logo` = #{logo} "
            + "WHERE `id` = #{id}")
    void updateTenantNameAndLogo(UserTenant tenant) throws Exception;

    @Update("UPDATE `user_tenant` SET "
            + "`tenantName` = #{tenantName} "
            + "WHERE `id` = #{id}")
    void updateTenantName(UserTenant tenant) throws Exception;

    @Select("      SELECT\n"
            + "        `ut`.`id`,\n"
            + "        `ut`.`tenantTitle`,\n"
            + "        `ut`.`tenantName`,\n"
            + "        `ut`.`contacts`,\n"
            + "        `ut`.`contactsAddress`,\n"
            + "        `ut`.`telephone`,\n"
            + "        `ut`.`createTime`,\n"
            + "        `ut`.`remark`,\n"
            + "        `ut`.`status`,\n"
            + "        `ut`.`logo`,\n"
            + "        `ut`.`matchColors`,\n"
            + "        `ut`.`isEffective`,\n"
            + "        `ua`.`name`,\n"
            + "        `ua`.`phoneNumber` AS `loginName`,\n"
            + "        `ua`.`mailbox` AS `email`\n"
            + "        FROM `user_tenant` `ut`\n"
            + "        LEFT JOIN `user_account` `ua` ON `ua`.`tenantId` = `ut`.`id`  AND `ua`.isTenantCreator = '1'\n"
            + "        WHERE `ut`.`id` = #{_parameter}")
    UserTenant getTenantInfoById(String tenantId) throws Exception;

    @Select("SELECT * FROM sys_upload_file WHERE id = #{Id}")
    SysUploadFile getFile(String id);
}
