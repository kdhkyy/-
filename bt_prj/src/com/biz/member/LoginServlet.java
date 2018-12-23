package com.biz.member;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.biz.shop.ShopDAO;
import com.biz.shop.ShopPicVO;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
 

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   // 로그아웃
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      System.out.println("logout");
      //logger.info("my service");
      HttpSession session = request.getSession();
      session.invalidate();
      session.setMaxInactiveInterval(0);
      response.sendRedirect("index.jsp");
      /*request.getRequestDispatcher("http://www.google.com").forward(request, response);*/ //내 프로젝트의 자원을 가져가기때문에 못가져옴
      
   }

   
   
   //로그인
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      String userid = request.getParameter("userid");
      String userpw = request.getParameter("userpw");
      
            MemberDAO dao = new MemberDAO();            
            MemberVO mvo = new MemberVO();
            
            mvo.setUserId(userid);
            mvo.setUserPw(userpw);
            
            mvo = dao.select(mvo);
            System.out.println(mvo.getUserSeq());
            System.out.println(mvo.getUserId());
            System.out.println(mvo.getUserPw());
            System.out.println(mvo.getUserName());
            System.out.println(mvo.getRegdate());
            System.out.println(mvo.getUserEmail());
            System.out.println(mvo.getUserDel());
            System.out.println(mvo.getUserGubun());
            
            if(mvo.getUserGubun() == null || mvo.getUserGubun().equals("")){
               response.sendRedirect("404.jsp");
            }else {
               
               HttpSession session = request.getSession();
               session.setAttribute("SESS_ID", mvo.getUserId());
               session.setAttribute("SESS_NAME", mvo.getUserName());
               session.setAttribute("SESS_GUBUN", mvo.getUserGubun());
               session.setAttribute("SESS_EMAIL", mvo.getUserGubun());
               session.setAttribute("SESS_PW", mvo.getUserPw());
               session.setAttribute("SESS_PROFILE_IMG", "2018110515143");
               request.setAttribute("REQ_PNT","1000");
               
               Cookie cname = new Cookie("COOKIE_NAME", mvo.getUserName());
               Cookie cgubun = new Cookie("COOKIE_GUBUN", mvo.getUserGubun());
               
               cname.setDomain("shj.com");
               cgubun.setDomain("shj.com");
               
               cname.setMaxAge(60*30);
               cgubun.setMaxAge(60*30);
               
               response.addCookie(cname); //쿠키 내려주기
               response.addCookie(cgubun);
               
               
               
               if(mvo.getUserGubun().equals("u")) {
                  //response.sendRedirect("index.jsp");                  
                  request.getRequestDispatcher("/index.jsp").forward(request, response);
               }else if(mvo.getUserGubun().equals("a")) {
                  //response.sendRedirect("admin/admin_main.jsp");
                  request.getRequestDispatcher("/index.jsp").forward(request, response);
               }
            }
                     
   }

}