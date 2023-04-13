import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Admin_Menu extends Menu {
	Admin_Menu am;

	public void acceptAttendance() {

	}

	@Override
	void checkAttendance() {

		// 관리자는 모든 학생의 출결정보를 조회할 수 있다
		// 1. 관리자는 학생 출결 조회 메뉴로 간다.
		// 2. 관리자는 학생들의 출결을 조회한다.
		// 2-1. 관리자는 학생들의 출결현황을 내림차순으로 정렬 한다.

		Scanner sc = new Scanner(System.in);
		boolean run = false;

		// 출석파일 정보 얻기
//		File file = new File("출석파일.txt");
//		
//		try {
//			FileInputStream fis = new FileInputStream(file);
//			ObjectInputStream oos = new ObjectInputStream(fis);
//
//			account_dic = (HashMap) oos.readObject(); 
//
//			Set<String> set = account_dic.keySet();
//			for (String v : set) { 
//				String name = account_dic.get(v).getName();
//				String attendance = account_dic.get(v).getAttendance();
//			}
//
//			oos.close();
//			fis.close();
//
//		} catch (Exception e) {
//			System.out.println("출석 정보 파일을 불러오는데 실패하였습니다.");
//			e.printStackTrace();
//		}

		// 출결조회 기능
		while (!run) {
			System.out.println("반을 선택하세요");
			System.out.print("[1] 더존  [2] 현대  [0]돌아가기  :");
			int menu = Integer.parseInt(sc.nextLine());
			System.out.println();
				
			switch (menu) {
			case 1:
				// 더존 account_dic 정보 출력
				// for문(내림차순정렬)
				break;

			case 2:
				// 현대 account_dic 정보 출력
				// for문(내림차순정렬)
				break;

			case 0:
				run = true;
				break;

			default:
				System.out.println("잘못된 메뉴를 선택하셨습니다.");
			}
		}
	}

	@Override
	void editInfo() {
		Scanner sc = new Scanner(System.in);
		System.out.println("수정할 학생의 아이디를 입력해주세요");
		String stuID = sc.nextLine();
		
		Account oldClass = map.get(stuID);
		System.out.println("변경할 반번호를 입력해주세요(1:(더존) or 2:(현대) 중 번호 선택");
		int newClass = Integer.parseInt(sc.nextLine());
		oldClass.setClassNumber(newClass);
		save(map, "test.txt");
	}

	
	@Override
	void MenuRun() {
		System.out.println("1. 출결 처리 2. 출결 확인 3. 학생 정보수정");
		System.out.println("메뉴를 선택해주세요.");
		Scanner sc = new Scanner(System.in);
		int select = Integer.parseInt(sc.nextLine());

		switch (select) {
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