package atm;

public class Account {
	private String userId;
	private String accNumber;
	private int money;
	
	public Account(String userId, String accNumber, int money) {
		this.userId = userId;
		this.accNumber = accNumber;
		this.money = 0;
	}
	
	public String getUserId() {
		return this.userId;
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

	@Override
	public String toString() {
		return "userId :" + userId + "\n accNumber :" + accNumber + "\n money :" + money;
	}
	
	
}
