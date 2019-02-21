package com.wyn.oauth.center.service.impl;

import com.wyn.common.entity.CustomUserDetails;
import com.wyn.common.entity.UserEntity;
import com.wyn.oauth.center.dao.MenuDao;
import com.wyn.oauth.center.dao.RoleDao;
import com.wyn.oauth.center.dao.UserDao;
import com.wyn.oauth.center.entity.MenuEntity;
import com.wyn.oauth.center.entity.RoleEntity;
import com.wyn.oauth.center.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private MenuDao menuDao;

    @Override
    public UserDetails loadUserByUsername(String s){
        /*模拟数据库操作*/
        UserEntity user = userDao.selectUserByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("用户:" + s + ",不存在!");
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        List<RoleEntity> roleValues = roleDao.getRoleValuesByUserId(user.getId());
        for (RoleEntity role:roleValues){
            //角色必须是ROLE_开头，可以在数据库中设置
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_"+role.getValue());
            grantedAuthorities.add(grantedAuthority);
            //获取权限
            List<MenuEntity> permissionList = menuDao.getMenusByRoleId(role.getId());
            for (MenuEntity menu:permissionList) {
                GrantedAuthority authority = new SimpleGrantedAuthority(menu.getCode());
                grantedAuthorities.add(authority);
            }
        }
        return new CustomUserDetails(user, true, true, grantedAuthorities);
    }

}
