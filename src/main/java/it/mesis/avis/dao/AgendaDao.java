package it.mesis.avis.dao;

import java.util.Date;
import java.util.List;

import it.mesis.avis.model.Agenda;
import it.mesis.avis.model.AgendaKey;
import it.mesis.avis.model.Donatore;
import it.mesis.avis.model.Puntoprelievo;
import it.mesis.avis.model.Tipodonaz;
import it.mesis.util.model.Hour;
import it.mesis.util.model.MonthlyBookings;
import it.mesis.util.model.ReportPreno;
import it.mesis.util.model.TipoDonaPuntoPrel;
import it.mesis.util.model.YearMonth;

public interface AgendaDao {

	Agenda findById(AgendaKey id);

	Tipodonaz getTipodonaz(int key);

	Puntoprelievo getPuntoprelievo(int key);
	
	MonthlyBookings getYearMonth(YearMonth yearMonth, TipoDonaPuntoPrel tipoDonazPuntoPrel, boolean updateable, AgendaKey agendaKey);

	void disdetta(AgendaKey id);

	Agenda prenota(String codinternodonat, Date datePreno, TipoDonaPuntoPrel tipoDonaPuntoPrel);
	
	Donatore getDonatore(String codinternodonat);

	List<Hour> freeHours(Date datePreno, TipoDonaPuntoPrel tipoDonaPuntoPrel);

	List<Hour> freeHours(Date datePreno, int puntoprelId, int tipoDonaId);
	
	List<Tipodonaz> getTipoDonazList();

	List<Puntoprelievo> getPuntiPrelievoList();

	List<ReportPreno> reportPreno(Date fromDate, Date toDate, Integer puntoPrelievo, Integer tipoDona);


}
