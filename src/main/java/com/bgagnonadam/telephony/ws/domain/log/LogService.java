package com.bgagnonadam.telephony.ws.domain.log;

import com.bgagnonadam.telephony.ws.api.log.dto.LogDto;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class LogService {
  private static final Logger logger = Logger.getLogger(LogService.class.getName());

  private LogRepository logRepository;
  private LogAssembler logAssembler;

  public LogService(LogRepository logRepository, LogAssembler logAssembler) {
    this.logRepository = logRepository;
    this.logAssembler = logAssembler;
  }

  public List<LogDto> findAllLogs() {
    logger.info("Get all logs");
    List<Log> logs = logRepository.findAll();
    return logs.stream().map(logAssembler::create).collect(Collectors.toList());
  }

  public void deleteLog(String id) {
    logger.info(String.format("Delete log with id %s", id));
    logRepository.remove(id);
  }
}
