package com.newpi.data.constant;

/**
 * 权限相关常量定义
 *
 * @author macro
 * @date 2020/6/19
 */
public class AuthConstant {

    /**
     * JWT存储权限前缀
     */
//   public static final String AUTHORITY_PREFIX = "ROLE_";
   public static final String AUTHORITY_PREFIX = "";

    /**
     * JWT存储权限属性
     */
    public static final String AUTHORITY_CLAIM_NAME = "authorities";

    /**
     * 后台管理client_id
     */
    public static final  String ADMIN_CLIENT_ID = "admin-app";

    /**
     * 前台client_id
     */
    public static final String PORTAL_CLIENT_ID = "portal-app";

    /**
     * 后台管理接口路径匹配
     */
    public static final String ADMIN_URL_PATTERN = "/whitzard-admin/**";

    /**
     * Redis缓存权限规则key
     */
    public static final String RESOURCE_ROLES_MAP_KEY = "auth:resourceRolesMap";

    /**
     * 认证信息Http请求头
     */
    public static final String JWT_TOKEN_HEADER = "Authorization";

    /**
     * JWT令牌前缀
     */
    public static final String JWT_TOKEN_PREFIX = "Bearer ";

    /**
     * 用户信息Http请求头
     */
    public static final String USER_NAME_HEADER = "username";

    public static final String EMAIL_HEADER = "email";

}
