package com.cc.dao.impl;

import org.springframework.stereotype.Repository;

import com.cc.dao.IFeesManagerDao;
import com.cc.po.FeesManager;
/**
 * @Author ChenXiang
 * @Date 2018/6/30,11:00
 */
@Repository("Fees")
public class FeesManagerDaoImpl extends BaseDaoImpl<FeesManager,Integer> implements IFeesManagerDao {

}
