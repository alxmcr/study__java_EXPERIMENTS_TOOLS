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

import com.dto.Word;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.service.ServiceWord;

@Path("/words")
public class WordResourceImpl implements WordResource {

	private static Logger logger = Logger.getLogger(WordResourceImpl.class);

	@Autowired
	@Qualifier("serviceWord")
	private ServiceWord serviceWord;

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String listAllWordsActivas() {
		logger.info("INICIO WordResourceImpl.listAllWordsActivas()");

		String jsonStr = "{\"error\": \"ocurrio un error en la creacion\"}";
		List<Word> todasLasWords = null;
		ObjectMapper mapper = null;

		if (this.serviceWord != null) {
			// Solo Words activas
			String estado = "ACTIVA";
			try {
				todasLasWords = this.serviceWord.listAllWordsByEstado(estado);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		} else {
			logger.error("this.serviceWord es NULO");
		}

		if (todasLasWords != null) {

			logger.info("# resultados: " + todasLasWords.size());

			mapper = new ObjectMapper();

			// to enable standard indentation ("pretty-printing"):
			mapper.enable(SerializationFeature.INDENT_OUTPUT);

			// to allow coercion of JSON empty String ("") to null Object
			// value:
			mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

			try {
				jsonStr = mapper.writeValueAsString(todasLasWords);
			} catch (JsonProcessingException e) {
				logger.error(e.getMessage());
			}
		} else {
			logger.error("resultado es NULO");
		}

		logger.info("FIN WordResourceImpl.listAllWordsActivas()");

		if (jsonStr != null) {
			logger.info("jsonStr->" + jsonStr);
		}

		return jsonStr;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String create(String jsonWord) throws Exception {
		logger.info("INICIO WordResourceImpl.create()");

		String jsonStr = "{\"error\": \"ocurrio un error en la creacion\"}";
		ObjectMapper mapper = null;
		Word resultado = null;

		System.out.println("jsonWord->" + jsonWord);
		logger.info("jsonWord->" + jsonWord);
		logger.error("jsonWord->" + jsonWord);

		try {

			if (this.serviceWord != null) {

				mapper = new ObjectMapper();

				Word Word = mapper.readValue(jsonWord, Word.class);
				resultado = this.serviceWord.create(Word);
			} else {
				logger.error("this.serviceWord es NULO");
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

		logger.info("FIN WordResourceImpl.create()");

		return jsonStr;
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String update(@PathParam("id") String id, String jsonWordModificada)
			throws Exception {
		logger.info("INICIO WordResourceImpl.update()");

		String jsonStr = "{\"error\": \"ocurrio un error en la creacion\"}";
		ObjectMapper mapper = null;
		Word resultado = null;

		System.out.println("jsonWordModificada->" + jsonWordModificada);
		logger.info("jsonWordModificada->" + jsonWordModificada);
		logger.error("jsonWordModificada->" + jsonWordModificada);

		try {

			if (this.serviceWord != null) {

				Word existeWord = this.serviceWord.findById(id);

				if (existeWord != null) {
					mapper = new ObjectMapper();

					Word WordModificada = mapper.readValue(jsonWordModificada,
							Word.class);

					resultado = this.serviceWord.update(WordModificada);
				}

			} else {
				logger.error("this.serviceWord es NULO");
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

		logger.info("FIN WordResourceImpl.update()");

		return jsonStr;
	}

	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String delete(@PathParam("id") String idWord) throws Exception {
		logger.info("INICIO WordResourceImpl.delete()");

		String jsonStr = "{\"error\": \"ocurrio un error en la creacion\"}";
		ObjectMapper mapper = null;
		Word resultado = null;

		try {

			if (this.serviceWord != null) {

				mapper = new ObjectMapper();

				resultado = this.serviceWord.delete(idWord);
			} else {
				logger.error("this.serviceWord es NULO");
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
			jsonStr = mapper.writeValueAsString(resultado);
			logger.info("jsonStr->" + jsonStr);
		} else {
			logger.error("El resultado es nulo");
		}

		logger.info("FIN WordResourceImpl.delete()");

		return jsonStr;
	}

	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String findById(@PathParam("id") String idWord) throws Exception {
		logger.info("INICIO WordResourceImpl.findById()");

		String jsonStr = "{\"error\": \"ocurrio un error en la creacion\"}";
		Word Word = null;
		ObjectMapper mapper = null;

		try {

			if (this.serviceWord != null) {
				Word = this.serviceWord.findById(idWord);
			} else {
				logger.error("resultado es NULO");
			}

		} catch (Exception e) {
			logger.error(e);
			throw e;
		}

		if (Word != null) {
			mapper = new ObjectMapper();

			// to enable standard indentation ("pretty-printing"):
			mapper.enable(SerializationFeature.INDENT_OUTPUT);

			// to allow coercion of JSON empty String ("") to null Object
			// value:
			mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

			jsonStr = mapper.writeValueAsString(Word);
			logger.info("jsonStr->" + jsonStr);
		} else {
			logger.error("El resultado es nulo");
		}

		logger.info("FIN WordResourceImpl.findById()");

		return jsonStr;
	}
	
	@GET
	@Path("search/{texto}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String findContainsTexto(@PathParam("texto") String texto) throws Exception {
		logger.info("INICIO WordResourceImpl.findContainsTexto()");

		String jsonStr = "{\"error\": \"ocurrio un error en la creacion\"}";
		List<Word> words = null;
		ObjectMapper mapper = null;

		try {
			if (this.serviceWord != null) {
				words = this.serviceWord.findContainsTexto(texto);
			} else {
				logger.error("resultado es NULO");
			}

		} catch (Exception e) {
			logger.error(e);
			throw e;
		}

		if (words != null) {
			mapper = new ObjectMapper();

			// to enable standard indentation ("pretty-printing"):
			mapper.enable(SerializationFeature.INDENT_OUTPUT);

			// to allow coercion of JSON empty String ("") to null Object
			// value:
			mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

			jsonStr = mapper.writeValueAsString(words);
			logger.info("jsonStr->" + jsonStr);
		} else {
			logger.error("El resultado es nulo");
		}

		logger.info("FIN WordResourceImpl.findContainsTexto()");

		return jsonStr;
	}
}
