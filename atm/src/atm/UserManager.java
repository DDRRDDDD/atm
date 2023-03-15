package atm;

import java.util.ArrayList;
import java.util.Random;

public class UserManager {
	private static ArrayList<User> list = new ArrayList<>();
	
	public void addUser(String name, String pw) {
		String id = getPrimaryId();
		list.add(new User(id, pw, name));
	}
	
	public void addUser(User user) {
		list.add(user);
	}
	
	// 사본을 반환
	public User getUser(int index) {
		if(list.size()-1 < index)
			return null;
		
		User user = list.get(index);
		
		String id = user.getId();
		String password = user.getPassword();
		String name = user.getName();
		
		User tmp = new User(id, password, name);
		int length = AccountManager.LIMMIT;
		for(int i = 0; i < length; i++) {
			Account acc = user.getAcc(i);
			if(acc == null)
				break;
			
			tmp.addAcc(acc);
		}
		return tmp;
	}
	
	public void setUser(int index, User user) {
		if(list.size()-1 < index)
			return;

		list.set(index, user);
	}
	
	public void deleteUser(int index) {
		if(list.size()-1 < index)
			return;
		
		list.remove(index);
	}
	
	private String getPrimaryId() {
		Random ran = new Random();
		String key = "";
		loop:while(true) {
			int randomNum = ran.nextInt(9000) + 1001;
			key = String.valueOf(randomNum);
			
			for(int i = 0; i < list.size(); i++) {
				String str = list.get(i).getId();
				if(str.equals(key))
					continue loop;
			}
			break;
		}
		
		return key;
	}
	
}
