package com.hneb.fwk.domain;

/**
 * http请求返回的最外层对象
 * @author Administrator
 * @param <T>
 *
 */
public class Result<T> {

	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	private Integer code;
	private String msg;
	private T data;//具体内容
}
