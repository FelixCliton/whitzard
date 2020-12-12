package com.newpi.data.service;

import com.newpi.data.entity.Menu;

import java.util.List;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/2 5:02 PM
 * @desc:
 */
public interface MenuService {

    /**
     * find all by ids
     *
     * @param ids
     *
     * @return
     */
    List<Menu> findAllByIds(List<Integer> ids);


}
