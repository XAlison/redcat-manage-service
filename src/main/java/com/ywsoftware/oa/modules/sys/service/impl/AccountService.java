package com.ywsoftware.oa.modules.sys.service.impl;

import com.ywsoftware.oa.common.exception.GlobalException;
import com.ywsoftware.oa.common.utils.Constant;
import com.ywsoftware.oa.common.utils.MD5;
import com.ywsoftware.oa.modules.sys.domain.PaginatedFilter;
import com.ywsoftware.oa.modules.sys.domain.PaginatedItems;
import com.ywsoftware.oa.modules.sys.domain.entity.Account;
import com.ywsoftware.oa.modules.sys.service.SysRoleService;
import com.ywsoftware.oa.modules.sys.service.SysUserRoleService;
import com.ywsoftware.oa.modules.sys.service.mapper.AccountMapper;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    private final AccountMapper mapper;

    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private SysUserRoleService sysUserRoleService;

    public AccountService(AccountMapper mapper) {
        this.mapper = mapper;
    }

    public List<Account> findAll() {
        return mapper.findAll();
    }

    public Account find(String id) {
        return mapper.find(id);
    }

    public List<Account> getAccountsByTenantId(String tenantId, String phone) {
        Account account = new Account();
        account.setPhone(phone);
        account.setTenantId(tenantId);
        return mapper.getAccountsByTenantId(account);
    }

    public boolean haveSecurity(String id) {
        return mapper.haveSecurity(id);
    }

    public boolean validatorSecurity(String id, String password) throws NoSuchAlgorithmException {
        Account account = new Account();
        account.setId(id);
        account.setSecurityPasswordHash(MD5.md5(password));
        return mapper.validatorSecurity(account);
    }

    public PaginatedItems<Object> getManageAccounts(PaginatedFilter filter) {
        List<Object> loginLogs = mapper.getManageAccounts(filter);
        Long totalCount = mapper.getManageAccountsCount(filter);
        return new PaginatedItems<>(loginLogs, totalCount);
    }


    @Transactional
    public void save(Account user) {

        //检查角色是否越权
        checkRole(user);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getId(), user.getRoleIdList());
    }

    /**
     * 检查角色是否越权
     */
    private void checkRole(Account user){
        if(user.getRoleIdList() == null || user.getRoleIdList().size() == 0){
            return;
        }
        //如果不是超级管理员，则需要判断用户的角色是否自己创建
        if(Constant.SUPER_ADMIN.contains(user.getId())){
            return ;
        }

        //查询用户创建的角色列表
        List<Long> roleIdList = sysRoleService.queryRoleIdList(user.getId());

        //判断是否越权
        if(!roleIdList.containsAll(user.getRoleIdList())){
            throw new GlobalException("新增用户所选角色，不是本人创建");
        }
    }
}
