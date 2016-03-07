package com.bgagnonadam.telephony.ws.api;

import com.bgagnonadam.telephony.ws.api.dto.RecordDto;
import com.bgagnonadam.telephony.ws.domain.RecordNotFoundException;
import com.bgagnonadam.telephony.ws.domain.RecordService;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;

public class RecordResourceImpl implements RecordResource {

  private RecordService recordService;

  public RecordResourceImpl(RecordService recordService) {
    this.recordService = recordService;
  }

  @Override
  public List<RecordDto> getRecords() {
    return recordService.findAllRecords();
  }

  @Override
  public RecordDto getRecord(String id) {
    return recordService.findRecord(id);
  }

  @Override
  public void addRecord(RecordDto recordDto) {
    recordService.addRecord(recordDto);
  }

  @Override
  public void updateRecord(String id, RecordDto recordDto) {
    try {
      recordService.updateRecord(id, recordDto);
    } catch (RecordNotFoundException e) {
      throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                                                .entity(e.getMessage())
                                                .build());
    }
  }

  @Override
  public void deleteRecord(String id) {
    recordService.deleteRecord(id);
  }
}
