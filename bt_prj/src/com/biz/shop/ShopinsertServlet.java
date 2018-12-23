package com.biz.shop;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.biz.common.DBManager;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.Part;

@WebServlet("/shop_insert") 	
public class ShopinsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String saveDirectory = "C:/uploads";
		int maxPostSize = 1024 * 1024 * 10; //10M
		String encoding = "UTF-8";
		FileRenamePolicy policy = new DefaultFileRenamePolicy();
		
		//--------------------------------------------
		// * c:/uploads 폴더에 파일카피 input/output Stream
		// * 중복파일 rename
		//--------------------------------------------
		MultipartRequest mrequest = new MultipartRequest(request,saveDirectory,maxPostSize,encoding,policy);
		
		//--------------------------------------------
		// * shop_info 관련 파라미터
		//--------------------------------------------
		String sname = mrequest.getParameter("sname");
		String placename = mrequest.getParameter("placename");
		String lat = mrequest.getParameter("lat");
		String lng = mrequest.getParameter("lng");
		String sinfo = mrequest.getParameter("sinfo");
		
	/*	
		//--------------------------------------------
		// * shop_pic 관련  멀티첨부파일 파라미터
		// * input type=pname1
		// * input type=pname2 ...
		//--------------------------------------------
		ArrayList<ShopPicVO> pvolist  = new ArrayList<ShopPicVO>();
		ShopPicVO pvo  = new ShopPicVO();
		Enumeration formNames = mrequest.getFileNames();
		while(formNames.hasMoreElements()) {
			String inputTagNames = (String)formNames.nextElement();
			//원본파일명
			String origPnames = mrequest.getOriginalFileName(inputTagNames);
			
			if(origPnames != null) {
				//중복 시 리네임된 파일명
				String sysPnames = mrequest.getFilesystemName(inputTagNames);
				//파일크기
				//File pfiles = mrequest.getFile(inputTagNames); 
				//long attachFileSizes = pfiles.length();		
				
				pvo.setPname(origPnames);
				pvo.setPpath(saveDirectory); 
				if(pvolist.size() == 0) {
					pvo.setPyn("y");
				} else {
					pvo.setPyn("n");
				}
				pvolist.add(pvo);
			}
		}
				*/
		
		
		/* ---------------------------------------------------------------
         * 멀티파일업로드 
         * <input type="file" name="files[]" multiple>  name 1개로 여러 파일 올릴 경우
         * ---------------------------------------------------------------
         */
		 //MultipartParser parser = new MultipartParser(request, 1024 * 1024 * 10);
		 MultipartParser parser = new MultipartParser(request, 1024 * 1024 * 10, false, false, "UTF-8"); 
		 
         Part part = null;
        
         while ((part = parser.readNextPart()) != null) {
        	 
        	 
             if (part.isFile()) {
                 FilePart fPart = (FilePart) part;  
                 String name = fPart.getFileName();
                 if (name != null) {
                     long fileSize = fPart.writeTo(new File("c:/uploads"));
                     System.out.println(fPart.getFilePath() + "," + fileSize );
                 } else {
                     System.out.println("error");
                 }
             }
         }// end while 
		
		/*//--------------------------------------------
		// * DB Connection 
		//--------------------------------------------
		DBManager db = new DBManager();
		Connection conn = db.dbCon();
		try {
		conn.setAutoCommit(false); //conn에 의한 모든 트랜잭션이 정상적으로 처리된 경우만  commit함
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		//--------------------------------------------
		// 1. CURRVAL+1 SSEQ 가져오기
		//--------------------------------------------
		ShopDAO dao = new ShopDAO();	
		int next_sseq = dao.selectNextSseq(conn);
		
		if(next_sseq > 0) {
			try {
			//--------------------------------------------
			// 2. DB저장작업
			//    SHOP_INFO 테이블 정보 입력 : 1번 입력
			//--------------------------------------------
			ShopVO svo = new ShopVO();
			svo.setSseq(next_sseq);
			svo.setSname(sname);
			svo.setSinfo(sinfo);
			svo.setLat(lat);
			svo.setLng(lng);
			svo.setPlacename(placename);
			int infoInsertRes = dao.insertShopInfo(svo, conn);
			
			//--------------------------------------------
			// 3. DB저장작업
			//    SHOP_PIC 테이블 정보 입력 : pvolist.size()번 입력
			//--------------------------------------------
			if(infoInsertRes > 0) {
				pvo.setSseq(next_sseq);
				int shopPicInsertRes = 0;
				for(int i=0; i<pvolist.size(); i++) {
					shopPicInsertRes = dao.insertShopPic(pvolist.get(i), conn);
				}
			} 	
		} catch (Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
			
			try {
				conn.commit();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		//--------------------------------------------
		// * DB close
		//--------------------------------------------
		db.dbClose(null, null, conn);
			*/
		
		
//		-----------------------------------------------		
//		cos.jar를 이용한 단일 파일 업로드 처리 
//		-----------------------------------------------
//		String origPname = mrequest.getOriginalFileName("pname");
//		//3.File 객체를 이용한 파일명 
//		File pfile = mrequest.getFile("pname");
//		String filePname  = pfile.getName(); 	
//				
//		//중복 시 리네임된 파일명
//		String sysPname = mrequest.getFilesystemName("pname");
//		
//		//파일크기
//		long attachFileSize = pfile.length();		
//		
//		String attachFileContentType = mrequest.getContentType("pname");
//		
//		//파일 확장자 처리
//		String attachFileExt = "jpg";
//		if(origPname.lastIndexOf(".") != -1) {
//			attachFileExt = origPname.substring(origPname.lastIndexOf(".")+1);
//		}
//		if(!attachFileExt.toUpperCase().equals("JPG") &&
//				!attachFileExt.toUpperCase().equals("PNG") &&
//				!attachFileExt.toUpperCase().equals("GIF")
//		) {
//			response.setContentType("text/html; charset=UTF-8");
//			PrintWriter out = response.getWriter();
//			out.println("<script>alert('이미지 첨부만 가능')</script>");
//			//response.sendRedirect("shop_form.jsp");
//		}
			
		
	}

}

