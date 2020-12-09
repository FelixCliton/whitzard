package com.newpi.data.service.impl;

import com.google.common.collect.Lists;
import com.newpi.data.constant.MessageConstant;
import com.newpi.data.dao.UserDao;
import com.newpi.data.entity.RolePermission;
import com.newpi.data.entity.User;
import com.newpi.data.entity.UserRole;
import com.newpi.data.enums.ResultCode;
import com.newpi.data.exception.WhitzardException;
import com.newpi.data.model.PermissionDTO;
import com.newpi.data.model.UserDetail;
import com.newpi.data.model.UserDTO;
import com.newpi.data.service.PermissionService;
import com.newpi.data.service.RolePermissionService;
import com.newpi.data.service.UserRoleService;
import com.newpi.data.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/2 4:42 PM
 * @desc:
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public void add(UserDTO userDto) {

        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setAccountExpired(false)
                .setAccountLocked(false)
                .setPasswordExpired(false)
                .setEnabled(true)
                .setCreateTime(new Date());
        userDao.save(user);
    }

    @Override
    public void delete(Integer id) {
        Optional<User> userOptional = userDao.findById(id);
        if (userOptional.isPresent()) {
            userDao.save(userOptional.get().setEnabled(false));
        }
    }

    @Override
    public void update(UserDTO userDto) {
        Optional<User> userOptional = userDao.findById(userDto.getId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            BeanUtils.copyProperties(userDto, user);
            user.setUpdateTime(new Date());
            userDao.save(user);
        }
        throw new WhitzardException(ResultCode.USER_NOT_FOUND);
    }

    @Override
    public UserDTO findByEmail(String email) {
        User user = userDao.findByEmail(email);
        if (null == user) {
            throw new WhitzardException(ResultCode.USER_NOT_FOUND);
        }
        UserDTO userDto = new UserDTO();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    @Override
    public List<UserDTO> findAll() {

        Iterator<User> iterator = userDao.findAll().iterator();
        List<UserDTO> userDtoList = Lists.newArrayList();
        while (iterator.hasNext()) {
            User user = iterator.next();
            UserDTO userDto = new UserDTO();
            BeanUtils.copyProperties(user, userDto);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserDTO userDto = findByEmail(email);

        if (null==userDto) {
            throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }

        if (!userDto.getEnabled()) {
            throw new DisabledException(MessageConstant.ACCOUNT_DISABLED);
        } else if (userDto.getAccountLocked()) {
            throw new LockedException(MessageConstant.ACCOUNT_LOCKED);
        } else if (userDto.getAccountExpired()) {
            throw new AccountExpiredException(MessageConstant.ACCOUNT_EXPIRED);
        } else if (userDto.getPasswordExpired()) {
            throw new CredentialsExpiredException(MessageConstant.CREDENTIALS_EXPIRED);
        }

        UserDetail userDetail = new UserDetail();
        BeanUtils.copyProperties(userDto, userDetail);
        List<GrantedAuthority> authorities = Lists.newArrayList();

        List<UserRole> userRoles = userRoleService.findAllByUser(userDto.getId());
        List<Integer> roleIds = Lists.newArrayList();
        userRoles.forEach(userRole -> roleIds.add(userRole.getRoleId()));
        List<RolePermission> rolePermissions = rolePermissionService.findAllByRoles(roleIds);
        List<Integer> permissionIds = Lists.newArrayList();

        rolePermissions.forEach(rolePermission -> permissionIds.add(rolePermission.getPermissionId()));

        List<PermissionDTO> permissionDtoList = permissionService.findByIds(permissionIds);

        permissionDtoList.forEach(permissionDto -> authorities.add(new SimpleGrantedAuthority(permissionDto.getEnName())));

        userDetail.setAuthorities(authorities);
        return userDetail;
    }
}
