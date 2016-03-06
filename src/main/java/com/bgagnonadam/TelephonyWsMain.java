package com.bgagnonadam;

import com.bgagnonadam.telephony.ws.api.TelephonyResourceImpl;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * RESTApi setup without using DI or spring
 */
public class TelephonyWsMain {
  public static void main(String[] args)
          throws Exception {

    // Setup resources' dependencies

    // Setup resources
    TelephonyResourceImpl telephonyRepository = new TelephonyResourceImpl();

    // Setup context
    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/");
    ResourceConfig resourceConfig = ResourceConfig.forApplication(new Application() {
      @Override
      public Set<Object> getSingletons() {
        HashSet<Object> resources = new HashSet<>();
        // Add resources to context
        resources.add(telephonyRepository);
        return resources;
      }
    });
    ServletContainer servletContainer = new ServletContainer(resourceConfig);
    ServletHolder servletHolder = new ServletHolder(servletContainer);
    context.addServlet(servletHolder, "/*");

    // Setup http server
    Server server = new Server(8080);
    server.setHandler(context);

    try {
      server.start();
      server.join();
    } finally {
      server.destroy();
    }
  }
}
