package atm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
	
	private AccountManager am;
	private UserManager um;
	private String fileName;
	private File file;

	public FileManager(String brandName) {
		this.fileName = brandName + "Data.txt";
		this.file = new File(this.fileName);
		this.um = new UserManager();
		this.am = new AccountManager();
	}

	public void save() {
		StringBuilder stringBuilder = new StringBuilder();

		int idx = 0;
		User user = null;
		while((user = this.um.getUser(idx++)) != null) {
			String id = user.getId();
			String password = user.getPassword();
			String name = user.getName();

			stringBuilder.append(id).append("/").append(password).append("/").append(name);

			int index = 0;
			Account acc = null;
			while((acc = user.getAcc(index++)) != null) {
				String accNum = acc.getAccNumber();
				String money = String.valueOf(acc.getMoney());

				stringBuilder.append("/").append(accNum).append("/").append(money);
			}
			stringBuilder.append("\n");
		}
		//마지막 개행문자 자르기
		int lastCharIdx = stringBuilder.length()-1;
		stringBuilder.deleteCharAt(lastCharIdx);

		try(FileWriter fileWriter = new FileWriter(this.file)){	
			fileWriter.write(stringBuilder.toString());
		} catch (IOException e) {
			System.err.println("error");
		}
	}

	public void load() {
		if(!this.file.exists()) {
			System.out.println("파일이 없음");
			return;
		}
		try (FileReader fr = new FileReader(this.file);
				BufferedReader br = new BufferedReader(fr);){
			
			while(br.ready()) {
				String data = br.readLine();
				String[] dataArr = data.split("/");
				
				String id = dataArr[0];
				String password = dataArr[1];
				String name = dataArr[2];
				
				User user = new User(id,password, name);
				
				int length = dataArr.length;
				for(int i = 3; i < length; i+=2) {
					String accNum = dataArr[i];
					int money = Integer.parseInt(dataArr[i+1]);
					
					Account acc = new Account(id, accNum, money);
					user.addAcc(acc);
					this.am.createAccount(acc);
				}
				this.um.addUser(user);
			}
		} catch (FileNotFoundException e) {
			System.err.println("error");
		} catch (IOException ioe) {
			System.err.println("IO error");
		}
	}
}

//아이디/비밀번호/이름/(계좌번호/돈)/(계좌번호/돈)/(계좌번호/돈)




