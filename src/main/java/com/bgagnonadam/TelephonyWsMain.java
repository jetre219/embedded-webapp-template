package com.bgagnonadam;

import com.bgagnonadam.telephony.ws.api.RecordResource;
import com.bgagnonadam.telephony.ws.api.RecordResourceImpl;
import com.bgagnonadam.telephony.ws.domain.Record;
import com.bgagnonadam.telephony.ws.domain.RecordAssembler;
import com.bgagnonadam.telephony.ws.domain.RecordRepository;
import com.bgagnonadam.telephony.ws.domain.RecordService;
import com.bgagnonadam.telephony.ws.infrastructure.CORSResponseFilter;
import com.bgagnonadam.telephony.ws.infrastructure.RecordDevDataFactory;
import com.bgagnonadam.telephony.ws.infrastructure.RecordRepositoryInMemory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
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
    RecordResource recordResource = createRecordResource();


    // Setup context (JERSEY + JETTY)
    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/");
    ResourceConfig resourceConfig = ResourceConfig.forApplication(new Application() {
      @Override
      public Set<Object> getSingletons() {
        HashSet<Object> resources = new HashSet<>();
        // Add resources to context
        resources.add(recordResource);
        return resources;
      }
    });
    resourceConfig.register(CORSResponseFilter.class);

    ServletContainer servletContainer = new ServletContainer(resourceConfig);
    ServletHolder servletHolder = new ServletHolder(servletContainer);
    context.addServlet(servletHolder, "/*");

    // Setup http server
    Server server = new Server(8090);
    server.setHandler(context);

    try {
      server.start();
      server.join();
    } finally {
      server.destroy();
    }
  }

  private static RecordResource createRecordResource() {
    // Setup resources' dependencies (DOMAIN + INFRASTRUCTURE)
    RecordRepository recordRepository = new RecordRepositoryInMemory();

    // For development ease
    RecordDevDataFactory recordDevDataFactory = new RecordDevDataFactory();
    List<Record> records = recordDevDataFactory.createMockData();
    records.stream().forEach(s -> recordRepository.save(s));

    RecordAssembler recordAssembler = new RecordAssembler();
    RecordService recordService = new RecordService(recordRepository, recordAssembler);

    return new RecordResourceImpl(recordService);
  }
}
