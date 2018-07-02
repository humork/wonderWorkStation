package com.cc.po;

import java.io.Serializable;

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

@Entity
@Table(name="CAR_PIC")
public class CarPic implements Serializable {
	private Integer id;
	private Car car;//多对一
	private String imgPath;
	private String des;
	
	
	@Id
	//主键生成策略
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="t1")
	//序列的名称
	@SequenceGenerator(name="t1",sequenceName="SEQ_CAR_PIC_ID")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cid")
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	@Column(name="img_path")
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	
}
