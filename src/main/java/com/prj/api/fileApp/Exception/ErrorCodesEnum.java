package com.prj.api.fileApp.Exception;

import java.util.HashMap;
import java.util.Map;

public enum ErrorCodesEnum {
	INTERNAL_ERROR(0), DATA_NOT_FOUND(1), BAD_REQUEST(2), PARAMETER_MISSING(3), PARAMETER_INVALID(
			4), CREATE_INDEX_EXCEPTION(5), POSTGRES_DB_EXCEPTION(6);

	private static final Map<Integer, ErrorCodesEnum> lookup = new HashMap<>();
	static {
		for (ErrorCodesEnum errorCode : ErrorCodesEnum.values())
			lookup.put(errorCode.getErrorCodeValue(), errorCode);
	}
	private int errorCodeValue;

	private ErrorCodesEnum(int errorCodeValue) {
		this.errorCodeValue = errorCodeValue;
	}

	/**
	 * @return
	 */
	public int getErrorCodeValue() {
		return errorCodeValue;
	}

	// for reverse lookup of ENUM value
	/**
	 * @param errorCodeValue
	 * @return
	 */
	public static ErrorCodesEnum get(int errorCodeValue) {
		return (ErrorCodesEnum) lookup.get(errorCodeValue);
	}
}