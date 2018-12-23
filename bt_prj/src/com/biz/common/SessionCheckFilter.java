package com.biz.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//필터(filter) : 

public class SessionCheckFilter implements Filter
{
	private ArrayList<String> checkingURLList;
	
	//filter의 순서 : init >> doFilter >> destroy
	//초기화(준비할 내용들)
	public void init(FilterConfig config) throws ServletException
	{
		String configUnCheckingURLs = config.getInitParameter("Checking");
		StringTokenizer token = new StringTokenizer(configUnCheckingURLs, ",");

		checkingURLList = new ArrayList();

		while ( token.hasMoreTokens() )
		{
			checkingURLList.add(token.nextToken());
		}
	}
	
	//실행(doFilter)
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException
	{

		// HttpServletRequest < ServletRequest 이기 때문에 httpS..으로 사용하려면 캐스팅 해줘야함. 
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String requestURL = request.getServletPath();

		if ( checkingURLList.contains(requestURL) || requestURL.indexOf("/admin/") != -1){
		
			HttpSession session = request.getSession(false);
			
			//세션 값 유무: 인증(Authentication) 검사
			if ( session == null || session.getAttribute("userId") == null ){
				response.sendRedirect("/login.jsp");
			} else {	//세션값 구분자 : 인가(Authorization) 검사
				String gubun = session.getAttribute("SESS_GUBUN").toString();
				if(gubun.equals("u")) { //구분자가 "u"임에도 불구하고 admin과 관련된 주소에 접근하려고 한다면 401 page로 뱉어내라
					if(requestURL.indexOf("/admin/") != -1) {
						response.sendRedirect("/401.jsp");
					}
			}
				chain.doFilter(req, res);
			}
		} 
		else {	
			chain.doFilter(req, res);
		}
	}

	//정리(해제)	
	public void destroy()
	{
	}
}
