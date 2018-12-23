package com.biz.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public interface Ch99JDBC {
	//Create (insert) 
	//Read (select) 
	//Update (update) 
	//Delete (delete)
//	public abstract ArrayList<EmpVO> empList();
	public ArrayList<EmpVO> empList(String serchColumn, Object serchValue);
	
	public int empInsert(int prmEmpno, String prmEname) ;
	
	public int empUpdate(int prmSal, int prmEmpno);
	
	public int empDelete(int prmEmpno);
	
	
}