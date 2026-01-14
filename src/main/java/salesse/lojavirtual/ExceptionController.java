package salesse.lojavirtual;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import salesse.lojavirtual.dto.ErrorDTOObject;

@RestControllerAdvice
@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

	/* Captura exceções do projeto */
	@ExceptionHandler({ Exception.class, RuntimeException.class, Throwable.class })
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatusCode statusCode, WebRequest request) {

		ErrorDTOObject error = new ErrorDTOObject();

		String msg = "";

		if (ex instanceof MethodArgumentNotValidException) {
			List<ObjectError> list = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors();

			for (ObjectError e : list) {
				msg += e.getDefaultMessage() + "\n";
			}
		} else {
			msg = ex.getMessage();
		}

		error.setError(msg);
		error.setCode(HttpStatus.METHOD_NOT_ALLOWED);
		return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/* Captura erro na parte de banco */
	@ExceptionHandler({ DataIntegrityViolationException.class, ConstraintViolationException.class, SQLException.class })
	protected ResponseEntity<Object> handleExceptionDataIntegry(Exception ex) {

		ErrorDTOObject error = new ErrorDTOObject();
		String msg = "";

		if (ex instanceof SQLException) {
			msg = ((SQLException) ex).getCause().getCause().getMessage();
		}

		error.setError(msg);
		error.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
