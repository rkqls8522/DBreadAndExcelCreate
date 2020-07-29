import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DBreadAndExcelCreate {

	static void start() {

		System.out.println("\n---------------\n해당사항 번호를 입력해주세요.\n" + "1. 로그인하기\n" + "2. 회원가입하기\n" + "3. 아이디 찾기\n"
				+ "4. 비밀번호 찾기\n" + "5. 회원 목록 확인\n" + "6. 탈퇴하기\n" + "7. 끝내기");

		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();

		System.out.println("\n---------------\n");
		switch (num) {
		case 1:
			login();
			break;
		case 2:
			join();
			break;
		case 3:
			find_id();
			break;
		case 4:
			find_password();
			break;
		case 5:
			check_user();
			break;
		case 6:
			delete_user();
			break;
		case 7:
			System.out.println("안녕히 가세요.");
			break;
		default:
			System.out.println("잘못 입력하셨습니다.");
			break;
		}

		if (num != 7) {
			System.out.println("\n더 원하시는 일이 있습니까?");
			start();
		}

	}

	public static void login() {
		DB db = new DB();
		Scanner sc = new Scanner(System.in);
		System.out.println("아이디를 입력하세요.");
		String id = sc.nextLine();
		System.out.println("비밀번호를 입력하세요.");
		String password = sc.nextLine();

		if (db.check_login(id, password) == "true")
			System.out.println("로그인 성공");
		else
			System.out.println("로그인 실패");

	}

	public static void join() {
		DB db = new DB();
		Scanner sc = new Scanner(System.in);
		String idPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
		String pwPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$";
		String namePattern = "[A-Za-z]*$";
		String agePattern = "[0-9]*$";
		String NumberPattern = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$";
		String e_mailPattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,6}$";
		String jusoPattern = "[A-Za-z0-9가-힣 ]*$";

		String id, password, name, sex, age, juso, number, e_mail;
		while (true) {
			System.out.println("아이디를 입력하세요.");
			System.out.println("영문자와 숫자를 포함하여 8~12자리로 입력하세요.");
			id = sc.nextLine();
			Matcher matId = Pattern.compile(idPattern).matcher(id);
			if (matId.matches() == false)
				System.out.println("다시 입력하세요.");
			else if (db.check_id(id) == "true")
				System.out.println("이미 있는 아이디입니다.");
			else if (id.contains(" "))
				System.out.println("공백이 있으면 안 됩니다.");
			else
				break;
		}
		while (true) {
			System.out.println("비밀번호를 입력하세요.");
			System.out.println("영문자와 숫자를 포함하여 8~12자리로 입력하세요.");
			password = sc.nextLine();
			String checkString="";
			int sum = 0;
			for(int i = 0; i < id.length() - 2; i++) {
				checkString = id.substring(i,i+3);
				if(password.contains(checkString))
					sum++;
			}
			Matcher matPassword = Pattern.compile(pwPattern).matcher(password);
			if (matPassword.matches() == false)
				System.out.println("다시 입력하세요.");
			else if (id.contains(" "))
				System.out.println("공백이 있으면 안 됩니다.");
			else if(sum > 0)
				System.out.println("아이디와 동일한 영문자나 숫자나 3자리 이상 연속으로 있으면 안 됩니다.");
			else
				break;
		}
		while (true) {
			System.out.println("이름을 입력하세요.");
			System.out.println("영문자로만 입력하세요.");
			name = sc.nextLine();
			Matcher matname = Pattern.compile(namePattern).matcher(name);
			if (matname.matches() == false)
				System.out.println("다시 입력하세요.");
			else
				break;
		}
		target: while (true) {
			System.out.println("성별을 입력하세요(1~2).\n" + "1. male\n" + "2. female");
			String num = sc.nextLine();
			switch (num) {
			case "1":
				sex = "male";
				break target;
			case "2":
				sex = "female";
				break target;
			default:
				System.out.println("다시 입력하세요.");
			}

		}
		while (true) {
			System.out.println("나이를 입력하세요.");
			age = sc.nextLine();
			Matcher matage = Pattern.compile(agePattern).matcher(age);
			if (matage.matches() == false)
				System.out.println("다시 입력하세요.");
			else
				break;
		}
		while (true) {
			System.out.println("주소를 입력하세요.");
			juso = sc.nextLine();
			Matcher matjuso = Pattern.compile(jusoPattern).matcher(juso);
			if (matjuso.matches() == false)
				System.out.println("다시 입력하세요.");
			else
				break;
		}

		while (true) {
			System.out.println("휴대전화번호를 입력하세요.\n" + "띄어쓰기 없이 '-'를 포함하고 입력하세요.");
			number = sc.nextLine();
			Matcher matnumber = Pattern.compile(NumberPattern).matcher(number);
			if (matnumber.matches() == false)
				System.out.println("다시 입력하세요.");
			else
				break;
		}
		while (true) {
			System.out.println("이메일을 입력하세요.");
			e_mail = sc.nextLine();
			Matcher matE_mail = Pattern.compile(e_mailPattern).matcher(e_mail);
			if (matE_mail.matches() == false)
				System.out.println("다시 입력하세요.");
			else
				break;
		}

		db.insert(id, password, name, sex, age, juso, number, e_mail);
		System.out.println("가입을 완료했습니다.");
		db.writeExcel();
	}

	public static void find_id() {
		DB db = new DB();
		Scanner sc = new Scanner(System.in);
		System.out.println("이름을 입력하세요.");
		String name = sc.nextLine();
		String matId = db.find_id(name);
		System.out.println("ID : " + matId);

	}

	public static void find_password() {
		DB db = new DB();
		Scanner sc = new Scanner(System.in);
		System.out.println("아이디를 입력하세요.");
		String id = sc.nextLine();

		System.out.println("이름을 입력하세요.");
		String name = sc.nextLine();

		String matPassword = db.find_password(id, name);
		System.out.println("PASSWORD : " + matPassword);

	}

	public static void check_user() {
		DB db = new DB();
		Scanner sc = new Scanner(System.in);
		System.out.println("회원 목록을 출력합니다.");
		db.read();

	}

	public static void delete_user() {
		DB db = new DB();
		Scanner sc = new Scanner(System.in);
		System.out.println("아이디를 입력하세요.");
		String id = sc.nextLine();
		if (db.check_id(id) == "true") {
			target: while (true) {
				System.out.println("정말로 탈퇴하시겠습니까? (1~2)\n" + "1. yes\n" + "2. no");
				String answer = sc.nextLine();
				switch (answer) {
				case "1":
					db.delete(id);
					System.out.println("탈퇴를 완료했습니다.");
					db.writeExcel();
					break target;
				case "2":
					break target;
				default:
					System.out.println("다시 입력하세요.");
				}
			}
		} else
			System.out.println("해당 아이디가 없습니다.");
		
	}

	public static void main(String[] args) {

		System.out.println("테스트를 시작합니다.");
		start();

	}

}
