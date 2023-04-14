import java.io.Serializable;
import java.util.Scanner;
import java.util.regex.Pattern;

//회원가입양식 클래스
public class Account implements Serializable {

	private String accountId;
	private String passWord;
	private String name;
	private String phoneNumber;
	private int classNumber;

	Account(String name, String accountId, String passWord, String phoneNumber, int classNumber) {

		this.accountId = accountId;
		this.passWord = passWord;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.classNumber = classNumber;
		System.out.println(this.name + "님 가입을 환영합니다!");

	}

	public String getAccountId() {
		return accountId;
	}

	public String getPassWord() {
		return passWord;
	}

	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public int getClassNumber() {
		return classNumber;
	}


	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setClassNumber(int classNumber) {
		this.classNumber = classNumber;
	}

	@Override
	public String toString() {

		return "내 정보 :[이름=" + name + ", 아이디=" + accountId + ", 휴대폰 번호=" + phoneNumber + ", 현재 비밀번호="
				+ passWord + ", 반 번호=" + classNumber + "]";
	}

}