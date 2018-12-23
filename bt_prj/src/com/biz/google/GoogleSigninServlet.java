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
@WebServlet("/google_auth")
public class GoogleSigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoogleSigninServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		https://accounts.google.com/o/oauth2/v2/auth
			
		String client_id = "879984487341-7imoijvsj2hha8se0nvdiiruf52bdr8e.apps.googleusercontent.com";
		String redirect_uri = "http%3A%2F%2Flocalhost%3A8015%2Fgoogle_redirect";
		String scope = "https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fcalendar.readonly";
		String access_type = "offline";
		String response_type = "code";
	}

}
