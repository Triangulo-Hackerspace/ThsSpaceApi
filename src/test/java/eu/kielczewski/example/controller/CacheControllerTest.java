package eu.kielczewski.example.controller;

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

import eu.kielczewski.example.domain.User;
import eu.kielczewski.example.service.BusinessService;
import eu.kielczewski.example.util.UserUtil;

@RunWith(MockitoJUnitRunner.class)
public class CacheControllerTest {

    @Mock
    private BusinessService<User> businessService;

    @Inject
    private UserController userController;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void shouldCreateUser() throws Exception {
        final User savedUser = stubServiceToReturnStoredUser();
        final User user = UserUtil.createUser();
        User returnedUser = userController.createUser(user);
        // verify user was passed to UserService
        verify(businessService, times(1)).save(user);
        assertEquals("Returned user should come from the service", savedUser, returnedUser);
    }

    private User stubServiceToReturnStoredUser() {
        final User user = UserUtil.createUser();
        when(businessService.save(any(User.class))).thenReturn(user);
        return user;
    }


    @Test
    public void shouldListAllUsers() throws Exception {
        stubServiceToReturnExistingUsers(10);
        Collection<User> users = userController.listUsers();
        assertNotNull(users);
        assertEquals(10, users.size());
        // verify user was passed to UserService
        verify(businessService, times(1)).getList();
    }

    private void stubServiceToReturnExistingUsers(int howMany) {
        when(businessService.getList()).thenReturn(UserUtil.createUserList(howMany));
    }

}
