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

//维修记录表
@Entity
@Table(name="REPAIR_RECORD")
public class RepairRec implements Serializable{
	private Integer id;
	private Car car;	//多对一
	private CurrentUnit repDepot;//修理厂.多对一
	private Timestamp sendTime;//送修时间
	private Timestamp estTime;//预计取车时间
	private String sendReason;//送修原因
	private String sendRemarks;//送修备注
	private Driver operator;//经办人	.多对一
	private Dictionary repType;//维修类别.多对一
	private Timestamp getTime;//取车时间
	private Double repCost;//花费金额
	private String repOption;//维修项目
	private String useStuff;//使用材料
	private String getRemarks;//取车备注
	
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
	@SequenceGenerator(name="t1",sequenceName="seq_repair_record_id")
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
	@JoinColumn(name="repair_depot_id")
	public CurrentUnit getRepDepot() {
		return repDepot;
	}
	public void setRepDepot(CurrentUnit repDepot) {
		this.repDepot = repDepot;
	}
	@Column(name="send_time")
	public Timestamp getSendTime() {
		return sendTime;
	}
	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}
	@Column(name="estimate_time")
	public Timestamp getEstTime() {
		return estTime;
	}
	public void setEstTime(Timestamp estTime) {
		this.estTime = estTime;
	}
	@Column(name="send_reason")
	public String getSendReason() {
		return sendReason;
	}
	public void setSendReason(String sendReason) {
		this.sendReason = sendReason;
	}
	@Column(name="send_remarks")
	public String getSendRemarks() {
		return sendRemarks;
	}
	public void setSendRemarks(String sendRemarks) {
		this.sendRemarks = sendRemarks;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="operator")
	public Driver getOperator() {
		return operator;
	}
	public void setOperator(Driver operator) {
		this.operator = operator;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="repair_type")
	public Dictionary getRepType() {
		return repType;
	}
	public void setRepType(Dictionary repType) {
		this.repType = repType;
	}
	@Column(name="get_time")
	public Timestamp getGetTime() {
		return getTime;
	}
	public void setGetTime(Timestamp getTime) {
		this.getTime = getTime;
	}
	@Column(name="repair_cost")
	public Double getRepCost() {
		return repCost;
	}
	public void setRepCost(Double repCost) {
		this.repCost = repCost;
	}
	@Column(name="repair_option")
	public String getRepOption() {
		return repOption;
	}
	public void setRepOption(String repOption) {
		this.repOption = repOption;
	}
	@Column(name="use_stuff")
	public String getUseStuff() {
		return useStuff;
	}
	public void setUseStuff(String useStuff) {
		this.useStuff = useStuff;
	}
	@Column(name="get_remarks")
	public String getGetRemarks() {
		return getRemarks;
	}
	public void setGetRemarks(String getRemarks) {
		this.getRemarks = getRemarks;
	}
	
}
