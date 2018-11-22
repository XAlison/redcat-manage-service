package com.ywsoftware.oa.modules.sys.service.impl;

import com.ywsoftware.oa.common.utils.Constant;
import com.ywsoftware.oa.modules.sys.dao.SysMenuDao;
import com.ywsoftware.oa.modules.sys.dao.SysUserDao;
import com.ywsoftware.oa.modules.sys.dao.SysUserTokenDao;
import com.ywsoftware.oa.modules.sys.domain.entity.Account;
import com.ywsoftware.oa.modules.sys.domain.entity.SysMenuEntity;
import com.ywsoftware.oa.modules.sys.domain.entity.SysUserTokenEntity;
import com.ywsoftware.oa.modules.sys.service.ShiroService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ShiroServiceImpl implements ShiroService {

    @Resource
    private SysMenuDao sysMenuDao;

    @Resource
    private SysUserDao sysUserDao;

    @Resource
    private SysUserTokenDao sysUserTokenDao;

    @Override
    public Set<String> getUserPermissions(String userId) {
        List<String> permsList;

        //系统管理员，拥有最高权限
        if(Constant.SUPER_ADMIN.contains(userId)){
            List<SysMenuEntity> menuList = sysMenuDao.selectList(null);
            permsList = new ArrayList<>(menuList.size());
            for(SysMenuEntity menu : menuList){
                permsList.add(menu.getPerms());
            }
        }else{
            permsList = sysUserDao.queryAllPerms(userId);
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for(String perms : permsList){
            if(StringUtils.isBlank(perms)){
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    @Override
    public SysUserTokenEntity queryByToken(String token) {
        return sysUserTokenDao.queryByToken(token);
    }

    @Override
    public Account queryUser(String userId) {
        return sysUserDao.selectById(userId);
    }
}
