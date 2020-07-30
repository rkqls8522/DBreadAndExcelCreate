package DBAndExcelData;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DBreadAndExcelCreate {
	DB db = new DB();
	static final Scanner sc = new Scanner(System.in);
	static final String idPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
	static final String pwPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$";
	static final String namePattern = "[A-Za-z ]*$";
	static final String agePattern = "[0-9]*$";
	static final String NumberPattern = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$";
	static final String e_mailPattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,6}$";
	static final String jusoPattern = "[A-Za-z0-9°¡-ÆR ]*$";

	void start() {

		System.out.println("\n---------------\nÇØ´ç»çÇ× ¹øÈ£¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.\n" + "1. ·Î±×ÀÎÇÏ±â\n" + "2. È¸¿ø°¡ÀÔÇÏ±â\n" + "3. ¾ÆÀÌµğ Ã£±â\n"
				+ "4. ºñ¹Ğ¹øÈ£ Ã£±â\n" + "5. È¸¿ø ¸ñ·Ï È®ÀÎ\n" + "6. Å»ÅğÇÏ±â\n" + "7. ³¡³»±â");

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
			System.out.println("¾È³çÈ÷ °¡¼¼¿ä.");
			break;
		default:
			System.out.println("Àß¸ø ÀÔ·ÂÇÏ¼Ì½À´Ï´Ù.");
			break;
		}

		if (num != 7) {
			System.out.println("\n´õ ¿øÇÏ½Ã´Â ÀÏÀÌ ÀÖ½À´Ï±î?");
			start();
		}

	}

	public void login() {
		System.out.println("¾ÆÀÌµğ¸¦ ÀÔ·ÂÇÏ¼¼¿ä.");
		String id = sc.nextLine();
		System.out.println("ºñ¹Ğ¹øÈ£¸¦ ÀÔ·ÂÇÏ¼¼¿ä.");
		String password = sc.nextLine();

		if (db.check_login(id, password))
			System.out.println("·Î±×ÀÎ ¼º°ø");
		else
			System.out.println("·Î±×ÀÎ ½ÇÆĞ");

	}

	public void join() {
		Matcher matPassword,matId,matname,matage,matjuso,matnumber,matE_mail;
		String id, password, name, sex, age, juso, number, e_mail;
		while (true) {
			System.out.println("¾ÆÀÌµğ¸¦ ÀÔ·ÂÇÏ¼¼¿ä.");
			System.out.println("¿µ¹®ÀÚ¿Í ¼ıÀÚ¸¦ Æ÷ÇÔÇÏ¿© 8~12ÀÚ¸®·Î ÀÔ·ÂÇÏ¼¼¿ä.");
			id = sc.nextLine();
			matId = Pattern.compile(idPattern).matcher(id);
			if (matId.matches() == false)
				System.out.println("´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.");
			else if (db.check_id(id))
				System.out.println("ÀÌ¹Ì ÀÖ´Â ¾ÆÀÌµğÀÔ´Ï´Ù.");
			else if (id.contains(" "))
				System.out.println("°ø¹éÀÌ ÀÖÀ¸¸é ¾È µË´Ï´Ù.");
			else
				break;
		}
		while (true) {
			System.out.println("ºñ¹Ğ¹øÈ£¸¦ ÀÔ·ÂÇÏ¼¼¿ä.");
			System.out.println("¿µ¹®ÀÚ¿Í ¼ıÀÚ¿Í Æ¯¼ö¹®ÀÚ¸¦ Æ÷ÇÔÇÏ¿© 8~12ÀÚ¸®·Î ÀÔ·ÂÇÏ¼¼¿ä.");
			password = sc.nextLine();
			String checkString="";
			int sum = 0;
			for(int i = 0; i < id.length() - 2; i++) {
				checkString = id.substring(i,i+3);
				if(password.contains(checkString))
					sum++;
			}
			matPassword = Pattern.compile(pwPattern).matcher(password);
			if (matPassword.matches() == false)
				System.out.println("´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.");
			else if (id.contains(" "))
				System.out.println("°ø¹éÀÌ ÀÖÀ¸¸é ¾È µË´Ï´Ù.");
			else if(sum > 0)
				System.out.println("¾ÆÀÌµğ¿Í µ¿ÀÏÇÑ ¿µ¹®ÀÚ³ª ¼ıÀÚ³ª 3ÀÚ¸® ÀÌ»ó ¿¬¼ÓÀ¸·Î ÀÖÀ¸¸é ¾È µË´Ï´Ù.");
			else
				break;
		}
		while (true) {
			System.out.println("ÀÌ¸§À» ÀÔ·ÂÇÏ¼¼¿ä.");
			System.out.println("¿µ¹®ÀÚ·Î¸¸ ÀÔ·ÂÇÏ¼¼¿ä.");
			name = sc.nextLine();
			matname = Pattern.compile(namePattern).matcher(name);
			if (matname.matches() == false)
				System.out.println("´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.");
			else
				break;
		}
		target: while (true) {
			System.out.println("¼ºº°À» ÀÔ·ÂÇÏ¼¼¿ä(1~2).\n" + "1. male\n" + "2. female");
			String num = sc.nextLine();
			switch (num) {
			case "1":
				sex = "male";
				break target;
			case "2":
				sex = "female";
				break target;
			default:
				System.out.println("´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.");
			}

		}
		while (true) {
			System.out.println("³ªÀÌ¸¦ ÀÔ·ÂÇÏ¼¼¿ä.");
			age = sc.nextLine();
			matage = Pattern.compile(agePattern).matcher(age);
			if (matage.matches() == false)
				System.out.println("´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.");
			else
				break;
		}
		while (true) {
			System.out.println("ÁÖ¼Ò¸¦ ÀÔ·ÂÇÏ¼¼¿ä.");
			juso = sc.nextLine();
			matjuso = Pattern.compile(jusoPattern).matcher(juso);
			if (matjuso.matches() == false)
				System.out.println("´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.");
			else
				break;
		}

		while (true) {
			System.out.println("ÈŞ´ëÀüÈ­¹øÈ£¸¦ ÀÔ·ÂÇÏ¼¼¿ä.\n" + "¶ç¾î¾²±â ¾øÀÌ '-'¸¦ Æ÷ÇÔÇÏ°í ÀÔ·ÂÇÏ¼¼¿ä.");
			number = sc.nextLine();
			matnumber = Pattern.compile(NumberPattern).matcher(number);
			if (matnumber.matches() == false)
				System.out.println("´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.");
			else
				break;
		}
		while (true) {
			System.out.println("ÀÌ¸ŞÀÏÀ» ÀÔ·ÂÇÏ¼¼¿ä.");
			e_mail = sc.nextLine();
			matE_mail = Pattern.compile(e_mailPattern).matcher(e_mail);
			if (matE_mail.matches() == false)
				System.out.println("´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.");
			else
				break;
		}

		db.insert(id, password, name, sex, age, juso, number, e_mail);
		System.out.println("°¡ÀÔÀ» ¿Ï·áÇß½À´Ï´Ù.");
		db.writeExcel();
	}

	public void find_id() {
		DB db = new DB();
		Scanner sc = new Scanner(System.in);
		System.out.println("ÀÌ¸§À» ÀÔ·ÂÇÏ¼¼¿ä.");
		String name = sc.nextLine();
		String matId = db.find_id(name);
		System.out.println("ID : " + matId);

	}

	public void find_password() {
		DB db = new DB();
		Scanner sc = new Scanner(System.in);
		System.out.println("¾ÆÀÌµğ¸¦ ÀÔ·ÂÇÏ¼¼¿ä.");
		String id = sc.nextLine();

		System.out.println("ÀÌ¸§À» ÀÔ·ÂÇÏ¼¼¿ä.");
		String name = sc.nextLine();

		String matPassword = db.find_password(id, name);
		System.out.println("PASSWORD : " + matPassword);

	}

	public void check_user() {
		DB db = new DB();
		Scanner sc = new Scanner(System.in);
		System.out.println("È¸¿ø ¸ñ·ÏÀ» Ãâ·ÂÇÕ´Ï´Ù.");
		db.read();

	}

	public void delete_user() {
		DB db = new DB();
		Scanner sc = new Scanner(System.in);
		System.out.println("¾ÆÀÌµğ¸¦ ÀÔ·ÂÇÏ¼¼¿ä.");
		String id = sc.nextLine();
		if (db.check_id(id)) {
			target: while (true) {
				System.out.println("Á¤¸»·Î Å»ÅğÇÏ½Ã°Ú½À´Ï±î? (1~2)\n" + "1. yes\n" + "2. no");
				String answer = sc.nextLine();
				switch (answer) {
				case "1":
					db.delete(id);
					System.out.println("Å»Åğ¸¦ ¿Ï·áÇß½À´Ï´Ù.");
					db.writeExcel();
					break target;
				case "2":
					break target;
				default:
					System.out.println("´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.");
				}
			}
		} else
			System.out.println("ÇØ´ç ¾ÆÀÌµğ°¡ ¾ø½À´Ï´Ù.");
		
	}


}
