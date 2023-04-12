import java.util.Scanner;

public class Admin_Menu extends Menu{
	Admin_Menu am;
	public void acceptAttendance() {
		
	}
	
	
	@Override
	void checkAttendance() {
		
	}

	@Override
	void editInfo() {
		
	}


	@Override
	void MenuRun() {
		System.out.println("1. 출결 처리 2. 출결 확인 3. 학생 정보수정");
		System.out.println("메뉴를 선택해주세요.");
		Scanner sc = new Scanner(System.in);
		int select = Integer.parseInt(sc.nextLine());
		
		switch(select) {
		case 1:
			acceptAttendance();
			MenuRun();
			break;
		case 2:
			checkAttendance();
			MenuRun();
			break;
		case 3:
			editInfo();
			break;
		}
	}

}
