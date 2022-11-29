
package com.timexmr.timexmr;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class NodeConnection {
	private String ip = "localhost";
	private int port = 38081;
	private URL url = null;
	
	NodeConnection(String ip, int port) throws MalformedURLException{
		this.url = new URL ("http://" + ip + ":" + Integer.toString(port) + "/json_rpc");
		this.ip = ip;
		this.port = port;
	}
	
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public int getPort() {
		return port;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public Boolean testConnection() throws IOException {
		String data = "{\"jsonrpc\":\"2.0\",\"id\":\"0\",\"method\":\"get_version\"}";
		JSONObject jsonObject = this.doRequest(data);
		if(jsonObject == null) {
			return null;
		}
		System.out.println(((JSONObject)jsonObject.get("result")).get("status"));

		if(((JSONObject)jsonObject.get("result")).get("status").equals("OK")) {
			System.out.println("Node up");
			return true;
		}
		else {
			System.out.println("Node down");
			return false;
		}
	}
	
	public Boolean pushTX(String raw_tx) {
		String data = "{\"tx_as_hex\":\"" + raw_tx + "\", \"do_not_relay\":false}";
		JSONObject jsonObject = this.doRequest(data);
		if(jsonObject == null) {
			return null;
		}
		if(((JSONObject)jsonObject.get("result")).get("status").equals("OK")) {
			System.out.println("Node up");
			return true;
		}
		else {
			System.out.println("Node down");
			return false;
		}
	}
	
	private JSONObject doRequest(String jsonString) {
		if(this.ip == "0.0.0.0" && this.port == 0000) {
			return null;
		}else if(true){
			HttpURLConnection http = null;
			try {
				http = (HttpURLConnection)this.url.openConnection();
				http.setRequestMethod("POST");
				http.setDoOutput(true);
				http.setRequestProperty("Accept", "application/json");
				http.setRequestProperty("Content-Type", "application/json");
				
				byte[] out = jsonString.getBytes(StandardCharsets.UTF_8);
				OutputStream stream = http.getOutputStream();
				stream.write(out);
				String theString = IOUtils.toString(http.getInputStream(),StandardCharsets.UTF_8);

			    JSONObject  jsonObject=new JSONObject();
			    JSONParser jsonParser=new  JSONParser();
			    if ((theString != null) && !(theString.isEmpty())) {
			        try {
			            jsonObject=(JSONObject) jsonParser.parse(theString);
			            http.disconnect();
			            return jsonObject;
			        } catch (org.json.simple.parser.ParseException e) {
			        	http.disconnect();
			            e.printStackTrace();
			            return null;
			        }
			    }
			}catch(Exception e){
				http.disconnect();
				System.out.println("Connection error");
				return null;
			}
		}
		return null;
	}
}
