package com.newpi.data.service;

import com.newpi.data.model.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/10 4:54 PM
 * @desc:
 */
@FeignClient("whitzard-auth")
public interface AuthService {

    /**
     *
     * @param parameters
     * @return
     */
    @PostMapping(value = "/oauth/token")
    ResultEntity getAccessToken(@RequestParam Map<String, String> parameters);

}
