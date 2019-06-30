package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;

import com.example.entities.Person;

public class PersonDAOImpl implements Dao<Person> {

	private EntityManager entityManager;
	private Person person;

	public PersonDAOImpl(EntityManager em) {
		this.entityManager = em;
	}

	@Override
	public Person save(Person person) throws Exception {

		try {
			entityManager.getTransaction().begin();

			if (person.getId() == null) {
				entityManager.persist(person);
			}

			entityManager.persist(person);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			if (entityManager.getTransaction() != null && entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			throw e;
		} finally {
			entityManager.close();
		}

		return person;
	}

	@Override
	public Person save(Person person, boolean isRollback) throws Exception {

		try {
			entityManager.getTransaction().begin();

			if (isRollback) {
				entityManager.getTransaction().setRollbackOnly();
			}

			if (person.getId() == null) {
				entityManager.persist(person);
			}

			if (entityManager.getTransaction().getRollbackOnly()) {
				System.out.println("Rollback...");
				entityManager.getTransaction().rollback();
			} else {
				entityManager.getTransaction().commit();
			}

		} catch (Exception e) {
			if (entityManager.getTransaction() != null && entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			throw e;
		} finally {
			entityManager.close();
		}

		return person;
	}

	@Override
	public Person savePersonDoWork(Person personToSave, boolean isRollback) throws Exception {

		try {
			entityManager.getTransaction().begin();

			if (isRollback) {
				System.out.println("Rollback if...");
				entityManager.getTransaction().setRollbackOnly();
			}

			Session session = entityManager.unwrap(Session.class);
			session.doWork(new Work() {

				@Override
				public void execute(Connection con) throws SQLException {
					// do something useful
					con.setAutoCommit(false);

					// Savepoint
					Savepoint saveBeforeInsert = con.setSavepoint();

					// INSERT SQL
					String sql = "INSERT INTO Person (lastname, name) VALUES (?, ?)";
					con.prepareStatement(sql);

					PreparedStatement st = con.prepareStatement(sql);
					st.setString(1, personToSave.getName());
					st.setString(2, personToSave.getLastname());

					// execute the preparedstatement insert
					Long idPerson = new Long(st.executeUpdate());

					if (isRollback) {
						System.out.println("Rollback execute()...");
						con.rollback(saveBeforeInsert);
					}

					// Close
					st.close();

					// Set data
					person = new Person();
					person.setId(idPerson);
					person.setName(personToSave.getName());
					person.setLastname(personToSave.getLastname());

				}
			});

		} catch (Exception e) {
			if (entityManager.getTransaction() != null && entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			throw e;
		} finally {
			entityManager.close();
		}

		return this.person;
	}
}