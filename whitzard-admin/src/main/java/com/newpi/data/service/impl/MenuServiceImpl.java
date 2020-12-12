package com.newpi.data.service.impl;

import com.newpi.data.dao.MenuDao;
import com.newpi.data.entity.Menu;
import com.newpi.data.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/11 11:31 AM
 * @desc:
 */
@Service
@Slf4j
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;


    @Override
    public List<Menu> findAllByIds(List<Integer> ids) {
        return menuDao.findByIds(ids);
    }
}
