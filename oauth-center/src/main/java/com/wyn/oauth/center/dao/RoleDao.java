package com.wyn.oauth.center.dao;

import com.wyn.oauth.center.entity.RoleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleDao {

    List<RoleEntity> getRoleValuesByUserId(@Param("user_id") Integer userId);
}
