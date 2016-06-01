package it.mesis.avis.service;

import it.mesis.avis.model.Agenda;
import it.mesis.avis.model.AgendaKey;
import it.mesis.avis.model.Donatore;
import it.mesis.avis.model.Puntoprelievo;
import it.mesis.avis.model.Tipodonaz;
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
		
	Agenda findById(AgendaKey id);

	Tipodonaz getTipodonaz(int key);

	Puntoprelievo getPuntoprelievo(int key);

	MonthlyBookings getYearMonth(YearMonth yearMonth, TipoDonaPuntoPrel tipoDonazPuntoPrel, DonaStatus donaStatus);
	
	void disdetta(AgendaKey id);
	
	Boolean hasPrenoActive(Donatore donatore);

	Agenda prenota(String codinternodonat, Date datePreno, TipoDonaPuntoPrel tipoDonaPuntoPrel);
	
	Agenda getPrenoActive(Donatore donatore) ;
	
	Donatore getDonatore(String codinternodonat);
	
	List<Hour> freeHours(Date datePreno, TipoDonaPuntoPrel tipoDonaPuntoPrel);
	
	List<Hour> freeHours(Date datePreno, int puntoprelId, int tipoDonaId);
	
	List<Tipodonaz> getTipoDonazList();
	
	List<Puntoprelievo> getPuntiPrelievoList();
	
	List<ReportPreno> reportPreno(Date fromDate, Date toDate, Integer puntoPrelievo, Integer tipoDona);
	
}
