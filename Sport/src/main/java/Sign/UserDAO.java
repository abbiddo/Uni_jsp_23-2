package Sign;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	public boolean addUser(User user) throws Exception {
		Connection conn = open();
		String sql = "INSERT INTO users(id, pw, name, point, major, stnumber, role) values(?,?,?,1000,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		if(CheckUser(user)) {
			return false;
		}
		try (conn; pstmt) {
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getMajor());
			pstmt.setInt(5, user.getStNumber());
			pstmt.setString(6, user.getRole());
			pstmt.executeUpdate();
		}
		return true;
	}
	
	public boolean CheckUser(User user) throws Exception{
		Connection conn = open();
		String sql = "SELECT * FROM users WHERE id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, user.getId());
		ResultSet rs = pstmt.executeQuery();
		
		return rs.next();
		
		
	}
	
	public User LoginUser(Login login) throws Exception{
		Connection conn = open();
		String sql = "SELECT * FROM users WHERE id=? AND pw=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, login.getId());
		pstmt.setString(2, login.getPassword());
		ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		try (conn; pstmt; rs) {
			User u = new User();
			u.setId(rs.getString("id"));
			u.setMajor(rs.getString("major"));
			u.setName(rs.getString("name"));
			u.setPassword(rs.getString("pw"));
			u.setStNumber(rs.getInt("stnumber"));
			u.setRole(rs.getString("role"));
			u.setPoint(rs.getInt("point"));
			return u;
		}
		
	}

}
