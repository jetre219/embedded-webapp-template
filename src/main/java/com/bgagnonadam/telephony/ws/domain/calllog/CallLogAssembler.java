package com.bgagnonadam.telephony.ws.domain.calllog;

import com.bgagnonadam.telephony.ws.api.calllog.dto.CallLogDto;

public class CallLogAssembler {
  public CallLogDto create(CallLog callLog) {
    CallLogDto callLogDto = new CallLogDto();
    callLogDto.id = callLog.getId();
    callLogDto.telephoneNumber = callLog.getTelephoneNumber();
    callLogDto.date = callLog.getDate();
    callLogDto.durationInSeconds = callLog.getDurationInSeconds();
    return callLogDto;
  }
}
