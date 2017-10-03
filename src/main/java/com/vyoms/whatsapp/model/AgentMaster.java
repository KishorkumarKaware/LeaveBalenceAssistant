package com.vyoms.whatsapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the agent_master database table.
 * 
 */
@Entity
@Table(name="agent_master")
@NamedQuery(name="AgentMaster.findAll", query="SELECT a FROM AgentMaster a")
public class AgentMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	private String active;

	@Column(name="email_address")
	private String emailAddress;

	@Id
	private Integer id;

	@Column(name="il_location")
	private String ilLocation;

	@Column(name="im_name")
	private String imName;

	@Column(name="im_rmmo")
	private String imRmmo;

	@Column(name="intermediary_cd")
	private String intermediaryCd;

	@Column(name="ipartner_user_id")
	private String ipartnerUserId;

	@Column(name="i_password")
	private String iPassword;

	private String login;

	@Column(name="pri_sub_ver_name")
	private String priSubVerName;

	@Column(name="pri_ver_name")
	private String priVerName;

	@Column(name="rmmo_name")
	private String rmmoName;

	@Column(name="ruw_emp_id")
	private String ruwEmpId;

	@Column(name="ruw_name")
	private String ruwName;

	@Column(name="ruw_supervisor")
	private String ruwSupervisor;

	@Column(name="ruw_supervisor_emp_code")
	private String ruwSupervisorEmpCode;

	private String status;

	@Column(name="telephone_number")
	private String telephoneNumber;

	@Column(name="uw_hub")
	private String uwHub;

	public AgentMaster() {
	}

	public String getActive() {
		return this.active;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public Integer getId() {
		return this.id;
	}

	public String getIlLocation() {
		return this.ilLocation;
	}

	public String getImName() {
		return this.imName;
	}

	public String getImRmmo() {
		return this.imRmmo;
	}

	public String getIntermediaryCd() {
		return this.intermediaryCd;
	}

	public String getIpartnerUserId() {
		return this.ipartnerUserId;
	}

	public String getIPassword() {
		return this.iPassword;
	}

	public String getLogin() {
		return this.login;
	}

	public String getPriSubVerName() {
		return this.priSubVerName;
	}

	public String getPriVerName() {
		return this.priVerName;
	}

	public String getRmmoName() {
		return this.rmmoName;
	}

	public String getRuwEmpId() {
		return this.ruwEmpId;
	}

	public String getRuwName() {
		return this.ruwName;
	}

	public String getRuwSupervisor() {
		return this.ruwSupervisor;
	}

	public String getRuwSupervisorEmpCode() {
		return this.ruwSupervisorEmpCode;
	}

	public String getStatus() {
		return this.status;
	}

	public String getTelephoneNumber() {
		return this.telephoneNumber;
	}

	public String getUwHub() {
		return this.uwHub;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIlLocation(String ilLocation) {
		this.ilLocation = ilLocation;
	}

	public void setImName(String imName) {
		this.imName = imName;
	}

	public void setImRmmo(String imRmmo) {
		this.imRmmo = imRmmo;
	}

	public void setIntermediaryCd(String intermediaryCd) {
		this.intermediaryCd = intermediaryCd;
	}

	public void setIpartnerUserId(String ipartnerUserId) {
		this.ipartnerUserId = ipartnerUserId;
	}

	public void setIPassword(String iPassword) {
		this.iPassword = iPassword;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPriSubVerName(String priSubVerName) {
		this.priSubVerName = priSubVerName;
	}

	public void setPriVerName(String priVerName) {
		this.priVerName = priVerName;
	}

	public void setRmmoName(String rmmoName) {
		this.rmmoName = rmmoName;
	}

	public void setRuwEmpId(String ruwEmpId) {
		this.ruwEmpId = ruwEmpId;
	}

	public void setRuwName(String ruwName) {
		this.ruwName = ruwName;
	}

	public void setRuwSupervisor(String ruwSupervisor) {
		this.ruwSupervisor = ruwSupervisor;
	}

	public void setRuwSupervisorEmpCode(String ruwSupervisorEmpCode) {
		this.ruwSupervisorEmpCode = ruwSupervisorEmpCode;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public void setUwHub(String uwHub) {
		this.uwHub = uwHub;
	}

}