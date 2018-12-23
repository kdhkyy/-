package com.biz.google;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GoogleSigninServlet
 */
@WebServlet("/google_redirect")
public class GoogleRedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoogleRedirectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 직접 소켓 통신(JAVA jdk)
		//2. Google API Client Libraries 
		
//			https://accounts.google.com/o/oauth2/v2/auth
//			client_id = "879984487341-7imoijvsj2hha8se0nvdiiruf52bdr8e.apps.googleusercontent.com"
//			redirect_uri = "http%3A%2F%2Flocalhost%3A8015%2Fgoogle_redirect"
//			scope = "https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fcalendar.readonly"
//			access_type = "offline"
//			response_type = "code"
			
//			https://accounts.google.com/o/oauth2/v2/auth?
//			String scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fdrive.metadata.readonly;
//			String access_type=offline;
//			String include_granted_scopes=true;
//			String state=state_parameter_passthrough_value&
//			String redirect_uri=http%3A%2F%2Foauth2.example.com%2Fcallback&
//			String response_type=code&
			String client_id="client_id";
		
//			https://accounts.google.com/o/oauth2/v2/auth
		
			String googleURL = "879984487341-7imoijvsj2hha8se0nvdiiruf52bdr8e.apps.googleusercontent.com";
			String redirect_uri = "http://localhost:8015/google_redirect";
			String scope = "https://www.googleapis.com/auth/calendar.readonly";
			String access_type = "offline";
			String response_type = "code";
		
			String urlStr = "https://";
			
			Map<String,Object> map = new LinkedHashMap<>();
			map.put("scope", scope);
			map.put("access_type", access_type);
			map.put("include_granted_scopes", true);
			map.put("redirect_uri", redirect_uri);
			map.put("response_type",response_type);
			map.put("client_id",client_id);
			
			//map데이터를 꺼내 k-v&k2=m& 문장생성			
	        StringBuilder buffer = new StringBuilder();
	        for (Map.Entry<String,Object> param : map.entrySet()) {
	            if (buffer.length() != 0) buffer.append('&');
	            buffer.append(URLEncoder.encode(param.getKey(), "UTF-8"));
	            buffer.append('=');
	            buffer.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
	        }
		 											
			//String >> Byte[]로 변경
			byte[] postDataBytes = buffer.toString().getBytes("UTF-8");
			//byte[] postDataBytes = postData.toString().getBytes("UTF-8");
	        
			URL url = new URL(googleURL); 
	
    		HttpURLConnection connection = (HttpURLConnection) url.openConnection();           
    		connection.setDoOutput(true); 
    		connection.setInstanceFollowRedirects(false); 
    		connection.setRequestMethod("POST"); 
    		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
    		connection.setRequestProperty("charset", "utf-8");
    		connection.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
    		connection.connect();
    		connection.getOutputStream().write(postDataBytes); //http body에 write == POST
		
    		
    		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
    		
    		String line = "";
            if ((line = in.readLine()) != null) {
                System.out.print(line);
            }
            
            //code <<동의서 코드가 오는지>>
            //apikey
            //securitykey
            //client_id
            // ----> access_token
	}

}
