package com.sdi.client.util;

import com.sdi.client.model.User;

public class Session {

	private static Session INSTANCE = null;

	private User user;

	private Session() {
	};

	public static Session getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Session();
		}
		return INSTANCE;
	}

	public User getUserSession() {
		return user;
	}

	public void setUserSession(User user) {
		this.user = user;
	}
}
