package com.bgagnonadam.telephony.ws.domain.log;

import com.bgagnonadam.telephony.ws.api.log.dto.LogDto;

public class LogAssembler {
  public LogDto create(Log log) {
    LogDto logDto = new LogDto();
    logDto.id = log.getId();
    logDto.telephoneNumber = log.getTelephoneNumber();
    logDto.date = log.getDate();
    logDto.durationInSeconds = log.getDurationInSeconds();
    return logDto;
  }
}
