package com.bmac.ffan.scheme.util;

public class FileData
{
  private String fileDataStr;
  private int returnFileSize;
  private byte[] fileDateByte;

  public String getFileDataStr()
  {
    return this.fileDataStr; }

  public void setFileDataStr(String fileDataStr) {
    this.fileDataStr = fileDataStr; }

  public int getReturnFileSize() {
    return this.returnFileSize; }

  public void setReturnFileSize(int returnFileSize) {
    this.returnFileSize = returnFileSize; }

  public byte[] getFileDateByte() {
    return this.fileDateByte; }

  public void setFileDateByte(byte[] fileDateByte) {
    this.fileDateByte = fileDateByte;
  }
}