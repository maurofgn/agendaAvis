package it.mesis.avis.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.mesis.avis.dao.AgendaDao;
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

@Service("agendaService")
@Transactional
public class AgendaServiceImpl implements AgendaService {

	@Autowired
	private AgendaDao dao;
	
	@Override
	public Agenda findById(AgendaKey id) {
		return dao.findById(id);
	}

	@Override
	public List<Tipodonaz> getTipoDonazList() {
		return dao.getTipoDonazList();
	}

	@Override
	public Tipodonaz getTipodonaz(int key) {
		return dao.getTipodonaz(key);
	}
	
	@Override
	public Puntoprelievo getPuntoprelievo(int key) {
		return dao.getPuntoprelievo(key);
	}
	
	@Override
	public MonthlyBookings getYearMonth(YearMonth yearMonth, TipoDonaPuntoPrel tipoDonazPuntoPrel, boolean updateable, AgendaKey agendaKey, boolean donor) {
		return dao.getYearMonth(yearMonth, tipoDonazPuntoPrel, updateable, agendaKey, donor);
	}

	@Override
	public void disdetta(AgendaKey id) {
		dao.disdetta(id);
	}

	@Override
	public Agenda prenota(String codinternodonat, Date datePreno, TipoDonaPuntoPrel tipoDonaPuntoPrel) {
		return dao.prenota(codinternodonat, datePreno, tipoDonaPuntoPrel);
	}

	@Override
	public Donatore getDonatore(String codinternodonat) {
		return dao.getDonatore(codinternodonat);
	}

	@Override
	public List<Hour> freeHours(Date datePreno, TipoDonaPuntoPrel tipoDonaPuntoPrel) {
		return tipoDonaPuntoPrel != null ? dao.freeHours(datePreno, tipoDonaPuntoPrel.getPuntoprelId(), tipoDonaPuntoPrel.getTipoDonaId()) : null;
	}
	
	@Override
	public List<Hour> freeHours(Date datePreno, int puntoprelId, int tipoDonaId) {
		return dao.freeHours(datePreno, puntoprelId, tipoDonaId);
	}

	@Override
	public List<Puntoprelievo> getPuntiPrelievoList() {
		return dao.getPuntiPrelievoList();
	}
	
	@Override
	public List<ReportPreno> reportPreno(Date fromDate, Date toDate, Integer puntoPrelievo, Integer tipoDona) {
		return dao.reportPreno(fromDate, toDate, puntoPrelievo, tipoDona);
	}

}
