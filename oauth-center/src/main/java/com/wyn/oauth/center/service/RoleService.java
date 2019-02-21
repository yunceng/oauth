package com.wyn.oauth.center.service;

import com.wyn.oauth.center.entity.RoleEntity;
import java.util.List;

public interface RoleService {
    List<RoleEntity> getRoleValuesByUserId(Integer userId);
}
