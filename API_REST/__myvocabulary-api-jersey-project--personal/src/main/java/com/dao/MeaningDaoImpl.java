package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;

import utilitarios.Utilitario;
import utilitarios.UtilitarioDao;

import com.dto.Meaning;
import com.dto.Word;
import com.fasterxml.jackson.core.JsonProcessingException;

public class MeaningDaoImpl implements MeaningDao {

	private static Logger logger = Logger.getLogger(MeaningDaoImpl.class);

	private UtilitarioDao utilitarioDao;

	public MeaningDaoImpl() {
		this.utilitarioDao = new UtilitarioDao();
	}

	public Meaning create(Meaning nuevoMeaning) throws Exception {
		logger.info("INICIO MeaningDaoImpl.create()");

		List<Meaning> Meanings = null;
		EntityManager em = null;
		EntityTransaction transaccion = null;
		try {

			if (nuevoMeaning != null) {
				String texto = nuevoMeaning.getText();
				em = this.utilitarioDao.obtenerEntityManager();
				transaccion = em.getTransaction();
				transaccion.begin();
				Query query = em.createNamedQuery("Meaning.findByTexto");
				query.setParameter("texto", texto);
				Meanings = query.getResultList();

				if (Meanings.isEmpty()) {
					String idMeaning = Utilitario.generateRandomString(20);
					nuevoMeaning.setId(idMeaning);
					em.persist(nuevoMeaning);

				} else {
					logger.error("Ya existe, una palabra con el mismo texto");
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

		logger.info("FIN MeaningDaoImpl.create()");
		return nuevoMeaning;
	}

	public Meaning update(Meaning MeaningModificado) throws Exception {
		logger.info("INICIO MeaningDaoImpl.update()");

		EntityManager em = null;
		EntityTransaction transaccion = null;
		Meaning MeaningActualizado = null;
		try {

			if (MeaningModificado != null) {

				Word word = MeaningModificado.getWord();

				if (word != null) {
					logger.info("MeaningModificado->" + MeaningModificado);
					logger.info("MeaningModificado.getWord()->" + word);

					em = this.utilitarioDao.obtenerEntityManager();
					transaccion = em.getTransaction();
					transaccion.begin();
					MeaningActualizado = em.merge(MeaningModificado);
					transaccion.commit();

					logger.info("Actualizacion de la entidad satisfactoriamente");
				} else {
					logger.error("word is null");
				}

			} else {
				logger.error("El parametro de entrada es NULO");
			}

		} catch (JsonProcessingException jpe) {
			logger.error(jpe);
			jpe.printStackTrace();
			throw jpe;
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

		logger.info("FIN MeaningDaoImpl.update()");
		return MeaningActualizado;
	}

	public Meaning delete(String idMeaning) throws Exception {
		logger.info("INICIO MeaningDaoImpl.delete()");

		EntityManager em = null;
		EntityTransaction transaccion = null;
		Meaning meaningAEliminar = null;
		try {

			if (idMeaning != null) {
				logger.info("idMeaning:" + idMeaning);

				em = this.utilitarioDao.obtenerEntityManager();

				logger.info("INICIO. Buscando el meaning con el id: "
						+ idMeaning);
				meaningAEliminar = em.find(Meaning.class, idMeaning);
				logger.info("FIN. Buscando el meaning con el id: " + idMeaning);

				if (meaningAEliminar != null) {
					logger.info("meaningAEliminar:" + meaningAEliminar);

					meaningAEliminar.getWord().setMeanings(null);

					transaccion = em.getTransaction();
					transaccion.begin();
					em.remove(meaningAEliminar);
					transaccion.commit();
					logger.info("Eliminacion de la entidad satisfactoriamente");
				} else {
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
		logger.info("FIN MeaningDaoImpl.delete()");
		return meaningAEliminar;
	}

	public Meaning findById(String idMeaning) throws Exception {
		logger.info("INICIO MeaningDaoImpl.findById()");

		EntityManager em = null;
		Meaning salida = null;
		EntityTransaction transaccion = null;
		try {

			if (idMeaning != null) {
				em = this.utilitarioDao.obtenerEntityManager();
				transaccion = em.getTransaction();
				transaccion.begin();
				salida = em.find(Meaning.class, idMeaning);
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

		logger.info("FIN MeaningDaoImpl.findById()");
		return salida;
	}

	public List<Meaning> findContainsTexto(String texto) throws Exception {
		logger.info("INICIO MeaningDaoImpl.findContainsTexto()");

		EntityManager em = null;
		EntityTransaction transaccion = null;
		List<Meaning> MeaningsContainsTexto = null;
		try {

			if (texto != null) {
				em = this.utilitarioDao.obtenerEntityManager();
				transaccion = em.getTransaction();
				transaccion.begin();
				Query query = em.createNamedQuery("Meaning.findContainsTexto");
				query.setParameter("texto", texto);
				MeaningsContainsTexto = query.getResultList();

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
		logger.info("FIN MeaningDaoImpl.findContainsTexto()");
		return MeaningsContainsTexto;
	}

	public List<Meaning> listAllMeaningsByEstado(String estado)
			throws Exception {
		logger.info("INICIO MeaningDaoImpl.listAllMeaningsByEstado()");

		List<Meaning> Meanings = null;
		EntityManager em = null;
		EntityTransaction transaccion = null;
		try {
			em = this.utilitarioDao.obtenerEntityManager();
			transaccion = em.getTransaction();
			transaccion.begin();
			Query query = em.createNamedQuery("Meaning.findAll");
			Meanings = query.getResultList();
			transaccion.commit();

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

		logger.info("FIN MeaningDaoImpl.listAllMeaningsByEstado()");
		return Meanings;
	}

}
