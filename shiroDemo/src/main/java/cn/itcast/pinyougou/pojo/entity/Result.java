package cn.itcast.pinyougou.pojo.entity;

import java.io.Serializable;

/**
 * 返回结果
 * @author liuso
 *
 */
public class Result implements Serializable{

	private boolean success;//是否成功
	
	private String message;//返回信息

	private String name;//登录用户名

	public Result(boolean success, String message,String name) {
		super();
		this.success = success;
		this.message = message;
		this.name=name;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
