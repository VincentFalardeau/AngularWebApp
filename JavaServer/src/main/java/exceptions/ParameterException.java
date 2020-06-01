package exceptions;

public class ParameterException extends Exception{
	
	private final String GENERIC_ERROR_MESSAGE = "Invalid parameter";
	
//	//Exception's error message.
//	private String mErrorMessage;
	
	//Constructor.
	public ParameterException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	    //this.setErrorMessage(errorMessage);
	}

//	//Gives the exception's error message
//	public String getErrorMessage() {
//		return mErrorMessage;
//	}
	
	//Gives the exception's generic error message.
	public String getGenericErrorMessage() {
		return GENERIC_ERROR_MESSAGE;
	}

//	//Sets the exception's error message.
//	public void setErrorMessage(String mMessage) {
//		this.mErrorMessage = mMessage;
//	}
}
