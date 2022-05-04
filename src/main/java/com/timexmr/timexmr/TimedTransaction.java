package com.timexmr.timexmr;


import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TimedTransaction {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String raw_tx;
	private Timestamp time;
	TimedTransaction(){}
	
	TimedTransaction(String raw_tx, Timestamp time){
		this.raw_tx = raw_tx;
		this.time = time;
	}
	public String getRaw_tx() {
		return raw_tx;
	}
	public void setRaw_tx(String raw_tx) {
		this.raw_tx = raw_tx;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public Long getId() {
		return id;
	}
	
	
}
