/**
 * 
 */
package com.bmac.ffan.common.persistence.model;

/**
 * @author xiongrl
 *
 */
public class ResultMap{
	
	private boolean success;
	private String msg;
	
	public ResultMap(boolean success,String msg){
		this.success = success;
		this.msg = msg;
	}
	
	public ResultMap(boolean success){
		this.success = success;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
