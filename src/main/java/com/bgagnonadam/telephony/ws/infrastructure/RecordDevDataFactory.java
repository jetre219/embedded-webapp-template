package com.bgagnonadam.telephony.ws.infrastructure;

import com.bgagnonadam.telephony.ws.domain.Record;
import jersey.repackaged.com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by Bruno on 2016-06-20.
 */
public class RecordDevDataFactory {

  public List<Record> createMockData() {
    List<Record> records = Lists.newArrayList();
    Record jobs = new Record();
    jobs.setId("1");
    jobs.setName("Steve Jobs");
    jobs.setAddress("California");
    jobs.setTelephoneNumber("514-999-0000");
    records.add(jobs);

    Record balmer = new Record();
    balmer.setId("2");
    balmer.setName("Steve Balmer");
    balmer.setAddress("Manitoba");
    balmer.setTelephoneNumber("781-888-1111");
    records.add(balmer);

    Record franklin = new Record();
    franklin.setId("3");
    franklin.setName("Benjamin Franklin");
    franklin.setAddress("Washington");
    franklin.setTelephoneNumber("964-543-6475");
    records.add(franklin);

    return records;
  }

}
