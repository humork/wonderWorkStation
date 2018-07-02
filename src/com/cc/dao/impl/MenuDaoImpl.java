package com.cc.dao.impl;

import org.springframework.stereotype.Repository;

import com.cc.dao.IMenuDao;
import com.cc.po.Menu;


@Repository("MenuDao")
public class MenuDaoImpl extends BaseDaoImpl<Menu,String> implements IMenuDao {

}
