package com.prj.api.fileApp.Exception;

public class FileProcessingException extends Exception {

	private static final long serialVersionUID = 1L;
	private final int errorCode;
	private final Exception exception;

	public FileProcessingException() {
		super();
		this.errorCode = 0;
		this.exception = null;
	}

	public FileProcessingException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
		this.exception = null;

	}

	public FileProcessingException(final String message, int errorCode, final Exception exception) {
		super(message, exception);
		this.errorCode = errorCode;
		this.exception = exception;
	}

	public FileProcessingException(final String message, final Exception exception) {
		super(message, exception);
		this.exception = exception;
		this.errorCode = 0;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public Exception getException() {
		return exception == null ? this : exception;
	}

	@Override
	public String toString() {
		return "FileProcessingException [errorCode=" + errorCode + ", exception=" + exception + "]";
	}
}