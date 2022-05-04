package com.timexmr.timexmr;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/")
public class RESTController {
	@Autowired
	TimedTransactionRepository tTXRepository;
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String getEntry(@PathVariable("id") long id) throws JsonProcessingException {
		System.out.println(id);
		Optional<TimedTransaction> tTX = tTXRepository.findById(id);
		if(tTX.isPresent()) {
			return JSONParser.toJSON(tTX.get());
		}else {
			return "NOT FOUND";
		}
    }
	
	@PostMapping(
			  value = "/", consumes = "application/json", produces = "application/json")
	public String addEntry(@RequestBody TimedTransaction tTX) {
		System.out.println(tTX.getRaw_tx());
		System.out.println(tTX.getTime());
		TimedTransaction newtTX = new TimedTransaction(tTX.getRaw_tx(), tTX.getTime());
		TimedTransaction savedT = tTXRepository.save(newtTX);
		return savedT.getId().toString();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteEntry(@PathVariable("id") long id) {
		tTXRepository.deleteById(id);
	}
    
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public String updateEntry(@PathVariable("id") long id, @RequestBody TimedTransaction tTX) {
		Optional<TimedTransaction> EDITtTX = tTXRepository.findById(id);
		if(EDITtTX.isPresent()) {
			EDITtTX.get().setTime(tTX.getTime());
			EDITtTX.get().setRaw_tx(tTX.getRaw_tx());
			tTXRepository.save(EDITtTX.get());
			return "OK";
		}
		return "NOT FOUND";
	}   
}