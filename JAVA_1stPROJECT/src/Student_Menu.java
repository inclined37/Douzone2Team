import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class Student_Menu extends Menu {
	
	private String fileName = " ";//경로 지정해주세요 
	HashMap<String,Account> smap =  super.map; // 회원정보 가져오기
    private boolean dataChange;
    //데이터가 변경되었는지 여부를 나타내는 변수선언 데이터가 변경되면 
    //이 변수값이 true가된다.
	
	public List<Account> accounts = new ArrayList<>();
	//public HashMap<String,Account> map = new HashMap<>();
	//super.login(map);
	
	public void loginlog() {
		
		smap=load();
		
		if(smap==null){ //파일이 없거나 입출력 오류일때
			smap = new HashMap<>();
			}
	}
	
	public void signUp() {
		
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
						accounts.add(acc); // 회원정보 ArrayList 생성
						map.put(getAccountId, acc); // ArrayList에 생성된 정보 키 :id / 나머지 정보 : 값으로 생성 
				
						save();
						
						HashMap<String,Account> m = load();
						
						System.out.println(m.get(getAccountId).getName());
						
						//역직렬화 

					}
				}
				run = true;
				break;
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
		Scanner sc = new Scanner(System.in);
		boolean run = false;
		
		while(!run) {
			System.out.print("[1]나의근태 현황  [2]그룹근태 현황  [0]돌아가기  :");
			int menu = Integer.parseInt(sc.nextLine());
			System.out.println();
			
			switch(menu) {
			case 1:
				//나의 근태 현황 가져오기~ (전부)
				break;
				
			case 2:
				//같은 반 학생들의 근태 현황 가져오기~ (당일)
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
		//비밀번호 입력
		
		//1. 비밀번호가 맞으면
			//휴대폰 번호 수정
				//1.1 양식에 어긋나면, 재 입력
			//비밀번호 변경
				//1.1 양식에 어긋나면, 재 입력
				//1.2 (비밀번호) 일치 안하면, 재 입력
		//2. 비밀번호가 틀리면
			//재 입력
		String password="";
		boolean run = false;
		while(!run) {
			if(password.equals("")) {
				System.out.print("비밀번호를 입력하세요: ");
				password = sc.nextLine();
				System.out.println();
				
				if(!password.contains(password)) {
					System.out.println("비밀번호가 일치하지 않습니다.");
					password = "";
				}
			} else {
				System.out.print("메뉴를 선택하세요: [1]휴대폰 번호 변경  [2]비밀번호 변경  [0]이전 메뉴로 돌아가기 : ");
				int menu = Integer.parseInt(sc.nextLine());
				System.out.println();
				
				switch(menu) {
				case 1:
					//휴대폰 번호 변경
					System.out.print("변경할 휴대폰 번호를 입력하세요: ");
					String phoneNumber = sc.nextLine();
					System.out.println();
					if(!Pattern.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", phoneNumber)) {
						System.out.println("휴대폰 번호 형식에 맞지 않습니다.");
					} else {
						//휴대폰 번호 변경~~
					}
					break;
					
				case 2:
					//비밀번호 변경
					System.out.print("새 비밀번호를 입력하세요: \n글자 수 제한 10~16자\n(영문자, 숫자 포함, 특수문자 포함)");
					String newPassword = sc.nextLine();
					System.out.println();
					if(!Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)([!@#$%*?&]?)[A-Za-z\\d!@#$%*?&]{10,16}$",newPassword)) {
						System.out.println("입력하신 비밀번호가 양식에 맞지 않습니다.");
					} else {
						System.out.print("비밀번호를 한번 더 입력하세요: ");
						String newPasswordCheck = sc.nextLine();
						System.out.println();
						//비밀번호가 일치 안 하는 경우
						if(!newPassword.equals(newPasswordCheck)) {
							System.out.println("비밀번호가 일치하지 않습니다.");
						} else {
							System.out.println("비밀번호가 변경되었습니다.");
							//비밀번호 변경~~
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
		login();
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
	
	
	private void save() { //직렬화(저장)만 하면 된다. 
		File file = new File(fileName);
		ObjectOutputStream oos = null; 

		try {
			oos = new ObjectOutputStream(new FileOutputStream(file,true));
			oos.writeObject(smap);
			System.out.println("저장이 완료되었습니다.");
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
				if(oos != null) {
				try{
					oos.close();
				}catch(IOException e) {	
					}
				}
				dataChange=false; //oos가 null 경우 
			}
		}
	
	private HashMap<String,Account> load() { //읽어오기(역직렬화)
		
		HashMap<String, Account> pMap = null;
		File file = new File(fileName);
		
		if(!file.exists()) {
			return null;
		}
		ObjectInputStream ois = null;
		try {
			//파일 입력용 스트림 객체 생성
			ois = new ObjectInputStream(new FileInputStream(file));
			pMap = (HashMap<String, Account>) ois.readObject();
			
//			Set<String> set = smap.keySet();
//			for(String str : set) {
//				System.out.println(smap.get(getAccountId).toString());
//			}
		}
		catch(FileNotFoundException e) {
			System.out.println("파일이 없네요");
		} catch (IOException e) {
			//handle exception
			return null;
		} catch (ClassNotFoundException e) {
			//Auto-generated catch block
			return null;
		} finally{
			if(ois!=null)
				try {
					ois.close();
				} catch (IOException e) {
					//handle exception
				}
			}
		return pMap;
		}

}
