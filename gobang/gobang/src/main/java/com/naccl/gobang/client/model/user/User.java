package com.naccl.gobang.client.model.user;

/**
 * @Description: 用户信息
 * @Author: Naccl
 * @Date: 2020-04-08
 */
public class User {
	private static volatile User user = null;
	private String username;
	private boolean vip = false;

	private User(String username) {
		this.username = username;
	}

	public static User getUser(String username) {
		if (user == null) {
			synchronized (User.class) {
				if (user == null) {
					user = new User(username);
				}
			}
		}
		return user;
	}

	public String getUsername() {
		return username;
	}

	public boolean isVip() {
		return vip;
	}

	public void setVip(boolean vip) {
		this.vip = vip;
	}
}
