import java.util.Scanner;


public class Admin_Menu extends Menu {

	@Override
	void checkAttendance() {
		String path = "./Attendance/attendance.txt";
        try {
		    loadlist(path);
        }catch (Exception e) {
        	e.printStackTrace();
        }
	}


	@Override
	void editInfo() {
		Scanner sc = new Scanner(System.in);
		System.out.println("수정할 학생의 아이디를 입력해주세요");
		String stuID = sc.nextLine();
		
		Account oldClass = map.get(stuID);
		System.out.println("변경할 반번호를 입력해주세요 1:(더존) or 2:(현대) 중 번호 선택");
		int newClass = Integer.parseInt(sc.nextLine());
		oldClass.setClassNumber(newClass);
		save(map, "test.txt");
		System.out.println(map.get(stuID).getClassNumber()+"반으로 변경이 완료되었습니다.");
	}

	
	@Override
	void MenuRun() {
		boolean flag = true;
		while(flag) {
			System.out.println("메뉴를 선택해주세요.");
			System.out.println("1. 출결 확인 2. 나가기");
			Scanner sc = new Scanner(System.in);
			int select = Integer.parseInt(sc.nextLine());
	
			switch (select) {
				case 1:
					checkAttendance();
					break;
				case 2:
					flag = false;
					break;
				case 3:
					break;
			}
		}
	}

}