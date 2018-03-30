package demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.model.Person;
import demo.repository.PersonRepository;
import demo.model.Message;

@Service
public class InsertService {

    @Autowired
	PersonRepository personRepository; 
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void perform(Object o, Message m) {
        logger.info("Perform Insert: {}", o);
        
        Person p = new Person();
    	p.setName(m.getData());
    	
    	String personId = personRepository.save(p).getId() ;
    	logger.info(m.getData() + " has been Inserted to DB. Id = " + personId);
    }
    
    public void perform(String name) {
        
        Person p = new Person();
    	p.setName(name);
    	
    	String personId = personRepository.save(p).getId() ;
    	logger.info(name + " has been Inserted to DB. Id = " + personId);
    }
}
