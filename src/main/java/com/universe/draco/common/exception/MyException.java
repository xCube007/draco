package com.universe.draco.common.exception;

/**
 * 自定义异常
 * 
 * @author yumc report
 * @date 2018-05-29
 */
public class MyException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    private String msg;
    private String code;
    
    public MyException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public MyException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}
	
	public MyException(String code, String msg) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}
	
	public MyException(String code, String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
