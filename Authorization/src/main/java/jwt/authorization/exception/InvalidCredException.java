package jwt.authorization.exception;

public class InvalidCredException extends RuntimeException {
	
	
public InvalidCredException(){
		
	}
	public InvalidCredException(String message) {
		super(message);
	}


}
