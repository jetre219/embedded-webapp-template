package com.bgagnonadam.telephony.ws.api.log;

import com.bgagnonadam.telephony.ws.api.log.dto.LogDto;
import com.bgagnonadam.telephony.ws.domain.log.LogService;

import java.util.List;

public class LogResourceImpl implements LogResource {

  private LogService logService;

  public LogResourceImpl(LogService logService) {
    this.logService = logService;
  }

  @Override
  public List<LogDto> getLogs() {
    return logService.findAllLogs();
  }

  @Override
  public void deleteLog(String id) {
    logService.deleteLog(id);
  }
}
