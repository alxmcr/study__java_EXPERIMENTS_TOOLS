package utilitarios;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.hibernate.jpa.HibernatePersistenceProvider;

public class UtilitarioDao {

	private static Logger logger = Logger.getLogger(UtilitarioDao.class);

	public EntityManager obtenerEntityManager() throws PersistenceException,
			Exception {
		logger.info("INICIO UtilitarioDao.obtenerEntityManager()");

		EntityManager em = null;
		EntityManagerFactory emf = null;

		HibernatePersistenceProvider hp = new HibernatePersistenceProvider();
		Map properties = new HashMap();

		try {
			emf = hp.createEntityManagerFactory("my-pu-2", properties);

			if (emf != null) {
				em = emf.createEntityManager();
			} else {
				logger.error("EntityManagerFactory is null");
			}

			if (em != null) {
				properties = em.getProperties();
			} else {
				logger.error("EntityManager is null");
			}

			if (properties != null) {
				// weired way, but works anyway
				for (Object key : properties.keySet()) {
					System.out.println("Key : " + key.toString() + " Value : "
							+ properties.get(key));
				}
			} else {
				logger.error("Properties is null");
			}
		} catch (PersistenceException e) {
			logger.error(e);
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			throw e;
		}

		logger.info("FIN UtilitarioDao.obtenerEntityManager()");
		return em;
	}
}
