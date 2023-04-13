import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public abstract class Menu {
	int menu;
	
	Scanner scan = new Scanner(System.in);

	public HashMap<String,Account> map = new HashMap<>(); // 키: id / 값: 그 외 정보를 학생, 관리자 로그인 정보를 각각 사용 예정
	
	//로그인
	public boolean login() {
		System.out.println("로그인 합니다.");

		String accountId;
		String accountPw;

		System.out.print("accountId 입력: ");
		accountId = scan.nextLine();

		if (map.containsKey(accountId)) {

			System.out.print("accountPw 입력: ");
			accountPw = scan.nextLine();

			if(map.get(accountId).getPassWord().equals(accountPw)) {
				//showMenu
				System.out.println("로그인에 성공하였습니다.");
				System.out.println(map.get(accountId).getName()+" 님 안녕하세요 ^^");
				return true;
			} else {
				System.out.println("입력한 accountPw가 일치하지 않습니다.");
			}
		}
		else {
			System.out.println("입력한 accountId가 존재하지 않습니다.");
		}
		return false;
	}
	
	//메뉴선택
	abstract void MenuRun();

	// 출결현황 확인
	abstract void checkAttendance();

	// 개인정보 수정
	abstract void editInfo();
 
}
