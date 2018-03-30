package demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.model.Message;
import demo.model.Person;
import demo.repository.PersonRepository;

@Service
public class DisplayService {
	
	@Autowired
	PersonRepository personRepository; 

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void perform(Object o, Message m) throws InterruptedException {
        logger.info("Perform Display: {}", o);
        Thread.sleep(100);
        for(Person p : personRepository.findAll()) {
        	logger.info("Id: " + p.getId() + "  Name; " + p.getName());
        }
    }
    
    public String perform() throws InterruptedException {
        
    	Thread.sleep(100);
        for(Person p : personRepository.findAll()) {
        	logger.info("Id: " + p.getId() + "  Name; " + p.getName());
        }
        return "Success";
    }
}
