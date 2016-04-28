package it.mesis.avis.revision;

import javax.persistence.Entity;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

@Entity
@RevisionEntity(AuditRevisionListener.class)
public class AuditEntity extends DefaultRevisionEntity {

	private static final long serialVersionUID = -3278104082834417099L;
	
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
