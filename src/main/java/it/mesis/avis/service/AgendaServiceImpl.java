package it.mesis.avis.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.mesis.avis.bean.jpa.AgendaEntity;
import it.mesis.avis.bean.jpa.AgendaEntityKey;
import it.mesis.avis.bean.jpa.DonatoreEntity;
import it.mesis.avis.bean.jpa.PuntoprelievoEntity;
import it.mesis.avis.bean.jpa.TipodonazEntity;
import it.mesis.avis.dao.AgendaDao;
import it.mesis.util.model.DonaStatus;
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
	public AgendaEntity findById(AgendaEntityKey id) {
		return dao.findById(id);
	}

	@Override
	public List<TipodonazEntity> getTipoDonazList() {
		return dao.getTipoDonazList();
	}

	@Override
	public TipodonazEntity getTipodonaz(int key) {
		return dao.getTipodonaz(key);
	}
	
	@Override
	public PuntoprelievoEntity getPuntoprelievo(int key) {
		return dao.getPuntoprelievo(key);
	}
	
	@Override
	public MonthlyBookings getYearMonth(YearMonth yearMonth, TipoDonaPuntoPrel tipoDonazPuntoPrel, DonaStatus donaStatus) {
		return dao.getYearMonth(yearMonth, tipoDonazPuntoPrel, donaStatus);
	}

	@Override
	public void disdetta(AgendaEntityKey id) {
		dao.disdetta(id);
	}

	@Override
	public AgendaEntity prenota(String codinternodonat, Date datePreno, TipoDonaPuntoPrel tipoDonaPuntoPrel) {
		return dao.prenota(codinternodonat, datePreno, tipoDonaPuntoPrel);
	}

	@Override
	public DonatoreEntity getDonatore(String codinternodonat) {
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
	public List<PuntoprelievoEntity> getPuntiPrelievoList() {
		return dao.getPuntiPrelievoList();
	}
	
	@Override
	public List<ReportPreno> reportPreno(Date fromDate, Date toDate, Integer puntoPrelievo, Integer tipoDona) {
		return dao.reportPreno(fromDate, toDate, puntoPrelievo, tipoDona);
	}

	@Override
	public Boolean hasPrenoActive(DonatoreEntity donatore) {
		return dao.hasPrenoActive(donatore);
	}

	@Override
	public AgendaEntity getPrenoActive(DonatoreEntity donatore) {
		return dao.getPrenoActive(donatore);
	}

}
