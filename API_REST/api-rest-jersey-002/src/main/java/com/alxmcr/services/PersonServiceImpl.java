package com.alxmcr.services;

import com.alxmcr.dao.PersonDAOImpl;
import com.alxmcr.entities.Person;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class PersonServiceImpl implements PersonService {

	private PersonDAOImpl dao;

	private EntityManagerFactory emf;
	private EntityManager em;

	public PersonServiceImpl() {

		this.emf = Persistence.createEntityManagerFactory("my-persistence-unit");

		this.em = this.emf.createEntityManager();

		// DAO
		this.dao = new PersonDAOImpl(this.em);
	}

	@Override
	public Person getPersonById(Long id) {
		Person person = null;

		try {
			person = this.dao.findById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.em.close();
		}

		return person;
	}

	@Override
	public Person getPersonByName(String name) {
		Person person = null;

		try {
			person = this.dao.findByName(name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.em.close();
		}
		return person;
	}

	@Override
	public List<Person> getAllPeople() {
		List<Person> people = null;

		try {
			people = this.dao.getAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.em.close();
		}
		return people;
	}

	@Override
	public Person savePerson(Person p) {
		Person person = null;

		try {
			person = this.dao.save(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.em.close();
		}
		return person;
	}

	@Override
	public Person savePerson(Person p, boolean isRollback) {
		Person person = null;

		try {
			person = this.dao.save(p, isRollback);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.em.close();
		}
		return person;
	}

	@Override
	public Person updatePerson(Long id, Person personUpdated) {
		Person person = null;

		try {
			person = this.dao.update(id, personUpdated);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.em.close();
		}

		return person;
	}

	@Override
	public Person deletePerson(Long id) {
		Person person = null;

		try {
			person = this.dao.delete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.em.close();
		}
		return person;
	}
}
