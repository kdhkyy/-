package com.biz.shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.biz.common.DBManager;
import com.sun.org.apache.regexp.internal.RE;

public class ShopDAO {

	//	ArrayList<ShopVO>	select	()	
	//	ShopVO	select	(int seq)	
	//	int	insert	(ShopVO svo)	
	//	int	update	(ShopVO svo)	
	//	int	delete	(int seq)	
	
	
	//------------------------------------------------------
	public int selectNextSseq(Connection PRMCONN) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int next_sseq = 0;
		try {
			//String sql = "select shop_info_seq.currval+1 as sseq from dual";
			String sql = "select max(sseq)+1 as sseq from shop_info";
			System.out.println(sql);
			pstmt = PRMCONN.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				next_sseq = rs.getInt("sseq");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				pstmt.close();
				rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return next_sseq;
	}
	
	public int	insertShopInfo(ShopVO svo , Connection PRMCONN) {
		PreparedStatement pstmt = null;
		int res = 0;
		try {
			StringBuffer sqlBuffer = new StringBuffer();
			
			sqlBuffer.append("insert into shop_info(sseq,sname,sinfo,lat,lng,regid,regdate,placename) ");
			sqlBuffer.append("values(?,?,?,?,?,?,sysdate,?) ");
			                //values(next_sseq,?,?,?,?,?,sysdate,?)
			System.out.println(sqlBuffer.toString());

			pstmt = PRMCONN.prepareStatement(sqlBuffer.toString());
			pstmt.setInt(1, svo.getSseq());
			pstmt.setString(2, svo.getSname());
			pstmt.setString(3, svo.getSinfo());
			pstmt.setObject(4, svo.getLat());
			pstmt.setObject(5, svo.getLng());
			pstmt.setString(6, svo.getRegid());
			pstmt.setString(7, svo.getPlacename());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return res;
	}
	
	public int	insertShopPic(ShopPicVO pvo, Connection PRMCONN) {
		PreparedStatement pstmt = null;
		int res = 0;
		try {
			
			StringBuffer sqlBuffer = new StringBuffer();
			sqlBuffer.append("insert into shop_pic(pseq, ppath, pname,sseq,pyn) ");
			sqlBuffer.append("values(shop_pic_seq.nextval,?,?,?,?) "); 
							//values(shop_pic_seq.nextval,?,?,next_sseq,?)
			System.out.println(sqlBuffer.toString());
			pstmt = PRMCONN.prepareStatement(sqlBuffer.toString());
			
			pstmt.setString(1, pvo.getPpath());
			pstmt.setString(2, pvo.getPname());
			pstmt.setInt(3, pvo.getSseq());
			pstmt.setString(4, pvo.getPyn());
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return res;
	}
	//------------------------------------------------------
	
	
	
	
	
	
	public ArrayList<ShopPicVO> selectShopPic(int sseq) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ShopPicVO> list = new ArrayList<ShopPicVO>(); 
		DBManager db = new DBManager();

		try {
			conn = db.dbCon();

			StringBuffer sqlBuffer = new StringBuffer();
			sqlBuffer.append("select pseq, ");
			sqlBuffer.append("nvl(ppath,'c:/uploads') as ppath, "); 
			sqlBuffer.append("nvl(pname, '000.png') as pname ");
			sqlBuffer.append("from shop_pic ");
			sqlBuffer.append("where sseq = ? ");
			sqlBuffer.append("order by pseq desc ");

			System.out.println(sqlBuffer.toString());

			pstmt = conn.prepareStatement(sqlBuffer.toString());
			pstmt.setInt(1, sseq);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ShopPicVO pvo = new ShopPicVO();
				pvo.setPseq(rs.getInt("pseq"));
				pvo.setPpath(rs.getString("ppath"));
				pvo.setPname(rs.getString("pname"));
				list.add(pvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//				db.dbClose(rs, pstmt, conn);
		}
		return list;

	}


	public ShopVO selectShopInfo(int sseq) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ShopVO svo = new ShopVO(); 
		DBManager db = new DBManager();

		try {
			conn = db.dbCon();

			StringBuffer sqlBuffer = new StringBuffer();
			sqlBuffer.append("select i.sseq, i.sname, i.sinfo, i.lat, i.lng ");
			sqlBuffer.append("from shop_info i ");
			sqlBuffer.append("where i.sseq=? ");


			System.out.println(sqlBuffer.toString());

			pstmt = conn.prepareStatement(sqlBuffer.toString());
			pstmt.setObject(1, sseq);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				svo.setSseq(rs.getInt("sseq"));
				svo.setSname(rs.getString("sname"));
				svo.setSinfo(rs.getString("sinfo"));
				svo.setLat(rs.getString("lat"));
				svo.setLng(rs.getString("lng"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//			db.dbClose(rs, pstmt, conn);
		}
		return svo;
	}

	public ArrayList<ShopVO> select(ShopVO vo) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ShopVO> list = new ArrayList<ShopVO>(); 
		DBManager db = new DBManager();

		try {
			conn = db.dbCon();
			
			StringBuffer sqlBuffer = new StringBuffer();
			sqlBuffer.append("select t.* ");
			sqlBuffer.append("from( ");
			sqlBuffer.append("select i.sseq, i.sname, i.sinfo, i.lat, i.lng, ");
			sqlBuffer.append("nvl(p.ppath,'c:/uploads') as ppath, "); 
			sqlBuffer.append("nvl(p.pname, '000.png') as pname, ");
			sqlBuffer.append("calc_distance(i.lat, i.lng, ?, ?) as distance ");
			sqlBuffer.append("from shop_info i, shop_pic p ");
			sqlBuffer.append("where i.sseq = p.sseq(+) ");
			sqlBuffer.append("     and p.pyn(+) = 'y' ");
			sqlBuffer.append("order by sseq desc ");
			sqlBuffer.append(") t ");
			sqlBuffer.append("where rownum <= ? ");
			
			System.out.println(sqlBuffer.toString());

			pstmt = conn.prepareStatement(sqlBuffer.toString());
			pstmt.setObject(1, vo.getLat());
			pstmt.setObject(2, vo.getLng());
			pstmt.setInt(3, vo.getTopn());
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ShopVO svo = new ShopVO();
				svo.setSseq(rs.getInt("sseq"));
				svo.setSname(rs.getString("sname"));
				svo.setSinfo(rs.getString("sinfo"));
				svo.setPpath(rs.getString("ppath"));
				svo.setPname(rs.getString("pname"));
				svo.setDistance(rs.getDouble("distance"));
				svo.setLat(rs.getString("lat"));
				svo.setLng(rs.getString("lng"));
				list.add(svo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//			db.dbClose(rs, pstmt, conn);
		}
		return list;
	}
}
