package com.vyoms.whatsapp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the plan database table.
 * 
 */
@Entity
@NamedQuery(name="Plan.findAll", query="SELECT p FROM Plan p")
public class Plan implements Serializable {
	private static final long serialVersionUID = 1L;

	private String col1;

	private String col2;

	private String col3;

	private String col4;

	@Id
	private Integer id;

	private String plan;

	public Plan() {
	}

	public String getCol1() {
		return this.col1;
	}

	public String getCol2() {
		return this.col2;
	}

	public String getCol3() {
		return this.col3;
	}

	public String getCol4() {
		return this.col4;
	}

	public Integer getId() {
		return this.id;
	}

	public String getPlan() {
		return this.plan;
	}

	public void setCol1(String col1) {
		this.col1 = col1;
	}

	public void setCol2(String col2) {
		this.col2 = col2;
	}

	public void setCol3(String col3) {
		this.col3 = col3;
	}

	public void setCol4(String col4) {
		this.col4 = col4;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

}