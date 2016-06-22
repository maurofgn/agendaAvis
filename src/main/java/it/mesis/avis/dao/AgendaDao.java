package it.mesis.avis.dao;

import java.util.Date;
import java.util.List;

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

public interface AgendaDao {

	AgendaEntity findById(AgendaEntityKey id);

	TipodonazEntity getTipodonaz(int key);

	PuntoprelievoEntity getPuntoprelievo(int key);
	
	MonthlyBookings getYearMonth(YearMonth yearMonth, TipoDonaPuntoPrel tipoDonazPuntoPrel, DonaStatus donaStatus);

	void disdetta(AgendaEntityKey id);

	AgendaEntity prenota(String codinternodonat, Date datePreno, TipoDonaPuntoPrel tipoDonaPuntoPrel);
	
	DonatoreEntity getDonatore(String codinternodonat);

	List<Hour> freeHours(Date datePreno, TipoDonaPuntoPrel tipoDonaPuntoPrel);

	List<Hour> freeHours(Date datePreno, int puntoprelId, int tipoDonaId);
	
	List<TipodonazEntity> getTipoDonazList();

	List<PuntoprelievoEntity> getPuntiPrelievoList();

	List<ReportPreno> reportPreno(Date fromDate, Date toDate, Integer puntoPrelievo, Integer tipoDona);
	
	Boolean hasPrenoActive(DonatoreEntity donatore);

	AgendaEntity getPrenoActive(DonatoreEntity donatore);

}
