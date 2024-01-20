package MyPage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Sign.User;

public class UserDAO {
	
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/test2";
	
	public Connection open() {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, "sa", "1234");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public User getUser(String userId) throws SQLException{
		Connection conn = open();
		User u = new User();
		
		String sql = "SELECT * FROM users WHERE id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userId);
		
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		
		try(conn;pstmt;rs){
			u.setId(rs.getString("id"));
			u.setPassword(rs.getString("pw"));
			u.setName(rs.getString("name"));
			u.setPoint(rs.getInt("point"));
			u.setMajor(rs.getString("major"));
			u.setStNumber(rs.getInt("stnumber"));
			u.setRole(rs.getString("role"));
		}
		return u;
	}
}
