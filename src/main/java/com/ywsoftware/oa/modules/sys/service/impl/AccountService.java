package com.ywsoftware.oa.modules.sys.service.impl;

import com.ywsoftware.oa.common.utils.MD5;
import com.ywsoftware.oa.modules.sys.domain.entity.Account;
import com.ywsoftware.oa.modules.sys.service.mapper.AccountMapper;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountMapper mapper;

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
}
