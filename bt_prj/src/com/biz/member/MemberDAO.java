package com.biz.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.biz.common.MyBatisFactory;
import com.biz.shop.ShopVO;

public class MemberDAO {
	
	public int changePassword(MemberVO vo) {
		SqlSession conn =null;
		int res=0;
		try {
			//singleton : factory instance의 중앙관리
			//factory instance : private static
			//public getFactory()를 통해 가져감

			conn  = MyBatisFactory.getFactory().openSession();
			res = conn.update("userNameSpace.", vo);
			conn.commit();

		}finally {
			conn.close();
		}
		return res;
	}


   public int delete(String id) {
      SqlSession conn =null;
      int res=0;
      try {
         //singleton : factory instance의 중앙관리
         //factory instance : private static
         //public getFactory()를 통해 가져감
   
         conn  = MyBatisFactory.getFactory().openSession();
         res = conn.insert("member_delete", id);
         conn.commit();
   
      }finally {
         conn.close();
      }
      return res;
   }
   
   public int update(MemberVO mvo) {
      SqlSession conn =null;
      int res=0;
      try {
         //singleton : factory instance의 중앙관리
         //factory instance : private static
         //public getFactory()를 통해 가져감
   
         conn  = MyBatisFactory.getFactory().openSession();
         res = conn.insert("member_update", mvo);
         conn.commit();
   
      }finally {
         conn.close();
      }
      return res;
   }
   
   public int insert(MemberVO mvo) {
   
      SqlSession conn =null;
      int res=0;
      try {
         //singleton : factory instance의 중앙관리
         //factory instance : private static
         //public getFactory()를 통해 가져감
   
         conn  = MyBatisFactory.getFactory().openSession();
         res = conn.insert("member_register", mvo);
         conn.commit();
   
      }finally {
         conn.close();
      }
      return res;
   }
   
   public MemberVO select(MemberVO mvo) {
      SqlSession conn =null;
      try {
         conn = MyBatisFactory.getFactory().openSession();
         if(mvo.getUserPw() == null) {
            mvo = conn.selectOne("member_detail", mvo);
            conn.commit();
         }else {
            mvo = conn.selectOne("login", mvo);
            conn.commit();
         }         
      } finally {
         conn.close();
      }
      return mvo;
   }
}