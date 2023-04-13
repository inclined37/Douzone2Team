import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class Student_Menu extends Menu {
	String filename = "db.txt";
	File fileS = new File(filename);
	
	public List<Account> accounts = new ArrayList<>();
	//public HashMap<String,Account> map = new HashMap<>();
	//super.login(map);
	
	public void signUp() {
		HashMap smap =  super.map;
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
						boolean chkId = true;
						// chkId = findEmail(tmp);
//						if (!chkId) {
//							System.out.println("사용 가능한 ID 입니다.");
//							getAccountId = tmp;
//							choice++;
//							flag = false;
//						} else {
//							System.out.println("사용하는 ID가 있습니다.다시 입력해주세요");
//							flag = true;
//						}
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
						Account acc = new Account(getName, getAccountId, getPassWord, getPhoneNumber, getClassNumber);
						accounts.add(acc);
						smap.put(getAccountId, acc);
						
						//파일 저장하고 읽어오는 method 작성 예정(하단 무시)
						
						
//						String filename = "name.txt";
//						
//						FileOutputStream fos = null;
//						BufferedOutputStream bos = null;
//						ObjectOutputStream oos = null;
//						
//						try {
//							fos = new FileOutputStream(filename, true);
//							bos = new BufferedOutputStream(fos);
//							oos = new ObjectOutputStream(bos);
//							
//							oos.writeObject(smap);
//							
//						}catch (Exception e) {
//							e.printStackTrace();
//						} finally {
//							try {
//								oos.close();
//								bos.close();
//								fos.close();
//							} catch (Exception e2){
//								e2.printStackTrace();
//							}
//						}
//						
//						
//						FileInputStream fis = null;
//						BufferedInputStream bis = null;
//						ObjectInputStream ois = null;
//						
//						try {
//							fis = new FileInputStream(filename);
//							bis = new BufferedInputStream(fis);
//							ois = new ObjectInputStream(bis);
//						
//							smap2 = (HashMap)ois.readObject();
//							Set<String> set = smap2.keySet();
//							
//							for(String str : set) {
//								System.out.println(smap2.get(getAccountId).toString());
//							}
//						} catch (Exception e2) {
//							e2.printStackTrace();
//						} finally {
//							try {
//								ois.close();
//								bis.close();
//								fis.close();
//							} catch (Exception e3) {
//								e3.printStackTrace();
//							}
//						}
					}
				}
				run = true;
				break;
			}
		}

	}

	public void login() {

		boolean found = false;
		System.out.println("로그인을 합니다.");

		System.out.println("아이디를 입력하세요: ");
		String accountId = sc.nextLine();

		System.out.println("비밀번호를 입력하세요: ");
		String passWord = sc.nextLine();
		
		for(int i = 0; i < accounts.size(); i++) {
			if( accountId == accounts.get(i).getAccountId()) {
				if(passWord == accounts.get(i).getPassWord()) {
					System.out.println("로그인 완료");
					found = true;
					//showMenu
				} else {
					System.out.println("비밀번호가 틀립니다.");
				}
			}
		}
		
		if(!found) {
			System.out.println("입력한 아이디가 존재하지 않습니다.");
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

		// 비밀번호 입력

		// 1. 비밀번호가 맞으면
		// 휴대폰 번호 수정
		// 1.1 양식에 어긋나면, 재 입력
		// 비밀번호 변경
		// 1.1 양식에 어긋나면, 재 입력
		// 1.2 (비밀번호) 일치 안하면, 재 입력
		// 2. 비밀번호가 틀리면
		// 재 입력
		String password = "";
		boolean run = false;
		while (!run) {
			if (password.equals("")) {
				System.out.print("비밀번호를 입력하세요: ");
				password = sc.nextLine();
				System.out.println();

				if (!password.contains(password)) {
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
						// 휴대폰 번호 변경~~
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
							// 비밀번호 변경~~
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
		//login();
		Scanner sc = new Scanner(System.in);
		//  가입/로그인
		boolean run1 = false;
		while(!run1) {
			System.out.println("********************************************");
			System.out.println("************ Douzone In and Out ************");
			System.out.println("********************************************");
			System.out.print("[1]로그인  [2]회원가입  [0]종료  : ");
			int select = Integer.parseInt(sc.nextLine());
			boolean check;
			
			switch(select) {
			case 1:
				check = login();
				//로그인 성공 시 while 탈출
				if(check) {
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
		//로그인 성공 시
		boolean run2 = false;
		while(!run2) {
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
