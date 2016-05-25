package it.mesis.avis.revision;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditRevisionListener implements RevisionListener {

	@Override
	public void newRevision(Object revisionEntity) {
		AuditEntity auditEntity = (AuditEntity) revisionEntity;
		String userName = SecurityContextHolder.getContext().getAuthentication().getName(); //get logged in username
		auditEntity.setUsername(userName);
	}

}
