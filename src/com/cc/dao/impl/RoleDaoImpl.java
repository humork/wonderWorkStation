package com.cc.dao.impl;

import org.springframework.stereotype.Repository;

import com.cc.dao.IRoleDao;
import com.cc.po.Role;
@Repository("RoleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role,Integer> implements IRoleDao {

}
