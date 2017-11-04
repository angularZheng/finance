package com.zhengbing.util.web;

/**
 * 平台响应对象
 * 
 * @author zhengbing
 *
 * @date 2017年2月5日
 */

public class ResultBean {

	private Meta meta;

	private Object data;

	public ResultBean success() {
		this.meta = new Meta(  "SUCCESS", "操作成功");
		return this;
	}

	public ResultBean success( Object data ) {
		this.meta = new Meta("SUCCESS", "操作成功");
		this.data = data;
		return this;
	}
	
	public ResultBean success( Object data, String message ) {
		this.meta = new Meta( "SUCCESS",
		       message );
		this.data = data;
		return this;
	}

	public ResultBean failure( String Code, String errMsg ) {
		this.meta = new Meta(  Code, errMsg );
		return this;
	}

	public Meta getMeta() {
		return meta;
	}

	public Object getData() {
		return data;
	}

	public class Meta {

		private String errCode;

		private String errMsg;

		public Meta(String errCode, String errMsg) {
			this.errCode = errCode;
			this.errMsg = errMsg;
		}

		public boolean isSuccess() {
			return this.errCode.equalsIgnoreCase( "success" );
		}

		public String getCode() {
			return errCode;
		}

		public String getMessage() {
			return errMsg;
		}

		@Override
		public String toString() {
			return "Meta [ code=" + errCode + ", message=" + errMsg + "]";
		}

	}
}
