package demo.repository;

import org.springframework.data.repository.CrudRepository;

import demo.model.Person;

public interface PersonRepository extends CrudRepository<Person, String> {
	@Override
	Person findOne(String id);
	
	@Override
	void delete(Person person);

}
