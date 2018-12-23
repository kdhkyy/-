package com.biz.craw;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.biz.common.JsoupTest;

/**
 * Servlet implementation class MangoPlateServlet
 */
@WebServlet("/mango")
public class MangoPlateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MangoPlateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  response.setContentType("text/html; charset=UTF-8");
	      MangoPlate mango = new MangoPlate();
	      String url = "https://www.mangoplate.com/top_lists/1511_mangwondongcafe";
	      String selector="#contents_list ul li";
	      //int res = soup.ytnCrawling(url, selector);
	      //System.out.println(res + "건 클롤링");
	      
	      ArrayList<MangoVO> list = mango.ytnCrawling(url,selector);
	      //System.out.println(list.size() + " 건 크롤링");
	      
	      PrintWriter out = response.getWriter();
	      for(MangoVO yvo : list) {
	         out.println("<a href='" + yvo.getUrl()+"'>" + yvo.getTitle() + "<br>");
	         out.println("<a href='" + yvo.getUrl()+"'>" + yvo.getContent()+"'><br>");
	         out.println("<img width='100' height='100' src='"+yvo.getImgsrc()+"'><br>");
	         
	      }
	      
	}


	//AJAX BEST : post
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html; charset=UTF-8");
	      MangoPlate mango = new MangoPlate();
	      String url = "https://www.mangoplate.com/top_lists/1511_mangwondongcafe";
	      String selector="#contents_list ul li";
	      //int res = soup.ytnCrawling(url, selector);
	      //System.out.println(res + "건 클롤링");
	      
	      ArrayList<MangoVO> list = mango.ytnCrawling(url,selector);
	      //System.out.println(list.size() + " 건 크롤링");
	      
	      PrintWriter out = response.getWriter();
	      for(MangoVO yvo : list) {
	         out.println("<a href='" + yvo.getUrl()+"'>" + yvo.getTitle() + "<br>");
	         out.println("<a href='" + yvo.getUrl()+"'>" + yvo.getContent()+"'><br>");
	         out.println("<img width='100' height='100' src='"+yvo.getImgsrc()+"'><br>");
	         
	      }
	      
	      //json 형태로 결과 내보내기
	}

}
