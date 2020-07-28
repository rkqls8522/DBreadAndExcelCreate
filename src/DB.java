import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	String url = "jdbc:mysql://127.0.0.1:3306/dbtest?useSSL=false&user=root&password=1234&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
	Connection conn = null;
	ResultSet rs = null;
	

	void connection(){
		
		
		// 드라이버 로드
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url); // 커넥션 생성
			System.out.println("Database connection OK!");
			
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
	
	void read() {
		
		try {
			// 커넥션 생성 전, 정의 먼저 깔끔하게 해주기
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url);
			Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String qu = "select * from dbtest.usertest;";
			
			rs = st.executeQuery(qu);
			
			System.out.println("id                  password            name                sex                 age                 juso\n"
					+ "-----------------------------------------------------------------------------------------------------------------------------------------");
			//각 열을 반복적으로 나타내줌
			while(rs.next()) {
				String id = rs.getString("id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String sex = rs.getString("sex");
				String age = rs.getString("age");
				String juso = rs.getString("juso");
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
	
	void insert(String id, String password, String name, String sex, int age, String juso) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url);
			Statement st = conn.createStatement();
			String sql = "insert into dbtest.usertest(`id`,`password`,`name`,`sex`,`age`,`juso`) values ('"+id+"','"+password+"','"+name+"','"+sex+"',"+age+",'"+juso+"');";
			
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
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url);
			Statement st = conn.createStatement();
			String qu = "select id from dbtest.usertest where name = '" + name+"';";
			
			rs = st.executeQuery(qu);
			String getId = String.valueOf(rs.next());
			System.out.println(getId);
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
		return "";
	}
	
	String check_id(String id) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url);
			Statement st = conn.createStatement();
			String qu = "select count(id) from dbtest.usertest where id = '" + id+"';";
			
			rs = st.executeQuery(qu);
			
		    String getId = String.valueOf(rs.next());
		    st.close();
		    return getId;
			
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
		return "";
	}
	
	String check_password(String id, String password) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url);
			Statement st = conn.createStatement();
			String qu = "select * from dbtest.usertest where id = '" + id + "' and password = '" + password+"';";
			
			rs = st.executeQuery(qu);
			
			String getPassword = String.valueOf(rs.next());
			
			st.close();
			return getPassword;
			
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
		return "";
	}
	
	void writeExcel() {
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url);
			Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String qu = "select * from dbtest.usertest;";
			
			rs = st.executeQuery(qu);
		
			//엑셀 클래스 부르기
			Excel excel = new Excel();
			
			
			while(rs.next()) {
				String id = rs.getString("id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String sex = rs.getString("sex");
				String age = rs.getString("age");
				String juso = rs.getString("juso");
				excel.create(id, password, name, sex, age, juso);
				excel.read();
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
