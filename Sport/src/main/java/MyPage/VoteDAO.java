package MyPage;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import Sign.User;
import Schedule.Vote;

public class VoteDAO {
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/test2";
	
	public Connection open() {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL,"sa", "1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public List<Vote> getAll(String userId) throws Exception{
		Connection conn = open();
		List<Vote> myVotesList = new ArrayList<>();
		String sql = "SELECT * FROM vote WHERE userid=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userId);
		ResultSet rs = pstmt.executeQuery();
		
		try(conn;pstmt;rs){
			while(rs.next()) {
				Vote v = new Vote();
				v.setVid(rs.getInt("vid"));
				v.setUserid(rs.getString("userid"));
				v.setSid(rs.getInt("sid"));
				v.setPoint(rs.getInt("point"));
				v.setVoteParticipant(rs.getString("voteparticipant"));
				myVotesList.add(v);
			}
		}
		return myVotesList;
	}
}
