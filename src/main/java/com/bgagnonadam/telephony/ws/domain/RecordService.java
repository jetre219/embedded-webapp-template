package com.bgagnonadam.telephony.ws.domain;

import com.bgagnonadam.telephony.ws.api.dto.RecordDto;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class RecordService {
  Logger logger = Logger.getLogger(RecordService.class.getName());

  private RecordRepository recordRepository;
  private RecordAssembler recordAssembler;

  public RecordService(RecordRepository recordRepository, RecordAssembler recordAssembler) {
    this.recordRepository = recordRepository;
    this.recordAssembler = recordAssembler;
  }

  public List<RecordDto> findAllRecords() {
    logger.info("Get all records");
    List<Record> records = recordRepository.findAll();
    return records.stream().map(recordAssembler::create).collect(Collectors.toList());
  }

  public RecordDto findRecord(String id) {
    logger.info(String.format("Get record with id %s", id));
    Record record = recordRepository.findById(id);
    return recordAssembler.create(record);
  }

  public void addRecord(RecordDto recordDto) {
    logger.info(String.format("Add new record %s", recordDto));
    Record record = recordAssembler.create(recordDto);
    record.setId(UUID.randomUUID().toString());
    recordRepository.save(record);
  }

  public void updateRecord(String id, RecordDto recordDto)
          throws RecordNotFoundException {
    logger.info(String.format("Update record with id %s", id));
    Record record = recordAssembler.create(recordDto);
    record.setId(id);
    recordRepository.update(record);
  }


  public void deleteRecord(String id) {
    logger.info(String.format("Delete record with id %s", id));
    recordRepository.remove(id);
  }


}
