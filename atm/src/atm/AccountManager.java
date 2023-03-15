package atm;

import java.util.ArrayList;
import java.util.Random;

public class AccountManager {
	static final int LIMMIT = 3;
	
	private static ArrayList<Account> list = new ArrayList<>();
	
	// 임시 출력문///////////////////////////////
	public void testrPint() {
//		System.out.println(list.size());
		for(Account i : list) {
			System.out.println(i.getAccNumber());
			System.out.println(i.getMoney());
		}
	}
	///////////////////////////////////////////
	
	public void createAccount(Account acc) {
		list.add(acc);
	}
	
	public Account createAccount(User user) {
		String id = user.getId();
		String accNumber = getAccNumber();
		
		int cnt = 0;
		for(int i = 0; i < list.size(); i++) {
			Account acc = list.get(i);
			String tmp = acc.getUserId();
			if(tmp.equals(id)) {
				cnt++;
			}
		}
		
		if(cnt == LIMMIT)
			return null;
	
		Account acc = new Account(id, accNumber, 0);
		list.add(acc);
		
		return acc;
	}
	
	// 사본을 반환
	public Account getAcc(int index) {
		if(list.size()-1 < index)
			return null;
		
		Account acc = list.get(index);
		
		String userId = acc.getUserId();
		String accNumber = acc.getAccNumber();
		int money = acc.getMoney();
		
		return new Account(userId, accNumber, money);
	}
	
	public void setAcc(int index, Account acc) {
		if(list.size()-1 < index)
			return;
		
		list.set(index, acc);
	}
	
	public Account deleteAcc(int index) {
		if(list.size()-1 < index)
			return null;

		return list.remove(index);
		
	}
	
	private String getAccNumber() {
		Random ran = new Random();
		StringBuilder sb = new StringBuilder();
		loop:while(true) {
			
			long randomNum = Math.abs(ran.nextLong());
			String str = String.valueOf(randomNum);
			
			//1111-1111-1111-1111
			sb.append(str.substring(0, 16));
			sb.insert(4, "-").insert(9, "-").insert(14,"-");
			
			for(int i = 0; i < list.size(); i++) {
				Account acc = list.get(i);
				String tmp = acc.getAccNumber();
				
				if(tmp.equals(sb.toString())) {
					continue loop;
				}
			}
			break;
		}
		return sb.toString();
	}
}
