import java.util.Scanner;

public class DBreadAndExcelCreate {

	static void start() {

		System.out.println("\n---------------\n해당사항 번호를 입력해주세요.\n"
				+ "1. 로그인하기\n"
				+ "2. 회원가입하기\n"
				+ "3. 아이디 찾기\n"
				+ "4. 비밀번호 찾기\n"
				+ "5. 회원 목록 확인\n"
				+ "6. 끝내기");
		
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		
		System.out.println("\n---------------\n");
		switch(num) {
		case 1 : login();break;
		case 2 : join();break;
		case 3 : find_id();break;
		case 4 : find_password();break;
		case 5 : check_user();break;
		case 6 : System.out.println("안녕히 가세요.");break;
		default : System.out.println("잘못 입력하셨습니다."); break;
		}
		
		if(num !=6)	start();	
		
		
		
		
		
		
	}
	
	
	
	
	public static void login() {
		Scanner sc = new Scanner(System.in);
		System.out.println("아이디를 입력하세요.");
		String id = sc.next();
	}
	
	public static void join() {
		Scanner sc = new Scanner(System.in);
		System.out.println("회원가입하기");
	}
	
	public static void find_id() {
		Scanner sc = new Scanner(System.in);
		System.out.println("이름을 입력하세요.");
		String name = sc.next();
		
		DB db = new DB();
		
		String matName = db.find_id(name);
		
		if(matName == true)
	}
	
	public static void find_password() {
		Scanner sc = new Scanner(System.in);
		System.out.println("비밀번호 찾기");
	}
	
	public static void check_user() {
		Scanner sc = new Scanner(System.in);
		System.out.println("회원 목록 확인");
	}
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {

		System.out.println("테스트를 시작합니다.");
		start();
		
	}

}


