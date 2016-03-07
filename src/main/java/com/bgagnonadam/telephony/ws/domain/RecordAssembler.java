package com.bgagnonadam.telephony.ws.domain;

import com.bgagnonadam.telephony.ws.api.dto.RecordDto;

public class RecordAssembler {
  public Record create(RecordDto recordDto) {
    Record record = new Record();
    record.setAddress(recordDto.getAddress());
    record.setTelephoneNumber(recordDto.getTelephoneNumber());
    record.setName(recordDto.getName());
    return record;
  }

  public RecordDto create(Record record) {
    RecordDto recordDto = new RecordDto();
    recordDto.setAddress(record.getAddress());
    recordDto.setTelephoneNumber(record.getTelephoneNumber());
    recordDto.setName(record.getName());
    recordDto.setId(record.getId());
    return recordDto;
  }
}
