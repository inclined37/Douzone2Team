import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Student_Menu extends Menu {

	List<Account> accounts = new ArrayList<>();
	
	public void signUp() {
		String getAccountId = "";
		String getPassWord = "";
		String getName = "";
		String getPhoneNumber = "";
		int getClassNumber = 0;

		Account account;
		
		//List<Account> accounts = new ArrayList<>(); // 리스트는 메뉴에 생성할 예정

		String idPattern = "^.+@[^\\\\.].*\\\\.[a-z]{2,}$"; // 이메일 형식
		String pwdPattern = "^(?=.*[A-Za-z])(?=.*\\d)([!@#$%*?&]?)[A-Za-z\\d!@#$%*?&]{10,16}$"; // 비밀번호 형식
		String phoneNumberPattern = "^01[0-1|6-9]\\-?\\d{3,4}\\-?\\d{4}$"; // 핸드폰번호 형식
		String tmp = "";
		Scanner sc = new Scanner(System.in);
		int choice = 0;

		while (true) {

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
						boolean chkId = true;
						// chkId = findEmail(tmp);
						if (!chkId) {
							System.out.println("사용 가능한 ID 입니다.");
							getAccountId = tmp;
							choice++;
							flag = false;
						} else {
							System.out.println("사용하는 ID가 있습니다.다시 입력해주세요");
							flag = true;
						}
					}
				}
			case 2:
				System.out.print("비밀번호를 입력해주세요 (10~16자리 사이의 영문자,숫자,특수문자가 포함) : ");
				tmp = sc.nextLine();
				boolean pwdregex = Pattern.matches(pwdPattern, tmp);

				if (pwdregex) {
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
					choice++;
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
						accounts.add(new Account(getName, getAccountId, getPassWord, getPhoneNumber, getClassNumber));
					}
				}
			}
		}

	}

	// 이메일주소 일치 확인
//	public boolean findEmail(String email) {
//		for (Account ac : this.accounts) {
//			if (ac.getAccountId().equals(email)) { // 같으면 다시 입력
//				return true;
//			}
//		}
//		return false; // 통과
//	}


	public void attendance() {

	}

	// 출결확인
	@Override
	void checkAttendance() {

	}

	@Override
	void editInfo() {

	}

	@Override
	void MenuRun() {
		System.out.println("1. 가입 2. 로그인 3. 출석 4. 출결확인 5. 정보 수정 6. 종료");
		System.out.println("메뉴를 선택해주세요.");
		Scanner sc = new Scanner(System.in);
		int select = Integer.parseInt(sc.nextLine());

		switch (select) {
		case 1:
			signUp();
			MenuRun();
			break;
		case 2:
			login();
			MenuRun();
			break;
		case 3:
			attendance();
			MenuRun();
			break;
		case 4:
			checkAttendance();
			MenuRun();
			break;
		case 5:
			editInfo();
			MenuRun();
			break;
		case 6:
			System.exit(0);
		}
	}

}
