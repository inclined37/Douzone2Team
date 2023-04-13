import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public abstract class Menu {
	int menu;

	Scanner scan = new Scanner(System.in);
	public HashMap<String, Account> map = new HashMap<>(); // 키: id / 값: 그 외 정보를 학생, 관리자 로그인 정보를 각각 사용 예정
	String loginId = "";
	// 로그인
	public boolean login() {
		load("test.txt");
		System.out.println("로그인 합니다.");

		String accountId;
		String accountPw;

		System.out.print("accountId 입력: ");
		accountId = scan.nextLine();

		if (map.containsKey(accountId)) {

			System.out.print("accountPw 입력: ");
			accountPw = scan.nextLine();

			if (map.get(accountId).getPassWord().equals(accountPw)) {
				// showMenu
				System.out.println("로그인에 성공하였습니다.");
				System.out.println(map.get(accountId).getName() + " 님 안녕하세요 ^^");
				loginId = accountId;
				return true;
			} else {
				System.out.println("입력한 accountPw가 일치하지 않습니다.");
			}
		} else {
			System.out.println("입력한 accountId가 존재하지 않습니다.");
		}
		return false;
	}

	
	
	// 메뉴선택
	abstract void MenuRun();

	// 출결현황 확인
	abstract void checkAttendance();

	// 개인정보 수정
	abstract void editInfo();

	
	public void save(HashMap<String, Account> map, String path) { // 직렬화(저장)만 하면 된다.

		File file = new File(path);
		ObjectOutputStream oos = null;

		try {
			oos = new ObjectOutputStream(new FileOutputStream(path, false));
			oos.writeObject(map);
			System.out.println("저장이 완료되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
				}
			}
//			dataChange = false; // oos가 null 경우
		}
	}
	
	public void load(String path) {
		File file = new File(path);
		FileInputStream fis = null;
		ObjectInputStream oos = null;
		try {
			fis = new FileInputStream(file);
			oos = new ObjectInputStream(fis);
			map = (HashMap) oos.readObject();
			System.out.println("불러온 유저수 : " + map.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}