package com.bgagnonadam.telephony.ws.infrastructure.log;

import com.bgagnonadam.telephony.ws.domain.log.Log;
import jersey.repackaged.com.google.common.collect.Lists;

import java.util.List;

public class LogDevDataFactory {

  public List<Log> createMockData() {
    List<Log> logs = Lists.newArrayList();
    Log log1 = new Log();
    log1.setId("1");
    log1.setTelephoneNumber("514-999-0000");
    log1.setDate("2016-07-31T16:45:00Z");
    log1.setDurationInSeconds(65);
    logs.add(log1);

    Log log2 = new Log();
    log2.setId("2");
    log2.setTelephoneNumber("418-682-3092");
    log2.setDate("2016-06-31T15:29:00Z");
    log2.setDurationInSeconds(99);
    logs.add(log2);

    Log log3 = new Log();
    log3.setId("3");
    log3.setTelephoneNumber("581-671-0992");
    log3.setDate("2016-07-30T08:32:33Z");
    log3.setDurationInSeconds(22);
    logs.add(log3);

    return logs;
  }
}
