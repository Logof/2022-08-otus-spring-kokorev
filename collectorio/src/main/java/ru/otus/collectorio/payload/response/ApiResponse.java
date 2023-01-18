package ru.otus.collectorio.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.HashMap;

@Data
@JsonPropertyOrder({
		"success",
		"message"
})
public class ApiResponse extends HashMap<String, Object> {

	private static final String STATUS = "status";
	private static final String CODE = "code";
	private static final String MESSAGE = "message";
	private static final String DATA = "data";


	public static final int SUCCESS_CODE = 0;
	public static final String SUCCESS_STATUS = "success";
	public static final String SUCCESS_MESSAGE = "success!";
	public static final int ERROR_CODE = 500;
	public static final String ERROR_STATUS = "error";
	public static final String ERROR_MESSAGE = "unknown exception!";
	public static final int VERIFICATION_ERROR_CODE = 400;
	public static final String VERIFICATION_ERROR_MESSAGE = "parity exception!";
	public static final int NOT_ALLOWED_CODE = 401;
	public static final String NOT_ALLOWED_MESSAGE = "Permission denied!";
	public static final int NOT_FOUND_CODE = 404;
	public static final String NOT_FOUND_MESSAGE = "path does not exist!";
	public static final int METHOD_ERROR_CODE = 405;
	public static final String METHOD_ERROR_MESSAGE = "The request method is not supported!";
	public static final int TOKEN_EXPIRE_CODE = 4001;
	public static final String TOKEN_EXPIRE_MESSAGE = "Credentials have expired, please login again!";
	public static final int WARNING_CODE = 600;

	public ApiResponse() {
		put(STATUS, SUCCESS_STATUS);
		put(CODE, SUCCESS_CODE);
		put(MESSAGE, SUCCESS_MESSAGE);
	}

	public static ApiResponse success() {
		return new ApiResponse();
	}
	public static ApiResponse success(Object data) {
		ApiResponse r = new ApiResponse();
		r.put(DATA, data);
		return r;
	}
	public static ApiResponse success(HashMap<String, Object> map) {
		ApiResponse r = new ApiResponse();
		r.putAll(map);
		return r;
	}

	public static ApiResponse error() {
		ApiResponse r = new ApiResponse();
		r.put(STATUS, ERROR_STATUS);
		r.put(CODE, ERROR_CODE);
		r.put(MESSAGE, ERROR_MESSAGE);
		return r;
	}

	public static ApiResponse error(Integer code) {
		ApiResponse r = new ApiResponse();
		r.put(STATUS, ERROR_STATUS);
		r.put(CODE, code);
		r.put(MESSAGE, ERROR_MESSAGE);
		return r;
	}

	public static ApiResponse error(String message) {
		ApiResponse r = new ApiResponse();
		r.put(STATUS, ERROR_STATUS);
		r.put(CODE, ERROR_CODE);
		r.put(MESSAGE, message);
		return r;
	}

	public static ApiResponse error(Integer code, String message) {
		ApiResponse r = new ApiResponse();
		r.put(STATUS, ERROR_STATUS);
		r.put(CODE, code);
		r.put(MESSAGE, message);
		return r;
	}

	@Override
	public ApiResponse put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
