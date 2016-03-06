package com.bgagnonadam.telephony.ws.api;

import com.bgagnonadam.telephony.ws.api.dto.RecordDto;

/**
 * Created by Bruno on 2016-03-05.
 */
public class TelephonyResourceImpl implements TelephonyResource {


  @Override
  public String getEntries() {
    return "{'hi':'farts'}";
  }

  @Override
  public String getEntry(String id) {
    return "{'hi':'"+id+"'}";
  }

  @Override
  public void addEntry() {

  }

  @Override
  public void updateEntry(String id, RecordDto recordDto) {
    System.out.println(recordDto.toString());
  }

  @Override
  public void deleteEntry(String id) {

  }
}
