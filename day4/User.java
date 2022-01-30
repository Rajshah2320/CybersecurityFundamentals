package day4;

public class User {

	 String uid;
	 String password;
	 String salt;
	 String hashed_value;
	
	public User(String uid,String password, String salt) {
		this.uid=uid;
		this.password=password;
		this.salt=salt;
	}
	
	public String getHashed_value() {
		return hashed_value;
	}
	public void setHashed_value(String hashed_value) {
		this.hashed_value = hashed_value;
	}

	
}
