package com.bgagnonadam.telephony.ws.api;

import com.bgagnonadam.telephony.ws.api.dto.RecordDto;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Bruno on 2016-03-05.
 */
@Path("/home")
public interface TelephonyResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  String getEntries();

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{id}")
  String getEntry(@PathParam("id") String id);

  @POST
  void addEntry();

  @PUT
  @Path("{id}")
  void updateEntry(@PathParam("id") String id,
                   RecordDto recordDto);

  @DELETE
  @Path("{id}")
  void deleteEntry(@PathParam("id") String id);
}
