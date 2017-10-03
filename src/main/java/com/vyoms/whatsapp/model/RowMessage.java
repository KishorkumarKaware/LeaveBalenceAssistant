package com.vyoms.whatsapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the row_messages database table.
 * 
 */
@Entity
@Table(name="row_messages")
@NamedQuery(name="RowMessage.findAll", query="SELECT r FROM RowMessage r")
public class RowMessage implements Serializable {
	private static final long serialVersionUID = 1L;

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

	@Column(name="ticket_status")
	private String ticketStatus;

	public RowMessage() {
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

	public String getTicketStatus() {
		return this.ticketStatus;
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

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

}