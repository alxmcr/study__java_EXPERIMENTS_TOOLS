package com.resource.dbmanaged;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.service.dbmanaged.ServicePostgreSQL;

@Path("/postgreSQL")
public class PostgreSQLResourceImpl implements PostgreSQLResource {

	private static Logger logger = Logger
			.getLogger(PostgreSQLResourceImpl.class);

	@Autowired
	@Qualifier("servicePostgreSQL")
	private ServicePostgreSQL servicePostgreSQL;

	@GET
	@Path("/count_process_postgresql")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String countProcessConnections() {
		logger.info("INICIO PostgreSQLResourceImpl.countProcessConnections()");

		String jsonStr = "{\"error\": \"ocurrio un error en la creacion\"}";
		int countProcess = 0;

		if (this.servicePostgreSQL != null) {
			try {
				countProcess = this.servicePostgreSQL.countProcessConnections();
				jsonStr = "{\"count_process_postgresql\":" + "'" + countProcess
						+ "'" + "}";
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		} else {
			logger.error("this.serviceWord es NULO");
		}

		logger.info("countProcess: " + countProcess);

		if (jsonStr != null) {
			logger.info("jsonStr->" + jsonStr);
		}

		logger.info("FIN PostgreSQLResourceImpl.countProcessConnections()");

		return jsonStr;
	}

	@GET
	@Path("/list_process_postgresql")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String listPIDPostgreSQL() {

		logger.info("INICIO PostgreSQLResourceImpl.listPIDPostgreSQL()");

		String jsonStr = "{\"error\": \"ocurrio un error en la creacion\"}";
		List<Integer> listPIDs = null;
		ObjectMapper mapper = null;

		if (this.servicePostgreSQL != null) {
			try {
				listPIDs = this.servicePostgreSQL.listPIDPostgreSQL();
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		} else {
			logger.error("this.serviceWord es NULO");
		}

		if (listPIDs != null) {

			logger.info("# resultados: " + listPIDs.size());

			mapper = new ObjectMapper();

			// to enable standard indentation ("pretty-printing"):
			mapper.enable(SerializationFeature.INDENT_OUTPUT);

			// to allow coercion of JSON empty String ("") to null Object
			// value:
			mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

			try {
				jsonStr = mapper.writeValueAsString(listPIDs);
			} catch (JsonProcessingException e) {
				logger.error(e.getMessage());
			}
		} else {
			logger.error("resultado es NULO");
		}

		if (jsonStr != null) {
			logger.info("jsonStr->" + jsonStr);
		}

		logger.info("FIN PostgreSQLResourceImpl.listPIDPostgreSQL()");

		return jsonStr;

	}

}
