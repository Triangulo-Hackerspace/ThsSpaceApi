package br.net.triangulohackerspace.spaceapi.util;

import java.util.ArrayList;
import java.util.List;

import br.net.triangulohackerspace.spaceapi.domain.User;

public class UserUtil {

	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";

	private UserUtil() {
	}

	public static User createUser() {
		return new User(USERNAME, PASSWORD);
	}

	public static List<User> createUserList(int howMany) {
		List<User> userList = new ArrayList<>();
		for (int i = 0; i < howMany; i++) {
			userList.add(new User("#" + i + USERNAME + i, PASSWORD));
		}
		return userList;
	}

	public static User getUser() {
		return new User("rogerio", "sena");
	}

}
