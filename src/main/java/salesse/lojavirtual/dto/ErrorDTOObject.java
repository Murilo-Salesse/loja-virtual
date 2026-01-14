package salesse.lojavirtual.dto;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class ErrorDTOObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String error;
	private HttpStatus code;
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public HttpStatus getCode() {
		return code;
	}
	public void setCode(HttpStatus methodNotAllowed) {
		this.code = methodNotAllowed;
	}
	
	
}
