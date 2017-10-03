package com.vyom.rasa.integration;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class validate_Response {
	private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.3; rv:36.0) Gecko/20100101 Firefox/36.04";
	// GET action URL, query string appended to the URL as ?stype=models
	private final String urlRaiseRequest = "http://192.168.1.194:8888/aeengine/rest/execute";

	// POST action URL
	private final static String urlPOST = "http://10.41.16.60:5000/parse";

	// Post data or a payload

	public static String inputLine = null;;

	// Main class
	public   String check_response(String msg) throws Exception {

		// POST example URL
		String return_state = "Valid";
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
		String postDataBody = "{\"q\":\"" + msg + "\"}";
		// Payload
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		System.out.println(postDataBody);
		// POST data added to the request as a part of body
		wr.writeBytes(postDataBody);
		wr.flush();
		wr.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String str = null;
		while ((inputLine = in.readLine()) != null) {
			str = inputLine;
		}
		System.out.println(str);
		JSONObject intent = new JSONObject(str).getJSONObject("intent");
		System.out.println("confidence " + intent.get("confidence"));
		System.out.println("name:- " + intent.get("name"));
		in.close();
		if (Double.parseDouble(intent.get("confidence").toString())>=1)
		{
			return "";
		}
		else
		{
			return intent.get("name").toString();
         }}
/*
		JSONObject entities = null;
		JSONArray user, pwd;
		String user_id = "";
		String password = "";

		try {
			entities = new JSONObject(new JSONObject(str).get("entities").toString().replace("[", "").replace("]", ""));
			System.out.println(entities);
			user = new JSONArray(new JSONObject(str).get("entities").toString());
			user_id = new JSONObject(user.get(0).toString()).get("value").toString();
			pwd = new JSONArray(new JSONObject(str).get("entities").toString());
			password = new JSONObject(pwd.get(1).toString()).get("value").toString();
		} catch (Exception e) {
		}
		System.out.println("UserID " + user_id);
		System.out.println("Passowrd " + password);
		try {
			if (user_id.toString()=="" &&password.toString()=="") {

				return_state = "please enter user_id and password";

			} else if (user_id.toString()=="") {

				return_state = "please enter user_id";
			} else if (password.toString()=="") {
				return_state = "please enter password";
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
		}
		return return_state;

	}
	/*
	public static void main(String[] args) throws Exception {
		String resp_msg = null;
		validate_Response valid = new validate_Response();
		try {
			 //resp_msg=valid.check_response("reset my password, user_id is pradnya.pujari@vyomlabs.com and password is asdfg@123");
			//resp_msg = valid.check_response("reset my password, user_id is pradnya.pujari@vyomlabs.com ");
			resp_msg = valid.check_response("reset my password ");
		} catch (JSONException e) {
			// TODO: handle exception
		}
		System.out.println("Response:" + resp_msg);
	}*/
}
