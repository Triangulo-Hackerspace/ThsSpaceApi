package br.net.triangulohackerspace.spaceapi.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.net.triangulohackerspace.spaceapi.domain.Contact;
import br.net.triangulohackerspace.spaceapi.repository.ContactRepository;
import br.net.triangulohackerspace.spaceapi.service.ContactService;
import br.net.triangulohackerspace.spaceapi.service.exception.AlreadyExistsException;
import br.net.triangulohackerspace.spaceapi.service.impl.ContactServiceImpl;
import br.net.triangulohackerspace.spaceapi.util.ContactUtil;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceImplTest {

    @Mock
    private ContactRepository contactRepository;

    private ContactService contactService;
    @Before
    public void setUp() throws Exception {
    	contactService = new ContactServiceImpl(contactRepository);
    }

    @Test
    public void shouldSaveNewContact_GivenThereDoesNotExistOneWithTheSameId_ThenTheSavedContactShouldBeReturned() throws Exception {
        final Contact savedContact = stubRepositoryToReturnContactOnSave();
        final Contact contact = ContactUtil.createContact();
        final Contact returnedContact = contactService.save(contact);
        // verify repository was called with contact
        verify(contactRepository, times(1)).save(contact);
        assertEquals("Returned contact should come from the repository", savedContact, returnedContact);
    }

    private Contact stubRepositoryToReturnContactOnSave() {
        Contact contact = ContactUtil.createContact();
        when(contactRepository.save(any(Contact.class))).thenReturn(contact);
        return contact;
    }

    @Test
    public void shouldSaveNewContact_GivenThereExistsOneWithTheSameId_ThenTheExceptionShouldBeThrown() throws Exception {
        stubRepositoryToReturnExistingContact();
        try {
        	contactService.save(ContactUtil.createContact());
            fail("Expected exception");
        } catch (AlreadyExistsException ignored) {
        }
        verify(contactRepository, never()).save(any(Contact.class));
    }

    private void stubRepositoryToReturnExistingContact() {
        final Contact contact = ContactUtil.createContact();
        when(contactRepository.findOne(contact.getId())).thenReturn(contact);
    }

    @Test
    public void shouldListAllContacts_GivenThereExistSome_ThenTheCollectionShouldBeReturned() throws Exception {
        stubRepositoryToReturnExistingContacts(10);
        Collection<Contact> list = contactService.getList();
        assertNotNull(list);
        assertEquals(10, list.size());
        verify(contactRepository, times(1)).findAll();
    }

    private void stubRepositoryToReturnExistingContacts(int howMany) {
        when(contactRepository.findAll()).thenReturn(ContactUtil.createContactList(howMany));
    }

    @Test
    public void shouldListAllContacts_GivenThereNoneExist_ThenTheEmptyCollectionShouldBeReturned() throws Exception {
        stubRepositoryToReturnExistingContacts(0);
        Collection<Contact> list = contactService.getList();
        assertNotNull(list);
        assertTrue(list.isEmpty());
		verify(contactRepository, times(1)).findAll();
    }

}
