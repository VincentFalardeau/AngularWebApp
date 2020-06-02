package httpControllers;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import logging.ExceptionLogger;

@SpringBootApplication
public class Main {
	
	public static void main(String[] args) {
		
		try {
            ExceptionLogger.setup();
        } catch (IOException e) {
            e.printStackTrace();
//            throw new RuntimeException("Problems with creating the log files");
        }
		
		SpringApplication.run(Main.class, args);
	}
}

