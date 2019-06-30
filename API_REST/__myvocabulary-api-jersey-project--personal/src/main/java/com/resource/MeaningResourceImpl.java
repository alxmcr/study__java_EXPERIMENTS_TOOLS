package com.resource;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.dto.Meaning;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.service.ServiceMeaning;

@Path("/meanings")
public class MeaningResourceImpl implements MeaningResource {

	private static Logger logger = Logger.getLogger(MeaningResourceImpl.class);

	@Autowired
	@Qualifier("serviceMeaning")
	private ServiceMeaning serviceMeaning;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String create(String jsonMeaning) throws Exception {
		logger.info("INICIO MeaningResourceImpl.create()");

		String jsonStr = "{\"error\": \"ocurrio un error en la creacion\"}";
		ObjectMapper mapper = null;
		Meaning resultado = null;

		try {

			if (this.serviceMeaning != null) {
				logger.info("jsonMeaning->" + jsonMeaning);
				mapper = new ObjectMapper();
				Meaning Meaning = mapper.readValue(jsonMeaning, Meaning.class);

				resultado = this.serviceMeaning.create(Meaning);
			} else {
				logger.error("this.serviceMeaning es NULO");
			}

		} catch (Exception e) {
			logger.error(e);
			throw e;
		}

		if (resultado != null) {
			// to enable standard indentation ("pretty-printing"):
			mapper.enable(SerializationFeature.INDENT_OUTPUT);

			// to allow coercion of JSON empty String ("") to null Object
			// value:
			mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			mapper.setDateFormat(dateFormat);
			jsonStr = mapper.writeValueAsString(resultado);
			logger.info("jsonStr->" + jsonStr);
		} else {
			logger.error("El resultado es nulo");
		}

		logger.info("FIN MeaningResourceImpl.create()");

		return jsonStr;
	}

	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String delete(@PathParam("id") String idMeaning) throws Exception {
		logger.info("INICIO MeaningResourceImpl.delete()");

		String jsonStr = "{\"error\": \"ocurrio un error en la creacion\"}";
		ObjectMapper mapper = null;
		Meaning resultado = null;

		try {

			if (this.serviceMeaning != null) {

				mapper = new ObjectMapper();

				resultado = this.serviceMeaning.delete(idMeaning);
			} else {
				logger.error("this.servicePalabra es NULO");
			}

			if (resultado != null) {
				logger.info("resultado->" + resultado);
				// to enable standard indentation ("pretty-printing"):
				mapper.enable(SerializationFeature.INDENT_OUTPUT);

				// to allow coercion of JSON empty String ("") to null Object
				// value:
				mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
				jsonStr = mapper.writeValueAsString(resultado);
				logger.info("jsonStr->" + jsonStr);
			} else {
				logger.error("El resultado es nulo");
			}

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			throw e;
		}

		logger.info("FIN MeaningResourceImpl.delete()");

		return jsonStr;
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String update(@PathParam("id") String id,
			String jsonMeaningModificada) throws Exception {
		logger.info("INICIO MeaningResourceImpl.update()");

		String jsonStr = "{\"error\": \"ocurrio un error en la creacion\"}";
		ObjectMapper mapper = null;
		Meaning resultado = null;

		try {

			if (this.serviceMeaning != null) {

				Meaning existeMeaning = this.serviceMeaning.findById(id);

				if (existeMeaning != null) {
					logger.info("existeMeaning->" + existeMeaning);
					logger.info("jsonMeaningModificada->"
							+ jsonMeaningModificada);

					mapper = new ObjectMapper();
					Meaning MeaningModificado = mapper.readValue(
							jsonMeaningModificada, Meaning.class);

					resultado = this.serviceMeaning.update(MeaningModificado);
				}

			} else {
				logger.error("this.servicePalabra es NULO");
			}

		} catch (Exception e) {
			logger.error(e);
			throw e;
		}

		if (resultado != null) {
			// to enable standard indentation ("pretty-printing"):
			mapper.enable(SerializationFeature.INDENT_OUTPUT);

			// to allow coercion of JSON empty String ("") to null Object
			// value:
			mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			mapper.setDateFormat(dateFormat);
			jsonStr = mapper.writeValueAsString(resultado);
			logger.info("jsonStr->" + jsonStr);
		} else {
			logger.error("El resultado es nulo");
		}

		logger.info("FIN MeaningResourceImpl.update()");

		return jsonStr;
	}

	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String findById(@PathParam("id") String idMeaning) throws Exception {
		logger.info("INICIO MeaningResourceImpl.findById()");

		String jsonStr = "{\"error\": \"ocurrio un error en la creacion\"}";
		Meaning Meaning = null;
		ObjectMapper mapper = null;

		try {

			if (this.serviceMeaning != null) {
				Meaning = this.serviceMeaning.findById(idMeaning);
			} else {
				logger.error("resultado es NULO");
			}

		} catch (Exception e) {
			logger.error(e);
			throw e;
		}

		if (Meaning != null) {
			mapper = new ObjectMapper();

			// to enable standard indentation ("pretty-printing"):
			mapper.enable(SerializationFeature.INDENT_OUTPUT);

			// to allow coercion of JSON empty String ("") to null Object
			// value:
			mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
			jsonStr = mapper.writeValueAsString(Meaning);
			logger.info("jsonStr->" + jsonStr);
		} else {
			logger.error("El resultado es nulo");
		}

		logger.info("FIN MeaningResourceImpl.findById()");

		return jsonStr;
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String listAllMeaningsActivas() throws Exception {
		logger.info("INICIO MeaningResourceImpl.listAllMeaningsActivas()");

		String jsonStr = "{\"error\": \"ocurrio un error en la creacion\"}";
		List<Meaning> todosLosMeanings = null;
		ObjectMapper mapper = null;

		try {

			if (this.serviceMeaning != null) {
				// Solo palabras activas
				String estado = "ACTIVA";
				todosLosMeanings = this.serviceMeaning
						.listAllMeaningsByEstado(estado);

				for (Meaning meaning : todosLosMeanings) {
					logger.info(meaning);
				}

			} else {
				logger.error("this.servicePalabra es NULO");
			}

			if (todosLosMeanings != null) {

				logger.info("# resultados: " + todosLosMeanings.size());

				mapper = new ObjectMapper();

				// to enable standard indentation ("pretty-printing"):
				mapper.enable(SerializationFeature.INDENT_OUTPUT);

				// to allow coercion of JSON empty String ("") to null Object
				// value:
				mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
				jsonStr = mapper.writeValueAsString(todosLosMeanings);
				logger.info("jsonStr->" + jsonStr);
			} else {
				logger.error("resultado es NULO");
			}

		} catch (Exception e) {
			logger.error(e);
			throw e;
		}

		logger.info("FIN MeaningResourceImpl.listAllMeaningsActivas()");

		return jsonStr;
	}

}
