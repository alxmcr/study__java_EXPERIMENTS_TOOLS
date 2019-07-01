#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.echo.boundary;

import java.sql.Timestamp;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ${package}.echo.control.EchoManager;
import ${package}.echo.entity.Echo;

@Path("echo")
@Produces(MediaType.TEXT_PLAIN)
@Transactional
public class EchoResource {
    @Inject
    EchoManager manager;

    @GET
    public String defaultEcho() {
        return "Silence";
    }

    @GET
    @Path("{locator}")
    public String echo(@PathParam("locator") String locator) {
        Echo echo = manager.find(locator);
        if (echo == null) {
          Timestamp now = new Timestamp(System.currentTimeMillis());
          echo = new Echo(locator, now);
          manager.insert(echo);
        }
        return locator + "${symbol_escape}n" + echo.toString();
    }
}
