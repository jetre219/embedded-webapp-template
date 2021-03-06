package com.bgagnonadam.telephony.ws.infrastructure.calllog;

import com.bgagnonadam.telephony.ws.domain.calllog.CallLog;
import com.bgagnonadam.telephony.ws.domain.calllog.CallLogRepository;
import jersey.repackaged.com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CallLogRepositoryInMemory implements CallLogRepository {

  private Map<String, CallLog> callLogs = new HashMap<>();

  @Override
  public List<CallLog> findAll() {
      return Lists.newArrayList(callLogs.values());
  }

  @Override
  public void save(CallLog callLog) {
    callLogs.put(callLog.getId(), callLog);
  }

  @Override
  public void remove(String id) {
    callLogs.remove(id);
  }
}
