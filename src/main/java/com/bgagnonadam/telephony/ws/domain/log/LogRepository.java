package com.bgagnonadam.telephony.ws.domain.log;

import java.util.List;

public interface LogRepository {
  List<Log> findAll();

  void save(Log log);

  void remove(String id);
}
