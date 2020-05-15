package ie.cct.Farm.Managment.Actions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -216402908347877569L;

	 public NotFoundException(String message) {
		 super(message);
	 }
}
