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
import java.util.List;

/**
 * Created by Bruno on 2016-03-05.
 */
@Path("/telephony/record")
public interface RecordResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  List<RecordDto> getRecords();

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{id}")
  RecordDto getRecord(@PathParam("id") String id);

  @POST
  void addRecord(RecordDto recordDto);

  @PUT
  @Path("{id}")
  void updateRecord(@PathParam("id") String id,
                    RecordDto recordDto);

  @DELETE
  @Path("{id}")
  void deleteRecord(@PathParam("id") String id);
}
