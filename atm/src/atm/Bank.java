package atm;

import java.util.Scanner;

public class Bank {
	private AccountManager am;
	private UserManager um;

	private Scanner sc;
	private int log;

	public Bank() {
		this.am = new AccountManager();
		this.um = new UserManager();
		this.sc = new Scanner(System.in);
		this.log = -1;
	}
	private int inputNumber() {
		System.out.println("메뉴 선택)");
		int input = 0;
		try {
			input = this.sc.nextInt();
		}catch(Exception e) {
			this.sc.next();
			return inputNumber();
		}
		return input;
	}
	
	private int duplPw(String pw) {
		int idx = 0;
		User user = null;
		while((user = this.um.getUser(idx)) != null) {
			String tmpPw = user.getPassword();
			if(tmpPw.equals(pw)) {
				return idx;				
			}
			idx++;
		}
		return -1;
	}
	
	private void signUp() {
		System.out.println("(input name)");
		String name = this.sc.next();
		System.out.println("(input password)");
		String password = this.sc.next();

		if(duplPw(password) > -1) {
			System.out.println("중복 있음");
			return;
		}
		um.addUser(name, password);
	}
	
	private void userAccClear(String id) {
		int idx = 0;
		Account acc = null;
		while((acc = this.am.getAcc(idx)) != null) {
			String tmpId = acc.getUserId();
			if(tmpId.equals(id)) {
				this.am.deleteAcc(idx);				
			
			}else idx++;
		}
	}

	private void dropUser() {
		System.out.println("(input password)");
		String password = this.sc.next();

		int index = duplPw(password);
		if(index < 0) {
			System.out.println("회원이 존재하지않음");
			return;
		}
		
		User user = this.um.getUser(index);
		userAccClear(user.getId());
		
		this.um.deleteUser(index);
		
		if(index == this.log) {
			this.log = -1;			
		}
	}
	
	private void accApplicate() {
		if(log < 0) {
			System.out.println("로그인 후 이용 가능한 서비스입니다");
			return;
		}
		Account acc = this.am.createAccount(this.um.getUser(log));
		
		if(acc == null) {
			System.out.println("통장 개설 불가능");
			return;
		}
		
		// UserManager의 사본 유저 데이터에서
		// Account 데이터를 추가해서 다시 정의함
		User user = this.um.getUser(log);
		user.addAcc(acc);
		this.um.setUser(log, user);
	}
	
	private void accDrop() {
		if(log < 0) {
			System.out.println("로그인 후 이용 가능한 서비스입니다");
			return;
		}
		System.out.println("insert accNumber");
		String accNumber = this.sc.next();
		
		int index = duplAccNumber(accNumber);
		if(index < 0) {
			System.out.println("계좌를 찾을 수 없음");
		}
		Account acc = this.am.deleteAcc(index);
		
		if(acc == null) {
			System.out.println("삭제 실패");
			return;
		}
		
		User user = setAccData(acc);
		this.um.setUser(log, user);
	}
	
	private User setAccData(Account acc) {
		int length = AccountManager.LIMMIT;
		User user = this.um.getUser(log);
		
		String id = user.getId();
		String password = user.getPassword();
		String name = user.getName();
		
		User result = new User(id, password, name);
		
		for(int i = 0; i < length; i++) {
			Account tmp = user.getAcc(i);
			if(tmp == null)
				break;
			
			String accNum = acc.getAccNumber();
			String tmpAccNum = tmp.getAccNumber();
			if(accNum.equals(tmpAccNum))
				continue;
			
			result.addAcc(tmp);
		}
		return result;
	}
	
	private int duplAccNumber(String accNumber) {
		int idx = 0;
		Account acc = null;
		while((acc = this.am.getAcc(idx)) != null) {
			String tmpAccNum = acc.getAccNumber();
			if(accNumber.equals(tmpAccNum))
				return idx;
			
			idx++;
		}
		return -1;
	}
	
	private void logIn() {
		if(log > -1) {
			System.out.println("이미 로그인 중입니다");
			return;
		}
		
		System.out.println("(input password)");
		String password = this.sc.next();
		
		int index = duplPw(password);
		if(index < 0) {
			System.out.println("그런 계정은 없다");
			return;
		}
		this.log = index;
	}
	
	private void signOut() {
		if(log < 0) {
			System.out.println("로그인 상태가 아닙니다");
			return;
		}
		this.log = -1;
	}

	public void run() {

		while(true) {
			userStatus();
			printMenu();
			int sel = inputNumber();
			if(sel == 0) break;
			else if(sel == 1) signUp();
			else if(sel == 2) dropUser();
			else if(sel == 3) accApplicate();
			else if(sel == 4) accDrop();
			else if(sel == 5) logIn();
			else if(sel == 6) signOut();
		}
		this.sc.close();
	}

	private void printMenu() {
		System.out.println("1. 회원가입");
		System.out.println("2. 회원탈퇴");
		System.out.println("3. 계좌신청");
		System.out.println("4. 계좌철회");
		System.out.println("5. 로그인");
		System.out.println("6. 로그아웃");
		System.out.println("0. 종료");
	}
	
	private void userStatus() {
		if(log < 0)
			return;
		
		User user = um.getUser(log);
		String name = user.getName();
		String password = user.getPassword();
		String id = user.getId();
		
		System.out.println("유저 ID : " +id);
		System.out.println("이름    : " +name);
		System.out.println("비밀번호 : " +password);
		
		int length = AccountManager.LIMMIT;
		for(int i = 0; i < length; i++) {
			Account acc = user.getAcc(i);
			if(acc == null) {
				return;
			}
			System.out.println("ㄴ ("+ acc.getAccNumber()+")");
		}
	}

}
