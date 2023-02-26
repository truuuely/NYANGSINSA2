package com.wan.nss.biz.image;

public class ImageVO {
   private int iNum; // 이미지 PK
   private int TargetNum; // 게시판 혹은 상품 데이터의 PK
   private int TypeNum; // 이미지 사용하는 테이블 구분
   private String iName; // 이미지 파일 이름
   
   public int getiNum() {
      return iNum;
   }
   public void setiNum(int iNum) {
      this.iNum = iNum;
   }
   public int getTargetNum() {
      return TargetNum;
   }
   public void setTargetNum(int targetNum) {
      TargetNum = targetNum;
   }
   public int getTypeNum() {
      return TypeNum;
   }
   public void setTypeNum(int typeNum) {
      TypeNum = typeNum;
   }
   public String getiName() {
      return iName;
   }
   public void setiName(String iName) {
      this.iName = iName;
   }
   @Override
   public String toString() {
      return "ImageVO [iNum=" + iNum + ", TargetNum=" + TargetNum + ", TypeNum=" + TypeNum + ", iName=" + iName + "]";
   }
   
}