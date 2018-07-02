package com.cc.po;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Q_ROLE")
public class Role implements Serializable {
	
	private Integer id;
	private String name;	
	private Set<Menu> menus=new HashSet<Menu>();
	
	
	//给用户指派角色时使用
	private boolean ck=false;
	
	//分页专用
	private int page;
	private int rows;
	
	//角色授权专用
	private String mids;//菜单ID，格式x,x,x
	
	@Transient
	public String getMids() {
		return mids;
	}
	public void setMids(String mids) {
		this.mids = mids;
	}
	@Transient
	public boolean isCk() {
		return ck;
	}
	public void setCk(boolean ck) {
		this.ck = ck;
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
	@SequenceGenerator(name="t1",sequenceName="SEQ_QROLE_ID")
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
	//角色和菜单的多对多
	@ManyToMany
	@JoinTable( name="Q_ROLE_MENU",
				joinColumns ={@JoinColumn(name="RID")},
				inverseJoinColumns ={@JoinColumn(name="MID")})
	public Set<Menu> getMenus() {
		return menus;
	}
	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}

	
	
	
	
	
}
