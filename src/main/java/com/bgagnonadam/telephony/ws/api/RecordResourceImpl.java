package com.bgagnonadam.telephony.ws.api;

import com.bgagnonadam.telephony.ws.api.dto.RecordDto;
import com.bgagnonadam.telephony.ws.domain.RecordService;

import java.util.List;

/**
 * Created by Bruno on 2016-03-05.
 */
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
    recordService.updateRecord(id, recordDto);
  }

  @Override
  public void deleteRecord(String id) {
    recordService.deleteRecord(id);
  }
}
