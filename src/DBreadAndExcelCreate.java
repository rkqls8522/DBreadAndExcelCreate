import java.util.Scanner;

public class DBreadAndExcelCreate {

	static void start() {

		System.out.println("\n---------------\n�ش���� ��ȣ�� �Է����ּ���.\n"
				+ "1. �α����ϱ�\n"
				+ "2. ȸ�������ϱ�\n"
				+ "3. ���̵� ã��\n"
				+ "4. ��й�ȣ ã��\n"
				+ "5. ȸ�� ��� Ȯ��\n"
				+ "6. ������");
		
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		
		System.out.println("\n---------------\n");
		switch(num) {
		case 1 : login();break;
		case 2 : join();break;
		case 3 : find_id();break;
		case 4 : find_password();break;
		case 5 : check_user();break;
		case 6 : System.out.println("�ȳ��� ������.");break;
		default : System.out.println("�߸� �Է��ϼ̽��ϴ�."); break;
		}
		
		if(num !=6)	start();	
		
		
		
		
		
		
	}
	
	
	
	
	public static void login() {
		Scanner sc = new Scanner(System.in);
		System.out.println("���̵� �Է��ϼ���.");
		String id = sc.next();
	}
	
	public static void join() {
		Scanner sc = new Scanner(System.in);
		System.out.println("ȸ�������ϱ�");
	}
	
	public static void find_id() {
		Scanner sc = new Scanner(System.in);
		System.out.println("�̸��� �Է��ϼ���.");
		String name = sc.next();
		
		DB db = new DB();
		
		String matName = db.find_id(name);
		
		if(matName == true)
	}
	
	public static void find_password() {
		Scanner sc = new Scanner(System.in);
		System.out.println("��й�ȣ ã��");
	}
	
	public static void check_user() {
		Scanner sc = new Scanner(System.in);
		System.out.println("ȸ�� ��� Ȯ��");
	}
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {

		System.out.println("�׽�Ʈ�� �����մϴ�.");
		start();
		
	}

}


