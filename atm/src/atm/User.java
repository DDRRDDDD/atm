package atm;

import java.util.ArrayList;

public class User {
	private String id;
	private String password;
	private String name;
	
	private ArrayList<Account> acc;
	
	public User(String id, String password, String name) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.acc = new ArrayList<Account>();
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Account getAcc(int index) {
		if(index > this.acc.size() - 1)
			return null;
		
		return acc.get(index);
	}
	
	public void addAcc(Account acc) {
		
		this.acc.add(acc);
	}
	
}
