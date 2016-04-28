package it.mesis.util.model;

import it.mesis.avis.model.Audit;

import java.util.ArrayList;
import java.util.List;

public class AuditMapper {

	public static AuditDto map(Audit audit) {
			AuditDto dto = new AuditDto(audit.getSsoId(), audit.getState(), audit.getCreated());
			dto.setId(audit.getId());
			return dto;
	}
	
	public static List<AuditDto> map(List<Audit> audits) {
		List<AuditDto> dtos = new ArrayList<AuditDto>();
		for (Audit audit: audits) {
			dtos.add(map(audit));
		}
		return dtos;
	}
}
