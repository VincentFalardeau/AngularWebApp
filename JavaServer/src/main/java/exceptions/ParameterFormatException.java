package exceptions;

import dataObjects.ParameterFormatExceptionDataObject;

//A http parameter has an invalid format
public class ParameterFormatException extends ParameterException{
	
	private String mExpectedFormat;
	
//	public ParameterFormatException(String errorMessage, Throwable err) {
//	    super(errorMessage, err);
//	    mErrorMessage = errorMessage;
//	    mParameterKey = "";
//	    mExpectedType = "";
//	}
	
	public ParameterFormatException(String errorMessage, Throwable err, String parameterKey, String expectedType) {
	    super(errorMessage, err, parameterKey);
	    mExpectedFormat = expectedType;
	}
	
	public String getExpectedType() {
		return mExpectedFormat;
	}
	
	public ParameterFormatExceptionDataObject toParameterFormatExceptionDataObject() {
		return new ParameterFormatExceptionDataObject(super.getErrorMessage(), super.getParameterKey(), getExpectedType());
	}
}
