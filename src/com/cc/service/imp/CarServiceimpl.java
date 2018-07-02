package com.cc.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cc.dao.ICarDao;
import com.cc.dao.ICarPicDao;
import com.cc.po.Car;
import com.cc.po.CarPic;
import com.cc.po.DataGrid;
import com.cc.service.ICarService;
import com.cc.util.FileUtil;
import com.cc.util.Page;

@Service("CarService")
public class CarServiceimpl implements ICarService{
	
	@Autowired
	private ICarDao icd;
	@Autowired
	private FileUtil f;
	@Autowired
	private ICarPicDao carpic;
	//查询所有
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<Car> findListCar(int state, int isdisable) {
			List<Car> list=new ArrayList<Car>();
			Map<String,Object> params=new HashMap<String,Object>();
			String hql="from Car c where 1=1 ";
			//车辆状态，是否出车.是否维修.1可用 其余不可用.
			if(state!=-1){
				hql+="and c.carState=:carState ";
				params.put("carState", state);
			}
			if(isdisable!=-1){
				hql+="and c.isdisable=:isdisable ";
				params.put("isdisable",isdisable);
			}
			return icd.getAll(hql, params);
	}
	
	//根据id查询
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Car findCarById(Integer id) {
		return icd.get(id);
	}
	
	//查询所有
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public DataGrid<Car> findAll(Car car) {
		DataGrid<Car> data=new DataGrid<Car>();
		List<Car> list=new ArrayList<Car>();
		Map<String,Object> params=new HashMap<String,Object>();
		String hql="from Car c where 1=1 ";
		if(car.getCarNo()!=null){
			hql+="and c.carNo =:carno ";
			params.put("carno",car.getCarNo());
		}
		Page page=new Page(car.getPage(),car.getRows());
		list=icd.getAllByPage(hql, params, page.getBegin(), page.getPageSize());
		if(list!=null && list.size()>0){
			data.setRows(list);
			Long l=findCount(car);
			data.setTotal(l);
		}
		return data;
	}
	
	//查询总数
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Long findCount(Car car) {
		String hql="select count(c) from Car c where 1=1 ";
		Map<String,Object> params=new HashMap<String,Object>();
		if(car.getCarNo()!=null){
			hql+="and c.carNo =:carno ";
			params.put("carno",car.getCarNo());
		}
		return icd.count(hql, params);
	}
	
	//新增
	@Override
	@Transactional
	public boolean add(Car car){
		return icd.add(car)>0?true:false;
	}
	
	//修改
	@Override
	@Transactional
	public boolean upd(Car car) {
		boolean res=false;
		//条件容器
		Map<String,Object> params =new HashMap<String,Object>();
		String hql="update Car c set c.carBrand.id=:carBrand," +
				"c.carModel.id=:carModel," +
				"c.carColor=:carColor," +
				"c.carLoad=:carLoad," +
				"c.carSeats=:carSeats," +
				"c.oilWear=:oilWear," +
				"c.initMil=:initMil," +
				"c.maintainMil=:maintainMil," +
				"c.maintainPeriod=:maintainPeriod," +
				"c.engineNum=:engineNum," +
				"c.frameNum=:frameNum," +
				"c.sup.id=:sup," +
				"c.purchasePrice=:purchasePrice," +
				"c.purchaseDate=:purchaseDate," +
				"c.dept.id=:dept," +
				"c.carState=:carState," +
				"c.remarks=:remarks," +
				"c.isdisable=:isdisable " +
				"where c.id=:id";
		params.put("carBrand", car.getCarBrand().getId());
		params.put("carModel", car.getCarModel().getId());
		params.put("carColor", car.getCarColor());
		params.put("carLoad", car.getCarLoad());
		params.put("carSeats", car.getCarSeats());
		params.put("oilWear", car.getOilWear());
		params.put("initMil", car.getInitMil());
		params.put("maintainMil", car.getMaintainMil());
		params.put("maintainPeriod",car.getMaintainPeriod());
		params.put("engineNum",car.getEngineNum());
		params.put("frameNum", car.getFrameNum());
		params.put("sup", car.getSup().getId());
		params.put("purchasePrice", car.getPurchasePrice());
		params.put("purchaseDate", car.getPurchaseDate());
		params.put("dept", car.getDept().getId());
		params.put("carState", car.getCarState());
		params.put("remarks", car.getRemarks());
		params.put("isdisable", car.getIsdisable());
		params.put("id", car.getId());
		
		if(icd.execute(hql, params)>0){
			res=true;
		}
		return res;
	}
	
	//删除
	@Override
	@Transactional
	public boolean del(Car car) {
		return icd.del(car)>0?true:false;
	}
	
	//判断车辆号码是否已存在
	@Override
	@Transactional
	public int findCarNo(Car car){
		String hql="select count(c) from Car c where c.carNo=:carno ";
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("carno",car.getCarNo());
		Long l=icd.count(hql, params);
		return l.intValue();
	}
	
	//删除对应的所有图片信息
	@Override
	@Transactional
	public boolean delCarPic(int carId) {
		boolean res=false;
		
		//获得实体
		Car car =icd.get(carId);
		if(car !=null){
			try {
				//删除所有图片,如果有的话
				List<CarPic> cplist=findCarPicList(carId);
				if(cplist !=null && cplist.size()>0){
					for(CarPic cp:cplist){
						/*FileUtil.delimg(cp.getImgPath());*/
					}
				}
				if(delCarPic(carId)){
					//删除主表
					icd.del(car);
				}				
				res=true;
			} catch (Exception e) {
				res=false;
			}
		}
		
		return res;
	}
	
	//删除对应id的图片信息
	@Override
	@Transactional
	public boolean delCarPicById(int carPicId, String carPicPath) {
		boolean res=false;
		Map<String,Object> params =new HashMap<String,Object>();
		
		try {
			f.delimg(carPicPath);
			String hql="delete CarPic cp where cp.id=:id";
			params.put("id", carPicId);
			if(icd.execute(hql, params)>0){
				res=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			res=false;
		}
		
		return res;
	}

	//修改对应的图片
	@Override
	@Transactional
	public boolean updCarPic(CarPic cp) {
		boolean res= false;
		
		if(carpic.upd(cp)>0){
			res=true;
		}
		
		return res;
	}
	
	//查询车辆图片信息
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<CarPic> findCarPicList(int id) {
		Map<String,Object> params =new HashMap<String,Object>();
		
		String hql="from CarPic cp where cp.car.id=:id ";
		params.put("id", id);
		
		return carpic.getAll(hql, params);
	}
	
	//上传
	@Override
	@Transactional
	public boolean addCarPic(Car car,MultipartFile[] file) {
		boolean res=false;
		CarPic cp=null;
		String newName="";
		boolean flag=false;
		int fCount=0;
		try {
			//判断图片信息
			if(file !=null&& file.length>0){
				//为了提高效率，直接给实例对象指定主键
				Car c =new Car();
				c.setId(car.getId());
				System.out.println("1");
				//循环图片对象
				for(int i=0;i<file.length;i++){
					cp=new CarPic();
					System.out.println("2");
					newName=FileUtil.createFileName(file[i].getOriginalFilename());
					
					cp.setImgPath(newName);					
					if(car.getDesc()!=null && car.getDesc().length>0){
						System.out.println("3");
						cp.setDes(car.getDesc()[i]);
					}
					cp.setCar(c);
					flag=f.uploadFile(file[i], newName);
					if(flag){
						System.out.println("4");
						carpic.add(cp);
						fCount++;
					}					
				}
			}
			System.out.println("5");
			//都上传成功了才成功
			if(fCount==file.length){
				System.out.println("6");
				res=true;
			}	
		} catch (Exception e) {
			res=false;
		}
		System.out.println(res);
		return res;
	}

}
