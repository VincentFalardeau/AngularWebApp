package exceptions;

//Exception that occurs when a request's parameter has an invalid value.
public class ParameterException extends Exception{
	
	private final String GENERIC_ERROR_MESSAGE = "Invalid parameter";

	//Constructor.
	public ParameterException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
	
	//Gives the exception's generic error message.
	public String getGenericErrorMessage() {
		return GENERIC_ERROR_MESSAGE;
	}

}
