package com.biz.mybatis;

import java.io.Reader;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.biz.member.MemberVO;


public class DBMyBaitsManager_copy {
	public SqlSession myBatisconn() {
		String path = "config/mybatis/config-mybatis.xml";
		Reader reader;
		SqlSessionFactory factory = null;
		SqlSession conn = null;
		
		try {
			reader = Resources.getResourceAsReader(path);
			factory = new SqlSessionFactoryBuilder().build(reader);
//			if(factory == null)
//				System.out.println("err");
//			else
//				System.out.println("factory build ok");
			conn = factory.openSession();
			
			MemberVO vo = new MemberVO();
			
//			vo.setUserId("admin");
						
			MemberVO res = conn.selectOne("userNameSpace.login",vo);
			System.out.println(res.getUserName());
			conn.commit();
			
			vo.setUserName("변경");
			vo.setUserPw("111");
			vo.setUserPw("333");
			vo.setUserEmail("dd@dd.com");
			vo.setPpath("c:/upload");
			vo.setPname("aa.jpg");
			int ures = conn.update("userNameSpace.member_update",vo);
			System.out.println(res+"건 수정");
			conn.commit();
			
//			String stringID="lkh";
//			conn.update("userNameSpace.member_delete",stringID);
//			conn.commit();
			
			
			
			MemberVO lvo = new MemberVO();
			lvo.setSearchGubun("user_id");
			lvo.setSearchStr("111");
			
			ArrayList<MemberVO> list = conn.selectOne("userNameSpace.member_list",vo);
			System.out.println(list.size()+"건");
			
			
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void mbClose(SqlSession conn) {
		if(conn != null) conn.close();
	}

	public static void main(String[] args) {
		DBMyBaitsManager_copy db = new DBMyBaitsManager_copy();
		SqlSession conn =db.myBatisconn();
		
		if(conn == null)
		{
			System.out.println("conn null");
		}
		else
		{
			System.out.println("conn ok");
		}
		db.mbClose(conn);
		
		//DI Call Test
//		GenericXmlApplicationContext ctx
//		 = new GenericXmlApplicationContext("classpath:test.xml");
//		TestDAO dao = (TestDAO)ctx.getBean("aaaa");
//		ArrayList<BoardVO> list = dao.boardList(1, 10);
//		System.out.println(list.size() + "건 출력");
	}

}
