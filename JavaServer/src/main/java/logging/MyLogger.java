package logging;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import httpControllers.MarkController;

public class MyLogger {
	static private FileHandler fileTxt;
    static private SimpleFormatter formatterTxt;
    
    static public void setup() throws IOException {

        //Get the global logger to configure it.
    	Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    	//All priorities are logged
        logger.setLevel(Level.ALL);
        
        //Logs are inputed into Logging.txt
        fileTxt = new FileHandler("Logging.txt");

        //Create a text formatter
        formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        logger.addHandler(fileTxt);
    }
    
    static public String getStackString(StackTraceElement[] elements) {
    	String stackString = "";
    	for (int i = 1; i < elements.length; i++) {
    	     StackTraceElement s = elements[i];
    	     stackString += "\tat " + s.getClassName() + "." + s.getMethodName() + "(" + s.getFileName() + ":" + s.getLineNumber() + ")";
    	     //System.out.println("\tat " + s.getClassName() + "." + s.getMethodName() + "(" + s.getFileName() + ":" + s.getLineNumber() + ")");
    	}
    	return stackString;
    	
    }
}
