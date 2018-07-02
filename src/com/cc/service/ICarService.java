package com.cc.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cc.po.Car;
import com.cc.po.CarPic;
import com.cc.po.DataGrid;


public interface ICarService {
	
	/**
	 * 根据传入的数，查询对应的内容
	 * @param state
	 * @param isdisable
	 * @return
	 */
	public List<Car> findListCar(int state,int isdisable);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public Car findCarById(Integer id);
	
	/**
	 * 查询全部
	 * @param car
	 * @return
	 */
	public DataGrid<Car> findAll(Car car);
	
	/**
	 * 查询总数
	 * @param car
	 * @return
	 */
	public Long findCount(Car car);
	
	/**
	 * 新增
	 * @param car
	 * @return
	 */
	public boolean add(Car car);
	
	/**
	 * 修改
	 * @param car
	 * @return
	 */
	public boolean upd(Car car);
	
	/**
	 * 删除
	 * @param car
	 * @return
	 */
	public boolean del(Car car);
	
	/**
	 * 判断车辆号码是否存在
	 * @param car
	 * @return
	 */
	public int findCarNo(Car car);
	
	/**
	 * 删除对应的所有图片
	 * @param carId
	 * @return
	 */
	public boolean delCarPic(int carId);
	
	/***
	 * 删除对应ID的图片信息
	 * @param carPicId 子表图片信息ID
	 * @param carPicPath 图片路径
	 * @return 成功与否
	 */
	public boolean delCarPicById(int carPicId,String carPicPath);
	
	/***
	 * 修改对应的图片
	 * @param cp 图片实体
	 * @return 成功与否
	 */
	public boolean updCarPic(CarPic cp);
	
	/***
	 * 查询车辆图片信息
	 * @param id 车辆ID
	 * @return 车辆图片信息集合
	 */
	public List<CarPic> findCarPicList(int id);
	
	/***
	 * 上传图片信息
	 * @param car 车辆信息实体其中有图片信息
	 * @return 成功与否
	 */
	public boolean addCarPic(Car car,MultipartFile[] file);
	
}
