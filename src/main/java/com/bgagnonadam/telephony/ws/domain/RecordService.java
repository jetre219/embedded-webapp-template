package com.bgagnonadam.telephony.ws.domain;

import com.bgagnonadam.telephony.ws.api.dto.RecordDto;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by Bruno on 2016-03-06.
 */
public class RecordService {

  private RecordRepository recordRepository;
  private RecordAssembler recordAssembler;

  public RecordService(RecordRepository recordRepository, RecordAssembler recordAssembler) {
    this.recordRepository = recordRepository;
    this.recordAssembler = recordAssembler;
  }

  public List<RecordDto> findAllRecords() {
    List<Record> records = recordRepository.findAll();
    return records.stream().map(recordAssembler::create).collect(Collectors.toList());
  }

  public RecordDto findRecord(String id) {
    Record record = recordRepository.findById(id);
    return recordAssembler.create(record);
  }

  public void addRecord(RecordDto recordDto) {
    Record record = recordAssembler.create(recordDto);
    record.setId(UUID.randomUUID().toString());
    recordRepository.save(record);
  }

  public void updateRecord(String id, RecordDto recordDto)
          throws RecordNotFoundException {
    Record record = recordAssembler.create(recordDto);
    record.setId(id);
    recordRepository.update(record);
  }


  public void deleteRecord(String id) {
    recordRepository.remove(id);
  }


}
