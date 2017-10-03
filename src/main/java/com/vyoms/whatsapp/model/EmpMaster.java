package com.vyoms.whatsapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the emp_master database table.
 * 
 */
@Entity
@Table(name = "emp_master")
@NamedQuery(name = "EmpMaster.findAll", query = "SELECT e FROM EmpMaster e")
public class EmpMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "business_group")
	private String businessGroup;

	@Column(name = "email_address")
	private String emailAddress;

	@Column(name = "employee_id")
	private Integer employeeId;

	@Column(name = "employee_name")
	private String employeeName;

	@Id
	private Integer id;

	private String telephone;

	private String vertical;

	public EmpMaster() {
	}

	public String getBusinessGroup() {
		return this.businessGroup;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public Integer getEmployeeId() {
		return this.employeeId;
	}

	public String getEmployeeName() {
		return this.employeeName;
	}

	public Integer getId() {
		return id;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public String getVertical() {
		return this.vertical;
	}

	public void setBusinessGroup(String businessGroup) {
		this.businessGroup = businessGroup;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setVertical(String vertical) {
		this.vertical = vertical;
	}

}