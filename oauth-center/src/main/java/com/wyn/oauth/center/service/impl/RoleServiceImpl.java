package com.wyn.oauth.center.service.impl;

import com.wyn.oauth.center.dao.RoleDao;
import com.wyn.oauth.center.entity.RoleEntity;
import com.wyn.oauth.center.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleSerivice")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<RoleEntity> getRoleValuesByUserId(Integer userId) {
        return roleDao.getRoleValuesByUserId(userId);
    }
}
