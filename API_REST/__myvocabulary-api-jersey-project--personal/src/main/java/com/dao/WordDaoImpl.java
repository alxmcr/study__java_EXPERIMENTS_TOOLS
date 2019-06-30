package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import utilitarios.Utilitario;
import utilitarios.UtilitarioDao;

import com.dto.Word;

public class WordDaoImpl implements WordDao {

	private static Logger logger = Logger.getLogger(WordDaoImpl.class);
	private UtilitarioDao utilitarioDao;

	public WordDaoImpl() {
		this.utilitarioDao = new UtilitarioDao();
	}

	public Word create(Word Word) throws Exception {
		logger.info("INICIO WordDaoImpl.create()");

		List<Word> Words = null;
		EntityManager em = null;
		EntityTransaction transaccion = null;
		try {

			if (Word != null) {
				String texto = Word.getText();
				em = this.utilitarioDao.obtenerEntityManager();
				transaccion = em.getTransaction();
				transaccion.begin();
				Query query = em.createNamedQuery("Word.findByTexto");
				query.setParameter("texto", texto);
				Words = query.getResultList();

				if (Words.isEmpty()) {
					String idWord = Utilitario.generateRandomString(20);
					Word.setId(idWord);
					em.persist(Word);

				} else {
					logger.error("Ya existe, una Word con el mismo texto");
				}

				transaccion.commit();

				logger.info("Creacion de la entidad satisfactoriamente");
			} else {
				logger.error("El parametro de entrada es NULO");
			}

		} catch (Exception e) {
			logger.error(e);
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

		logger.info("FIN WordDaoImpl.create()");
		return Word;
	}

	public List<Word> listAllWordsByEstado(String estado)
			throws PersistenceException, Exception {
		logger.info("INICIO WordDaoImpl.listAllWordsByEstado()");

		List<Word> Words = null;
		EntityManager em = null;
		EntityTransaction transaccion = null;

		try {

			logger.info("Obteniendo el EntityManager...");
			em = this.utilitarioDao.obtenerEntityManager();

			if (em != null) {
				logger.info("EntityManager not null...");
				transaccion = em.getTransaction();

				if (transaccion != null) {
					logger.info("transaccion not null...");
					transaccion.begin();
					Query query = em.createNamedQuery("Word.findAll");
					Words = query.getResultList();
					transaccion.commit();
				} else {
					logger.error("transaccion is null");
				}

			} else {
				logger.error("EntityManager is null");
			}
		} catch (Exception e) {
			logger.error(e);
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

		logger.info("FIN WordDaoImpl.listAllWordsByEstado()");
		return Words;
	}

	public Word findById(String idWord) throws Exception {
		logger.info("INICIO WordDaoImpl.findById()");

		EntityManager em = null;
		Word salida = null;
		EntityTransaction transaccion = null;
		try {

			if (idWord != null) {
				em = this.utilitarioDao.obtenerEntityManager();
				transaccion = em.getTransaction();
				transaccion.begin();
				salida = em.find(Word.class, idWord);
				transaccion.commit();
				logger.info("Creacion de la entidad satisfactoriamente");
			} else {
				logger.error("El parametro de entrada es NULO");
			}

		} catch (Exception e) {
			logger.error(e);
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

		logger.info("FIN WordDaoImpl.findById()");
		return salida;
	}

	public Word update(Word WordModificada) throws Exception {
		logger.info("INICIO WordDaoImpl.update()");

		EntityManager em = null;
		EntityTransaction transaccion = null;
		Word wordSQLUpdate = null;
		Word wordUpdated = null;
		try {

			if (WordModificada != null) {
				em = this.utilitarioDao.obtenerEntityManager();
				transaccion = em.getTransaction();
				transaccion.begin();
				wordSQLUpdate = em.merge(WordModificada);
				transaccion.commit();

				String idWord = wordSQLUpdate.getId();
				wordUpdated = this.findById(idWord);

				logger.info("Actualizacion de la entidad satisfactoriamente");
			} else {
				logger.error("El parametro de entrada es NULO");
			}

		} catch (Exception e) {
			logger.error(e);
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

		logger.info("FIN WordDaoImpl.update()");
		return wordUpdated;
	}

	public Word delete(String idWord) throws Exception {
		logger.info("INICIO WordDaoImpl.delete()");

		EntityManager em = null;
		EntityTransaction transaccion = null;
		Word wordAEliminar = null;
		try {

			if (idWord != null) {
				em = this.utilitarioDao.obtenerEntityManager();
				transaccion = em.getTransaction();
				transaccion.begin();
				wordAEliminar = em.find(Word.class, idWord);
				
				if(wordAEliminar!=null){
					logger.info("wordAEliminar:" + wordAEliminar);
					em.remove(wordAEliminar);
					transaccion.commit();
					logger.info("Eliminacion de la entidad satisfactoriamente");
				}else{
					logger.error("No existe la entidad. No se pudo eliminar.");
				}
				
			} else {
				logger.error("El parametro de entrada es NULO");
			}

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
		logger.info("FIN WordDaoImpl.delete()");
		return wordAEliminar;
	}

	public List<Word> findContainsTexto(String texto) throws Exception {
		logger.info("INICIO WordDaoImpl.findContainsTexto()");

		EntityManager em = null;
		EntityTransaction transaccion = null;
		List<Word> WordsContainsTexto = null;
		try {

			if (texto != null) {
				logger.info("texto->" + String.valueOf(texto));
				em = this.utilitarioDao.obtenerEntityManager();
				transaccion = em.getTransaction();
				transaccion.begin();
				Query query = em.createNamedQuery("Word.findContainsTexto");
				query.setParameter("texto", "%" + texto + "%");
				WordsContainsTexto = query.getResultList();

				transaccion.commit();

				logger.info("Creacion de la entidad satisfactoriamente");
			} else {
				logger.error("El parametro de entrada es NULO");
			}

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
		logger.info("FIN WordDaoImpl.findContainsTexto()");
		return WordsContainsTexto;
	}

}
