import java.util.Scanner;
import java.util.regex.Pattern;


//회원가입양식 클래스
public class Account { 
	
	private String accountId;
	private String passWord;
	private String name;
	private String phoneNumber;
	private int classNumber;
	
	
	Account(String name,String accountId,String passWord,String phoneNumber,int classNumber){
		
		this.accountId =accountId;
		this.passWord = passWord;
		this.name =name;
		this.phoneNumber =phoneNumber;
		this.classNumber = classNumber;
		System.out.println(this.toString()+"생성하였습니다.");
		
	}
 
	@Override
	public String toString() {
		
		return "Account [이름=" + name + ", accountId=" + accountId + ", phoneNumber=" + phoneNumber + ", passWord="
				+ passWord + ", classNumber=" + classNumber + "]";
	}
 
}