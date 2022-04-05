package com.newpi.data.model;

import com.newpi.data.domain.UserDTO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/2 4:24 PM
 * @desc:
 */
@Data
@Accessors(chain = true)
public class SecurityUser implements UserDetails {

    private String userName;

    private String email;

    private String password;

    private Boolean enabled;

    private String clientId;

    List<GrantedAuthority> authorities;

    private Boolean accountExpired;

    private Boolean accountLocked;

    private Boolean passwordExpired;

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !passwordExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


    public SecurityUser(UserDTO userDto) {
        BeanUtils.copyProperties(userDto, this);
        this.userName = userDto.getUsername();
        if (userDto.getRoles() != null) {
            authorities = new ArrayList<>();
            userDto.getRoles().forEach(item -> authorities.add(new SimpleGrantedAuthority(item)));
        }
    }
}
