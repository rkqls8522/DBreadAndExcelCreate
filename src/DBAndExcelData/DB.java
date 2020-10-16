package DBAndExcelData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	static final String url = "jdbc:mysql://127.0.0.1:3306/dbtest?useSSL=false&user=root&password=1234&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
	// Ŀ�ؼ� ���� ��, ���� ���� ����ϰ� ���ֱ�
	Connection conn = null;
	ResultSet rs = null;

//	void connection() {
//
//		// ����̹� �ε�
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			conn = DriverManager.getConnection(url); // Ŀ�ؼ� ����
//			System.out.println("Database connection OK!");
//
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} finally {
//			if (conn != null)
//				try {
//					conn.close();
//				} catch (SQLException conne) {
//				}
//		}
//	}

	void read() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url);
			Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String qu = "select * from dbtest.usertest;";

			rs = st.executeQuery(qu);

			System.out.println(
					"id                  password            name                sex            age       number              e_mail                   juso\n"
							+ "---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			// �� ���� �ݺ������� ��Ÿ����
			while (rs.next()) {
				String id = rs.getString("id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String sex = rs.getString("sex");
				String age = rs.getString("age");
				String juso = rs.getString("juso");
				String number = rs.getString("number");
				String e_mail = rs.getString("e_mail");
				System.out.format("%-20s%-20s%-20s%-15s%-10s%-20s%-25s%s\n", id, password, name, sex, age,number,e_mail, juso);
			}
			st.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException conne) {
				}
		}
	}

	void insert(String id, String password, String name, String sex, String age, String juso, String number,
			String e_mail) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url);
			Statement st = conn.createStatement();
			String sql = "insert into dbtest.usertest(`id`,`password`,`name`,`sex`,`age`,`juso`,`number`,`e_mail`) values ('"
					+ id + "','" + password + "','" + name + "','" + sex + "'," + age + ",'" + juso + "','" + number
					+ "','" + e_mail + "');";
			st.executeUpdate(sql);
			st.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException conne) {
				}
		}
	}

	// ȸ��Ż��� ���. id�� key���̱� ������ id�θ����� ó��.
	void delete(String id) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url);
			Statement st = conn.createStatement();
			String sql = "delete from dbtest.usertest where id = '" + id + "';";
			st.executeUpdate(sql);
			st.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException conne) {
				}
		}
	}

	String find_id(String name) {
		String id="";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url);
			Statement st = conn.createStatement();
			String qu = "select id from dbtest.usertest where name = '" + name + "';";

			rs = st.executeQuery(qu);
			if (rs.next()) {
				id = rs.getString("id");
				st.close();
			} else {
				return "�ش� ���̵� ã�� �� �����ϴ�.";
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException conne) {
				}
		}
		return id;
	}

	boolean check_id(String id) {
		boolean getId=false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url);
			Statement st = conn.createStatement();
			String qu = "select id from dbtest.usertest where id = '" + id + "';";

			rs = st.executeQuery(qu);
			getId = rs.next();
			st.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException conne) {
				}
		}
		return getId;
	}

	String find_password(String id, String name) {
		String password="";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url);
			Statement st = conn.createStatement();
			String qu = "select password from dbtest.usertest where id = '" + id + "' and name = '" + name + "';";

			rs = st.executeQuery(qu);

			if (rs.next()) {
				password = rs.getString("password");
				st.close();
			} else {
				return "�ش� ��й�ȣ�� ã�� �� �����ϴ�.";
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException conne) {
				}
		}
		return password;
	}

	boolean check_login(String id, String password) {
		boolean checkValue=false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url);
			Statement st = conn.createStatement();
			String qu = "select * from dbtest.usertest where id = '" + id + "' and password = '" + password + "';";

			rs = st.executeQuery(qu);

			checkValue = rs.next();
			st.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException conne) {
				}
		}
		return checkValue;
	}

	void writeExcel() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url);
			Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String qu = "select * from dbtest.usertest;";

			rs = st.executeQuery(qu);

			// ���� Ŭ���� �θ���
			Excel excel = new Excel();
			//���� ����
			excel.create();
			int i = 2;
			while (rs.next()) {
				String id = rs.getString("id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String sex = rs.getString("sex");
				String age = rs.getString("age");
				String juso = rs.getString("juso");
				String number = rs.getString("number");
				String e_mail = rs.getString("e_mail");
				
				//�������. �� �ֱ�
				excel.insert(id, password, name, sex, age, juso,number,e_mail,i);
				i++;
			}
			st.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException conne) {
				}
		}
	}

}
