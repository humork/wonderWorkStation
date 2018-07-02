package com.cc.dao.impl;

import org.springframework.stereotype.Repository;

import com.cc.dao.IViewExpirationDao;
import com.cc.po.ViewExpiration;
@Repository("ViewEXpir")
public class ViewExpirationDaoImpl extends BaseDaoImpl<ViewExpiration, Integer> implements IViewExpirationDao{
	
}
