package dataObjects;

public class ParameterExceptionDataObject {
	private String mErrorMessage;
	private String mParameterKey;
	
	public ParameterExceptionDataObject(String errorMessage, String parameterKey) {
	    mErrorMessage = errorMessage;
	    mParameterKey = parameterKey;
	}
	
	public String getErrorType() {
		return "ParameterException";
	}
	
	public String getErrorMessage() {
		return mErrorMessage;
	}
	
	public String getParameterKey() {
		return mParameterKey;
	}
}
