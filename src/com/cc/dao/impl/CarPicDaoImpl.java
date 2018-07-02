package com.cc.dao.impl;

import org.springframework.stereotype.Repository;

import com.cc.dao.ICarPicDao;
import com.cc.po.CarPic;
@Repository("CarPic")
public class CarPicDaoImpl extends BaseDaoImpl<CarPic, Integer> implements ICarPicDao {

}
