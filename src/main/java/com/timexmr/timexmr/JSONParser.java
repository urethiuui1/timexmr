package com.timexmr.timexmr;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter; 

public class JSONParser {
	public static String toJSON(TimedTransaction tTX) throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		return ow.writeValueAsString(tTX);
	}
	public static TimedTransaction toObject(String jsonstr) throws IOException {
		ObjectReader or = new ObjectMapper().reader();
		return or.readValue(jsonstr, TimedTransaction.class);
	}
}
