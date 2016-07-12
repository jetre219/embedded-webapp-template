package com.bgagnonadam.telephony.ws.domain;

import com.bgagnonadam.telephony.ws.api.dto.ContactDto;

public class ContactAssembler {
  public Contact create(ContactDto contactDto) {
    Contact contact = new Contact();
    contact.setAddress(contactDto.getAddress());
    contact.setTelephoneNumber(contactDto.getTelephoneNumber());
    contact.setName(contactDto.getName());
    return contact;
  }

  public ContactDto create(Contact contact) {
    ContactDto contactDto = new ContactDto();
    contactDto.setAddress(contact.getAddress());
    contactDto.setTelephoneNumber(contact.getTelephoneNumber());
    contactDto.setName(contact.getName());
    contactDto.setId(contact.getId());
    return contactDto;
  }
}
