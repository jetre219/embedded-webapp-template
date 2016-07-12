package com.bgagnonadam.telephony.ws.infrastructure;

import com.bgagnonadam.telephony.ws.domain.Contact;
import com.bgagnonadam.telephony.ws.domain.ContactNotFoundException;
import com.bgagnonadam.telephony.ws.domain.ContactRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ContactRepositoryInMemory implements ContactRepository {

  private Map<String, Contact> contacts = new HashMap<>();

  @Override
  public List<Contact> findAll() {
    if (contacts.values().size() > 0) {
      return contacts.values().stream().collect(Collectors.toList());
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
