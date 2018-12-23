package com.biz.shop;

import java.util.ArrayList;

public class ShopVO {
	
	//shop_info
	private int sseq;
	private String sname;  //사용자 입력 상호명
	private String sinfo;
	private String lat;
	private String lng;
	private String placename;  //지도에서 입력 상호명
	private String regid;
	private String regdate;
	private double distance;
	
	private int topn = 4;
	
	public int getTopn() {
		return topn;
	}
	public void setTopn(int topn) {
		this.topn = topn;
	}

	
	//페이징을 위한 stringSeq, endSeq
	private int startSeq;
	private int endSeq;
	private int currentPage;
	
	
	
	
	//상세보기 페이지: 이미지목록
	private ArrayList<ShopPicVO> pvolist; //VO에 VO를 넣는방법
	public ArrayList<ShopPicVO> getPvolist() {
		return pvolist;
	}
	public void setPvolist(ArrayList<ShopPicVO> pvolist) {
		this.pvolist = pvolist;
	}
	
	
	//shop_pic
	private String pseq;
	private String ppath;
	private String pname;
	public int getSseq() {
		return sseq;
	}
	public void setSseq(int sseq) {
		this.sseq = sseq;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSinfo() {
		return sinfo;
	}
	public void setSinfo(String sinfo) {
		this.sinfo = sinfo;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getRegid() {
		return regid;
	}
	public void setRegid(String regid) {
		this.regid = regid;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	public String getPseq() {
		return pseq;
	}
	public void setPseq(String pseq) {
		this.pseq = pseq;
	}
	public String getPpath() {
		return ppath;
	}
	public void setPpath(String ppath) {
		this.ppath = ppath;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getPlacename() {
		return placename;
	}
	public void setPlacename(String placename) {
		this.placename = placename;
	}

	

}
