package com.vyoms.whatsapp.model;

public class Policy {

	public boolean agent = false;

	private String agent_Im_Id;

	public boolean attachment=false;

	public boolean cheque = false;

	public boolean chequeImage = false;
	public boolean convert = false;

	private String defect_description;

	public boolean intermediate = false;

	private String issue;

	private String message=null;

	public String name = null;

	public boolean offline = false;

	public boolean pdf = false;

	public String pin = null;

	public boolean pincode = false;

	public boolean plan = false;

	public String planName = null;

	private String policyQuoteNo;

	public String prem = null;

	private String product;

	private String remark;

	private String result=null;

	private String slipNo;

	public boolean start = false;

	public String stateCity = null;

	public String sumInsured = null;
	
	private String trans;
	
	public String emailId = null;
	
	public String UCVId = null;

	private String samaccount;

	private String aeReqNumber;

	private String aeRequestStatus;

	private String option;
	
	private String empid = null;
	
	private String leavetype= null;
	
	
	public String getDefect_description() {
		return defect_description;
	}


	public void setDefect_description(String defect_description) {
		this.defect_description = defect_description;
	}


	public String getIssue() {
		return issue;
	}


	public void setIssue(String issue) {
		this.issue = issue;
	}


	public String getTrans() {
		return trans;
	}


	public void setTrans(String trans) {
		this.trans = trans;
	}


	public String getSamaccount() {
		return samaccount;
	}


	public void setSamaccount(String samaccount) {
		this.samaccount = samaccount;
	}


	public String getAeReqNumber() {
		return aeReqNumber;
	}


	public void setAeReqNumber(String aeReqNumber) {
		this.aeReqNumber = aeReqNumber;
	}


	public String getLeavetype() {
		return leavetype;
	}


	public void setLeavetype(String leavetype) {
		this.leavetype = leavetype;
	}


	public Policy() {
		super();
	}
	
	
	public String getOption() {
		return option;
	}



	public void setOption(String option) {
		this.option = option;
	}



	public String getEmpid() {
		return empid;
	}



	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String get_defect_description() {
		// TODO Auto-generated method stub
		return defect_description;
	}

	public  String getAgent_Im_Id() {
		
		return agent_Im_Id;
	}

	public boolean getAttachment() {
		// TODO Auto-generated method stub
		return attachment;
	}

	public String getIssueRelated()
	{
		return issue;
	}

	public String getMessage() {
		return message;
	}

	public String getName() {
		return name;
	}

	public String getPin() {
		return pin;
	}

	public String getPlanName() {
		return planName;
	}

	public String getPolicyQuoteNo() {
		// TODO Auto-generated method stub
		return policyQuoteNo;
	}

	public String getPrem() {
		return prem;
	}
	public String getProduct()
	{
		return product;
	}
	public String getRemark() {
		// TODO Auto-generated method stub
		return remark;
	}

	public String getResult() {
		return result;
	}

	public String getSlipNo() {
		// TODO Auto-generated method stub
		return slipNo;
	}

	public String getStateCity() {
		return stateCity;
	}

	public String getSumInsured() {
		return sumInsured;
	}

	public String getTransaction() {
		// TODO Auto-generated method stub
		return trans;
		
	}

	public String getEmailId() {
		// TODO Auto-generated method stub
		return emailId;
	}
	
	public String getUCVId() {
		// TODO Auto-generated method stub
		return UCVId;
	}
	
	public boolean isAgent() {
		return agent;
	}

	public boolean isCheque() {
		return cheque;
	}

	public boolean isChequeImage() {
		return chequeImage;
	}

	public boolean isConvert() {
		return convert;
	}
	
	public boolean isIntermediate() {
		return intermediate;
	}

	public boolean isOffline() {
		return offline;
	}

	public boolean isPdf() {
		return pdf;
	}

	public boolean isPincode() {
		return pincode;
	}

	public boolean isPlan() {
		return plan;
	}

	public boolean isStart() {
		return start;
	}

	public void set_defect_description(String defect_description) {
		// TODO Auto-generated method stub
		this.defect_description=defect_description;
	}

	public void setAgent(boolean agent) {
		this.agent = agent;
	}

	public  void setAgent_Im_Id(String agent_Im_Id) {
		
		this.agent_Im_Id = agent_Im_Id;
	}

	public void setAttachment(boolean attachment) {
		// TODO Auto-generated method stub
		this.attachment=attachment;
	}

	public void setCheque(boolean cheque) {
		this.cheque = cheque;
	}

	public void setChequeImage(boolean chequeImage) {
		this.chequeImage = chequeImage;
	}
	public void setConvert(boolean convert) {
		this.convert = convert;
	}
	public void setIntermediate(boolean intermediate) {
		this.intermediate = intermediate;
	}
	public void setIssueRelated(String issue)
	{
		this.issue=issue;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setOffline(boolean offline) {
		this.offline = offline;
	}
	public void setPdf(boolean pdf) {
		this.pdf = pdf;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public void setPincode(boolean pincode) {
		this.pincode = pincode;
	}

	public void setPlan(boolean plan) {
		this.plan = plan;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public void setPolicyQuoteNo(String policyQuoteNo) {
		// TODO Auto-generated method stub
		this.policyQuoteNo=policyQuoteNo;
	}

	public void setPrem(String prem) {
		this.prem = prem;
	}

	public void setProduct(String product)
	{
		this.product=product;
	}

	public void setRemark(String remark) {
		this.remark=remark;
		// TODO Auto-generated method stub
		
	}

	public void setResult(String result) {
		this.result = result;
	}
	public void setSlipNo(String slipNo) {
		// TODO Auto-generated method stub
		this.slipNo=slipNo;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public void setStateCity(String stateCity) {
		this.stateCity = stateCity;
	}

	public void setSumInsured(String sumInsured) {
		this.sumInsured = sumInsured;
	}
	public void setTransaction(String trans) {
		// TODO Auto-generated method stub
		this.trans=trans;
		
	}

	public void setEmailId(String emailId) {
		// TODO Auto-generated method stub
		this.emailId = emailId;
	}

	public void setUCVId(String UCVId) {
		// TODO Auto-generated method stub
		this.UCVId = UCVId;
	}

	public String setSamAccount(String samaccount) {
		return this.samaccount=samaccount;
		
		
	}

	public String getSamAccountName() {
		// TODO Auto-generated method stub
		return samaccount;
	}

	public void setAeRequestNumber(String aeReqNumber) {
		// TODO Auto-generated method stub
		this.aeReqNumber=aeReqNumber;
	}
	public String getAeRequestNumber() {
		// TODO Auto-generated method stub
		return aeReqNumber;
	}

	public String getAeRequestStatus() {
		// TODO Auto-generated method stub
		return aeRequestStatus;
	}
	public String setAeRequestStatus(String aeRequestStatus ) {
		return this.aeRequestStatus=aeRequestStatus;
	}

	
}
