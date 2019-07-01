#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Configuration for a JAX-RS endpoint. Delete it, if you don't use JAX-RS.
 */
@ApplicationPath("api")
public class JAXRSConfiguration extends Application { }
