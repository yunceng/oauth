package com.wyn.oauth.center.service.impl;

import com.wyn.oauth.center.dao.MenuDao;
import com.wyn.oauth.center.entity.MenuEntity;
import com.wyn.oauth.center.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<MenuEntity> getMenusByRoleId(Integer roleId) {
        return menuDao.getMenusByRoleId(roleId);
    }
}
