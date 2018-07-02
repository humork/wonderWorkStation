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
import javax.persistence.Transient;
@Entity
@Table(name="CURRENT_UNIT")
public class CurrentUnit implements Serializable{
	private Integer id;
	private String unitName;
	private Dictionary unitType;
	private String address;
	private String tel;
	private String contacts;
	private String remarks;
	private Integer isdisable=1;
	
	//分页专用
	private int page;
	private int rows;
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
	@SequenceGenerator(name="t1",sequenceName="SEQ_CURRENT_UNIT_ID")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="Unit_Name")
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="unit_type")
	public Dictionary getUnitType() {
		return unitType;
	}
	public void setUnitType(Dictionary unitType) {
		this.unitType = unitType;
	}
	@Column(name="Address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name="Tel")
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Column(name="contacts")
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
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
