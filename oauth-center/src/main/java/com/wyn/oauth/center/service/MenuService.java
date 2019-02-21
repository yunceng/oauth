package com.wyn.oauth.center.service;

import com.wyn.oauth.center.entity.MenuEntity;
import java.util.List;

public interface MenuService {

    List<MenuEntity> getMenusByRoleId(Integer roleId);

}
