package dataObjects;

public class ParameterFormatExceptionDataObject {
	private String mErrorMessage;
	private String mParameterKey;
	private String mExpectedType;
	
	public ParameterFormatExceptionDataObject(String errorMessage, String parameterKey, String expectedType) {
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
}
