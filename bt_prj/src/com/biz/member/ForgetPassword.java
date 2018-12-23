package com.biz.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.biz.shop.ShopDAO;

/**
 * Servlet implementation class ForgetPassword
 */
@WebServlet("/forget")
public class ForgetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ForgetPassword() {
        super();
    }
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		
		//pw 랜덤설정----------------------------
        //0~9까지 생성 *10
        int rnum1 = (int)(Math.random() *10);
        int rnum2 = (int)(Math.random() *10);
        int rnum3 = (int)(Math.random() *10);
        int rnum4 = (int)(Math.random() *10);
        
        String newPw = "!@"+rnum1+rnum2+rnum3+rnum4;
        System.out.println(newPw);
		
		String subject = "<BR_PRJ>변경 된 비밀번호 알림";
		
		MemberDAO dao = new MemberDAO();
		MemberVO vo = new MemberVO();
		vo.setUserEmail(email);
		vo.setUserPw(newPw);
		
		int res = dao.changePassword(vo);
		
		if(res > 0) {
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("<html>");
		buffer.append("<head>");
		buffer.append("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
		buffer.append("<title>Insert title here</title>");
		buffer.append("</head>");
		buffer.append("<body>");
		buffer.append("<table width='200'>");
		buffer.append("<tr><td><img src='http://www.ikosmo.co.kr/userfile/main_list/1523413562121866.jpg'></td></tr>");         
		buffer.append("<tr><td>비밀번호 확인</td></tr>");
		buffer.append("</table>");
		buffer.append("</body>");
		buffer.append("</html>");
		
		SendMailTest g = new SendMailTest();
		g.SendMail(email, subject, buffer.toString());
		} else {
			response.sendRedirect("404.jsp");
		}
		
	}

}
