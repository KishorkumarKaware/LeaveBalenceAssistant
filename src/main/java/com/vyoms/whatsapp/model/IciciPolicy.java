package com.vyoms.whatsapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the icici_policy database table.
 * 
 */
@Entity
@Table(name="icici_policy")
@NamedQuery(name="IciciPolicy.findAll", query="SELECT i FROM IciciPolicy i")
public class IciciPolicy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="get_name_pin")
	private String getNamePin;

	@Column(name="get_name_pin_received")
	private String getNamePinReceived;

	@Column(name="interaction_no")
	private String interactionNo;

	@Column(name="m_date")
	private String mDate;

	@Id
	@Column(name="m_id")
	private Integer mId;

	@Column(name="msg_content")
	private String msgContent;

	@Column(name="msg_from")
	private String msgFrom;

	@Column(name="msg_send_flag")
	private String msgSendFlag;

	@Column(name="msg_ticket_status_send")
	private String msgTicketStatusSend;

	@Column(name="plan_details")
	private String planDetails;

	@Column(name="plan_details_pdf_path")
	private String planDetailsPdfPath;

	@Column(name="plan_details_send")
	private String planDetailsSend;

	@Column(name="plan_details_text_received_flag")
	private String planDetailsTextReceivedFlag;

	@Column(name="send_policy_png")
	private String sendPolicyPng;

	@Column(name="ticket_status")
	private String ticketStatus;

	public IciciPolicy() {
	}

	public String getGetNamePin() {
		return this.getNamePin;
	}

	public String getGetNamePinReceived() {
		return this.getNamePinReceived;
	}

	public String getInteractionNo() {
		return this.interactionNo;
	}

	public String getMDate() {
		return this.mDate;
	}

	public Integer getMId() {
		return this.mId;
	}

	public String getMsgContent() {
		return this.msgContent;
	}

	public String getMsgFrom() {
		return this.msgFrom;
	}

	public String getMsgSendFlag() {
		return this.msgSendFlag;
	}

	public String getMsgTicketStatusSend() {
		return this.msgTicketStatusSend;
	}

	public String getPlanDetails() {
		return this.planDetails;
	}

	public String getPlanDetailsPdfPath() {
		return this.planDetailsPdfPath;
	}

	public String getPlanDetailsSend() {
		return this.planDetailsSend;
	}

	public String getPlanDetailsTextReceivedFlag() {
		return this.planDetailsTextReceivedFlag;
	}

	public String getSendPolicyPng() {
		return this.sendPolicyPng;
	}

	public String getTicketStatus() {
		return this.ticketStatus;
	}

	public void setGetNamePin(String getNamePin) {
		this.getNamePin = getNamePin;
	}

	public void setGetNamePinReceived(String getNamePinReceived) {
		this.getNamePinReceived = getNamePinReceived;
	}

	public void setInteractionNo(String interactionNo) {
		this.interactionNo = interactionNo;
	}

	public void setMDate(String mDate) {
		this.mDate = mDate;
	}

	public void setMId(Integer mId) {
		this.mId = mId;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public void setMsgFrom(String msgFrom) {
		this.msgFrom = msgFrom;
	}

	public void setMsgSendFlag(String msgSendFlag) {
		this.msgSendFlag = msgSendFlag;
	}

	public void setMsgTicketStatusSend(String msgTicketStatusSend) {
		this.msgTicketStatusSend = msgTicketStatusSend;
	}

	public void setPlanDetails(String planDetails) {
		this.planDetails = planDetails;
	}

	public void setPlanDetailsPdfPath(String planDetailsPdfPath) {
		this.planDetailsPdfPath = planDetailsPdfPath;
	}

	public void setPlanDetailsSend(String planDetailsSend) {
		this.planDetailsSend = planDetailsSend;
	}

	public void setPlanDetailsTextReceivedFlag(String planDetailsTextReceivedFlag) {
		this.planDetailsTextReceivedFlag = planDetailsTextReceivedFlag;
	}

	public void setSendPolicyPng(String sendPolicyPng) {
		this.sendPolicyPng = sendPolicyPng;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

}