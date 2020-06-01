package exceptions;

public class ParameterException extends Exception{
	
	//Exception's error message.
	private String mErrorMessage;
	
	//Constructor.
	public ParameterException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	    this.setErrorMessage(errorMessage);
	}

	//Gives the exception's error message
	public String getErrorMessage() {
		return mErrorMessage;
	}

	//Sets the exception's error message.
	public void setErrorMessage(String mMessage) {
		this.mErrorMessage = mMessage;
	}
}
