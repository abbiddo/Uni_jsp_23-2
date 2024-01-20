package Schedule;

public class Schedule {
	
	private int sid;
	private String subject;
	private String date;
	private String participant1;
	private String participant2;
	private String winner;
	
	
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getParticipant1() {
		return participant1;
	}
	public void setParticipant1(String participant1) {
		this.participant1 = participant1;
	}
	
	public String getParticipant2() {
		return participant2;
	}
	public void setParticipant2(String participant2) {
		this.participant2 = participant2;
	}
	
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
	
}
