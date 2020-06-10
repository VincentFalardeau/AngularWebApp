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
        
        //TODO concatenate time stamp to the log file
        //TODO use log4j
        
        //Logs are inputed into Logging.txt
        fileTxt = new FileHandler("log/JavaServer"+""+".log");

        //Create a text formatter
        formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        logger.addHandler(fileTxt);
    }
}
