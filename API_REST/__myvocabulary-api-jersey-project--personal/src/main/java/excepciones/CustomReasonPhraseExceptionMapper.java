package excepciones;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

@Provider
public class CustomReasonPhraseExceptionMapper implements ExceptionMapper<CustomReasonPhraseException> {
	
	private static Logger logger = Logger
			.getLogger(CustomReasonPhraseExceptionMapper.class);

	public Response toResponse(CustomReasonPhraseException bex) {
		
		logger.info("toResponse()");
		
		logger.error(bex);
		
		return Response.status(new CustomReasonPhraseExceptionStatusType(Status.BAD_REQUEST))
				.entity("Custom Reason Phrase exception occured : " + bex.getMessage())
				.build();
	}

}
