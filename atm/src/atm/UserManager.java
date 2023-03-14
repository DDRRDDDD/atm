package atm;

import java.util.ArrayList;

public class UserManager {
	private static ArrayList<User> list = new ArrayList<>();
	
	private UserManager() {
		throw new AssertionError("안된다 돌아가");
	}
	
	//Create
	public static void addUser(User user) {
		list.add(user);
	}
	
	//Read
	public static User getUser(int index) {
		if(list.size() < 1)
			return null;
		
		User user = list.get(index);
		
		String id = user.getId();
		String password = user.getPassword();
		String name = user.getName();
		
		return new User(id, password, name);
	}
	
	//Update
	public static void setUser(int index, User user) {
		if(list.size() < 1)
			return;
		
		list.set(index, user);
	}
	
	//Delete
	public static void deleteUser(int index) {
		list.remove(index);
	}
}
