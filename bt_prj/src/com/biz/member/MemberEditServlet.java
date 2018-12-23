package com.biz.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MemberEditServlet
 */
@WebServlet("/edit")
public class MemberEditServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
    
    //DB회원정보 조회후 가지고 /prrofile.jsp로 이동
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      HttpSession session = request.getSession();
      if(session.getAttribute("SESS_ID") == null){
         response.sendRedirect("login.jsp");
         return;
      }else {
         
         String sess_id = session.getAttribute("SESS_ID").toString();               
         
         
         //String sess_profile_img = session.setAttribute("SESS_PROFILE_IMG", "2018110515143");
         //request.setAttribute("REQ_PNT","1000");
         
            
         MemberDAO mdao = new MemberDAO();
         MemberVO mvo = new MemberVO();
         //mvo.setUser_id(sess_id);      
         //mvo = mdao.select(mvo);
         
         request.getRequestDispatcher("/profile.jsp").forward(request, response);
      }
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   //수정버튼을 클릭 시 DB 수정작업 
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      
      HttpSession session = request.getSession();
      if(session.getAttribute("SESS_ID") == null){
         response.sendRedirect("login.jsp");
         return;
      }else {
         
         String sess_id = session.getAttribute("SESS_ID").toString();
         String userpw = request.getParameter("user_pw");
         String username = request.getParameter("user_name");
         
         
         System.out.println(userpw+ "" + username +""+ sess_id);
         
         //String sess_profile_img = session.setAttribute("SESS_PROFILE_IMG", "2018110515143");
         //request.setAttribute("REQ_PNT","1000");
         
            
         MemberDAO mdao = new MemberDAO();
         MemberVO mvo = new MemberVO();
         mvo.setUserId(sess_id);
         mvo.setUserPw(userpw);
         mvo.setUserName(username);
         int res = 0;
         
         res = mdao.update(mvo);
         
         if(res == 0) {
            response.sendRedirect("404.jsp");
         }else{
            response.sendRedirect("index.jsp");
         }
         
      }
      
      
      
   }

}