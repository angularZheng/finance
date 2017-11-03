package com.zhengbing.common.security;

import com.zhengbing.entity.Role;
import com.zhengbing.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by zhengbing on 2017-11-01.
 */
public class SecurityUser extends User implements UserDetails {
    private static final long serialVersionUID = 1L;
    public SecurityUser(User user) {
        if(user != null){
            this.setId(user.getId());
            this.setRole(user.getRole());
            this.setUsername( user.getUsername());
            this.setEmail(user.getEmail());
            this.setPassword(user.getPassword());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority > authorities = new ArrayList<>();
        Role role = this.getRole();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
        authorities.add(authority);
        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
