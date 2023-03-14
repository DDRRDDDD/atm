package atm;

public class Account {
	private String id;
	private String accNumber;
	private int money;
	
	public Account(String id, String accNumber) {
		this.id = id;
		this.accNumber = accNumber;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getAccNumber() {
		return this.accNumber;
	}
	
	public int getMoney() {
		return this.money;
	}
	
	public void setMoney(int money) {
		this.money = money;
	}
}
