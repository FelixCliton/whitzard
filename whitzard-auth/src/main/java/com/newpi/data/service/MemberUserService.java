package com.newpi.data.service;

import com.newpi.data.domain.UserDTO;
import com.newpi.data.model.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/10 11:25 AM
 * @desc:
 */
@FeignClient("whitzard-manager")
public interface MemberUserService {

    /**
     * load user by username
     * @param userName
     * @return
     */
    @GetMapping("user/loadByUsername")
    ResultEntity<UserDTO> loadUserByUsername(@RequestParam("username") String userName);


}
