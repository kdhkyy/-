//package com.biz.common;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class DBManager_copy {
//	
//	public Connection dbCon() {
//		Connection conn = null;
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver"); //orecleDriver를 불러오면 oracle에 있는 걸 한번에 static해줌
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		
//		try {
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "kosmo", "0000"); //getconnection은 private 여서 new 해줄 수없음
//			if(conn != null)
//				System.out.println("conn success");
//			else
//				//오타, jdbc6.jar, 네트워크 회선/권한문제로 인하여 오류가 날 수도 있음.
//				System.out.println("conn faild");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return conn; 
//
//	}
//	
//	public void dbClose(Connection conn) {
//		try {
//			//반드시 만든 순서에서 역순으로 close 해야 함.
//			//if(rs != null) rs.close();
//			//if(pstmt != null) pstmt.close();
//			if(conn != null) conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//	}
//	
//
//}
