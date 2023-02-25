package com.wan.nss.biz.orderdetail;

public class OrderDetailVO {
	private int odNum;
	private int oNum;
	private int pNum;
	private int odCnt;
	private int odPrice;
	private String pName;
	
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

