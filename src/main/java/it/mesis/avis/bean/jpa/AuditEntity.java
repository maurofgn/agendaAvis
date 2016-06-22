package it.mesis.avis.bean.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "AUDIT")
public class AuditEntity {
	
	public AuditEntity() {
		super();
	}
	
	public AuditEntity(String ssoId, String state) {
		super();
		this.ssoId = ssoId;
		this.created = new Date();
		this.state = state;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Size(min=1, max=30)
	@NotEmpty
	@Column(name = "SSO_ID", nullable = false)
	private String ssoId;

	@DateTimeFormat(pattern="dd/MM/yyyy hh:mm:ss")
	@Column(name = "CREATED", nullable = false)
	private Date created;

	@Size(min=1, max=250)
	@NotEmpty
	@Column(name = "STATE", nullable = false)
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + id;
		result = prime * result + ((ssoId == null) ? 0 : ssoId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuditEntity other = (AuditEntity) obj;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (id != other.id)
			return false;
		if (ssoId == null) {
			if (other.ssoId != null)
				return false;
		} else if (!ssoId.equals(other.ssoId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Audit [ssoId=" + ssoId + ", created=" + created + ", state=" + state + "]";
	}

}
