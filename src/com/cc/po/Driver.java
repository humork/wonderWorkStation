package com.cc.po;

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

//驾驶员资料表
@Entity
@Table(name="DRIVER")
public class Driver implements Serializable {
	private Integer id;
	private String name;
	private Dictionary dept;//多对一
	private String gender;
	private Date birthday;
	private String card;
	private String phone;
	private Date entrytime;
	private String address;
	private String driverNo;
	private Dictionary driverType;//多对一
	private String remarks;
	private Integer isdisable=1;
	
	//分页专用
	private int page;
	private int rows;
	
	@Id
	//主键生成策略
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="t1")
	//序列的名称
	@SequenceGenerator(name="t1",sequenceName="SEQ_DRIVING_ID")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="DEPT_ID")
	public Dictionary getDept() {
		return dept;
	}
	public void setDept(Dictionary dept) {
		this.dept = dept;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getEntrytime() {
		return entrytime;
	}
	public void setEntrytime(Date entrytime) {
		this.entrytime = entrytime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name="Driver_no")
	public String getDriverNo() {
		return driverNo;
	}
	public void setDriverNo(String driverNo) {
		this.driverNo = driverNo;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="DRIVER_TYPE")
	public Dictionary getDriverType() {
		return driverType;
	}
	public void setDriverType(Dictionary driverType) {
		this.driverType = driverType;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getIsdisable() {
		return isdisable;
	}
	public void setIsdisable(Integer isdisable) {
		this.isdisable = isdisable;
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
	
	
}
