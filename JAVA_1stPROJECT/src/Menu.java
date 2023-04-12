
public abstract class Menu {
	int menu;
	
	//로그인
	public void login() {
		
	}
	
	//메뉴선택
	abstract void MenuRun();
	//출결현황 확인
	abstract void checkAttendance();
	
	//개인정보 수정
	abstract void editInfo();

	
}
