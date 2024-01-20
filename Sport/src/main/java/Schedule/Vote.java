package Schedule;

public class Vote {

	private int vid;
	private String userid;
	private int sid;
	private int point;
	private String voteParticipant;
	
	
	public int getVid() {
		return vid;
	}
	public void setVid(int vid) {
		this.vid = vid;
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	
	public String getVoteParticipant() {
		return voteParticipant;
	}
	public void setVoteParticipant(String voteParticipant) {
		this.voteParticipant = voteParticipant;
	}
	
}