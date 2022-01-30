package day4;

import java.util.HashMap;

import day2.MD5;

public class Authentication {

	static HashMap<String, User> listOfUsers;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		listOfUsers = new HashMap<>();

		Authentication au = new Authentication();
		au.createUser("Raj", "123", "Shah");
		au.createUser("Alice", "456", "Alice");
		au.createUser("Bob", "789", "Bob");

		au.printUsers();

		au.searchUser("Raj", "123");
		au.searchUser("Raj", "Shah");

	}

	public void printUsers() {
		for (User en : listOfUsers.values()) {
			System.out.println(en.uid + en.hashed_value);
		}
	}

	public void createUser(String uid, String password, String salt) {

		User user = new User(uid, password, salt);
		user.setHashed_value(MD5.getMd5(password + salt));
		listOfUsers.put(uid, user);
	}

	public void searchUser(String uid, String password) {
		if (listOfUsers.containsKey(uid)) {
			User user = listOfUsers.get(uid);
			if (user.getHashed_value().equals(MD5.getMd5(password + user.salt)))
				System.out.println("User is authenticated");
			else
				System.out.println("User is not authenticated");
		} else {
			System.out.println("User does not exist");
		}
	}

}
