package com.basic.assignment.Exception;

public class ApiResponseException {
	
		private int statusCode;
	    private String message;
		
	    public ApiResponseException() {
			super();
			// TODO Auto-generated constructor stub
		}

		public ApiResponseException(int statusCode, String message) {
			super();
			this.statusCode = statusCode;
			this.message = message;
		}

		public int getStatusCode() {
			return statusCode;
		}

		public void setStatusCode(int statusCode) {
			this.statusCode = statusCode;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	    
	    
	    
	    
	    
	    
}
