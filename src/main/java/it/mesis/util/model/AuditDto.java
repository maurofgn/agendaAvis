package it.mesis.util.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AuditDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private static final SimpleDateFormat dfh = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	public AuditDto(String ssoId, String state, Date created) {
		super();
		this.ssoId = ssoId;
		this.created = created;
		this.state = state;
	}

	private int id;
	private String ssoId;
	private Date created;
	private String state;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSsoId() {
		return ssoId;
	}

	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}

	public Date getCreated() {
		return created;
	}
	
	public String getCreatedString() {
		return df.format(created);
	}
	
	public String getCreatedHourString() {
		return dfh.format(created);
	}


	public void setCreated(Date created) {
		this.created = created;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Audit [ssoId=" + ssoId + ", created=" + created + ", state=" + state + "]";
	}

}
