package com.dao.dbmanaged;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import utilitarios.Constantes;
import utilitarios.UtilitarioDao;

import com.dao.WordDaoImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PostgreSQLDaoImpl implements PostgreSQLDao {

	private static Logger logger = Logger.getLogger(WordDaoImpl.class);
	private UtilitarioDao utilitarioDao;
	private String nameDatabase = Constantes.NAME_DATABASE_REMOTE;

	public PostgreSQLDaoImpl() {
		this.utilitarioDao = new UtilitarioDao();
	}

	@Override
	public int countProcessConnections() throws Exception {
		int countProcess = 0;

		logger.info("INICIO PostgreSQLDaoImpl.countProcessConnections()");
		logger.info("NAME_DATABASE:" + this.nameDatabase);

		EntityManager em = null;
		EntityTransaction transaccion = null;

		try {
			em = this.utilitarioDao.obtenerEntityManager();
			transaccion = em.getTransaction();
			transaccion.begin();

			String sqlCountProcess = " select ";
			sqlCountProcess += " count(*) as numberConnections ";
			sqlCountProcess += " from pg_stat_activity ";
			sqlCountProcess += " where datname = '" + this.nameDatabase + "'";
			logger.info("sql->" + sqlCountProcess);
			Query query = em.createNativeQuery(sqlCountProcess);

			String countProcessStr = String.valueOf(query.getSingleResult());

			BigInteger aux = new BigInteger(countProcessStr);

			countProcess = Integer.valueOf(aux.intValue());
			logger.info("countProcess->" + countProcess);
			transaccion.commit();

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			if (transaccion != null && transaccion.isActive()) {
				logger.info("Ocurrio un error y se realizo un rollback");
				transaccion.rollback();
			}
			throw e;
		} finally {
			if (em != null && em.isOpen()) {
				logger.info("em is closed");
				em.close();
			}
		}

		logger.info("FIN PostgreSQLDaoImpl.countProcessConnections()");

		return countProcess;
	}

	public List<Integer> listPIDPostgreSQL() throws Exception {
		int countProcess = 0;

		logger.info("INICIO PostgreSQLDaoImpl.listPIDPostgreSQL()");
		logger.info("NAME_DATABASE:" + this.nameDatabase);
		EntityManager em = null;
		EntityTransaction transaccion = null;
		List<Integer> listPIDs = null;
		ObjectMapper mapper = null;

		try {
			em = this.utilitarioDao.obtenerEntityManager();
			transaccion = em.getTransaction();
			transaccion.begin();

			String sqlListPIDs = " select ";
			sqlListPIDs += " pid ";
			sqlListPIDs += " from pg_stat_activity ";
			sqlListPIDs += " where datname = '" + this.nameDatabase + "'";
			sqlListPIDs += " order by(state) desc ";
			logger.info("sql->" + sqlListPIDs);
			Query query = em.createNativeQuery(sqlListPIDs);

			listPIDs = query.getResultList();

			for (int pid : listPIDs) {
				logger.info("PID:" + pid);
			}

			logger.info("countProcess->" + countProcess);
			transaccion.commit();

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			if (transaccion != null && transaccion.isActive()) {
				logger.info("Ocurrio un error y se realizo un rollback");
				transaccion.rollback();
			}
			throw e;
		} finally {
			if (em != null && em.isOpen()) {
				logger.info("em is closed");
				em.close();
			}
		}

		logger.info("FIN PostgreSQLDaoImpl.listPIDPostgreSQL()");

		return listPIDs;
	}

}
