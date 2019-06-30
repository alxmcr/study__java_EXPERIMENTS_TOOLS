package excepciones;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

@Provider
public class AppExceptionMapper implements ExceptionMapper<AppException> {

	private static Logger logger = Logger
			.getLogger(AppExceptionMapper.class);
	
	public Response toResponse(AppException ex) {
		logger.info("toResponse()");
		
		logger.error(ex);
		
		return Response.status(ex.getStatus())
				.entity(new ErrorMessage(ex))
				.type(MediaType.APPLICATION_JSON).
				build();
	}

}
