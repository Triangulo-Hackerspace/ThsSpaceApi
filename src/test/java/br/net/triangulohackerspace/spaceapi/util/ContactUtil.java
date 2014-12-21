package br.net.triangulohackerspace.spaceapi.util;

import java.util.ArrayList;
import java.util.List;

import br.net.triangulohackerspace.spaceapi.domain.Contact;

public class ContactUtil {

	private static final String EMAIL = "e@xample.com";
	private static final String TWITTER = "@example";
	private static final String IRC = "irc://irc.freenode.net/example";
	private static final String ML = "public@lists.example.com";
	private static final String ISSUEMAIL = "ZUB4YW1wbGUuY29tCg==";

	private ContactUtil() {
	}

	public static Contact createContact() {
		return getContact();
	}

	public static List<Contact> createContactList(int howMany) {
		List<Contact> contactList = new ArrayList<>();
		for (int i = 0; i < howMany; i++) {
			contactList.add(getContact());
		}
		return contactList;
	}

	public static Contact getContact() {
		return new Contact(EMAIL, TWITTER, IRC, ML, ISSUEMAIL,
				SpaceUtil.getSpace());
	}
}
