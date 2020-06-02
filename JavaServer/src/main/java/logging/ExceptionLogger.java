package logging;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import httpControllers.MarkController;

public class ExceptionLogger {
	static private FileHandler fileTxt;
    static private SimpleFormatter formatterTxt;
    
    static public void setup() throws IOException {

        //Get the logger of MarkController to configure it.
    	Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

        logger.setLevel(Level.ALL);
        fileTxt = new FileHandler("Logging.txt");

        // create a TXT formatter
        formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        logger.addHandler(fileTxt);
    }
}
