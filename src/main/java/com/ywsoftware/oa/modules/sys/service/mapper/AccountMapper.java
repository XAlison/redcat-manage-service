package com.ywsoftware.oa.modules.sys.service.mapper;

import com.ywsoftware.oa.modules.sys.domain.PaginatedFilter;
import com.ywsoftware.oa.modules.sys.domain.entity.Account;
import com.ywsoftware.oa.modules.sys.domain.model.LoginModel;
import com.ywsoftware.oa.modules.sys.domain.model.TenantAccountModel;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AccountMapper {

    @Select("SELECT * FROM user_account WHERE state = 1")
    List<Account> findAll();

    @Select("SELECT * FROM user_account WHERE id = #{id} AND state = 1")
    Account find(String id);

    @Select("SELECT id, name, isManager, managePassword AS managePasswordHash, tenantId FROM user_account WHERE phoneNumber = #{phone} AND state = 1")
    List<Account> findByPhone(String phone);

    List<TenantAccountModel> findTenantAccounts(String phone);

    @Select("SELECT id, name, phoneNumber AS phone, tenantId, isManager manager FROM user_account WHERE phoneNumber = #{phone} AND isManager = 1 "
            + "AND id = #{id} AND managePassword = #{password} AND state = 1")
    Account findUserByPhoneAndPassword(LoginModel loginModel);

    @Update("UPDATE user_account SET managePassword = #{password} "
            + "WHERE phoneNumber = #{phone} AND isManager = 1 AND id = #{id} AND state = 1")
    void setManagePassword(LoginModel loginModel);

    @Select("SELECT id,phoneNumber as phone,name FROM user_account "
            + "WHERE tenantId = #{tenantId} AND state = 1 AND phoneNumber like  CONCAT(#{phone},'%') ")
    List<Account> getAccountsByTenantId(Account account);

    @Update("UPDATE user_account SET managePassword = NULL, isManager = 0 "
            + "WHERE isManager = 1 AND id = #{userId} AND state = 1")
    void delManager(String userId);

    @Select("SELECT * FROM user_account WHERE id = #{id} AND managePassword = #{managePasswordHash} AND state = 1 AND isManager = 1 ")
    Account getAccountByManagePwd(Account account);

    @Update("UPDATE user_account SET managePassword = NULL, isManager = 1 "
            + "WHERE (isManager is NULL OR isManager = 0) AND id = #{id} AND state = 1")
    int setManager(String id);

    @Select("SELECT CASE WHEN reportPassword IS NULL THEN FALSE ELSE TRUE END FROM user_account WHERE id = #{id}")
    boolean haveSecurity(String id);

    @Select("SELECT COUNT(*) FROM user_account WHERE id=#{id} AND reportPassword=#{securityPasswordHash}")
    boolean validatorSecurity(Account account);

    List<Object> getManageAccounts(PaginatedFilter filter);

    Long getManageAccountsCount(PaginatedFilter filter);
}
