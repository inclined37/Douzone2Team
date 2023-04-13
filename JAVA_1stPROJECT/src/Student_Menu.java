import java.awt.print.Book;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Student_Menu extends Menu {


//	private boolean dataChange;
	// 데이터가 변경되었는지 여부를 나타내는 변수선언 데이터가 변경되면
	// 이 변수값이 true가된다.
	private String fileName = "test.txt";
	public List<Account> accounts = new ArrayList<>();

	public void signUp() {
		load(fileName);
		String getAccountId = "";
		String getPassWord = "";
		String getName = "";
		String getPhoneNumber = "";
		int getClassNumber = 0;

		Account account;

		String idPattern = "^.+@[^\\\\.].*\\\\.[a-z]{2,}$"; // 이메일 형식
		String pwdPattern = "^(?=.*[A-Za-z])(?=.*\\d)([!@#$%*?&]?)[A-Za-z\\d!@#$%*?&]{10,16}$"; // 비밀번호 형식
		String phoneNumberPattern = "^01[0-1|6-9]\\-?\\d{3,4}\\-?\\d{4}$"; // 핸드폰번호 형식
		String tmp = "";
		Scanner sc = new Scanner(System.in);
		int choice = 0;

		boolean run = false;
		while (!run) {

			switch (choice) {

			case 0:
				System.out.print("이름을 입력해주세요 :");
				getName = sc.nextLine();
				choice++;
			case 1:
				boolean flag = true;
				while (flag) { // true이면 계속 돌고 false은 통과!!!!!!!
					System.out.print("회원가입 하실 이메일 주소를 입력해주세요 (example@gmail.com) :");
					flag = false;
					tmp = sc.nextLine();
					boolean idregex = Pattern.matches(idPattern, tmp);

					if (idregex) { // 이메일 형식이 아닐 경우
						System.out.print("이메일 형식이 잘못되었습니다.다시 입력해주세요");
						flag = true;
					} else {// false면 통과
						System.out.println("##맵##" + map.containsKey(tmp));
						if(!map.containsKey(tmp)) { //다르면 사용 가능하다.
							
							System.out.println("사용 가능한 ID 입니다.");
							getAccountId = tmp;
							choice++;
							flag = false;
						}else {
							System.out.println("ID가 존재합니다. 다른 ID로 입력해주세요.");
							flag = true;
						}
					}
				}
			case 2:
				System.out.print("비밀번호를 입력해주세요 (10~16자리 사이의 영문자,숫자,특수문자가 포함) : ");
				tmp = sc.nextLine();
				boolean pwdregex = Pattern.matches(pwdPattern, tmp);

				if (!pwdregex) {
					System.out.print("비밀번호 형식이 잘못되었습니다. 다시 입력해주세요");
					break;
				} else {
					System.out.print("정상적으로 입력하였습니다.");
					getPassWord = tmp;
					choice++;
				}
			case 3:
				System.out.print("핸드폰 번호를 입력해주세요 (010-0000-0000) :");
				tmp = sc.nextLine();
				boolean phoneregex = Pattern.matches(phoneNumberPattern, getPhoneNumber);

				if (phoneregex) {
					System.out.print("핸드폰 번호 형식이 잘못되었습니다. 다시 입력해주세요");
					break;
				} else {
					System.out.print("정상적으로 입력하였습니다.");
					getPhoneNumber = tmp;
					choice++;
					break;
				}
			case 4:
				try {
					System.out.print("반번호를 입력해주세요(1:(더존) or 2:(현대) 중 번호 선택");
					getClassNumber = sc.nextInt();
				} catch (NumberFormatException e) {
					System.out.println("숫자가 아닌 문자로 입력하셨습니다.");
				} finally {
					if (!(getClassNumber == 1 || getClassNumber == 2)) { // 다시 try 구문으로 가기 로직 다시
						System.out.print("1과 2 중 선택 가능합니다.");
						break;
					} else {
						System.out.print("모든 정보가 정상적으로 입력되었습니다.");
						Account acc = new Account(getName, getAccountId, getPassWord, getPhoneNumber, getClassNumber);
						accounts.add(acc); // 회원정보 ArrayList 생성
						map.put(getAccountId, acc); // ArrayList에 생성된 정보 키 :id / 나머지 정보 : 값으로 생성
						System.out.println("******" + map.get(getAccountId).getName());


						save(map, fileName); // 여기까지는 타

						

					}
				}
				run = true;
				break;
			}
		}

	}

	public void attendance() {
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		File attend = new File("./Attendance/" + name + ".txt");

	}

	// 출결확인
	@Override
	void checkAttendance() {
		Scanner sc = new Scanner(System.in);
		boolean run = false;

		while (!run) {
			System.out.print("[1]나의근태 현황  [2]그룹근태 현황  [0]돌아가기  :");
			int menu = Integer.parseInt(sc.nextLine());
			System.out.println();

			switch (menu) {
			case 1:
				// 나의 근태 현황 가져오기~ (전부)
				break;

			case 2:
				// 같은 반 학생들의 근태 현황 가져오기~ (당일)
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

		String password = "";
		boolean run = false;
		while (!run) {
			if (password.equals("")) {
				System.out.print("비밀번호를 입력하세요: ");
				password = sc.nextLine();
				System.out.println();

				if (!map.get(loginId).getPassWord().equals(password)) {
					System.out.println("비밀번호가 일치하지 않습니다.");
					password = "";
				}
			} else {
				System.out.print("메뉴를 선택하세요: [1]휴대폰 번호 변경  [2]비밀번호 변경  [0]이전 메뉴로 돌아가기 : ");
				int menu = Integer.parseInt(sc.nextLine());
				System.out.println();

				switch (menu) {
				case 1:
					// 휴대폰 번호 변경
					System.out.print("변경할 휴대폰 번호를 입력하세요: ");
					String phoneNumber = sc.nextLine();
					System.out.println();
					if (!Pattern.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", phoneNumber)) {
						System.out.println("휴대폰 번호 형식에 맞지 않습니다.");
					} else {
						//getName, getAccountId, getPassWord, getPhoneNumber, getClassNumber
						map.replace(loginId, new Account(map.get(loginId).getName(), loginId, map.get(loginId).getPassWord(), phoneNumber, map.get(loginId).getClassNumber()));
						save(map, fileName);
					}
					break;

				case 2:
					// 비밀번호 변경
					System.out.print("새 비밀번호를 입력하세요: \n글자 수 제한 10~16자\n(영문자, 숫자 포함, 특수문자 포함)");
					String newPassword = sc.nextLine();
					System.out.println();
					if (!Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)([!@#$%*?&]?)[A-Za-z\\d!@#$%*?&]{10,16}$",
							newPassword)) {
						System.out.println("입력하신 비밀번호가 양식에 맞지 않습니다.");
					} else {
						System.out.print("비밀번호를 한번 더 입력하세요: ");
						String newPasswordCheck = sc.nextLine();
						System.out.println();

						// 비밀번호가 일치 안 하는 경우
						if (!newPassword.equals(newPasswordCheck)) {
							System.out.println("비밀번호가 일치하지 않습니다.");
						} else {
							System.out.println("비밀번호가 변경되었습니다.");
							map.replace(loginId, new Account(map.get(loginId).getName(), loginId, newPassword, map.get(loginId).getPhoneNumber(), map.get(loginId).getClassNumber()));
							save(map, fileName);
						}
					}
					break;

				case 0:
					run = true;
					break;

				default:
					System.out.println("잘못된 메뉴를 선택하셨습니다.");
				}
			}
		}
	}

	@Override
	void MenuRun() {
	
		Scanner sc = new Scanner(System.in);
 
		boolean run1 = false;
		while (!run1) {
			System.out.println("********************************************");
			System.out.println("************ Douzone In and Out ************");
			System.out.println("********************************************");
			System.out.print("[1]로그인  [2]회원가입  [0]종료  : ");
			int select = Integer.parseInt(sc.nextLine());
			boolean check;

			switch (select) {
			case 1:
				check = login();
				// 로그인 성공 시 while 탈출
				if (check) {
					run1 = true;
				}
				break;
			case 2:
				signUp();
				break;
			case 0:
				System.exit(0);
			}
		}
		// 로그인 성공 시
		boolean run2 = false;
		while (!run2) {
			System.out.println("[1]출석  [2]출결확인  [3]개인정보 수정  [0]로그아웃");
			System.out.println("메뉴를 선택해주세요.");

			int select = Integer.parseInt(sc.nextLine());

			switch (select) {
			case 1:
				attendance();
				break;
			case 2:
				checkAttendance();
				break;
			case 3:
				editInfo();
				break;
			case 0:
				run2 = true;
			}
		}
	}

 
}