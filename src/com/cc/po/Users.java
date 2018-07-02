package com.cc.po;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Q_USERS")
public class Users implements Serializable{
	private Integer id;
	private String lname;
	private String lpass;
	private String rname;
	private Integer age;
	private String gender;
	private String address;
	private String phone;
	private Dictionary dept;//字典表部门
	private Dictionary post; //字典表职位
	private Date birthday;
	private String card;//身份证
	private Date entrytime;//入职时间
	private Integer isdisable=1;//是否停用。1代表启用，其余代表停用。
	
	
	private Set<Role> roles=new HashSet<Role>();
	
	//分页专用
	private int page;//当前页数
	private int rows;//每页显示的资料数
	
	//多笔删除专用
	private String ids;	
	
	//指派角色专用，格式：1,2,3
	private String rids;
	
	//原始密码,修改密码验证原始密码专用
	private String opass;	
	
	@Transient
	public String getOpass() {
		return opass;
	}
	public void setOpass(String opass) {
		this.opass = opass;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="dept_id")
	public Dictionary getDept() {
		return dept;
	}
	public void setDept(Dictionary dept) {
		this.dept = dept;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="post_id")
	public Dictionary getPost() {
		return post;
	}
	public void setPost(Dictionary post) {
		this.post = post;
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
	public Date getEntrytime() {
		return entrytime;
	}
	public void setEntrytime(Date entrytime) {
		this.entrytime = entrytime;
	}
	public Integer getIsdisable() {
		return isdisable;
	}
	public void setIsdisable(Integer isdisable) {
		this.isdisable = isdisable;
	}
	@Transient
	public String getRids() {
		return rids;
	}
	public void setRids(String rids) {
		this.rids = rids;
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
	@Transient
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	@Id
	//主键生成策略
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="t1")
	//序列的名称
	@SequenceGenerator(name="t1",sequenceName="SEQ_QUSERS_ID")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getLpass() {
		return lpass;
	}
	public void setLpass(String lpass) {
		this.lpass = lpass;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	//用户和角色多对多关系配置
	@ManyToMany
	@JoinTable( name="Q_USERS_ROLE",
				joinColumns ={@JoinColumn(name="USID")},
				inverseJoinColumns ={@JoinColumn(name="RID")})
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
	
	
	
}
