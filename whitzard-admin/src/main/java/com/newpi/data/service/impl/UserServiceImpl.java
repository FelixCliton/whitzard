package com.newpi.data.service.impl;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.newpi.data.constant.AuthConstant;
import com.newpi.data.dao.UserDao;
import com.newpi.data.domain.UpdatePasswordParam;
import com.newpi.data.domain.UserDTO;
import com.newpi.data.domain.UserParam;
import com.newpi.data.entity.*;
import com.newpi.data.enums.ResultCode;
import com.newpi.data.exception.WhitzardException;
import com.newpi.data.model.ResultEntity;
import com.newpi.data.service.AuthService;
import com.newpi.data.service.RoleService;
import com.newpi.data.service.UserRoleService;
import com.newpi.data.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/2 4:42 PM
 * @desc:
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthService authService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public void add(UserParam param) {
        if (null != findByUsername(param.getUsername())) {
            throw new WhitzardException(ResultCode.USER_EXIST);
        }
        User user = new User();
        BeanUtils.copyProperties(param, user);
        user.setPassword(encoder.encode(param.getPassword()))
                .setAccountExpired(false)
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
    public void update(User user) {
        Optional<User> userOptional = userDao.findById(user.getId());
        if (userOptional.isPresent()) {
            user.setUpdateTime(new Date());

            User rawUser = userOptional.get();
            if (!rawUser.getPassword().equals(user.getPassword())
                    && !encoder.encode(user.getPassword()).equals(rawUser.getPassword())) {
                user.setPassword(encoder.encode(user.getPassword()));
            }

            userDao.save(user);
        }
        throw new WhitzardException(ResultCode.USER_NOT_FOUND);
    }

    @Override
    public User findByUsername(String username) {
        User user = userDao.findByUsername(username);
        return user;
    }

    @Override
    public User findById(Integer id) {
        Optional<User> userOptional = userDao.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        return null;
    }

    @Override
    public List<User> findAll() {

        Iterator<User> iterator = userDao.findAll().iterator();
        List<User> userList = Lists.newArrayList();
        while (iterator.hasNext()) {
            User user = iterator.next();
            userList.add(user);
        }
        return userList;
    }

    @Override
    public List<Role> findRoleList(Integer userId) {
        return roleService.findRoleList(userId);
    }

    @Override
    public List<Resource> findResourceList(Integer userId) {

        List<UserRole> userRoles = userRoleService.findAllByUser(userId);
        if (null == userRoles || userRoles.isEmpty()) {
            return Lists.newArrayList();
        }
        List<Integer> roleIds = userRoles.stream().map(userRole -> userRole.getRoleId()).collect(Collectors.toList());

        return roleService.findResource(roleIds);
    }

    @Override
    public List<Permission> findPermissionList(Integer userId) {
        List<UserRole> userRoles = userRoleService.findAllByUser(userId);
        if (null == userRoles || userRoles.isEmpty()) {
            return Lists.newArrayList();
        }
        List<Integer> roleIds = userRoles.stream().map(userRole -> userRole.getRoleId()).collect(Collectors.toList());

        return roleService.findPermission(roleIds);
    }

    @Override
    public List<Menu> findMenuList(Integer userId) {
        List<UserRole> userRoles = userRoleService.findAllByUser(userId);
        if (null == userRoles || userRoles.isEmpty()) {
            return Lists.newArrayList();
        }
        List<Integer> roleIds = userRoles.stream().map(userRole -> userRole.getRoleId()).collect(Collectors.toList());

        return roleService.findMenu(roleIds);
    }

    @Override
    public ResultEntity login(String username, String password) {
        if (Strings.isNullOrEmpty(username) || Strings.isNullOrEmpty(password)) {
            throw new WhitzardException(ResultCode.USER_NAME_OR_PASSWORD_ERROR);
        }
        Map<String, String> params = new HashMap<>();
        params.put("client_id", AuthConstant.ADMIN_CLIENT_ID);
        params.put("client_secret", "123456");
        params.put("grant_type", "password");
        params.put("username", username);
        params.put("password", password);
        ResultEntity resultEntity = authService.getAccessToken(params);
        if (ResultCode.SUCCESS.getCode() == resultEntity.getHeader().getCode() && resultEntity.getData() != null) {
            //TODO 记录登陆日志
        }
        return resultEntity;
    }

    @Override
    public User findCurrentUser() {
        String username = request.getHeader(AuthConstant.USER_NAME_HEADER);
        if (Strings.isNullOrEmpty(username)) {
            throw new WhitzardException(ResultCode.UNAUTHORIZED);
        }

        User user = findByUsername(username);
        return user;
    }

    @Override
    public User updatePassword(UpdatePasswordParam param) {
        if (Strings.isNullOrEmpty(param.getUsername())
                || Strings.isNullOrEmpty(param.getOldPassword())
                || Strings.isNullOrEmpty(param.getNewPassword())) {
            throw new WhitzardException(ResultCode.USER_NAME_OR_PASSWORD_ERROR);
        }
        User user = findByUsername(param.getUsername());
        if (null == user) {
            throw new WhitzardException(ResultCode.USER_NOT_FOUND);
        }
        if (!encoder.encode(param.getOldPassword()).equals(user.getPassword())) {
            throw new WhitzardException(ResultCode.USER_NAME_OR_PASSWORD_ERROR);
        }
        user.setPassword(encoder.encode(param.getNewPassword()));
        user = userDao.save(user);
        return user;
    }


    @Override
    public UserDTO loadUserByUsername(String username) {
        User user = findByUsername(username);
        if (null == user) {
            throw new WhitzardException(ResultCode.USER_NOT_FOUND);
        }
        UserDTO userDto = new UserDTO();
        List<Role> roleList = roleService.findRoleList(user.getId());
        if (null != roleList && !roleList.isEmpty()) {
            List<String> roleStrList = roleList.stream().map(item -> item.getId() + "_" + item.getEnName()).collect(Collectors.toList());
            userDto.setRoles(roleStrList);
        }
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    @Override
    public void bindRole(Integer userId, List<Integer> roleIds) {
        roleService.bindRole(userId, roleIds);
    }

}
