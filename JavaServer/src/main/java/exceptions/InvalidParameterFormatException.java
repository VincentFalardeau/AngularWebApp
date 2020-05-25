package exceptions;

//A http parameter has an invalid format
public class InvalidParameterFormatException extends Exception{
	public InvalidParameterFormatException(String errorMessage, Throwable err) {
	    super(errorMessage, err);
	}
}
