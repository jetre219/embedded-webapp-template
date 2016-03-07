package com.bgagnonadam.telephony.ws.domain;

import java.util.List;

public interface RecordRepository {
  List<Record> findAll();

  Record findById(String id);

  void update(Record record)
          throws RecordNotFoundException;

  void save(Record record);

  void remove(String id);
}
