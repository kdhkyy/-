package com.biz.craw;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.biz.common.JsoupTest;


/**
 * Servlet implementation class YTNCrawServlet
 */
@WebServlet("/ytn")
public class YTNCrawServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
  

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub      
      response.setContentType("text/html; charset=UTF-8");
      JsoupTest soup = new JsoupTest();
      String url = "http://www.ytn.co.kr/photo/photo_list_1406.html";
      String selector="div#ytn_list_v2014 dl.photo_list";
      //int res = soup.ytnCrawling(url, selector);
      //System.out.println(res + "건 클롤링");
      
      ArrayList<YTNVO> list = soup.ytnCrawling(url,selector);
      //System.out.println(list.size() + " 건 크롤링");
      
      PrintWriter out = response.getWriter();
      for(YTNVO yvo : list) {
         out.println("<a href='"+yvo.getUrl()+"'>" + yvo.getTitle());
         out.println("<img width='100' height='100' src='"+yvo.getImgsrc()+"'><br>");
      }
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}