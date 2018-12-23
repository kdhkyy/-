package com.biz.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
//-------------------------------------
// Setting
//-------------------------------------
//
//자바프로젝트 우클릭/프로퍼티즈/자바 빌드 패스/Lib탭/jre보임

import com.kosmo.web.Ch99JDBC;

public class Ch99JDBCImpl implements Ch99JDBC {
	//ODBC : windows에서 사용되는 DB와 연결
	//JDBC(Java Database Connectivity )
	//    : Driver
	//    : jar(java Archive(압축)) = zip파일
	//    : API (메서드 기능의 묶음)
	//목적 : Java와 DB를 연결
	
	public ArrayList<EmpVO> empList(String serchColumn, Object serchValue){

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<EmpVO> list = new ArrayList<EmpVO>();	
		DBManager db = new DBManager();
		

		try {
			conn = db.dbCon();
			String sql = "select * from emp where "+serchColumn+" =?"; // ?=? 조건값
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setObject(1, serchValue);  //바인딩(=?) 첫 번째 물음표가  serchValue 인 것을 찾는다. 바인딩은 "값"을 바인딩한다. "컬럼"은 바인딩하지 않는다.
			rs = pstmt.executeQuery();
			
			while(rs.next()) { // 결과가 있는 한 계속해서 읽음 
				EmpVO evo = new EmpVO(); //while문 밖에다 만들경우 같은 자리에 값이 들어가기 때문에 맨 마지막 값만 출력함
				evo.setEname(rs.getString("ename"));
				evo.setDeptno(rs.getInt("deptno"));
				evo.setJob(rs.getString("job"));
				
				list.add(evo);
			
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
			}

		return list;

		
	}

//	public static void main(String[] args) {
	public ArrayList<EmpVO> empList1() {
				
		//try안에서 new하면 finally에서 쓸 수 없기때문에 try 밖에서 초기화 해준다.
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<EmpVO> list = new ArrayList<EmpVO>();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //orecleDriver를 불러오면 oracle에 있는 걸 한번에 static해줌
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		

		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "kosmo", "0000"); //getconnection은 private 여서 new 해줄 수없음
			if(conn != null)
				System.out.println("conn success");
			else
				//오타, jdbc6.jar, 네트워크 회선/권한문제로 인하여 오류가 날 수도 있음.
				System.out.println("conn faild");

			String sql = "select empno, ename from emp"; // String은 이 안에서만 사용할꺼기 때문에 위에서 초기화 해주지 않아도 상관없다.
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) { // 결과가 있는 한 계속해서 읽음 
				EmpVO evo = new EmpVO(); //while문 밖에다 만들경우 같은 자리에 값이 들어가기 때문에 맨 마지막 값만 출력함
				evo.setEmpno(rs.getInt("empno"));
				evo.setEname(rs.getString("ename"));
				list.add(evo);
				
//				int v1 = rs.getInt("empno"); //컬럼이름으로 접근하는것이 효율적이다
//				String v2 = rs.getString("ename");		
//				System.out.println(v1 + "\t" + v2);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//반드시 만든 순서에서 역순으로 close 해야 함.
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return list;

	}
	
	
	//입력
	public int empInsert(int prmEmpno, String prmEname) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DBManager db = new DBManager();
		int res = 0;
		
		try {
			conn = db.dbCon();
			String sql = "insert into emp(empno, ename) values (?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, prmEmpno);
			pstmt.setString(2, prmEname);
			res = pstmt.executeUpdate();
	
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			db.dbClose(rs, pstmt, conn);
		}
		return res;
	}
	
		
	//수정
	public int empUpdate(int prmSal, int prmEmpno) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			DBManager db = new DBManager();
			int res = 0;
			
			try {
				conn = db.dbCon();
				String sql = "update emp set sal=? where empno=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, prmSal);
				pstmt.setInt(2, prmEmpno);
				res = pstmt.executeUpdate();
		
				
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				db.dbClose(rs, pstmt, conn);
			}
			return res;

	}
	
	//삭제
	public int empDelete(int prmEmpno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DBManager db = new DBManager();
		int res = 0;
		
		try {
			conn = db.dbCon();
			String sql = "delete from emp where empno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, prmEmpno);
			res = pstmt.executeUpdate();
	
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			db.dbClose(rs, pstmt, conn);
		}
		return res;

	}
	//-----------------------------------------------------------
	//SWING JTABLE 용도 
	//-----------------------------------------------------------
	public ArrayList<Vector> empListForSwing(){

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DBManager db = new DBManager();
		

		ArrayList<Vector> list = new ArrayList<Vector>();
		try {
			conn = db.dbCon();
			String sql = "select * from emp"; // ?=? 조건값
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) { // 결과가 있는 한 계속해서 읽음 
				Vector<Object> vt = new Vector<Object>();
				vt.addElement(rs.getInt("empno"));
				vt.addElement(rs.getString("ename"));
				vt.addElement(rs.getString("job"));
				vt.addElement(rs.getInt("deptno"));
				
				list.add(vt);
		}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
			}

		return list;

		
	}

	//---------------------------------------------------------------
	//SWING UPDATE용도
	//---------------------------------------------------------------
	
	public int empUpdate(EmpVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int res = 0;
		DBManager db = new DBManager();
		
		
		try {
			conn = db.dbCon();
			String sql = "update emp set ename=?, job=?, deptno=? where empno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getEname());
			pstmt.setString(2, vo.getJob());
			pstmt.setInt(2, vo.getDeptno());
			pstmt.setInt(2, vo.getEmpno());
			res = pstmt.executeUpdate();
	
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return res;

}
	
	public ArrayList<EmpVO> empList(){

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<EmpVO> list = new ArrayList<EmpVO>();	
		DBManager db = new DBManager();
		

		try {
			conn = db.dbCon();
			String sql = "select empno, ename, to_char(hiredate,'YYYY-MM-DD') hiredate from emp";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) { // 결과가 있는 한 계속해서 읽음 
				EmpVO evo = new EmpVO(); //while문 밖에다 만들경우 같은 자리에 값이 들어가기 때문에 맨 마지막 값만 출력함
				evo.setEmpno(rs.getInt("empno"));
				evo.setEname(rs.getString("ename"));
				evo.setHiredate(rs.getString("hiredate"));
				
				list.add(evo);
			
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
			}

		return list;

		
	}


	public ArrayList<EmpVO> empList2(Object text) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<EmpVO> list = new ArrayList<EmpVO>();	
		DBManager db = new DBManager();
		

		try {
			conn = db.dbCon();
			String sql = "select * from emp where empno =?"; // ?=? 조건값
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setObject(1, text);  //바인딩(=?) 첫 번째 물음표가  serchValue 인 것을 찾는다. 바인딩은 "값"을 바인딩한다. "컬럼"은 바인딩하지 않는다.
			rs = pstmt.executeQuery();
			
			while(rs.next()) { // 결과가 있는 한 계속해서 읽음 
				EmpVO evo = new EmpVO(); //while문 밖에다 만들경우 같은 자리에 값이 들어가기 때문에 맨 마지막 값만 출력함
				evo.setEname(rs.getString("ename"));
				evo.setDeptno(rs.getInt("deptno"));
				evo.setJob(rs.getString("job"));
				
				list.add(evo);
			
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
			}

		return list;
	}
	
}

