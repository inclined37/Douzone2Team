package TeamProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//도서 관리자 클래스

import java.util.HashMap;

import java.util.Scanner;
import java.util.Set;

import kr.or.DataObject.BMI;

public class BookManager {

	Scanner scan = new Scanner(System.in);
	static HashMap<String, Book> book_dic = new HashMap<String, Book>();
	static HashMap<String, Book> account_dic = new HashMap<String, Book>();
	static int count = 1;

	public void Run() {

		int key = 0;

		while ((key = selectMenu()) != 0) {

			switch (key) {

			case 1:
				addBook();
				break;

			case 2:
				removeBook();
				break;

			case 3:
				searchBook();
				break;

			case 4:
				listBook();
				break;

			case 5:
				listISBN();
				break;

			case 6:
				save();
				break;

			case 7:
				load();
				break;

			case 8:
				login();
				break;

			default:
				System.out.println("잘못 선택하였습니다.");
				break;

			}

		}

		System.out.println("종료합니다...");

	}

	int selectMenu() {

		System.out.println("1:추가 2:삭제 3:검색 4:도서 목록 5:ISBN 6:save 7.load 8.로그인 목록0:종료");

		int key = scan.nextInt();

		scan.nextLine();

		return key;

	}

	void addBook() {

		String isbn;

		System.out.print("추가할 도서 ISBN:");

		isbn = scan.nextLine();

		if (book_dic.containsKey(isbn)) {

			System.out.println("이미 존재하는 ISBN입니다.");

			return;

		}

		String title;

		int price;

		System.out.print("도서 제목:");

		title = scan.nextLine();

		System.out.print("가격:");

		price = scan.nextInt();

		scan.nextLine();

		Book book = new Book(isbn, title, price);

		book_dic.put(isbn, book);

		System.out.println(book.toString() + " 생성하였습니다.");

	}

	void removeBook() {

		String isbn;

		System.out.print("삭제할 도서 ISBN:");

		isbn = scan.nextLine();

		if (book_dic.containsKey(isbn)) {

			book_dic.remove(isbn);

			System.out.println("삭제하였습니다.");

		}

		else {

			System.out.println("존재하지 않습니다.");

		}

	}

	void searchBook() {

		String isbn;

		System.out.print("검색할 도서 ISBN:");

		isbn = scan.nextLine();

		if (book_dic.containsKey(isbn)) {

			Book book = book_dic.get(isbn);
			System.out.println("검색 결과>>" + book.toString());

		}

		else {

			System.out.println("존재하지 않습니다.");

		}

	}

	void listBook() {

		System.out.println("도서 목록");

		int cnt = book_dic.size();

		System.out.println("도서 수:" + cnt);

		for (Book book : book_dic.values()) {

			System.out.println(book.toString());

		}

	}

	void listISBN() {

		System.out.println("ISBN 목록");

		int cnt = book_dic.size();

		System.out.println("도서 수:" + cnt);

		for (String isbn : book_dic.keySet()) {

			System.out.println(isbn);

		}

	}

	void save() {
		File file = new File("book.txt");

		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(book_dic); // book_dic 안에는 (1,new Book) (2,new Book) (3, new Book)

			oos.close();
			fos.close();
		} catch (Exception e) {
			System.out.println("에러발생!!!");
			e.printStackTrace();
		}
		System.out.println("저장되었습니다.");
	}

	void load() {
		File file = new File("book.txt");
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream oos = new ObjectInputStream(fis);

			book_dic = (HashMap) oos.readObject();

			Set<String> set = book_dic.keySet();
			System.out.println("isbn\ttitle\tprice\t");
			for (String v : set) {
				String isbn = book_dic.get(v).getIsbn();
				String title = book_dic.get(v).getTitle();
				int price = book_dic.get(v).getPrice();
				System.out.printf("book: %s\t %s\t %d\t \n", isbn, title, price);
			}
			oos.close();
			fis.close();

		} catch (Exception e) {
			System.out.println("불러오는데 실패하였습니다.");
			e.printStackTrace();
		}
	}

	void login() {
		System.out.println("로그인 합니다.");
		
		String accountId;
		String accountPw;
		
		System.out.print("accountId 입력: ");
		accountId = scan.nextLine();
		
		if (account_dic.containsKey(accountId)) {
			
			System.out.print("accountPw 입력: ");
			accountPw = scan.nextLine();
			
			if(account_dic.get(accountId).passWord == accountPw) {
				//showMenu
			} else {
				System.out.println("입력한 accountPw가 일치하지 않습니다.");
			}
		}
		else {
			System.out.println("입력한 accountId가 존재하지 않습니다.");
			return;
		}
	}
}












