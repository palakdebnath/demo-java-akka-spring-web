package demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.model.Message;
import demo.model.Person;
import demo.repository.PersonRepository;

@Service
public class DeleteService {

	@Autowired
	PersonRepository personRepository; 

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void perform(Object o, Message m) {
		logger.info("Perform Delete: {}", o);

		personRepository.delete(m.getData());

		logger.info(m.getData() + " has been deleted from DB!");
	}
	
	public void perform(String id) {

		personRepository.delete(id);
		logger.info(id + " has been deleted from DB!");
	}
}
