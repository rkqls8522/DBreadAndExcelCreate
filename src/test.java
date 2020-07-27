import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class test {

	public static void main(String[] args) {

		// 커넥션 생성 전, 정의 먼저 깔끔하게 해주기
		String url = "jdbc:mysql://127.0.0.1:3306/dbtest?useSSL=false&user=root&password=1234&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
		Connection conn = null;
		
		String id = "rkqls";
		String password = "word";
		String name = "gabinnn";
		String sex = "mail";
		String age = "999";
		String juso = "서울특별시 땡떙동 떙댕";
		

		// 드라이버 로드
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url); // 커넥션 생성
			System.out.println("Database connection OK!");
			ResultSet rs = null;

			//쿼리
			Statement st = conn.createStatement();
			String sql = "insert into dbtest.usertest(`id`,`password`,`name`,`sex`,`age`,`juso`) values ('"+id+"','"+password+"','"+name+"','"+sex+"',"+age+",'"+juso+"');";
			String qu = "select * from dbtest.usertest;";
			
//			st.executeUpdate(sql);
			rs = st.executeQuery(qu);

			
			System.out.println("id                  password            name                sex                 age                 juso\n"
					+ "-----------------------------------------------------------------------------------------------------------------------------------------");
			//각 열을 반복적으로 나타내줌
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
