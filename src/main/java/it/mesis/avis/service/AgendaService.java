package it.mesis.avis.service;

import it.mesis.avis.bean.jpa.AgendaEntity;
import it.mesis.avis.bean.jpa.AgendaEntityKey;
import it.mesis.avis.bean.jpa.DonatoreEntity;
import it.mesis.avis.bean.jpa.PuntoprelievoEntity;
import it.mesis.avis.bean.jpa.TipodonazEntity;
import it.mesis.util.model.DonaStatus;
import it.mesis.util.model.Hour;
import it.mesis.util.model.MonthlyBookings;
import it.mesis.util.model.ReportPreno;
import it.mesis.util.model.TipoDonaPuntoPrel;
import it.mesis.util.model.YearMonth;

import java.util.Date;
import java.util.List;

public interface AgendaService {
	
//	List<Agenda> findAll();
		
	AgendaEntity findById(AgendaEntityKey id);

	TipodonazEntity getTipodonaz(int key);

	PuntoprelievoEntity getPuntoprelievo(int key);

	MonthlyBookings getYearMonth(YearMonth yearMonth, TipoDonaPuntoPrel tipoDonazPuntoPrel, DonaStatus donaStatus);
	
	void disdetta(AgendaEntityKey id);
	
	Boolean hasPrenoActive(DonatoreEntity donatore);

	AgendaEntity prenota(String codinternodonat, Date datePreno, TipoDonaPuntoPrel tipoDonaPuntoPrel);
	
	AgendaEntity getPrenoActive(DonatoreEntity donatore) ;
	
	DonatoreEntity getDonatore(String codinternodonat);
	
	List<Hour> freeHours(Date datePreno, TipoDonaPuntoPrel tipoDonaPuntoPrel);
	
	List<Hour> freeHours(Date datePreno, int puntoprelId, int tipoDonaId);
	
	List<TipodonazEntity> getTipoDonazList();
	
	List<PuntoprelievoEntity> getPuntiPrelievoList();
	
	List<ReportPreno> reportPreno(Date fromDate, Date toDate, Integer puntoPrelievo, Integer tipoDona);
	
}
