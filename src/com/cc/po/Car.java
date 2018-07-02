	package com.cc.po;

import java.io.File;
import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
//车辆表
@Entity
@Table(name="CAR")
public class Car implements Serializable {
	private Integer id;
	private String carNo;
	private Dictionary carBrand;//多对一
	private Dictionary carModel;//多对一
	private Integer carColor;//采用本地颜色数据
	private Double carLoad;
	private Integer carSeats;
	private Double oilWear;
	private Integer initMil;
	private Integer maintainMil;
	private Integer maintainPeriod;
	private String engineNum;
	private String frameNum;
	private CurrentUnit sup;//多对一
	private Double purchasePrice;
	private Date purchaseDate;
	private Dictionary dept;//多对一
	private Integer carState;//采用本地车辆状态数据
	private String remarks;
	private Integer isdisable=1;
	//private Set<CarPic> carPics;//一对多
	
	//分页专用
	private int page;
	private int rows;
	
	//删除单一图片专用
	private int carPicId;
	private String carPicPath;
	
	//查询车辆型号专用
	private int carBrandId;
	
	//上传车辆图片专用
	private File[] carPic;
	private String[] carPicFileName;
	private String[] carPicContentType;
	private String[] desc;
	
	
	
	@Transient
	public String getCarPicPath() {
		return carPicPath;
	}
	public void setCarPicPath(String carPicPath) {
		this.carPicPath = carPicPath;
	}
	@Transient
	public String[] getDesc() {
		return desc;
	}
	public void setDesc(String[] desc) {
		this.desc = desc;
	}
	@Transient
	public File[] getCarPic() {
		return carPic;
	}
	public void setCarPic(File[] carPic) {
		this.carPic = carPic;
	}
	@Transient
	public String[] getCarPicFileName() {
		return carPicFileName;
	}
	public void setCarPicFileName(String[] carPicFileName) {
		this.carPicFileName = carPicFileName;
	}
	@Transient
	public String[] getCarPicContentType() {
		return carPicContentType;
	}
	public void setCarPicContentType(String[] carPicContentType) {
		this.carPicContentType = carPicContentType;
	}
	@Transient
	public int getCarBrandId() {
		return carBrandId;
	}
	public void setCarBrandId(int carBrandId) {
		this.carBrandId = carBrandId;
	}
	@Transient
	public int getCarPicId() {
		return carPicId;
	}
	public void setCarPicId(int carPicId) {
		this.carPicId = carPicId;
	}
	@Transient
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	@Transient
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	@Id
	//主键生成策略
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="t1")
	//序列的名称
	@SequenceGenerator(name="t1",sequenceName="SEQ_CAR_ID")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="car_no")
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="car_brand")
	public Dictionary getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(Dictionary carBrand) {
		this.carBrand = carBrand;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="car_model")
	public Dictionary getCarModel() {
		return carModel;
	}
	public void setCarModel(Dictionary carModel) {
		this.carModel = carModel;
	}
	@Column(name="car_color")
	public Integer getCarColor() {
		return carColor;
	}
	public void setCarColor(Integer carColor) {
		this.carColor = carColor;
	}
	@Column(name="car_load")
	public Double getCarLoad() {
		return carLoad;
	}
	public void setCarLoad(Double carLoad) {
		this.carLoad = carLoad;
	}
	@Column(name="car_seats")
	public Integer getCarSeats() {
		return carSeats;
	}
	public void setCarSeats(Integer carSeats) {
		this.carSeats = carSeats;
	}
	@Column(name="oil_wear")
	public Double getOilWear() {
		return oilWear;
	}
	public void setOilWear(Double oilWear) {
		this.oilWear = oilWear;
	}
	@Column(name="init_mil")
	public Integer getInitMil() {
		return initMil;
	}
	public void setInitMil(Integer initMil) {
		this.initMil = initMil;
	}
	@Column(name="maintain_mil")
	public Integer getMaintainMil() {
		return maintainMil;
	}
	public void setMaintainMil(Integer maintainMil) {
		this.maintainMil = maintainMil;
	}
	@Column(name="maintain_period")
	public Integer getMaintainPeriod() {
		return maintainPeriod;
	}
	public void setMaintainPeriod(Integer maintainPeriod) {
		this.maintainPeriod = maintainPeriod;
	}
	@Column(name="engine_num")
	public String getEngineNum() {
		return engineNum;
	}
	public void setEngineNum(String engineNum) {
		this.engineNum = engineNum;
	}
	@Column(name="frame_num")
	public String getFrameNum() {
		return frameNum;
	}
	public void setFrameNum(String frameNum) {
		this.frameNum = frameNum;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sup_id")
	public CurrentUnit getSup() {
		return sup;
	}
	public void setSup(CurrentUnit sup) {
		this.sup = sup;
	}
	@Column(name="purchase_price")
	public Double getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	@Column(name="purchase_date")
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="dept_id")
	public Dictionary getDept() {
		return dept;
	}
	public void setDept(Dictionary dept) {
		this.dept = dept;
	}
	@Column(name="car_state")
	public Integer getCarState() {
		return carState;
	}
	public void setCarState(Integer carState) {
		this.carState = carState;
	}
	@Column(name="remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Column(name="isdisable")
	public Integer getIsdisable() {
		return isdisable;
	}
	public void setIsdisable(Integer isdisable) {
		this.isdisable = isdisable;
	}

	
	
	
	
	
	
}
