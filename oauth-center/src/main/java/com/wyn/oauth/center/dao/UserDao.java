package com.wyn.oauth.center.dao;

import com.wyn.common.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

    UserEntity selectUserByUsername(@Param("username") String username);

}
