package exceptions;

import dataObjects.ParameterExceptionDataObject;
import dataObjects.ParameterFormatExceptionDataObject;
import dataObjects.ParameterExceptionDataObject;

public class ParameterException extends Exception{
	
	private String mErrorMessage;
	private String mParameterKey;
	
	public ParameterException(String errorMessage, Throwable err) {
	    super(errorMessage, err);
	    mErrorMessage = errorMessage;
	    mParameterKey = "";
	}
	
	public ParameterException(String errorMessage, Throwable err, String parameterKey) {
	    super(errorMessage, err);
	    mErrorMessage = errorMessage;
	    mParameterKey = parameterKey;
	}
	
	public String getErrorMessage() {
		return mErrorMessage;
	}
	
	public String getParameterKey() {
		return mParameterKey;
	}
	
	public ParameterExceptionDataObject toParameterExceptionDataObject() {
		return new ParameterExceptionDataObject(getErrorMessage(), getParameterKey());
	}

}
