package com.biz.common;

import java.util.ArrayList;

public class Ch999JDBCCall {

	public static void main(String[] args) {
		
		
		Ch99JDBCImpl t = new Ch99JDBCImpl();
//		ArrayList<EmpVO> list = new ArrayList<EmpVO>();
//		list = t.empList();
//		위와 같은 의미로 아래의 식도 사용할 수 있다.
		ArrayList<EmpVO> list = t.empList("deptno", 10);
		
		System.out.println(list.size());
		
//		for(int i=0; i<list.size(); i++) {
//			EmpVO evo = list.get(i);
//		}   ↓↓↓ 같은의미
//		for(EmpVO evo : list) {
//			System.out.println(evo.getEmpno() + "\t" + evo.getEname());
//		}
//	
		
		for(int i=0; i<list.size(); i++) {
//			EmpVO evo = list.get(i); 이렇게 나눠서 작성해도 됨
			System.out.println(list.get(i).getEmpno() + "\t" + list.get(i).getEname()); //\t : 한칸띄기

		}
		
		int insRes = t.empInsert(8001, "kim");
		System.out.println(insRes);
		
		
		int uptRes = t.empUpdate(9000, 7369);
		System.out.println(uptRes + "건 입력");
		
		int delRes = t.empDelete(7902);
		System.out.println(delRes + "건 삭제");
	}

}
