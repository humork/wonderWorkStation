package com.cc.dao.impl;

import org.springframework.stereotype.Repository;

import com.cc.dao.ICarDao;
import com.cc.po.Car;
@Repository("car")
public class CarDaoImpl extends BaseDaoImpl<Car, Integer> implements ICarDao {

}
