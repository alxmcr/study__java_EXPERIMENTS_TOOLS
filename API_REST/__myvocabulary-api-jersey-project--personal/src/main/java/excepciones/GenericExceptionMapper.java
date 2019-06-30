package excepciones;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.persistence.PersistenceException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	private static Logger logger = Logger
			.getLogger(GenericExceptionMapper.class);
	
	private ErrorMessage errorMessage;

	public Response toResponse(Throwable ex) {
		logger.info("toResponse()");
		// logger.error(ex);

		this.setHttpStatus(ex);
		
		this.errorMessage.setCode(AppConstants.GENERIC_APP_ERROR_CODE);
		this.errorMessage.setMessage(ex.getMessage());
		
		StringWriter errorStackTrace = new StringWriter();
		ex.printStackTrace(new PrintWriter(errorStackTrace));
		this.errorMessage.setDeveloperMessage(errorStackTrace.toString());
		this.errorMessage.setLink(AppConstants.BLOG_POST_URL);

		int statusCode = this.errorMessage.getStatus();
		String developerMessage = this.errorMessage.getDeveloperMessage();
		String message = this.errorMessage.getMessage();
		
		logger.info("statusCode: " + statusCode);
		logger.info("developerMessage: " + developerMessage);
		logger.info("message: " + message);
		
		ResponseBuilder responseBuilder = Response.status(statusCode);
		ResponseBuilder responseBuilderEntity = responseBuilder.entity(this.errorMessage);
		ResponseBuilder responseBuilderEntityType = responseBuilderEntity.type(MediaType.APPLICATION_JSON);
		Response response = responseBuilderEntityType.build();
		
		return response;
	}

	private void setHttpStatus(Throwable ex) {

		int statusCode = 0;
		String reasonPhrase = null;

		logger.info("setHttpStatus()");
		// logger.error(ex);

		if (ex instanceof WebApplicationException) {
			// NICE way to combine both of methods, say it in the blog
			
			this.errorMessage = new ErrorMessage();
			
			statusCode = ((WebApplicationException) ex).getResponse()
					.getStatus();
			reasonPhrase = Response.Status.NO_CONTENT.getReasonPhrase();
		}
		if (ex instanceof PersistenceException) {
			this.errorMessage = new ErrorMessage(new PersistenceException(ex));
			statusCode = Response.Status.NO_CONTENT.getStatusCode();
			reasonPhrase = Response.Status.NO_CONTENT.getReasonPhrase();
		} else {
			this.errorMessage = new ErrorMessage();
			statusCode = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
			reasonPhrase = Response.Status.NO_CONTENT.getReasonPhrase();
		}

		this.errorMessage.setStatus(statusCode);

		//logger.info("statusCode: " + statusCode);
		//logger.info("reasonPhrase: " + reasonPhrase);
	}
}
