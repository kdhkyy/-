package com.biz.shop;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShopDetailSevlet
 */
@WebServlet("/shop_detail")
public class ShopDetailSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopDetailSevlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String sseqStr = request.getParameter("sseq");
		int sseq = Integer.parseInt(sseqStr);
		ShopDAO dao = new ShopDAO();
		
		//상세정보
		ShopVO svo = dao.select(sseq);
		//이미지정보
		ArrayList<ShopPicVO> spvo = dao.Picselect(sseq);
		if(spvo.size()<=0) {
			ShopPicVO pvo = new ShopPicVO();
			
		}
		
		request.setAttribute("SVO",svo);
		request.setAttribute("LIST_PIC", spvo);
		request.getRequestDispatcher("/shop_detail.jsp").forward(request, response);
				
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
/*	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}*/

}
