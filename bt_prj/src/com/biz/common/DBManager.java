package com.biz.common;

import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBManager {
	
	public SqlSession dbCon() {
		String path = "config/mybatis/config-mybatis.xml";
		Reader reader;
		SqlSessionFactory factory = null;
		SqlSession conn = null;
	
		try {
			reader = Resources.getResourceAsReader(path);
			factory = new SqlSessionFactoryBuilder().build(reader);
			conn = factory.openSession();			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public void dbClose(Connection conn) {
		try {
			//반드시 만든 순서에서 역순으로 close 해야 함.
			//if(rs != null) rs.close();
			//if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	

}
