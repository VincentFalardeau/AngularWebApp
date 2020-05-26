package exceptions;

import dataObjects.ParameterFormatExceptionDataObject;

//A http parameter has an invalid format
public class ParameterFormatException extends Exception{
	
	private String mErrorMessage;
	private String mParameterKey;
	private String mExpectedType;
	
	public ParameterFormatException(String errorMessage, Throwable err) {
	    super(errorMessage, err);
	    mErrorMessage = errorMessage;
	    mParameterKey = "";
	    mExpectedType = "";
	}
	
	public ParameterFormatException(String errorMessage, Throwable err, String parameterKey, String expectedType) {
	    super(errorMessage, err);
	    mErrorMessage = errorMessage;
	    mParameterKey = parameterKey;
	    mExpectedType = expectedType;
	}
	
	public String getErrorMessage() {
		return mErrorMessage;
	}
	
	public String getParameterKey() {
		return mParameterKey;
	}
	
	public String getExpectedType() {
		return mExpectedType;
	}
	
	public ParameterFormatExceptionDataObject toDataObject() {
		return new ParameterFormatExceptionDataObject(getErrorMessage(), getParameterKey(), getExpectedType());
	}
}
