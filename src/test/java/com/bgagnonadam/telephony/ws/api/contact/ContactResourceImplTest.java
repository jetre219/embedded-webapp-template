package com.bgagnonadam.telephony.ws.api.contact;

import com.bgagnonadam.telephony.ws.api.contact.ContactResource;
import com.bgagnonadam.telephony.ws.api.contact.ContactResourceImpl;
import com.bgagnonadam.telephony.ws.api.contact.dto.ContactDto;
import com.bgagnonadam.telephony.ws.domain.contact.ContactService;
import jersey.repackaged.com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ContactResourceImplTest {
    @Mock
    private ContactService contactService;
    @Mock
    private ContactDto contactDto;

    private ContactResource contactResource;


    @Before
    public void setUp() throws Exception {
        contactResource = new ContactResourceImpl(contactService);
    }

    @Test
    public void whenFindAllContacts_thenDelegateToService() {
        // given
        BDDMockito.given(contactService.findAllContacts()).willReturn(Lists.newArrayList(contactDto));

        // when
        List<ContactDto> contactDtos = contactResource.getContacts();

        // then
        assertThat(contactDtos, org.hamcrest.Matchers.hasItem(contactDto));
        Mockito.verify(contactService).findAllContacts();
    }

}