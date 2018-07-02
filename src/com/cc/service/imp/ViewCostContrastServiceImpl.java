package com.cc.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cc.dao.IViewCostContrastDao;
import com.cc.po.ReportColumn;
import com.cc.po.ViewCostContrast;
import com.cc.service.IViewCostContrastService;

@Service("/ViewCostContrastService")
public class ViewCostContrastServiceImpl implements IViewCostContrastService{
	
	@Autowired
	private IViewCostContrastDao ivc;
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	@Override
	public List<ReportColumn<Double>> findVCCList(ViewCostContrast vcc) {
		
		List<ReportColumn<Double>> list=new ArrayList<ReportColumn<Double>>();
		
		ReportColumn<Double> rep=new ReportColumn<Double>();
		
		rep.setName("车牌号码");
		
		//临时变量
		ViewCostContrast  view=null;
		
		Map<String,Object> params=new HashMap<String,Object>();
		String hql="from ViewCostContrast where 1=1 ";
		List<ViewCostContrast> llll=ivc.getAll(hql, params);

		if(llll!=null){
			//x轴数据
			String[] x=new String[llll.size()];
			//y轴数据
			Double[] y=new Double[llll.size()];
			
			for(int i=0;i<llll.size();i++){
				view=llll.get(i);
				x[i]=view.getCarNo();//车牌号
				y[i]=view.getCarCost();//钱数
			}
			rep.setX(x);
			rep.setY(y);
			
			list.add(rep);
		}	
		return list;
	}

}
