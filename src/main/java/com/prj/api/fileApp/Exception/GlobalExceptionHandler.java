package com.prj.api.fileApp.Exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	protected static Logger log = LogManager.getLogger(GlobalExceptionHandler.class);

	/**
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(value = { FileProcessingException.class })
	public @ResponseBody ResponseEntity<ExceptionJSON> handleFileProcessingException(FileProcessingException ex,
			HttpServletRequest request) {
		log.error(ex.getMessage(), ex);
		ExceptionJSON response = new ExceptionJSON();
		response.setCode(getHttpStatus(ErrorCodesEnum.get(ex.getErrorCode())).value());
		response.setUrl(request.getRequestURL().toString());
		response.setMessage(ex.getMessage());
		return new ResponseEntity<ExceptionJSON>(response,
				getHttpStatus(ErrorCodesEnum.get(ex.getErrorCode())));
	}


	private HttpStatus getHttpStatus(ErrorCodesEnum fileErrorCode) {
		switch (fileErrorCode) {
		case INTERNAL_ERROR:
			return HttpStatus.INTERNAL_SERVER_ERROR;
		case BAD_REQUEST:
		case PARAMETER_INVALID:
			return HttpStatus.BAD_REQUEST;
		case PARAMETER_MISSING:
			return HttpStatus.UNPROCESSABLE_ENTITY;
		default:
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}
}