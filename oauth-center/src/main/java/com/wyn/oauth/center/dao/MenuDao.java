package com.wyn.oauth.center.dao;

import com.wyn.oauth.center.entity.MenuEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuDao {

    List<MenuEntity> getMenusByRoleId(@Param("role_id") Integer roleId);
}
