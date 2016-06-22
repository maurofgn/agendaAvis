package it.mesis.avis.revision;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditRevisionListener implements RevisionListener {

	@Override
	public void newRevision(Object revisionEntity) {
		AuditEntityUser auditEntity = (AuditEntityUser) revisionEntity;
		String userName = SecurityContextHolder.getContext().getAuthentication().getName(); //get logged in username
		auditEntity.setUsername(userName);
	}

}
