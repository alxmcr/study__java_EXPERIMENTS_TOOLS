package com.alxmcr.app;

import com.alxmcr.entities.Person;
import com.alxmcr.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class App {
    private static Session session;

    public static void main(String[] args) {

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();

            /*
            Person per1 = findPersonById(1);
            System.out.println(per1);

            Person per2 = findPersonById(2);
            System.out.println(per2);
            */

            List<Person> people = findAllPeople();
            System.out.println("Size" + people.size());
            System.out.println(people);

        } catch (Exception e) {
            e.printStackTrace();
            session.close();
        }


    }

    private static List<Person> findAllPeople() {
        List<Person> people = null;

        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("from Person");
        people = query.getResultList();

        tx.commit();


        return people;
    }

    private static Person findPersonById(int id) {

        Person p = null;


        Transaction tx = session.beginTransaction();

        // To get one row from db corresponding to primary key
        p = session.get(Person.class, id);

        tx.commit();

        return p;
    }


}