package com.cc.dao.impl;

import org.springframework.stereotype.Repository;

import com.cc.dao.IDictionaryDao;
import com.cc.po.Dictionary;
@Repository("Diction")
public class DictionaryDaoImpl extends BaseDaoImpl<Dictionary, Integer> implements IDictionaryDao {

}
