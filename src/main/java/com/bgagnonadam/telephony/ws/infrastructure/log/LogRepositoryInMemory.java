package com.bgagnonadam.telephony.ws.infrastructure.log;

import com.bgagnonadam.telephony.ws.domain.log.Log;
import com.bgagnonadam.telephony.ws.domain.log.LogRepository;
import jersey.repackaged.com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogRepositoryInMemory implements LogRepository {

  private Map<String, Log> logs = new HashMap<>();

  @Override
  public List<Log> findAll() {
    if (logs.values().size() > 0) {
      return Lists.newArrayList(logs.values());
    } else {
      return new ArrayList<>();
    }
  }


  @Override
  public void save(Log log) {
    logs.put(log.getId(), log);
  }

  @Override
  public void remove(String id) {
    logs.remove(id);
  }
}
