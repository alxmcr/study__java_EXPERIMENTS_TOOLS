#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.echo.control;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ${package}.echo.entity.Echo;

public class EchoManager {
    @PersistenceContext
    EntityManager em;

    public Echo find(String message) {
        return em.find(Echo.class, message);
    }

    public void insert(Echo echo) {
        em.persist(echo);
    }
}
