import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class test {

	public static void main(String[] args) {

		// Ŀ�ؼ� ���� ��, ���� ���� ����ϰ� ���ֱ�
		String url = "jdbc:mysql://127.0.0.1:3306/dbtest?useSSL=false&user=root&password=1234&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
		Connection conn = null;
		
		String id = "rkqls";
		String password = "word";
		String name = "gabinnn";
		String sex = "mail";
		String age = "999";
		String juso = "����Ư���� ������ ����";
		

		// ����̹� �ε�
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url); // Ŀ�ؼ� ����
			System.out.println("Database connection OK!");
			ResultSet rs = null;

			//����
			Statement st = conn.createStatement();
			String sql = "insert into dbtest.usertest(`id`,`password`,`name`,`sex`,`age`,`juso`) values ('"+id+"','"+password+"','"+name+"','"+sex+"',"+age+",'"+juso+"');";
			String qu = "select * from dbtest.usertest;";
			
//			st.executeUpdate(sql);
			rs = st.executeQuery(qu);

			
			System.out.println("id                  password            name                sex                 age                 juso\n"
					+ "-----------------------------------------------------------------------------------------------------------------------------------------");
			//�� ���� �ݺ������� ��Ÿ����
			while(rs.next()) {
				id = rs.getString("id");
				password = rs.getString("password");
				name = rs.getString("name");
				sex = rs.getString("sex");
				age = rs.getString("age");
				juso = rs.getString("juso");
				System.out.format("%-20s%-20s%-20s%-20s%-20s%s\n",id,password,name,sex,age,juso);
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
