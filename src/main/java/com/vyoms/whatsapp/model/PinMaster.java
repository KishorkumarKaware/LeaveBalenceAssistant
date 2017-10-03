package com.vyoms.whatsapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the pin_master database table.
 * 
 */
@Entity
@Table(name="pin_master")
@NamedQuery(name="PinMaster.findAll", query="SELECT p FROM PinMaster p")
public class PinMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	private String correctedpincode;

	@Column(name="dat_end_dt")
	private String datEndDt;

	@Column(name="dat_start_dt")
	private String datStartDt;

	@Id
	private Integer id;

	@Column(name="num_citydistrict_cd")
	private String numCitydistrictCd;

	@Column(name="num_country_cd")
	private String numCountryCd;

	@Column(name="num_state_cd")
	private String numStateCd;

	@Column(name="num_visibility")
	private String numVisibility;

	@Column(name="txt_citydistrict")
	private String txtCitydistrict;

	@Column(name="txt_pincode_locality")
	private String txtPincodeLocality;

	@Column(name="txt_state")
	private String txtState;

	public PinMaster() {
	}

	public String getCorrectedpincode() {
		return this.correctedpincode;
	}

	public String getDatEndDt() {
		return this.datEndDt;
	}

	public String getDatStartDt() {
		return this.datStartDt;
	}

	public Integer getId() {
		return this.id;
	}

	public String getNumCitydistrictCd() {
		return this.numCitydistrictCd;
	}

	public String getNumCountryCd() {
		return this.numCountryCd;
	}

	public String getNumStateCd() {
		return this.numStateCd;
	}

	public String getNumVisibility() {
		return this.numVisibility;
	}

	public String getTxtCitydistrict() {
		return this.txtCitydistrict;
	}

	public String getTxtPincodeLocality() {
		return this.txtPincodeLocality;
	}

	public String getTxtState() {
		return this.txtState;
	}

	public void setCorrectedpincode(String correctedpincode) {
		this.correctedpincode = correctedpincode;
	}

	public void setDatEndDt(String datEndDt) {
		this.datEndDt = datEndDt;
	}

	public void setDatStartDt(String datStartDt) {
		this.datStartDt = datStartDt;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNumCitydistrictCd(String numCitydistrictCd) {
		this.numCitydistrictCd = numCitydistrictCd;
	}

	public void setNumCountryCd(String numCountryCd) {
		this.numCountryCd = numCountryCd;
	}

	public void setNumStateCd(String numStateCd) {
		this.numStateCd = numStateCd;
	}

	public void setNumVisibility(String numVisibility) {
		this.numVisibility = numVisibility;
	}

	public void setTxtCitydistrict(String txtCitydistrict) {
		this.txtCitydistrict = txtCitydistrict;
	}

	public void setTxtPincodeLocality(String txtPincodeLocality) {
		this.txtPincodeLocality = txtPincodeLocality;
	}

	public void setTxtState(String txtState) {
		this.txtState = txtState;
	}

}