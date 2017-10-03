package com.vyoms.whatsapp.type;

import java.io.IOException;
import java.net.ConnectException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.vyom.rasa.integration.validate_Response;
import com.vyom.whatsapp.rest.aduserreset.aeRestCallADPassrest;
import com.vyoms.whatsapp.implementation.WhatsAppImplementation;
import com.vyoms.whatsapp.model.AgentMaster;
import com.vyoms.whatsapp.model.EmpMaster;
import com.vyoms.whatsapp.model.PinMaster;
import com.vyoms.whatsapp.model.Policy;
import com.vyoms.whatsapp.service.AgentMasterService;
import com.vyoms.whatsapp.service.EmpMasterService;
import com.vyoms.whatsapp.service.PinMasterService;
import com.vyoms.whatsapp.util.Constants;
import com.vyoms.whatsapp.util.DriverUtility;
import com.vyoms.whatsapp.util.LeaveBalance;

public class WhatsApp implements WhatsAppImplementation {

	public static Actions actions;

	public static int browserType;

	public static WebDriver driver = null;

	public static boolean init = false;
	public long SourceIDSeq = 94;// 113;
	public static HashMap<String, String> msgs = null;
	public static boolean inLoop = false;
	public static String rasa_Response = null;
	//public static String rasa_Request=null;
	validate_Response validate_msg = new validate_Response();

	/*
	 * public WhatsApp(PinMasterService pinMasterService, AgentMasterService
	 * agentMasterService, EmpMasterService empMasterService) { // TODO
	 * Auto-generated constructor stub }
	 */

	public void sendMessage(String title, String msg) throws InterruptedException {
		Thread.sleep(1000);
		driver.switchTo().defaultContent();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("main")));
		WebElement main = driver.findElement(By.id("main"));
		WebElement messageList = main.findElement(By.className("_9tCEa"));
		List<WebElement> messages = messageList.findElements(By.className("msg"));
		String lastMessage = null;
		if (messages.size() >= 1) {
			WebElement message = messages.get(messages.size() - 1);
			WebElement msgLast = null;
			try {
				msgLast = message.findElement(By.className("message-text"));
			} catch (ElementNotFoundException e) {
				// TODO: handle exception
			} catch (NoSuchElementException e) {
			}

			try {
				System.out.println(msgLast.getText());
				// lastMessage = msgLast.findElements(By.tagName("span")).get(1).getText();
				lastMessage = msgLast.getText();
			} catch (NullPointerException e) {
				// TODO: handle exception
			}
		}
		boolean alreadySent = false;
		if (lastMessage != null && removeSpecialCharacter(lastMessage).equals(removeSpecialCharacter(msg))) {
			alreadySent = true;
		}
		if (!alreadySent) {
			WebElement blockCompose = main.findElement(By.className("block-compose"));
			WebElement inputTextDiv = blockCompose.findElement(By.className("input-container"));
			wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[@id='main']/footer/div[1]/div[2]/div/div[2]")));
			WebElement inputText = inputTextDiv
					.findElement(By.xpath("//*[@id='main']/footer/div[1]/div[2]/div/div[2]"));
			inputText.click();
			inputText.clear();
			inputText.sendKeys(msg.replaceAll("\n", Keys.chord(Keys.SHIFT, Keys.ENTER)));
			// inputText.sendKeys(msg);please wait while we are genrating your
			// quote \n(you shall receive an email for the same )
			WebElement button = blockCompose.findElements(By.className("compose-btn-send")).get(0);
			button.click();
			System.out.println("Sent Message=" + msg);
			msgs.put(title, removeSpecialCharacter(msg));
		}

	}



	// AgentMasterService agentMasterService;
	// public HashMap<String, AgentMaster> agents;
	String downloadFilepath = Constants.downloadFilepath;
	public HashMap<String, EmpMaster> employees;
	// EmpMasterService empMasterService;
	public static HashMap<String, Date> lastReply = new HashMap<>();
	public String gTickets;

	// public HashMap<String, PinMaster> pinCodes;

	// PinMasterService pinMasterService;

	public HashMap<String, Policy> policy;

	protected String rep;

	// Changes for Intermidate Id
	public HashMap<String, String> validIntermediateIds;

	public String downloadImage() throws InterruptedException {
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("main")));

		WebElement main = driver.findElement(By.id("main"));
		String result = null;
		List<WebElement> msgs = main.findElements(By.className("image-thumb"));
		WebElement msg = msgs.get(msgs.size() - 1);
		try {
			wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("spinner-container")));
			WebElement resultImg = msg.findElement(By.tagName("img"));
			resultImg.click();
			driver.switchTo().defaultContent();
			wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("app")));
			WebElement app = driver.findElement(By.id("app"));
			WebElement mediaPanelHeader = app.findElement(By.className("media-panel-header"));
			WebElement downloadButton = mediaPanelHeader.findElement(By.tagName("a"));
			downloadButton.click();
			WebElement closeButton = mediaPanelHeader.findElement(By.tagName("button"));
			closeButton.click();
		} catch (Exception e) {
			result = msg.findElement(By.className("message-system-e2e")).findElement(By.className("emojitext"))
					.getText();
			if (result.equals("Messages you text to this chat and calls are secured with end-to-end encryption.")) {
				msg = msgs.get(msgs.size() - 2);
				result = msg.findElement(By.className("message-text")).findElements(By.tagName("span")).get(1)
						.getText();
			}
		}
		System.out.println("Message=" + result);
		return result;
	}

	public String getFileName(String filePath) {
		String fileName = new StringBuffer().append(" ")
				.append(filePath.substring(filePath.lastIndexOf("\\") + 1, filePath.length())).append(" ").toString();
		return fileName.replaceAll("\\P{Print}", "").trim();
	}

	public String getMessage(String msg) {
		String message = new StringBuffer().append(" ").append(msg).append(" ").toString();
		return message.replaceAll("\\P{Print}", "").trim();
	}

	public void cleanBrowser() {
		String cmd = "Taskkill /IM chromedriver.exe /F";
		try {
			Process p = Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public void init(int bType) {
		if (init == false) {
			System.out.println("Startig INIT ");
			// pinCodes = pinMasterService.getListOfPinMaster();
			// agents = agentMasterService.getListOfAgentMaster();
			// Changes for Intermidate Id
			// validIntermediateIds = populateValidIntermidateId(agents);
			// employees = empMasterService.getListOfEmpMaster();
			driver = DriverUtility.getDriver(bType, downloadFilepath);
			actions = new Actions(driver);
			msgs = new HashMap<String, String>();
			policy = new HashMap<>();
			browserType = bType;
			// gTickets=new HashMap<String,String>();
		}
	}

	// Changes for Intermidate Id
	public Boolean isValidIntermediateId(String id) {
		boolean result = false;
		if (validIntermediateIds.containsKey(id))
			result = true;
		return result;
	}

	public String messageReply(String from, String msg) throws Exception {
		from = from.replace("+91", "").replace(" ", "");
		String reply = "No valid input";
		Pattern agent_Im_Id_pattrn = Pattern.compile("[a-zA-Z]{4}\\d{5}");
		// agent_Im_Id.matcher(msg);
		Matcher agent_Im_Id = agent_Im_Id_pattrn.matcher(msg);
		Pattern intermediateIdPattern = Pattern.compile("(\\d{12})");
		intermediateIdPattern.matcher(msg);
		Pattern.compile(
				"(([A-D]{1}|[a-d]{1})[1-4]{1})|(([A-D]{1}|[a-d]{1})[1-4]{1}[$]{1}[0-9]{1,7})|(([A-D]{1}|[a-d]{1})[1-4]{1}[@]{1}[0-9]{1,5})|(([A-D]{1}|[a-d]{1})[1-4]{1}[$]{1}[0-9]{1,7}[@]{1}[0-9]{1,5})|(([A-D]{1}|[a-d]{1})[1-4]{1}[@]{1}[0-9]{1,5}[$]{1}[0-9]{1,7})");
		from.split(" ");

		//Pattern empidRegexPattern = Pattern.compile("[1-0]{4}\\d{5}");
		// agent_Im_Id.matcher(msg);
		Matcher empidRegex = agent_Im_Id_pattrn.matcher(msg);

		try
		{
			if(msg.equalsIgnoreCase("exit"))
			{
				rasa_Response=msg;
			}else if(msg.contains("empid")){
				rasa_Response=msg;
			}else
			{
				rasa_Response=msg;
				//rasa_Response=(new validate_Response()).check_response(msg);
				System.out.println("Rasa default responce="+rasa_Response);
			}


		}/*catch(ConnectException e){

		reply="Hi,"+from+" Unnable to connect with RASA.";

		sendMessage(from, reply);

		policy.get(from).setStart(false);

		policy.remove(from);

		reply="Hi,"+from+" Your session has ben teminated Please ping us again \n Thank You !! ";

		sendMessage(from, reply);

	}*/
		catch(Exception e){}
		System.out.println("Got the response from RASA *****" + rasa_Response + "******");
		//String checking1option="";

		if (!policy.containsKey(from) && rasa_Response.equalsIgnoreCase("hi") && from.contains("8698206727"))
		{
			final Policy saveImageForAgent = new Policy();
			saveImageForAgent.setStart(true);
			policy.put(from, saveImageForAgent);
			reply = "Hello!" + from + " You have reached the balance checking assistant. How may I assist you today?";
			sendMessage(from, reply);

		}
		if(policy.containsKey(from)&& policy.get(from).isStart() && rasa_Response.equalsIgnoreCase("Exit"))
		{
				policy.get(from).setStart(false);
				policy.remove(from);
				reply="Thank you for reaching the balance checking assistant. It was a pleasure assisting you. Do reach us for further assistance.";
				sendMessage(from, reply);
				reply="Have a great day.";
				sendMessage(from, reply);
				return removeSpecialCharacter(reply);
		}
		if(policy.containsKey(from) && policy.get(from).isStart() && rasa_Response.equalsIgnoreCase("leave balance"))//||rasa_Response.contains("Leave Balance")))
		{
			reply =  "Sure! Let me take a few details first. Please mention your employee ID.";
			sendMessage(from, reply);
			return removeSpecialCharacter(reply);
		}
		if(policy.containsKey(from) && policy.get(from).isStart() && rasa_Response.toLowerCase().contains("ae"))//||rasa_Response.contains("Leave Balance")))
		{
			String str[]=rasa_Response.split(" ");
			policy.get(from).setEmpid(str[str.length-1]);
			policy.get(from).setOption(rasa_Response);
		}

		
		/*if(policy.containsKey(from) && policy.get(from).isStart() && policy.get(from).getOption()!=null && (rasa_Response.equals("1")||rasa_Response.equals("2")||rasa_Response.equals("3")||rasa_Response.equals("4")))
		{
			policy.get(from).setOption(rasa_Response);
		}*/
		if (policy.containsKey(from) && policy.get(from).isStart() && (rasa_Response.equalsIgnoreCase("1") || rasa_Response.equalsIgnoreCase("Casual leave") ))//||rasa_Response.equalsIgnoreCase("cl")||rasa_Response.equalsIgnoreCase("casual leave")))
		{
			policy.get(from).setLeavetype("Casual leave");
		}else 	if (policy.containsKey(from)&& policy.get(from).isStart() && (rasa_Response.equalsIgnoreCase("2") || rasa_Response.equalsIgnoreCase("Sick leave") ))
		{
			policy.get(from).setLeavetype("Sick Leave");
		}else	if (policy.containsKey(from)&& policy.get(from).isStart() && (rasa_Response.equalsIgnoreCase("3") || rasa_Response.equalsIgnoreCase("Paid leave") ))
		{
			policy.get(from).setLeavetype("Paid leave");
		}else	if (policy.containsKey(from)&& policy.get(from).isStart() && (rasa_Response.equalsIgnoreCase("4") || rasa_Response.equalsIgnoreCase("Total leave") ))
		{
			policy.get(from).setLeavetype("Total");
		}else if(policy.containsKey(from) && policy.get(from).isStart() && policy.get(from).getEmpid() != null && policy.get(from).getLeavetype() == null && policy.get(from).getOption() != null)
		{
			reply =  "Thank you. Please mention the type of leave."+"\n1. Casual leave"+"\n2. Sick Leave"+"\n3. Paid Leave"+"\n4. Total leave";
			sendMessage(from, reply);
		}


		if(policy.containsKey(from)&& policy.get(from).isStart() && policy.get(from).getLeavetype() != null && policy.get(from).getEmpid() != null)
		{
			policy.get(from).setOption(null);
			String result = LeaveBalance.getLeave(policy.get(from).getEmpid(), policy.get(from).getLeavetype());
			try{
				float t=Float.valueOf((String)result);
						
			}catch(Exception e){
				reply =result;
				sendMessage(from, reply);		
				return removeSpecialCharacter(reply);
			}
			
			System.out.println("EnpId="+policy.get(from).getEmpid()+"Leave type="+policy.get(from).getLeavetype());
			reply ="Great! Your remaining "+policy.get(from).getLeavetype()+" Balance Is *"+result+"*";
			//Great! You have total ….as casual leave. Your remaining Casual Leave Balance is 18.
			sendMessage(from, reply);
			reply =  "\nPlease mention the type of leave would you like to know."+"\n1. Casual leave"+"\n2. Sick Leave"+"\n3. Paid Leave"+"\n4. Total leave \n Or Type *EXIT* to terminate the conversation..";
			sendMessage(from, reply);
			//policy.get(from).setLeavetype(null);
		}else{
			sendMessage(from, reply);
		}
		
		return removeSpecialCharacter(reply);
	}

	public String readLastMessage() throws InterruptedException {
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		WebElement main = driver.findElement(By.id("main"));
		String result = null;
		List<WebElement> msgs = main.findElements(By.className("message-list"));
		WebElement msg = msgs.get(msgs.size() - 1);
		try {
			result = msg.findElement(By.className("message-text")).findElements(By.tagName("span")).get(1).getText();
		} catch (Exception e) {
			result = msg.findElement(By.className("message-system-e2e")).findElement(By.className("emojitext"))
					.getText();
			if (result.equals("Messages you text to this chat and calls are secured with end-to-end encryption.")) {
				msg = msgs.get(msgs.size() - 2);
				result = msg.findElement(By.className("message-text")).findElements(By.tagName("span")).get(1)
						.getText();
			}
		}
		System.out.println("Message=" + result);
		return result;
	}

	public String removeSpecialCharacter(String temp) {
		try {
			return temp.replaceAll("\\P{Print}", "").trim();
		} catch (Exception e) {
			return temp;
		}
	}

	public void sendDocument(String title, String docPath) throws InterruptedException {
		driver.switchTo().defaultContent();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("main")));
		WebElement main = driver.findElement(By.id("main"));
		WebElement chatControl = main.findElement(By.className("pane-chat-controls"));
		List<WebElement> items = chatControl.findElements(By.className("menu-item"));
		WebElement attachment = items.get(1).findElement(By.tagName("button"));
		attachment.click();

		WebElement docAttachment = items.get(1).findElements(By.tagName("input")).get(1);
		docAttachment.sendKeys(docPath);

		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("drawer-body")));
		WebElement drawerBody = driver.findElement(By.className("drawer-body"));

		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("drawer-controls")));
		WebElement textButton = drawerBody.findElement(By.className("drawer-controls"))
				.findElement(By.tagName("button"));
		textButton.click();

	}

	public void sendImage(String title, String imgPath, String msg) throws InterruptedException {
		driver.switchTo().defaultContent();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("main")));
		WebElement main = driver.findElement(By.id("main"));
		WebElement chatControl = main.findElement(By.className("pane-chat-controls"));
		List<WebElement> items = chatControl.findElements(By.className("menu-item"));
		WebElement attachment = items.get(1).findElement(By.tagName("button"));
		attachment.click();

		WebElement imageAttachment = items.get(1).findElements(By.tagName("input")).get(0);
		imageAttachment.sendKeys(imgPath);

		wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("drawer-body")));
		WebElement drawerBody = driver.findElement(By.className("drawer-body"));

		wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("input-wrapper")));

		WebElement inputWrapper = drawerBody.findElement(By.className("input-wrapper"));
		WebElement inputEmoji = drawerBody.findElement(By.className("input-emoji"));
		WebElement inputText = inputEmoji.findElement(By.tagName("div"));
		inputWrapper.click();
		inputText.sendKeys(msg);

		WebElement textButton = drawerBody.findElement(By.className("drawer-controls"))
				.findElement(By.tagName("button"));
		textButton.click();

	}

	public boolean downloadDoc() throws InterruptedException {

		Thread.sleep(2000);

		driver.switchTo().defaultContent();

		WebDriverWait wait = new WebDriverWait(driver, 15);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("main")));



		WebElement main = driver.findElement(By.id("main"));

		boolean result = false;

		List<WebElement> msgs = main.findElements(By.className("document-body"));

		WebElement msg = msgs.get(msgs.size() - 1);

		try {

			wait = new WebDriverWait(driver, 15);

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("spinner-container")));

			WebElement resultDoc = msg.findElement(By.className("doc-state"));

			resultDoc.click();

			result =true;
			//result = msg.findElement(By.className("message-system-e2e")).findElement(By.className("emojitext")).getText();

		} catch (NoSuchElementException e) {


		}

		//System.out.println("Message=" + result);

		Thread.sleep(3000);

		return result;

	}

	@Override
	public void userList() throws Exception {

		if (driver == null) {

			WhatsApp.driver = DriverUtility.getDriver(browserType, downloadFilepath);

			init = false;

		}

		if (init == false) {

			driver.get("https://web.whatsapp.com/");

			WebDriverWait wait = new WebDriverWait(driver, 60);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pane-side")));

			init = true;

		}

		inLoop = false;

		if (inLoop == false) {

			inLoop = true;

			driver.switchTo().defaultContent();

			WebElement side = driver.findElement(By.id("pane-side"));

			WebElement paneSide = side.findElement(By.id("pane-side"));

			List<WebElement> chats = paneSide.findElements(By.className("chat-body"));// ("div")).findElement(By.tagName("div")).findElement(By.tagName("div")).findElements(By.tagName("div"));

			for (WebElement chat : chats) {

			//	System.out.println("Active Chats Size:-" + chats.size());

				// chats = paneSide.findElements(By.className("chat-body"));

				try {

					try {

						actions.moveToElement(chat).click().perform();

						// chat.click();

					} catch (Exception e1) {

						// TODO Auto-generated catch block

						chat.click();

						actions.moveToElement(chat).click().perform();

					}

					WebElement chatTitle = chat.findElement(By.className("chat-main"))

							.findElement(By.className("chat-title")).findElement(By.tagName("span"));
					// System.out.println("chatTitleAttrib: -"+chatTitle.getAttribute("title"));
					// System.out.println("chatTitleText: -"+chatTitle.getText());
					WebElement chatSecondary = chat.findElement(By.className("chat-secondary"));// last-msg
					// System.out.println("ChatSecondaryText: -"+chatSecondary.getText());
					// System.out.println("ChatSecondaryAttrib:
					// -"+chatSecondary.getAttribute("title"));
					// System.out.println("ChatSecondary: -"+chatSecondary.getTagName());
					WebElement lastMsg = chatSecondary.findElement(By.className("chat-status"));
					// System.out.println("LastMsg get Attribute: -"+lastMsg.getText());
					// System.out.println("LastMsg get Text: -"+lastMsg.getText());
					String chatUnreadCount = chat.findElement(By.className("chat-secondary"))

							.findElement(By.className("chat-meta")).findElement(By.tagName("span")).getText();

					if (!chatUnreadCount.equals("") && !msgs.containsKey(chatTitle.getAttribute("title"))) {

						msgs.put(chatTitle.getAttribute("title"),

								removeSpecialCharacter("Old" + lastMsg.getText()));

					}

					actions.moveToElement(chat).click().perform();

					// System.out.println(chatTitle.getAttribute("title") + "="

					// + lastMsg.getAttribute("title"));

					WebDriverWait wait = new WebDriverWait(driver, 15);

					wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("chat-secondary")));

					if (!chatTitle.getAttribute("title").contains(",")) {

						if (processMessage(chatTitle.getAttribute("title"))

								&& msgs.containsKey(chatTitle.getAttribute("title"))) {

							if ((!msgs.get(chatTitle.getAttribute("title"))

									.equals(removeSpecialCharacter(lastMsg.getText()))

									&& removeSpecialCharacter(lastMsg.getText()).length() != 0)

									|| msgs.get(chatTitle.getAttribute("title")).equals(removeSpecialCharacter("Photo"))

									|| msgs.get(chatTitle.getAttribute("title")).equals(removeSpecialCharacter("GIF"))

									|| (msgs.get(chatTitle.getAttribute("title")).contains(removeSpecialCharacter("/"))

											&& removeSpecialCharacter(lastMsg.getText()).length() == 21)) {

								try {

									System.out.println("Old Message="
											+ msgs.get(chatTitle.getAttribute("title")).length() + " New Message= "
											+ removeSpecialCharacter(lastMsg.getText()).length());

									actions.moveToElement(chat).click().perform();

									if (removeSpecialCharacter(lastMsg.getText()).equals(

											"?Messages you text to this chat and calls are secured with end-to-end encryption.")) {

										chat.click();

										// Thread.sleep(2000);

									}

									msgs.put(chatTitle.getAttribute("title"),

											removeSpecialCharacter(lastMsg.getText()));

									// if

									// (chatTitle.getAttribute("title").contains("pritish"))

									// {

									String a = chatTitle.getAttribute("title");

									try {

										chat.click();

										System.out.println(a);

									} catch (Exception e) {

										// TODO Auto-generated catch block

										actions.moveToElement(chat).click().perform();

										chat.click();

										System.out.println(chatTitle.getAttribute("title"));

										e.printStackTrace();

									}

									String message = messageReply(chatTitle.getAttribute("title"),

											removeSpecialCharacter(lastMsg.getText()));

									lastReply.put(chatTitle.getAttribute("title"), new Date());

									Thread.sleep(1000);

									if (!removeSpecialCharacter(lastMsg.getText()).equals("Photo")

											&& !removeSpecialCharacter(lastMsg.getText()).equals("GIF")

											&& !(removeSpecialCharacter(lastMsg.getText()).contains("/")

													&& removeSpecialCharacter(lastMsg.getText())

													.length() == 21)) {

										WebElement archi = driver.findElement(

												By.xpath("//*[@id='pane-side']/div/div/div/div/div/div/div[2]"));

										WebElement archTitle = archi.findElement(By.xpath(

												"//*[@id='pane-side']/div/div/div/div[1]/div/div/div[2]/div[1]/div[1]/span"));

										System.out.println(a.equals(archTitle.getText().toString().trim()));

										if (a.equals(archTitle.getText().toString().trim())) {

											actions.moveToElement(archi).click().perform();

											archi.click();

											actions.contextClick(archi).build().perform();

											wait.until(ExpectedConditions

													.visibilityOfElementLocated((By.className("dropdown"))));

											WebElement arch = driver.findElement(By.className("dropdown"));

											List<WebElement> archive = arch.findElements(By.tagName("li"));

											archive.get(0).click();

										} else {

											actions.moveToElement(chat).click().perform();

											chat.click();

											actions.contextClick(chat).build().perform();

											wait.until(ExpectedConditions

													.visibilityOfElementLocated((By.className("dropdown"))));

											WebElement arch = driver.findElement(By.className("dropdown"));

											List<WebElement> archive = arch.findElements(By.tagName("li"));

											archive.get(0).click();

										}
									}

								} catch (Exception e) {

									// TODO Auto-generated catch block

									e.printStackTrace();

								}

								// }

							} else {

								if (!removeSpecialCharacter(lastMsg.getText()).equals("Photo")

										&& !removeSpecialCharacter(lastMsg.getText()).equals("GIF")

										&& !(removeSpecialCharacter(lastMsg.getText()).contains("/")

												&& removeSpecialCharacter(lastMsg.getText())

												.length() == 21)) {

								}

							}

						} else {

							actions.moveToElement(chat).click().perform();

							if (removeSpecialCharacter(lastMsg.getText()).equals(

									"?Messages you text to this chat and calls are secured with end-to-end encryption.")) {

								chat.click();

								// Thread.sleep(2000);

							}

							System.out.println(

									"Length=" + removeSpecialCharacter(lastMsg.getText()).length());

							if (removeSpecialCharacter(lastMsg.getText()).length() > 0) {

								msgs.put(chatTitle.getAttribute("title"),

										removeSpecialCharacter(lastMsg.getText()));
								lastReply.put(chatTitle.getAttribute("title"), new Date());

								System.out.println("Starting Message=" + chatTitle.getAttribute("title") + " "
										+ removeSpecialCharacter(lastMsg.getText()));

							}

							String message = messageReply(chatTitle.getAttribute("title"),

									removeSpecialCharacter(lastMsg.getText()));

							WebElement archi = driver
									.findElement(By.xpath("//*[@id='pane-side']/div/div/div/div[1]/div/div/div[2]"));

							actions.moveToElement(archi).click().perform();

							chat.click();

							actions.contextClick(archi).build().perform();

							wait.until(ExpectedConditions.visibilityOfElementLocated((By.className("dropdown"))));

							WebElement arch = driver.findElement(By.className("dropdown"));

							List<WebElement> drop = arch.findElements(By.tagName("li"));

							/* WebElement archive; */

							try {

								drop.get(0).click();

							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}

				} catch (Exception e) {

					// TODO Auto-generated catch block

					e.printStackTrace();

				}
			}

			inLoop = false;
		}

		// System.out.println("HDFC LOGGED IN");

		Thread.sleep(1500);

		// userList();
	}

	public boolean processMessage(String key) {
		Date d1 = lastReply.get(key);
		if (d1 == null)
			return true;
		Date d2 = new Date();
		long seconds = (d2.getTime() - d1.getTime()) / 1000;
		if (seconds > 1) {
			return true;
		}
		return false;
	}

}
