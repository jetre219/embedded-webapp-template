package com.bgagnonadam.telephony.ws.domain;

import java.util.List;

/**
 * Created by Bruno on 2016-03-06.
 */
public interface RecordRepository {
  List<Record> findAll();

  Record findById(String id);

  void update(Record record);

  void save(Record record);

  void remove(String id);
}
