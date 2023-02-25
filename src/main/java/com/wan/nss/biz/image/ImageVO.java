package com.wan.nss.biz.image;

public class ImageVO {
   private int iNum;
   private int TargetNum;
   private int TypeNum;
   private String iName;
   
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