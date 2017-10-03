package com.vyoms.whatsapp.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AgentMessage.class)
public abstract class AgentMessage_ {

	public static volatile SingularAttribute<AgentMessage, String> msgSendFlag;
	public static volatile SingularAttribute<AgentMessage, String> msgContent;
	public static volatile SingularAttribute<AgentMessage, String> msgTicketStatusSend;
	public static volatile SingularAttribute<AgentMessage, String> ticketStatus;
	public static volatile SingularAttribute<AgentMessage, Integer> mId;
	public static volatile SingularAttribute<AgentMessage, String> msgFrom;
	public static volatile SingularAttribute<AgentMessage, String> mDate;
	public static volatile SingularAttribute<AgentMessage, String> interactionNo;

}

