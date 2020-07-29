import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DBreadAndExcelCreate {

	static void start() {

		System.out.println("\n---------------\n�ش���� ��ȣ�� �Է����ּ���.\n" + "1. �α����ϱ�\n" + "2. ȸ�������ϱ�\n" + "3. ���̵� ã��\n"
				+ "4. ��й�ȣ ã��\n" + "5. ȸ�� ��� Ȯ��\n" + "6. Ż���ϱ�\n" + "7. ������");

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
			System.out.println("�ȳ��� ������.");
			break;
		default:
			System.out.println("�߸� �Է��ϼ̽��ϴ�.");
			break;
		}

		if (num != 7) {
			System.out.println("\n�� ���Ͻô� ���� �ֽ��ϱ�?");
			start();
		}

	}

	public static void login() {
		DB db = new DB();
		Scanner sc = new Scanner(System.in);
		System.out.println("���̵� �Է��ϼ���.");
		String id = sc.nextLine();
		System.out.println("��й�ȣ�� �Է��ϼ���.");
		String password = sc.nextLine();

		if (db.check_login(id, password) == "true")
			System.out.println("�α��� ����");
		else
			System.out.println("�α��� ����");

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
		String jusoPattern = "[A-Za-z0-9��-�R ]*$";

		String id, password, name, sex, age, juso, number, e_mail;
		while (true) {
			System.out.println("���̵� �Է��ϼ���.");
			System.out.println("�����ڿ� ���ڸ� �����Ͽ� 8~12�ڸ��� �Է��ϼ���.");
			id = sc.nextLine();
			Matcher matId = Pattern.compile(idPattern).matcher(id);
			if (matId.matches() == false)
				System.out.println("�ٽ� �Է��ϼ���.");
			else if (db.check_id(id) == "true")
				System.out.println("�̹� �ִ� ���̵��Դϴ�.");
			else if (id.contains(" "))
				System.out.println("������ ������ �� �˴ϴ�.");
			else
				break;
		}
		while (true) {
			System.out.println("��й�ȣ�� �Է��ϼ���.");
			System.out.println("�����ڿ� ���ڸ� �����Ͽ� 8~12�ڸ��� �Է��ϼ���.");
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
				System.out.println("�ٽ� �Է��ϼ���.");
			else if (id.contains(" "))
				System.out.println("������ ������ �� �˴ϴ�.");
			else if(sum > 0)
				System.out.println("���̵�� ������ �����ڳ� ���ڳ� 3�ڸ� �̻� �������� ������ �� �˴ϴ�.");
			else
				break;
		}
		while (true) {
			System.out.println("�̸��� �Է��ϼ���.");
			System.out.println("�����ڷθ� �Է��ϼ���.");
			name = sc.nextLine();
			Matcher matname = Pattern.compile(namePattern).matcher(name);
			if (matname.matches() == false)
				System.out.println("�ٽ� �Է��ϼ���.");
			else
				break;
		}
		target: while (true) {
			System.out.println("������ �Է��ϼ���(1~2).\n" + "1. male\n" + "2. female");
			String num = sc.nextLine();
			switch (num) {
			case "1":
				sex = "male";
				break target;
			case "2":
				sex = "female";
				break target;
			default:
				System.out.println("�ٽ� �Է��ϼ���.");
			}

		}
		while (true) {
			System.out.println("���̸� �Է��ϼ���.");
			age = sc.nextLine();
			Matcher matage = Pattern.compile(agePattern).matcher(age);
			if (matage.matches() == false)
				System.out.println("�ٽ� �Է��ϼ���.");
			else
				break;
		}
		while (true) {
			System.out.println("�ּҸ� �Է��ϼ���.");
			juso = sc.nextLine();
			Matcher matjuso = Pattern.compile(jusoPattern).matcher(juso);
			if (matjuso.matches() == false)
				System.out.println("�ٽ� �Է��ϼ���.");
			else
				break;
		}

		while (true) {
			System.out.println("�޴���ȭ��ȣ�� �Է��ϼ���.\n" + "���� ���� '-'�� �����ϰ� �Է��ϼ���.");
			number = sc.nextLine();
			Matcher matnumber = Pattern.compile(NumberPattern).matcher(number);
			if (matnumber.matches() == false)
				System.out.println("�ٽ� �Է��ϼ���.");
			else
				break;
		}
		while (true) {
			System.out.println("�̸����� �Է��ϼ���.");
			e_mail = sc.nextLine();
			Matcher matE_mail = Pattern.compile(e_mailPattern).matcher(e_mail);
			if (matE_mail.matches() == false)
				System.out.println("�ٽ� �Է��ϼ���.");
			else
				break;
		}

		db.insert(id, password, name, sex, age, juso, number, e_mail);
		System.out.println("������ �Ϸ��߽��ϴ�.");
		db.writeExcel();
	}

	public static void find_id() {
		DB db = new DB();
		Scanner sc = new Scanner(System.in);
		System.out.println("�̸��� �Է��ϼ���.");
		String name = sc.nextLine();
		String matId = db.find_id(name);
		System.out.println("ID : " + matId);

	}

	public static void find_password() {
		DB db = new DB();
		Scanner sc = new Scanner(System.in);
		System.out.println("���̵� �Է��ϼ���.");
		String id = sc.nextLine();

		System.out.println("�̸��� �Է��ϼ���.");
		String name = sc.nextLine();

		String matPassword = db.find_password(id, name);
		System.out.println("PASSWORD : " + matPassword);

	}

	public static void check_user() {
		DB db = new DB();
		Scanner sc = new Scanner(System.in);
		System.out.println("ȸ�� ����� ����մϴ�.");
		db.read();

	}

	public static void delete_user() {
		DB db = new DB();
		Scanner sc = new Scanner(System.in);
		System.out.println("���̵� �Է��ϼ���.");
		String id = sc.nextLine();
		if (db.check_id(id) == "true") {
			target: while (true) {
				System.out.println("������ Ż���Ͻðڽ��ϱ�? (1~2)\n" + "1. yes\n" + "2. no");
				String answer = sc.nextLine();
				switch (answer) {
				case "1":
					db.delete(id);
					System.out.println("Ż�� �Ϸ��߽��ϴ�.");
					db.writeExcel();
					break target;
				case "2":
					break target;
				default:
					System.out.println("�ٽ� �Է��ϼ���.");
				}
			}
		} else
			System.out.println("�ش� ���̵� �����ϴ�.");
		
	}

	public static void main(String[] args) {

		System.out.println("�׽�Ʈ�� �����մϴ�.");
		start();

	}

}
