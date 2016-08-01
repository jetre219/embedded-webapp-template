package com.bgagnonadam;

import com.bgagnonadam.telephony.ws.api.calllog.CallLogResource;
import com.bgagnonadam.telephony.ws.api.calllog.CallLogResourceImpl;
import com.bgagnonadam.telephony.ws.api.contact.ContactResource;
import com.bgagnonadam.telephony.ws.api.contact.ContactResourceImpl;
import com.bgagnonadam.telephony.ws.domain.calllog.CallLog;
import com.bgagnonadam.telephony.ws.domain.calllog.CallLogAssembler;
import com.bgagnonadam.telephony.ws.domain.calllog.CallLogRepository;
import com.bgagnonadam.telephony.ws.domain.calllog.CallLogService;
import com.bgagnonadam.telephony.ws.domain.contact.Contact;
import com.bgagnonadam.telephony.ws.domain.contact.ContactAssembler;
import com.bgagnonadam.telephony.ws.domain.contact.ContactRepository;
import com.bgagnonadam.telephony.ws.domain.contact.ContactService;
import com.bgagnonadam.telephony.ws.http.CORSResponseFilter;
import com.bgagnonadam.telephony.ws.infrastructure.calllog.CallLogDevDataFactory;
import com.bgagnonadam.telephony.ws.infrastructure.calllog.CallLogRepositoryInMemory;
import com.bgagnonadam.telephony.ws.infrastructure.contact.ContactDevDataFactory;
import com.bgagnonadam.telephony.ws.infrastructure.contact.ContactRepositoryInMemory;
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
@SuppressWarnings("all")
public class TelephonyWsMain {
  public static boolean isDev = true; // Would be a JVM argument or in a .property file

  public static void main(String[] args)
          throws Exception {

    // Setup resources (API)
    ContactResource contactResource = createContactResource();
    CallLogResource callLogResource = createCallLogResource();

    // Setup API context (JERSEY + JETTY)
    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/api/");
    ResourceConfig resourceConfig = ResourceConfig.forApplication(new Application() {
      @Override
      public Set<Object> getSingletons() {
        HashSet<Object> resources = new HashSet<>();
        // Add resources to context
        resources.add(contactResource);
        resources.add(callLogResource);
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
    if (isDev) {
      ContactDevDataFactory contactDevDataFactory = new ContactDevDataFactory();
      List<Contact> contacts = contactDevDataFactory.createMockData();
      contacts.stream().forEach(contactRepository::save);
    }

    ContactAssembler contactAssembler = new ContactAssembler();
    ContactService contactService = new ContactService(contactRepository, contactAssembler);

    return new ContactResourceImpl(contactService);
  }

  private static CallLogResource createCallLogResource() {
    // Setup resources' dependencies (DOMAIN + INFRASTRUCTURE)
    CallLogRepository callLogRepository = new CallLogRepositoryInMemory();

    // For development ease
    if (isDev) {
      CallLogDevDataFactory callLogDevDataFactory = new CallLogDevDataFactory();
      List<CallLog> callLogs = callLogDevDataFactory.createMockData();
      callLogs.stream().forEach(callLogRepository::save);
    }

    CallLogAssembler callLogAssembler = new CallLogAssembler();
    CallLogService callLogService = new CallLogService(callLogRepository, callLogAssembler);

    return new CallLogResourceImpl(callLogService);
  }
}
