package httpControllers;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import logging.MyLogger;

@SpringBootApplication
public class Main {
	
	public static void main(String[] args) {
		
		try {
            MyLogger.setup();
        } catch (IOException e) {
            e.printStackTrace();
//            throw new RuntimeException("Problems with creating the log files");
        }
		
		SpringApplication.run(Main.class, args);
	}
}

