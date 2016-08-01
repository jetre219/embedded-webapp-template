package com.bgagnonadam.telephony.ws.api.log;


import com.bgagnonadam.telephony.ws.api.log.dto.LogDto;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/telephony/logs")
public interface LogResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  List<LogDto> getLogs();

  @DELETE
  @Path("{id}")
  void deleteLog(@PathParam("id") String id);
}
