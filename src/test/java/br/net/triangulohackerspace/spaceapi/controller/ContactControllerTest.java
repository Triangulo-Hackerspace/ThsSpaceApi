package br.net.triangulohackerspace.spaceapi.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.net.triangulohackerspace.spaceapi.controller.ContactController;
import br.net.triangulohackerspace.spaceapi.domain.Contact;
import br.net.triangulohackerspace.spaceapi.service.ContactService;
import br.net.triangulohackerspace.spaceapi.util.ContactUtil;

@RunWith(MockitoJUnitRunner.class)
public class ContactControllerTest {

    @Mock
    private ContactService contactService;

    @Inject
    private ContactController contactController;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void shouldCreateContact() throws Exception {
        final Contact savedContact = stubServiceToReturnStoredContact();
        final Contact contact = ContactUtil.createContact();
        Contact returnedContact = contactController.createContact(contact);
        // verify contact was passed to ContactService
        verify(contactService, times(1)).save(contact);
        assertEquals("Returned contact should come from the service", savedContact, returnedContact);
    }

    private Contact stubServiceToReturnStoredContact() {
        final Contact contact = ContactUtil.createContact();
        when(contactService.save(any(Contact.class))).thenReturn(contact);
        return contact;
    }


    @Test
    public void shouldListAllContacts() throws Exception {
        stubServiceToReturnExistingContacts(10);
        Collection<Contact> contacts = contactController.listContacts();
        assertNotNull(contacts);
        assertEquals(10, contacts.size());
        // verify contact was passed to ContactService
        verify(contactService, times(1)).getList();
    }

    private void stubServiceToReturnExistingContacts(int howMany) {
        when(contactService.getList()).thenReturn(ContactUtil.createContactList(howMany));
    }

}
