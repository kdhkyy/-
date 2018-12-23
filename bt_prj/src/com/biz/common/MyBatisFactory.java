package com.biz.common;

import java.io.IOException;
import java.io.Reader;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisFactory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static SqlSessionFactory factory = null;



    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
    	
    	String path = "./config/mybatis/config-mybatis.xml";
    	String path2 = "./config/mybatis/config-mybatis.xml";
		Reader reader;
		SqlSession conn = null;
	
		try {
			reader = Resources.getResourceAsReader(path2);
			factory = new SqlSessionFactoryBuilder().build(reader);
			conn = factory.openSession();			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
       	
//		System.out.println("init 호출");
//		String size = config.getInitParameter("MAX_SIZE");
//		String updir = config.getInitParameter("UPDIR");
//		System.out.println(size+","+updir);
//		
//		
//		ServletContext ctx = config.getServletContext();
//	    String comm = ctx.getInitParameter("COMMON");
//	    System.out.println("COMMON" + comm);   
		
	}
    
    public static SqlSessionFactory getFactory() {
    	return factory;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
