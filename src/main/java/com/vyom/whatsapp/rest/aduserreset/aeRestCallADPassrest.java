/**
 * 
 */
/**
 * @author admin
 *
 */
package com.vyom.whatsapp.rest.aduserreset;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * HTTP GET and POST example
 *
 * @author iampayload
 *
 */
public class aeRestCallADPassrest {

    private final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.3; rv:36.0) Gecko/20100101 Firefox/36.04";
        // GET action URL, query string appended to the URL as ?stype=models
    //private final String urlRaiseRequest = "http://192.168.1.194:8888/aeengine/rest/execute";
    private final String urlRaiseRequest="http://10.41.4.134:8080/aeengine/rest/execute";
    // POST action URL
    //private final String urlPOST = "http://192.168.1.194:8888/aeengine/rest/authenticate";
    private final String urlPOST = "http://10.41.4.134:8080/aeengine/rest/authenticate";
    // Post data or a payload
    
    String inputLine;
    // Main class
    public static void main(String[] args) throws Exception {

        aeRestCallADPassrest http = new aeRestCallADPassrest();

        //System.out.println("Testing send HTTP GET request   HTML output is below \n");

       
       // System.out.println( "--------------------------------------------------------------------------------------------------------");

        //System.out.println("Raising Request in AE  \n");
        String token = http.getToken();
        System.out.println("Got Token :"+token);
        
        //http.GetSamName("8149360340", "6", token);
        //http.getStatus("6", token);
       //http.ResetADPassword("demo1","7",token);
        
        

    }

    public String ResetADPassword(String samname, String sourceIDSeq, String token) throws IOException {

    	String RequestJSON="{"
        		+ "\"orgCode\": \"TEAMCOMPUTERS\","
        		+ "\"workflowName\": \"Reset password of ADuser\","
        		+ "\"userId\": \"Whatsapp\","
        		+ "\"sourceId\":null,"
        		+ "\"source\": \"Postman\","
        		+ "\"responseMailSubject\": \"null\","
        		+ "\"params\": ["
        		+ "{"
        		+ "\"name\": \"samname\","
        		+ "\"value\":  \""+samname+"\","
        		+ "\"type\": \"String\","
        		+ "\"order\": 1,"
        		+ "\"secret\": false,"
        		+ "\"optional\": false,"
        		+ "\"displayName\": \"Enter SAM Account\""
        		+ "}"
        		+"]"
        		+ "}"
        		+ "";

        URL url = new URL(urlRaiseRequest);

        // Send post request
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        // basic reuqest header to simulate a browser request
        con.setRequestMethod("GET");
        con.setRequestProperty("X-session-token",token);
        con.setRequestProperty("Content-Type","application/json");
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        
        // POST data added to the request as a part of body
        wr.writeBytes(RequestJSON);
        wr.flush();
        wr.close();
        // Reading the HTML output of the POST HTTP request
        //int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine,str = null;
        while ((inputLine = in.readLine()) != null)
            	str=inputLine;
        in.close();
        System.out.println(str);
        String aeRequestNo=new JSONObject(str).getBigInteger("automationRequestId").toString();
        String aeRequestStatus=new JSONObject(str).getString("responseCode").toString();
        while (aeRequestNo.equals("0"))
        {
        	System.out.println("Changing SequeceID and Raising Again");
        	 sourceIDSeq=Integer.toString((int)(Integer.parseInt(sourceIDSeq)+1));
        	aeRestCallADPassrest reqest=new aeRestCallADPassrest();
        	
        	aeRequestNo=reqest.ResetADPassword(samname,sourceIDSeq , token);
        	if (!aeRequestNo.equals("0"))
        	{
        		break;
        	}
        	
        }
        System.out.println("Request Number:- " +aeRequestNo);
        System.out.println("Request Status:- " +aeRequestStatus);
       // Get Result From AE
        return aeRequestNo;
       
    
		// TODO Auto-generated method stub
		
	}

	// HTTP POST request
    public String getToken() throws Exception {

        // POST example URL
        URL obj = new URL(urlPOST);

        // Send post request
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // Basic reuqest header to simulate a browser request
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setRequestProperty("Upgrade-Insecure-Requests", "1");
        con.setRequestProperty("Connection", "keep-alive");
        con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        String postDataBody = "username=whatsapp&password=Admin@123";
        // Payload
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());

        // POST data added to the request as a part of body
        wr.writeBytes(postDataBody);
        wr.flush();
        wr.close();

        // Reading the HTML output of the POST HTTP request
        //int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String str = null;
        while ((inputLine = in.readLine()) != null)
        {
           str=inputLine;
        }
        
        String sessionToken = new JSONObject(str).getString("sessionToken");
       // System.out.println(success);
        //System.out.println(sessionToken);
        in.close();
        
        return sessionToken;
    }


    // HTTP GET request
    public String GetSamName(String mobNumber, String SourceID,String token ) throws Exception {
    	String RequestJSON="{"
        		+ "\"orgCode\": \"TEAMCOMPUTERS\","
        		+ "\"workflowName\": \"GetSamName\","
        		+ "\"userId\": \"Whatsapp\","
        		+ "\"sourceId\":null,"
        		+ "\"source\": \"whatsapp\","
        		+ "\"responseMailSubject\": \"null\","
        		+ "\"params\": ["
        		+ "{"
        		+ "\"name\": \"MobileNumber\","
        		+ "\"value\":  \""+mobNumber+"\","
        		+ "\"type\": \"String\","
        		+ "\"order\": 1,"
        		+ "\"secret\": false,"
        		+ "\"optional\": false,"
        		+ "\"displayName\": \"MobileNumber\""
        		+ "}"
        		+"]"
        		+ "}"
        		+ "";

        URL url = new URL(urlRaiseRequest);

        // Send post request
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        // basic reuqest header to simulate a browser request
        con.setRequestMethod("GET");
        con.setRequestProperty("X-session-token",token);
        con.setRequestProperty("Content-Type","application/json");
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        
        // POST data added to the request as a part of body
        wr.writeBytes(RequestJSON);
        wr.flush();
        wr.close();
        // Reading the HTML output of the POST HTTP request
        //int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine,str = null;
        while ((inputLine = in.readLine()) != null)
            	str=inputLine;
        in.close();
        System.out.println(str);
        String aeRequestNo=new JSONObject(str).getBigInteger("automationRequestId").toString();
        String aeRequestStatus=new JSONObject(str).getString("responseCode").toString();
        while (aeRequestNo.equals("0"))
        {
        	System.out.println("Changing SequeceID and Raising Again");
        	 SourceID=Integer.toString((int)(Integer.parseInt(SourceID)+1));
        	aeRestCallADPassrest reqest=new aeRestCallADPassrest();
        	
        	aeRequestNo=reqest.GetSamName(mobNumber,SourceID , token);
        	if (!aeRequestNo.equals("0"))
        	{
        		break;
        	}
        	
        }
        System.out.println("Request Number:- " +aeRequestNo);
        System.out.println("Request Status:- " +aeRequestStatus);
       // Get Result From AE
        return aeRequestNo;
       
    }
    public String getStatus(String aeRequestNo,String token) throws Exception
    {
    	 URL aeResultUrl = new URL("http://10.41.4.134:8080/aeengine/rest/workflowinstances/"+aeRequestNo.trim());

         // Send post request
         HttpURLConnection conn = (HttpURLConnection) aeResultUrl.openConnection();

         // basic reuqest header to simulate a browser request

         conn.setRequestMethod("GET");
         conn.setRequestProperty("X-session-token",token);
         conn.setRequestProperty("Content-Type","application/json");
         conn.setDoOutput(true);
              
         BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
         String str1 = null;
 		//String inputLine,str = null;
         while ((inputLine = in.readLine()) != null)
             	str1=inputLine;
         //System.out.println(str1);
         String workflowResponse=null;
         try{
        	 
        	 workflowResponse=new JSONObject(new JSONObject(str1).getString("workflowResponse").toString()).getString("message").replace("\n", "").trim();;
         	}
         catch(JSONException e)
         {
        	 
         }
         String status=new JSONObject(str1).getString("status").toString();
         System.out.println("workflowResponse :"+workflowResponse);
         System.out.println("status :"+status);
         in.close();
         //String rep="";
         String rep=workflowResponse;
		return rep.replace("\n","");
    }

	public String GetSamlockState(String from, String SourceID, String token) throws IOException {
		// TODO Auto-generated method stub
		String mobNumber=from.replace(" ","").replace("+91", "");
		String RequestJSON="{"
        		+ "\"orgCode\": \"TEAMCOMPUTERS\","
        		+ "\"workflowName\": \"Unlock ADUser\","
        		+ "\"userId\": \"Whatsapp\","
        		+ "\"sourceId\":null,"
        		+ "\"source\": \"Postman\","
        		+ "\"responseMailSubject\": \"null\","
        		+ "\"params\": ["
        		+ "{"
        		+ "\"name\": \"MobileNumber\","
        		+ "\"value\":  \""+mobNumber+"\","
        		+ "\"type\": \"String\","
        		+ "\"order\": 1,"
        		+ "\"secret\": false,"
        		+ "\"optional\": false,"
        		+ "\"displayName\": \"MobileNumber\""
        		+ "}"
        		+"]"
        		+ "}"
        		+ "";

        URL url = new URL(urlRaiseRequest);

        // Send post request
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        // basic reuqest header to simulate a browser request
        con.setRequestMethod("GET");
        con.setRequestProperty("X-session-token",token);
        con.setRequestProperty("Content-Type","application/json");
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        
        // POST data added to the request as a part of body
        wr.writeBytes(RequestJSON);
        wr.flush();
        wr.close();
        // Reading the HTML output of the POST HTTP request
        //int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine,str = null;
        while ((inputLine = in.readLine()) != null)
            	str=inputLine;
        in.close();
        System.out.println(str);
        String aeRequestNo=new JSONObject(str).getBigInteger("automationRequestId").toString();
        String aeRequestStatus=new JSONObject(str).getString("responseCode").toString();
        while (aeRequestNo.equals("0"))
        {
        	System.out.println("Changing SequeceID and Raising Again");
        	 SourceID=Integer.toString((int)(Integer.parseInt(SourceID)+1));
        	aeRestCallADPassrest reqest=new aeRestCallADPassrest();
        	
        	aeRequestNo=reqest.GetSamlockState(mobNumber,SourceID , token);
        	if (!aeRequestNo.equals("0"))
        	{
        		break;
        	}
        	
        }
        System.out.println("Request Number:- " +aeRequestNo);
        System.out.println("Request Status:- " +aeRequestStatus);
       // Get Result From AE
        return aeRequestNo;
	}
	public String diskCleanupState(String SourceID, String token) throws Exception{


    	String RequestJSON="{"
        		+ "\"orgCode\": \"TEAMCOMPUTERS\","
        		+ "\"workflowName\": \"Disk Cleanup\","
        		+ "\"userId\": \"Whatsapp\","
        		+ "\"sourceId\":null,"
        		+ "\"source\": \"whatsapp\","
        		+ "\"responseMailSubject\": \"null\","
        		+ "\"params\": ["
        		+ "{"
        		+ "}"
        		+"]"
        		+ "}"
        		+ "";

        URL url = new URL(urlRaiseRequest);

        // Send post request
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        // basic reuqest header to simulate a browser request
        con.setRequestMethod("GET");
        con.setRequestProperty("X-session-token",token);
        con.setRequestProperty("Content-Type","application/json");
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        
        // POST data added to the request as a part of body
        wr.writeBytes(RequestJSON);
        wr.flush();
        wr.close();
        // Reading the HTML output of the POST HTTP request
        //int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine,str = null;
        while ((inputLine = in.readLine()) != null)
            	str=inputLine;
        in.close();
        System.out.println(str);
        String aeRequestNo=new JSONObject(str).getBigInteger("automationRequestId").toString();
        String aeRequestStatus=new JSONObject(str).getString("responseCode").toString();
        while (aeRequestNo.equals("0"))
        {
        	System.out.println("Changing SequeceID and Raising Again");
        	 SourceID=Integer.toString((int)(Integer.parseInt(SourceID)+1));
        	aeRestCallADPassrest reqest=new aeRestCallADPassrest();
        	
        	aeRequestNo=reqest.diskCleanupState(SourceID, token);
        	if (!aeRequestNo.equals("0"))
        	{
        		break;
        	}
        	
        }
        System.out.println("Request Number:- " +aeRequestNo);
        System.out.println("Request Status:- " +aeRequestStatus);
       // Get Result From AE
        return aeRequestNo;
       
    
		// TODO Auto-generated method stub
		
	}
	public String GetSoftwareName(String mobNumber, String SourceID,String token ) throws Exception
	{    	String RequestJSON="{"
    		+ "\"orgCode\": \"TEAMCOMPUTERS\","
    		+ "\"workflowName\": \"GetSamName\","
    		+ "\"userId\": \"Whatsapp\","
    		+ "\"sourceId\":null,"
    		+ "\"source\": \"whatsapp\","
    		+ "\"responseMailSubject\": \"null\","
    		+ "\"params\": ["
    		+ "{"
    		+ "\"name\": \"MobileNumber\","
    		+ "\"value\":  \""+mobNumber+"\","
    		+ "\"type\": \"String\","
    		+ "\"order\": 1,"
    		+ "\"secret\": false,"
    		+ "\"optional\": false,"
    		+ "\"displayName\": \"MobileNumber\""
    		+ "}"
    		+"]"
    		+ "}"
    		+ "";

    URL url = new URL(urlRaiseRequest);

    // Send post request
    HttpURLConnection con = (HttpURLConnection) url.openConnection();

    // basic reuqest header to simulate a browser request
    con.setRequestMethod("GET");
    con.setRequestProperty("X-session-token",token);
    con.setRequestProperty("Content-Type","application/json");
    con.setDoOutput(true);
    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
    
    // POST data added to the request as a part of body
    wr.writeBytes(RequestJSON);
    wr.flush();
    wr.close();
    // Reading the HTML output of the POST HTTP request
    //int responseCode = con.getResponseCode();
    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String inputLine,str = null;
    while ((inputLine = in.readLine()) != null)
        	str=inputLine;
    in.close();
    System.out.println(str);
    String aeRequestNo=new JSONObject(str).getBigInteger("automationRequestId").toString();
    String aeRequestStatus=new JSONObject(str).getString("responseCode").toString();
    while (aeRequestNo.equals("0"))
    {
    	System.out.println("Changing SequeceID and Raising Again");
    	 SourceID=Integer.toString((int)(Integer.parseInt(SourceID)+1));
    	aeRestCallADPassrest reqest=new aeRestCallADPassrest();
    	
    	aeRequestNo=reqest.GetSoftwareName(mobNumber, SourceID, token);
    	if (!aeRequestNo.equals("0"))
    	{
    		break;
    	}
    	
    }
    System.out.println("Request Number:- " +aeRequestNo);
    System.out.println("Request Status:- " +aeRequestStatus);
   // Get Result From AE
    return aeRequestNo;
 
	}
	public String GetSoftwareVersion(String softwareName, String sourceIDSeq, String token) throws IOException {

    	String RequestJSON="{"
        		+ "\"orgCode\": \"TEAMCOMPUTERS\","
        		+ "\"workflowName\": \"Reset password of ADuser\","
        		+ "\"userId\": \"Whatsapp\","
        		+ "\"sourceId\":null,"
        		+ "\"source\": \"Postman\","
        		+ "\"responseMailSubject\": \"null\","
        		+ "\"params\": ["
        		+ "{"
        		+ "\"name\": \"samname\","
        		+ "\"value\":  \""+softwareName+"\","
        		+ "\"type\": \"String\","
        		+ "\"order\": 1,"
        		+ "\"secret\": false,"
        		+ "\"optional\": false,"
        		+ "\"displayName\": \"Enter SAM Account\""
        		+ "}"
        		+"]"
        		+ "}"
        		+ "";

        URL url = new URL(urlRaiseRequest);

        // Send post request
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        // basic reuqest header to simulate a browser request
        con.setRequestMethod("GET");
        con.setRequestProperty("X-session-token",token);
        con.setRequestProperty("Content-Type","application/json");
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        
        // POST data added to the request as a part of body
        wr.writeBytes(RequestJSON);
        wr.flush();
        wr.close();
        // Reading the HTML output of the POST HTTP request
        //int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine,str = null;
        while ((inputLine = in.readLine()) != null)
            	str=inputLine;
        in.close();
        System.out.println(str);
        String aeRequestNo=new JSONObject(str).getBigInteger("automationRequestId").toString();
        String aeRequestStatus=new JSONObject(str).getString("responseCode").toString();
        while (aeRequestNo.equals("0"))
        {
        	System.out.println("Changing SequeceID and Raising Again");
        	 sourceIDSeq=Integer.toString((int)(Integer.parseInt(sourceIDSeq)+1));
        	aeRestCallADPassrest reqest=new aeRestCallADPassrest();
        	
        	aeRequestNo=reqest.GetSoftwareVersion(softwareName, sourceIDSeq, token);
        	if (!aeRequestNo.equals("0"))
        	{
        		break;
        	}
        	
        }
        System.out.println("Request Number:- " +aeRequestNo);
        System.out.println("Request Status:- " +aeRequestStatus);
       // Get Result From AE
        return aeRequestNo;
    
	}
	public String getIPAddress(String mobNumber, String SourceID,String token ) throws Exception
	{    	String RequestJSON="{"
    		+ "\"orgCode\": \"TEAMCOMPUTERS\","
    		+ "\"workflowName\": \"GetSamName\","
    		+ "\"userId\": \"Whatsapp\","
    		+ "\"sourceId\":null,"
    		+ "\"source\": \"whatsapp\","
    		+ "\"responseMailSubject\": \"null\","
    		+ "\"params\": ["
    		+ "{"
    		+ "\"name\": \"MobileNumber\","
    		+ "\"value\":  \""+mobNumber+"\","
    		+ "\"type\": \"String\","
    		+ "\"order\": 1,"
    		+ "\"secret\": false,"
    		+ "\"optional\": false,"
    		+ "\"displayName\": \"MobileNumber\""
    		+ "}"
    		+"]"
    		+ "}"
    		+ "";

    URL url = new URL(urlRaiseRequest);

    // Send post request
    HttpURLConnection con = (HttpURLConnection) url.openConnection();

    // basic reuqest header to simulate a browser request
    con.setRequestMethod("GET");
    con.setRequestProperty("X-session-token",token);
    con.setRequestProperty("Content-Type","application/json");
    con.setDoOutput(true);
    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
    
    // POST data added to the request as a part of body
    wr.writeBytes(RequestJSON);
    wr.flush();
    wr.close();
    // Reading the HTML output of the POST HTTP request
    //int responseCode = con.getResponseCode();
    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String inputLine,str = null;
    while ((inputLine = in.readLine()) != null)
        	str=inputLine;
    in.close();
    System.out.println(str);
    String aeRequestNo=new JSONObject(str).getBigInteger("automationRequestId").toString();
    String aeRequestStatus=new JSONObject(str).getString("responseCode").toString();
    while (aeRequestNo.equals("0"))
    {
    	System.out.println("Changing SequeceID and Raising Again");
    	 SourceID=Integer.toString((int)(Integer.parseInt(SourceID)+1));
    	aeRestCallADPassrest reqest=new aeRestCallADPassrest();
    	
    	aeRequestNo=reqest.getIPAddress(mobNumber,SourceID , token);
    	if (!aeRequestNo.equals("0"))
    	{
    		break;
    	}
    	
    }
    System.out.println("Request Number:- " +aeRequestNo);
    System.out.println("Request Status:- " +aeRequestStatus);
   // Get Result From AE
    return aeRequestNo;
 
	}
	
}