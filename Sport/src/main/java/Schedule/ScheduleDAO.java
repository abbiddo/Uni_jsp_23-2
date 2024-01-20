package Schedule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDAO {
	
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/test2";
	
	// DB 연결
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
	
	public Schedule recentMatch() throws SQLException {
		Connection conn = open();
		Schedule n = new Schedule();

		String sql = "SELECT * FROM schedule WHERE date > FORMATDATETIME(NOW(),'yyyy-MM-dd hh:mm') order by date asc";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		
		try(conn; pstmt; rs) {
			n.setSid(rs.getInt("sid"));
			n.setSubject(rs.getString("subject"));
			n.setDate(rs.getString("date"));
			n.setParticipant1(rs.getString("participant1"));
			n.setParticipant2(rs.getString("participant2"));
		}

		return n;
	}

	public List<Schedule> getAll() throws SQLException {
		Connection conn = open();
		List<Schedule> scheduleList = new ArrayList<>();

		String sql = "SELECT * FROM schedule WHERE date > FORMATDATETIME(NOW(),'yyyy-MM-dd hh:mm') order by date asc";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		try(conn; pstmt; rs) {
			while(rs.next()) {
				Schedule n = new Schedule();
				n.setSid(rs.getInt("sid"));
				n.setSubject(rs.getString("subject"));
				n.setDate(rs.getString("date"));
				n.setParticipant1(rs.getString("participant1"));
				n.setParticipant2(rs.getString("participant2"));
				n.setWinner(rs.getString("winner"));
				scheduleList.add(n);
			}
		}

		return scheduleList;
	}
	
	public String getSchedule(int id, int choice) throws SQLException {
		Connection conn = open();
		String sql = "SELECT participant"+choice+" from schedule where sid = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		
		try(conn; pstmt; rs) {
			return rs.getString("participant"+choice);
		}
	}
	
	public void addVote(Vote v) throws Exception {
		Connection conn = open();
		String sql = "insert into Vote(userid, sid, point, voteParticipant) values (?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try(conn; pstmt) {
			pstmt.setString(1,  v.getUserid());
			pstmt.setInt(2, v.getSid());
			pstmt.setInt(3,  v.getPoint());
			pstmt.setString(4, v.getVoteParticipant());
			pstmt.executeUpdate();
		}
	}
	
	public void updatePoint(String id, int point) throws Exception {
		Connection conn = open();
		String sql = "UPDATE users SET point = point - ? where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try(conn; pstmt){
			pstmt.setInt(1,  point);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
		}
	}
	
	public List<Schedule> getLastSchedule() throws SQLException {
		Connection conn = open();
		List<Schedule> scheduleList = new ArrayList<>();

		String sql = "SELECT * FROM schedule WHERE date < FORMATDATETIME(NOW(),'yyyy-MM-dd hh:mm') order by date desc";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		try(conn; pstmt; rs) {
			while(rs.next()) {
				Schedule n = new Schedule();
				n.setSid(rs.getInt("sid"));
				n.setSubject(rs.getString("subject"));
				n.setDate(rs.getString("date"));
				n.setParticipant1(rs.getString("participant1"));
				n.setParticipant2(rs.getString("participant2"));
				n.setWinner(rs.getString("winner"));
				scheduleList.add(n);
			}
		}

		return scheduleList;
	}
	
	public void winnerSave(int sid, String winner) throws Exception  {
		Connection conn = open();
		String sql = "UPDATE schedule SET winner = ? where sid = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try(conn; pstmt){
			pstmt.setString(1,  winner);
			pstmt.setInt(2, sid);
			pstmt.executeUpdate();
		}
	}
	
	public void updatePoint(int point, String id) throws Exception  {
		Connection conn = open();
		String sql = "Update users set point = point + ? where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		try(conn; pstmt){
			pstmt.setInt(1, point * 2);
			pstmt.setString(2,  id);
			pstmt.executeUpdate();
		}
	}
	
	public void winnerPoint(int sid, String winner) throws Exception  {
		Connection conn = open();
		String sql = "select userid, point from vote where sid = ? and voteParticipant = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, sid);
		pstmt.setString(2,  winner);
		
		ResultSet rs = pstmt.executeQuery();

		List<Vote> vote = new ArrayList<>();		
		try(conn; pstmt; rs) {
			while(rs.next()) {
				Vote v = new Vote();
				v.setUserid(rs.getString("userid"));
				v.setPoint(rs.getInt("point"));
				vote.add(v);
			}
		}

		for (Vote v : vote) {
			updatePoint(v.getPoint(), v.getUserid());
		}

	}
	
	public void addSchedule(Schedule s) throws Exception {
		Connection conn = open();
		String sql = "insert into Schedule(subject, date, participant1, participant2) values(?, ? ,? ,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try(conn; pstmt){
			pstmt.setString(1, s.getSubject());
			pstmt.setString(2, s.getDate());
			pstmt.setString(3, s.getParticipant1());
			pstmt.setString(4, s.getParticipant2());			
			pstmt.executeUpdate();
		}
	}
	
}
