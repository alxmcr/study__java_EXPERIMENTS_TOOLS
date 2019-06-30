package utilitarios;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Utilitario {

	private static Logger logger = Logger.getLogger(Utilitario.class);

	public static String generateRandomString(int length) throws Exception {

		logger.info("INICIO Utilitario.generateRandomString()");

		StringBuffer buffer = new StringBuffer();
		String characters = "";

		characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

		int charactersLength = characters.length();

		for (int i = 0; i < length; i++) {
			double index = Math.random() * charactersLength;
			buffer.append(characters.charAt((int) index));
		}
		logger.info("FIN Utilitario.generateRandomString()");

		return buffer.toString();
	}

	public static String convertirObjetoAJsonString(Object objeto)
			throws JsonProcessingException {

		logger.info("INICIO Utilitario.convertirObjetoAJsonString()");

		String jsonStr = "";

		ObjectMapper mapper = null;

		if (objeto != null) {
			mapper = new ObjectMapper();

			// to enable standard indentation ("pretty-printing"):
			mapper.enable(SerializationFeature.INDENT_OUTPUT);

			// to allow coercion of JSON empty String ("") to null Object
			// value:
			mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

			jsonStr = mapper.writeValueAsString(objeto);
		} else {
			logger.error("El parametro de entrada es NULO");
		}

		logger.info("FIN Utilitario.convertirObjetoAJsonString()");

		return jsonStr;
	}
}
