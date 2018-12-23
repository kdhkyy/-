package com.biz.shop;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kosmo.common.PagingUtil;

/**
 * Servlet implementation class ShopServlet
 */
@WebServlet("/shop")
public class ShopServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    
    if(jsonStr != null || !jsonStr.equals(null)) {
    Gson gson = new Gson();
    ShopVO vo = gson.fromJson(jsonStr, ShopVO.class);
            
    HashMap<String, Object> map = new HashMap<>(); 
	    ShopDAO dao = new ShopDAO();
    vo.setTopn(2);
	    //ArrayList<ShopVO> list = dao.select(vo);
	    ArrayList<ShopVO> list = dao.select(vo);
  
	    if(vo.getTopn() == -1) {
	    	int currentPage = 1;
	    	currentPage = vo.getcurrentPage();
	    	
	    	
	    	//전체게시물수
	    	int totalCnt = dao.selectCount();
	    	
	    	int blockCount = 10;   //한 블럭의 게시물 수
	    	int blockPage = 5;    //한화면에 보여질 블럭 수
	    	PagingUtil page  = new PagingUtil("/shop?", 
	    									  currentPage, 
	    									  totalCnt, 
	    									  blockCount, 
	    									  blockPage);
	    	String pagingHtml = page.getPagingHtml();
	    	request.setAttribute("PAGING", pagingHtml); 
	    	
	    	
	    	//전체목록
	    	
	    	
	    	
	    	 	    	
	    	map.put("MPA_PAGING", pagingHtml);
	    	map.put("MAP_LIST", list);
	    	
	    } else {
	    	
	    	
	    }
    }
	    
    String returnStr = gson.toJson(list);
    response.setContentType("application/json; charset=UTF-8");
    System.out.println("------");
    PrintWriter out = response.getWriter();
    System.out.println(returnStr);
    out.println(returnStr);
    }
    
    
    //오라클에서 위도/경도 계산해서 가지고 오는 방법으로 붙이기.
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jsonStr = request.getParameter("KEY");
        	//{"lat":mylat ,"lng":mylng, "currentPage":1}
        
        if(jsonStr != null || !jsonStr.equals(null)) {
        Gson gson = new Gson();
        ShopVO vo = gson.fromJson(jsonStr, ShopVO.class);
                
        HashMap<String, Object> map = new HashMap<>(); 
  	    ShopDAO dao = new ShopDAO();
        vo.setTopn(2);
  	    //ArrayList<ShopVO> list = dao.select(vo);
  	    ArrayList<ShopVO> list = dao.select(vo);
      
  	    if(vo.getTopn() == -1) {
  	    	int currentPage = 1;
  	    	currentPage = vo.getcurrentPage();
  	    	
  	    	
  	    	//전체게시물수
  	    	int totalCnt = dao.selectCount();
  	    	
  	    	int blockCount = 10;   //한 블럭의 게시물 수
  	    	int blockPage = 5;    //한화면에 보여질 블럭 수
  	    	PagingUtil page  = new PagingUtil("/shop?", currentPage, totalCnt, blockCount, blockPage);
  	    	String pagingHtml = page.getPagingHtml();
  	    	//request.setAttribute("PAGING", pagingHtml); 
  	    	
  	    	
  	    	//전체목록
  	    	
  	    	
  	    	
  	    	 	    	
  	    	map.put("MPA_PAGING", pagingHtml);
  	    	map.put("MAP_LIST", list);
  	    	
  	    } else {
  	    	
  	    	
  	    }
        }
  	    
        String returnStr = gson.toJson(list);
        response.setContentType("application/json; charset=UTF-8");
        System.out.println("------");
        PrintWriter out = response.getWriter();
        System.out.println(returnStr);
        out.println(returnStr);
        		
     }
    
/*   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String jsonStr = request.getParameter("MYKEY");
      Gson gson = new Gson();
      ShopVO vo = gson.fromJson(jsonStr, ShopVO.class);
      
      
	  ShopDAO dao = new ShopDAO();
      ArrayList<ShopVO> list = dao.select(vo);
      request.setAttribute("SHOP_LIST", list);
      request.getRequestDispatcher("index.jsp").forward(request, response);
      
   }*/

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
//   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      ShopDAO dao = new ShopDAO();
//      ArrayList<ShopVO> list = dao.select();
//      request.setAttribute("SHOP_LIST", list);
//      request.getRequestDispatcher("index.jsp").forward(request, response);
//   }

}