package com.timexmr.timexmr;

import java.io.IOException;
import java.sql.Timestamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootApplication
public class TimexmrApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(TimexmrApplication.class, args);		
	}

}
