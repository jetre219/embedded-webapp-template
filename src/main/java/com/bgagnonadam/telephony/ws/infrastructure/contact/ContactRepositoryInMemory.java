package com.bgagnonadam.telephony.ws.infrastructure.contact;

import com.bgagnonadam.telephony.ws.domain.contact.Contact;
import com.bgagnonadam.telephony.ws.domain.contact.ContactNotFoundException;
import com.bgagnonadam.telephony.ws.domain.contact.ContactRepository;
import jersey.repackaged.com.google.common.collect.Lists;

import java.util.*;

public class ContactRepositoryInMemory implements ContactRepository {

  private Map<String, Contact> contacts = new HashMap<>();

  @Override
  public List<Contact> findAll() {
    if (contacts.values().size() > 0) {
      return Lists.newArrayList(contacts.values());
    } else {
      return new ArrayList<>();
    }
  }

  @Override
  public Contact findById(String id) {
    return contacts.get(id);
  }

  @Override
  public void update(Contact contact)
          throws ContactNotFoundException {
    Contact foundContact = contacts.get(contact.getId());
    if (foundContact != null) {
      contacts.put(contact.getId(), contact);
    } else {
      throw new ContactNotFoundException("Contact not found, cannot be updated");
    }
  }

  @Override
  public void save(Contact contact) {
    contacts.put(contact.getId(), contact);
  }

  @Override
  public void remove(String id) {
    contacts.remove(id);
  }
}
