package com.alxmcr.errors;

public class ErrorApp {
	private String message;

	public ErrorApp() {
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ErrorApp [message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}

}
