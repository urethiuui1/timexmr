package com.timexmr.timexmr;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimedTransactionRepository extends CrudRepository<TimedTransaction, Long>{

}