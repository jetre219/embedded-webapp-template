package com.bgagnonadam;

import com.bgagnonadam.telephony.ws.api.ContactResource;
import com.bgagnonadam.telephony.ws.api.ContactResourceImpl;
import com.bgagnonadam.telephony.ws.domain.Contact;
import com.bgagnonadam.telephony.ws.domain.ContactAssembler;
import com.bgagnonadam.telephony.ws.domain.ContactRepository;
import com.bgagnonadam.telephony.ws.domain.ContactService;
import com.bgagnonadam.telephony.ws.infrastructure.CORSResponseFilter;
import com.bgagnonadam.telephony.ws.infrastructure.ContactDevDataFactory;
import com.bgagnonadam.telephony.ws.infrastructure.ContactRepositoryInMemory;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * RESTApi setup without using DI or spring
 */
public class TelephonyWsMain {
  public static void main(String[] args)
          throws Exception {

    // Setup resources (API)
    ContactResource contactResource = createContactResource();


    // Setup API context (JERSEY + JETTY)
    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/api/");
    ResourceConfig resourceConfig = ResourceConfig.forApplication(new Application() {
      @Override
      public Set<Object> getSingletons() {
        HashSet<Object> resources = new HashSet<>();
        // Add resources to context
        resources.add(contactResource);
        return resources;
      }
    });
    resourceConfig.register(CORSResponseFilter.class);

    ServletContainer servletContainer = new ServletContainer(resourceConfig);
    ServletHolder servletHolder = new ServletHolder(servletContainer);
    context.addServlet(servletHolder, "/*");

    // Setup static file context (WEBAPP)
    WebAppContext webapp = new WebAppContext();
    webapp.setResourceBase(webapp.getClass().getClassLoader().getResource("webapp").toExternalForm());
    webapp.setContextPath("/");
    webapp.setParentLoaderPriority(true);

    // Setup http server
    ContextHandlerCollection contexts = new ContextHandlerCollection();
    contexts.setHandlers(new Handler[] { context, webapp });
    Server server = new Server(8080);
    server.setHandler(contexts);

    try {
      server.start();
      server.join();
    } finally {
      server.destroy();
    }
  }

  private static ContactResource createContactResource() {
    // Setup resources' dependencies (DOMAIN + INFRASTRUCTURE)
    ContactRepository contactRepository = new ContactRepositoryInMemory();

    // For development ease
    ContactDevDataFactory contactDevDataFactory = new ContactDevDataFactory();
    List<Contact> contacts = contactDevDataFactory.createMockData();
    contacts.stream().forEach(contactRepository::save);

    ContactAssembler contactAssembler = new ContactAssembler();
    ContactService contactService = new ContactService(contactRepository, contactAssembler);

    return new ContactResourceImpl(contactService);
  }
}
