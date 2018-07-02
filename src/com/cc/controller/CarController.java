package com.cc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cc.po.Car;
import com.cc.po.TempJSON;
import com.cc.service.ICarService;
import com.cc.service.ICurrentUnitService;
import com.cc.service.IDictionaryService;
import com.cc.util.JsonUtil;
import com.cc.util.SysStr;

@Controller
@RequestMapping("/CarController")
public class CarController {
	
	@Autowired
	private ICarService icr;
	@Autowired
	private IDictionaryService idc;
	@Autowired
	private ICurrentUnitService icus;
	
	//查询所有
	@RequestMapping(value="/findAll",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findAll(Car car){
		return JsonUtil.writeJson(icr.findAll(car),new String[]{"dic","car"});
	}
	
	//填充车辆品牌下拉列表
	@RequestMapping(value="/findCarBrandList",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findCarBrandList(){
		return JsonUtil.writeJson(idc.findDicListByName("车辆品牌"),new String[]{"dic"});
	}
	
	//填充车辆型号下拉列表
	@RequestMapping(value="/findCarModelList",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findCarModelList(Car car){
		return JsonUtil.writeJson(idc.findDicListByPid(car.getCarBrand().getId()), new String[]{"dic"});
	}
	
	//填充部门下拉列表
	@RequestMapping(value="/findDept",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findDept(){
		return JsonUtil.writeJson(idc.findDicListByName("部门"),new String[]{"dic"});
	}
	
	//填充供应商下拉列表
	@RequestMapping(value="/findSupList",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findSupList(){
		return JsonUtil.writeJson(icus.findCUListByType("4S商家"),new String[]{"unitType"});
	}
	
	//新增
	@RequestMapping(value="/add",produces="text/html;charset=utf-8")
	@ResponseBody
	public String add(Car car){
		TempJSON ts=new TempJSON();
		ts.setMsg(SysStr.addfail);
		
		if(icr.add(car)){
			ts.setMsg(SysStr.addsuccess);
			ts.setSuccess(true);
		}
		return JsonUtil.writeJson(ts);
	}
	
	//根据id查询
	@RequestMapping(value="/findid",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findid(Car car){
		return JsonUtil.writeJson(icr.findCarById(car.getId()));
	}
	
	//判断汽车号码是否存在
	@RequestMapping(value="/findCarNo",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findCarNo(Car car){
		return JsonUtil.writeJson(icr.findCarNo(car));
	}
	
	//修改
	@RequestMapping(value="/upd",produces="text/html;charset=utf-8")
	@ResponseBody
	public String upd(Car car){
		TempJSON ts=new TempJSON();
		ts.setMsg(SysStr.editfail);
		if(icr.upd(car)){
			ts.setMsg(SysStr.editsuccess);
			ts.setSuccess(true);
		}
		return JsonUtil.writeJson(ts);
	}
	
	//删除
	@RequestMapping(value="/del",produces="text/html;charset=utf-8")
	@ResponseBody
	public String del(Car car){
		TempJSON ts=new TempJSON();
		ts.setMsg(SysStr.delfail);
		if(icr.del(car)){
			ts.setMsg(SysStr.delsuccess);
			ts.setSuccess(true);
		}
		return JsonUtil.writeJson(ts);
	}
	
	//查询启用车辆状态
	@RequestMapping(value="/findCarList",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findCarList(){
		return JsonUtil.writeJson(icr.findListCar(-1, 1), new String[]{"dic","carBrand","carModel","dept","sup"});		
	}
	
	//查询车辆图片信息
	@RequestMapping(value="/findCarPic",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findCarPic(Car car){
		return JsonUtil.writeJson(icr.findCarPicList(car.getId()),"car");
	}
	
	//新增车辆图片信息
	@RequestMapping(value="/addCarPic",produces="text/html;charset=utf-8")
	@ResponseBody
	public String addCarPic(Car car,@RequestParam("file")MultipartFile[] file){
		TempJSON ts =new TempJSON();
		ts.setMsg(SysStr.uploadfail);		
		if(icr.addCarPic(car,file)){
			ts.setMsg(SysStr.uploadsuccess);
			ts.setSuccess(true);
		}
		return JsonUtil.writeJson(ts);
	}
		
	//删除对应图片的信息
	@RequestMapping(value="/delCarPicById",produces="text/html;charset=utf-8")
	@ResponseBody
	public String delCarPicById(Car car){
			TempJSON ts =new TempJSON();
			ts.setMsg(SysStr.delfail);
			
			if(icr.delCarPicById(car.getCarPicId(),car.getCarPicPath())){
				ts.setMsg(SysStr.delsuccess);
				ts.setSuccess(true);
			}
		return JsonUtil.writeJson(ts);
	}
}
