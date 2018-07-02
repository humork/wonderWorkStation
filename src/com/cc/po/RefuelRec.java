package com.cc.po;

import java.io.Serializable;
import java.sql.Timestamp;

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

//加油记录实体
@Entity
@Table(name="REFUELING_RECORD")
public class RefuelRec implements Serializable{
	private Integer id;
	private Car car;//多对一
	private CurrentUnit oilSta;//加油站 。多对一
	private Timestamp oilDate;
	private Dictionary oilLabel;//油料标号。多对一
	private Double unitPrice=0.0;
	private Double amount;
	private Integer this_mil=0;//本次里程
	private Integer last_mil=0;//上次里程
	private Double this_volu=0.0;//本次加油量
	private Double last_volu=0.0;//上次加油量
	private Driver operator;//多对一
	private String remarks;
	
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
	@SequenceGenerator(name="t1",sequenceName="seq_refueling_record_id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CAR_ID")
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="oil_station_id")
	public CurrentUnit getOilSta() {
		return oilSta;
	}
	public void setOilSta(CurrentUnit oilSta) {
		this.oilSta = oilSta;
	}
	@Column(name="oil_date")
	public Timestamp getOilDate() {
		return oilDate;
	}
	public void setOilDate(Timestamp oilDate) {
		this.oilDate = oilDate;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="oil_label")
	public Dictionary getOilLabel() {
		return oilLabel;
	}
	public void setOilLabel(Dictionary oilLabel) {
		this.oilLabel = oilLabel;
	}
	@Column(name="Unit_Price")
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	@Column(name="total_amount")
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Integer getThis_mil() {
		return this_mil;
	}
	public void setThis_mil(Integer this_mil) {
		this.this_mil = this_mil;
	}
	public Integer getLast_mil() {
		return last_mil;
	}
	public void setLast_mil(Integer last_mil) {
		this.last_mil = last_mil;
	}
	@Column(name="this_gas_volume")
	public Double getThis_volu() {
		return this_volu;
	}
	public void setThis_volu(Double this_volu) {
		this.this_volu = this_volu;
	}
	@Column(name="last_gas_volume")
	public Double getLast_volu() {
		return last_volu;
	}
	public void setLast_volu(Double last_volu) {
		this.last_volu = last_volu;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="operator")
	public Driver getOperator() {
		return operator;
	}
	public void setOperator(Driver operator) {
		this.operator = operator;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
