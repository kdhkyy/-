package com.biz.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PwCheckServlet
 */
@WebServlet("/pwcheck")
public class PwCheckServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PwCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      HttpSession session = request.getSession();
      if(session.getAttribute("SESS_ID") == null){
         response.sendRedirect("login.jsp");
         return;
      }else {
         
         String sess_id = session.getAttribute("SESS_ID").toString();               
         String sess_name = session.getAttribute("SESS_NAME").toString();         
         String sess_email = session.getAttribute("SESS_EMAIL").toString();
         String sess_pw = session.getAttribute("SESS_PW").toString();
         
         //String sess_profile_img = session.setAttribute("SESS_PROFILE_IMG", "2018110515143");
         //request.setAttribute("REQ_PNT","1000");
         
            
         MemberDAO mdao = new MemberDAO();
         MemberVO mvo = new MemberVO();
         mvo.setUserId(sess_id);
         mvo.setUserName(sess_name);         
         mvo.setUserEmail(sess_email);         
         mvo.setUserPw(sess_pw);
         mvo = mdao.select(mvo);
         request.setAttribute("KEY_MVO", mvo);
         request.getRequestDispatcher("/pwcheck.jsp").forward(request, response);
      }
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      HttpSession session = request.getSession();
      if(session.getAttribute("SESS_ID") == null){
         response.sendRedirect("login.jsp");
         return;
      }else {
         
         String sess_id = session.getAttribute("SESS_ID").toString();               
         String pw = request.getParameter("userpw");
         
         //String sess_profile_img = session.setAttribute("SESS_PROFILE_IMG", "2018110515143");
         //request.setAttribute("REQ_PNT","1000");
         
            
         MemberDAO mdao = new MemberDAO();
         MemberVO mvo = new MemberVO();
         mvo.setUserId(sess_id);
         mvo.setUserPw(pw);
         mvo = mdao.select(mvo);
         
         if(mvo.getUserGubun() == null) {
            response.sendRedirect("pwcheck.jsp");
         }else {
            
            response.sendRedirect("/edit");
         }
         
         //request.setAttribute("KEY_MVO", mvo);
         //request.getRequestDispatcher("/pwCheck.jsp").forward(request, response);
           
      }
      
      String mode = request.getParameter("mode");
      
      if(mode.equals("del")) {
    	  response.sendRedirect("/del");
      } else {
    	  response.sendRedirect("/edit");  
      }
      
   } 
      

}