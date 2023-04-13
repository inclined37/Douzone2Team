import java.util.HashMap;
import java.util.Scanner;

public abstract class Menu {
	int menu;
	
	Scanner scan = new Scanner(System.in);
	
	//Student_Menu smenu = new Student_Menu();
	//학생 map
	//public HashMap<String,Account> map = smenu.map;
	
	public HashMap<String,Account> map = new HashMap<>();
	
	//로그인
	public void login() {
		System.out.println("로그인 합니다.");

		String accountId;
		String accountPw;

		System.out.print("accountId 입력: ");
		accountId = scan.nextLine();

		if (map.containsKey(accountId)) {

			System.out.print("accountPw 입력: ");
			accountPw = scan.nextLine();

			if(map.get(accountId).getPassWord() == accountPw) {
				//showMenu
			} else {
				System.out.println("입력한 accountPw가 일치하지 않습니다.");
			}
		}
		else {
			System.out.println("입력한 accountId가 존재하지 않습니다.");
		}
	}
	
	//메뉴선택
	abstract void MenuRun();
	//출결현황 확인
	abstract void checkAttendance();
	
	//개인정보 수정
	abstract void editInfo();

	
}
