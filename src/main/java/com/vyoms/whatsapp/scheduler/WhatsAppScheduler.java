package com.vyoms.whatsapp.scheduler;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vyoms.whatsapp.service.AgentMasterService;
import com.vyoms.whatsapp.service.EmpMasterService;
import com.vyoms.whatsapp.service.PinMasterService;
import com.vyoms.whatsapp.structure.BrowserType;
import com.vyoms.whatsapp.type.WhatsApp;

@Component
public class WhatsAppScheduler {

	/*@Autowired
	AgentMasterService agentMasterService;

	@Autowired
	EmpMasterService empMasterService;
	
	
	
	@Autowired
	PinMasterService pinMasterService;
	*/
	public WhatsApp whatsAppAccount;
	public boolean inOperation = false;
	public void init() {
		//whatsAppAccount = new WhatsApp(pinMasterService,agentMasterService,empMasterService);
		whatsAppAccount = new WhatsApp();
		whatsAppAccount.init(BrowserType.CHROME_BROWSER);		
	}

	@PreDestroy
	public void logout() throws InterruptedException {
	}

	@Scheduled(cron = "*/1 * * * * *")
	public void performOperation() throws Exception {

		if (inOperation == false) {
			init();
		}
		whatsAppAccount.userList();
		inOperation = true;
	}

}