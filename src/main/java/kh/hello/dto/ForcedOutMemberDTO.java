package kh.hello.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ForcedOutMemberDTO {
	private int seq;
	private String id;
	private String email;
	private String reason;
	private Timestamp outDate;
	
	public ForcedOutMemberDTO() {
		super();
	}

	
	public ForcedOutMemberDTO(int seq, String id, String email, String reason, Timestamp outDate) {
		super();
		this.seq = seq;
		this.id = id;
		this.email = email;
		this.reason = reason;
		this.outDate = outDate;
	}

	

	public int getSeq() {
		return seq;
	}


	public void setSeq(int seq) {
		this.seq = seq;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}


	public Timestamp getOutDate() {
		return outDate;
	}


	public void setOutDate(Timestamp outDate) {
		this.outDate = outDate;
	}


	public String getFormedDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		return sdf.format(outDate);
	}
	

}
