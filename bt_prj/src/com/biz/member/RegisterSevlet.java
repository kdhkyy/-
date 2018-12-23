package com.biz.member;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

/**
 * Servlet implementation class RegisterSevlet
 */
@WebServlet("/register")
public class RegisterSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterSevlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String saveDirectory = "C:/uploads/profile";
		int maxPostSize = 1024 * 1024 * 10; //10M
		String encoding = "UTF-8";
		FileRenamePolicy policy = new DefaultFileRenamePolicy();
		
		MultipartRequest mrequest = new MultipartRequest(request,saveDirectory,maxPostSize,encoding,policy);
		
		String uid = mrequest.getParameter("user_id");
		String uname = mrequest.getParameter("user_name");
		String upw = mrequest.getParameter("user_pw");
		String uemail = mrequest.getParameter("user_email");
		String pname = mrequest.getParameter("pname");
		
		MemberDAO dao = new MemberDAO();            
        MemberVO mvo = new MemberVO();
        
        int res = 0;
        int res2 = 0;

        Enumeration formNames = mrequest.getFileNames();
        while(formNames.hasMoreElements()) {
           String inputTagNames = (String)formNames.nextElement();
           //원본파일명
           String origPnames = mrequest.getOriginalFileName(inputTagNames);
           
           if(origPnames != null) {
              
              //중복 시 리네임된 시스템 파일명
              String sysPnames = mrequest.getFilesystemName(inputTagNames);
              //파일크기
              //File pfiles = mrequest.getFile(inputTagNames); 
              //long attachFileSizes = pfiles.length();   
              mvo.setPname(origPnames);
              mvo.setSysname(sysPnames);
              mvo.setPpath(saveDirectory);
           }

        }
        
        
        mvo.setUserId(uid);
        mvo.setUserName(uname);
        mvo.setUserPw(upw);
        mvo.setUserEmail(uemail);
        mvo.setPname(pname);
        mvo.setUserSeq(res2);
        
        res = dao.insert(mvo);

        
        System.out.println(res);
        //response.sendRedirect("index.jsp");                  
        request.getRequestDispatcher("login.jsp").forward(request, response);

		
		
	}

}
