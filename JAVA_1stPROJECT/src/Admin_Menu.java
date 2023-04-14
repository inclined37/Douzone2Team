import java.io.IOException;
import java.util.Scanner;


public class Admin_Menu extends Menu {
	String path = "test.txt";

	@Override
	void checkAttendance() {
		String path = "./Attendance/attendance.txt";
		try {
			loadlist(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	void editInfo() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("수정할 학생의 아이디를 입력해주세요");
		String stuID = sc.nextLine();
		
		if(!map.containsKey(stuID)) {
			System.err.println("아이디가 존재하지 않습니다.");
		}else {	
		Account oldClass = map.get(stuID);
		System.out.println("변경할 반번호를 입력해주세요 1:(더존) or 2:(현대) 중 번호 선택");
		int newClass = Integer.parseInt(sc.nextLine());
		oldClass.setClassNumber(newClass);
		map.put(stuID, oldClass);
		save(map, path);
		System.out.println(map.get(stuID).getName()+"학생이"+map.get(stuID).getClassNumber()+"반으로 변경이 완료되었습니다.");
		}
	}

	
	@Override
	void MenuRun() {
		load("test.txt");
		boolean flag = true;
		while(flag) {
			System.out.println("메뉴를 선택해주세요.");
			System.out.println("1. 출결 확인 2. 학생 정보 변경 3. 나가기");
			Scanner sc = new Scanner(System.in);
			int select = Integer.parseInt(sc.nextLine());
	
			switch (select) {
				case 1:
					checkAttendance();
					break;
				case 2:
					editInfo();
					break;
				case 3:
					flag = false;
					break;
			}
		}
	}

}