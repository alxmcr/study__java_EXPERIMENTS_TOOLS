package com.alxmcr.echo.boundary;

import java.sql.Timestamp;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.alxmcr.echo.control.EchoManager;
import com.alxmcr.echo.entity.Echo;

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
        return locator + "\n" + echo.toString();
    }
}
