package it.mesis.avis.dao;

import it.mesis.avis.bean.Audit;
import it.mesis.avis.bean.jpa.AuditEntity;
import it.mesis.util.model.jq.ColumnsDataTable;
import it.mesis.util.model.jq.DataTable;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface AuditDao {

	void save(AuditEntity audit);
	
	AuditEntity findById(int id);
	
	List<AuditEntity> findAllBySSO(String sso, Timestamp timestamp);
	
	List<AuditEntity> findAllAudits();

	List<AuditEntity> findAudits(Date dateFrom, Date dateTo, String user, String state);

	/**
	 * 
	 * @param firstResult primo record da leggere (0 based)
	 * @param pageSize
	 * @param dateFrom
	 * @param dateTo
	 * @param user
	 * @param state
	 * @return List<Audit>
	 */
	List<AuditEntity> findAudits(int firstResult, int pageSize, Date dateFrom, Date dateTo, String user, String state);
	
	/**
	 * 
	 * @param columnsDataTable dati da estrarre e modalità di ordinamento
	 * @param dateFrom dal
	 * @param dateTo al
	 * @param user user per like
	 * @param state messaggio per like
	 * @return DataTable<AuditDto> con la pagina richiesta che soddisfa il filtro
	 */
	DataTable<Audit> findAuditsPages(ColumnsDataTable columnsDataTable, Date dateFrom, Date dateTo, String user, String state);
	
	/**
	 * 
	 * @param dateFrom dal
	 * @param dateTo al
	 * @param user user per like
	 * @param state messaggio per like
	 * @return nr records che soddisfano il filtro
	 */
	Long count(Date dateFrom, Date dateTo, String user, String state);

	/**
	 * 
	 * @return record totali della tabella
	 */
	Long count();
}

