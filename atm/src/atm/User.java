package atm;

import java.util.Objects;

public class User {
	private String id;
	private String password;
	private String name;
	
	public User(String id, String password, String name) {
		this.id = id;
		this.password = password;
		this.name = name;
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		User user = (User)obj;
		boolean idIsTrue = Objects.equals(this.id, user.id);
		boolean nameIsTrue = Objects.equals(this.name, user.name);
		boolean passwordIsTrue = Objects.equals(this.name, user.name);
		
		return idIsTrue && nameIsTrue && passwordIsTrue;
	}
	
	
}
