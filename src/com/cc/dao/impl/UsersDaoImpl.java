package com.cc.dao.impl;

import org.springframework.stereotype.Repository;

import com.cc.po.Users;
import com.cc.dao.IUsersDao;
@Repository("usedao")
public class UsersDaoImpl extends BaseDaoImpl<Users, Integer> implements IUsersDao {

}
