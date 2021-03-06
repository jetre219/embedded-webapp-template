package com.bgagnonadam.telephony.ws.domain.contact;

import com.bgagnonadam.telephony.ws.api.contact.dto.ContactDto;
import jersey.repackaged.com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceTest {

  @Mock
  private Contact contact;
  @Mock
  private ContactDto contactDto;
  @Mock
  private ContactRepository contactRepository;
  @Mock
  private ContactAssembler contactAssembler;

  private ContactService contactService;

  @Before
  public void setUp()
          throws Exception {
    contactService = new ContactService(contactRepository, contactAssembler);
  }

  @Test
  public void givenContactsInRepository_whenFindAllContacts_thenReturnDtos()
          throws Exception {
    // given
    BDDMockito.given(contactRepository.findAll()).willReturn(Lists.newArrayList(contact));
    BDDMockito.given(contactAssembler.create(contact)).willReturn(contactDto);

    // when
    List<ContactDto> contactDtos = contactService.findAllContacts();

    // then
    assertThat(contactDtos, org.hamcrest.Matchers.hasItem(contactDto));
    Mockito.verify(contactRepository).findAll();
    Mockito.verify(contactAssembler).create(org.mockito.Matchers.eq(contact));
  }

}