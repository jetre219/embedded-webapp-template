package com.bgagnonadam.telephony.ws.infrastructure;

import com.bgagnonadam.telephony.ws.domain.Record;
import com.bgagnonadam.telephony.ws.domain.RecordRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class RecordRepositoryInMemory implements RecordRepository {

  Map<String, Record> records = new HashMap<>();

  @Override
  public List<Record> findAll() {
    if (records.values().size() > 0) {
      return records.values().stream().collect(Collectors.toList());
    } else {
      return new ArrayList<>();
    }
  }

  @Override
  public Record findById(String id) {
    return records.get(id);
  }

  @Override
  public void update(Record record) {
    Record foundRecord = records.get(record.getId());
    if (foundRecord != null) {
      records.put(record.getId(), record);
    } else {
      throw new IllegalArgumentException("Record not found, cannot be updated");
    }
  }

  @Override
  public void save(Record record) {
    records.put(record.getId(), record);
  }

  @Override
  public void remove(String id) {
    records.remove(id);
  }
}
