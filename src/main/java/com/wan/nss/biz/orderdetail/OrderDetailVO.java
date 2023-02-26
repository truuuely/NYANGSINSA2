package com.wan.nss.biz.orderdetail;

public class OrderDetailVO {
	private int odNum; //주문 상세 번호
	private int oNum; // 주문 번호
	private int pNum; // 상품 번호
	private int odCnt; // 상세 주문 개수
	private int odPrice; // 상세 주문 가격 
	private String pName; // 상품 이름
	
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public int getOdNum() {
		return odNum;
	}
	public void setOdNum(int odNum) {
		this.odNum = odNum;
	}
	public int getoNum() {
		return oNum;
	}
	public void setoNum(int oNum) {
		this.oNum = oNum;
	}
	public int getpNum() {
		return pNum;
	}
	public void setpNum(int pNum) {
		this.pNum = pNum;
	}
	public int getOdCnt() {
		return odCnt;
	}
	public void setOdCnt(int odCnt) {
		this.odCnt = odCnt;
	}
	public int getOdPrice() {
		return odPrice;
	}
	public void setOdPrice(int odPrice) {
		this.odPrice = odPrice;
	}
	@Override
	public String toString() {
		return "OrderDetailVO [odNum=" + odNum + ", oNum=" + oNum + ", pNum=" + pNum + ", odCnt=" + odCnt + ", odPrice="
				+ odPrice + ", pName=" + pName + "]";
	}

}

