package eu.kielczewski.example.util;

import eu.kielczewski.example.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserUtil {

    private static final long ID = 0;
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    private UserUtil() {
    }

    public static User createUser() {
        return new User(ID, USERNAME, PASSWORD);
    }

    public static List<User> createUserList(int howMany) {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            userList.add(new User(ID, "#" + i + USERNAME + i, PASSWORD));
        }
        return userList;
    }

}
