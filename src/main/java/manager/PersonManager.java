package manager;

import java.util.List;
import dao.GameDAO;
import dao.PersonDAO;
import dao.SessionFactory;
import entities.Person;

public class PersonManager {

	private PersonDAO personDAO = new PersonDAO(SessionFactory.getSessionFactory()
			.getSession());

	public Person createPerson(String firstName, String middleName, String lastName, String email, String phone){
		Person p = new Person(firstName,middleName,lastName,email,phone);
		
		p = personDAO.insert(p);
		
		return p;
	}
	
	public Person updatePerson(String username, String firstName, String middleName, String lastName, String email, String phone){
		Person p = personDAO.select(username);
		p.setFirstName(firstName);
		p.setMiddleName(middleName);
		p.setLastName(lastName);
		p.setEmail(email);
		p.setPhoneNumber(phone);
		
		return personDAO.update(p);
	}	
	
	public Person updatePerson(Person person){
		return personDAO.update(person);
	}
	
	public void deleteRideEntry(String username)
	{
		Person person = personDAO.select(username);
		
		personDAO.delete(person);
	}
	
	public Person selectPerson(String username){
		return personDAO.select(username);
	}
	
	public List<Person> listPeople()
	{
		List<Person> people = personDAO.selectAll();
		
		return people;
	}
	
}
