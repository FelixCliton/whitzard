package com.newpi.data.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Oauth2获取Token返回信息封装
 *
 * @author macro
 * @date 2020/7/17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class Oauth2TokenDTO {
    /**
     * 访问令牌
     */
    private String token;
    /**
     * 刷新令牌
     */
    private String refreshToken;
    /**
     * 访问令牌头前缀
     */
    private String tokenHead;
    /**
     * 有效时间（秒）
     */
    private Integer expiresIn;
}
