package it.mesis.tmm.service;

import it.mesis.tmm.dao.AnagraficaDao;
import it.mesis.tmm.model.Anagrafica;
import it.mesis.util.model.Tipizza;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("anagraficaService")
@Transactional
public class AnagraficaServiceImpl implements AnagraficaService {
	
	@Autowired
	private AnagraficaDao dao;

	@Override
	public Anagrafica findById(int id) {
		return dao.findById(id);
	}

	@Override
	public void saveAnagrafica(Anagrafica anagrafica) {
		dao.saveAnagrafica(anagrafica);
	}

	@Override
	public void updateAnagrafica(Anagrafica anagrafica) {
		Anagrafica entity = dao.findById(anagrafica.getId());
		if (entity != null) {
//			try {
//				BeanUtils.copyProperties(entity, anagrafica);
//			} catch (IllegalAccessException | InvocationTargetException e) {
//				e.printStackTrace();
//			}
			
			entity.setCognome(anagrafica.getCognome());
			entity.setNome(anagrafica.getNome());
			entity.setSesso(anagrafica.getSesso());
			entity.setCodfiscale(anagrafica.getCodfiscale());
			entity.setIdluogonascita(anagrafica.getIdluogonascita());
			entity.setDatanascita(anagrafica.getDatanascita());
			
			entity.setCognomeNome();
			entity.setDatamod(new Date());	//LocalDate.now());
			
//			entity.setIdprofessione(anagrafica.getIdprofessione());
//			entity.setIdrescomune(anagrafica.getIdrescomune());
//			entity.setResindirizzo(anagrafica.getResindirizzo());
//			entity.setRescitta(anagrafica.getRescitta());
//			entity.setResprovincia(anagrafica.getResprovincia());
//			entity.setRescap(anagrafica.getRescap());
//			entity.setRestelefono(anagrafica.getRestelefono());
//			entity.setIdcittadinanza(anagrafica.getIdcittadinanza());
//			entity.setCodsanreg(anagrafica.getCodsanreg());
//			entity.setDocidentita(anagrafica.getDocidentita());
//			entity.setUsl(anagrafica.getUsl());
//			entity.setCellulare(anagrafica.getCellulare());
//			entity.setCoddonatore(anagrafica.getCoddonatore());
//			entity.setCodpaziente(anagrafica.getCodpaziente());
//			entity.setCt(anagrafica.getCt());
//			entity.setIdmedico(anagrafica.getIdmedico());
//			entity.setCognomenome(anagrafica.getCognomenome());
//			entity.setDatamod(anagrafica.getDatamod());
//			entity.setIdnazionalita(anagrafica.getIdnazionalita());
//			entity.setIdtitolostudio(anagrafica.getIdtitolostudio());
//			entity.setConsensoprivacy(anagrafica.getConsensoprivacy());
//			entity.setNonvalidata(anagrafica.getNonvalidata());
//			entity.setCarattidentificativo(anagrafica.getCarattidentificativo());
//			entity.setEmail(anagrafica.getEmail());
//			entity.setCampolibero01(anagrafica.getCampolibero01());
//			entity.setCodiceanagrafecentrale(anagrafica.getCodiceanagrafecentrale());
//			entity.setTitolo(anagrafica.getTitolo());
//			entity.setResnumerocivico(anagrafica.getResnumerocivico());
//			entity.setPopuplibero01d(anagrafica.getPopuplibero01d());
//			entity.setCampolibero01d(anagrafica.getCampolibero01d());
//			entity.setPopuplibero01p(anagrafica.getPopuplibero01p());
//			entity.setCampolibero01p(anagrafica.getCampolibero01p());
//			entity.setDatadecesso(anagrafica.getDatadecesso());
//			entity.setDatibloccati(anagrafica.getDatibloccati());
//			entity.setIdrescomuneCent(anagrafica.getIdrescomuneCent());
//			entity.setResindirizzoCent(anagrafica.getResindirizzoCent());
//			entity.setRescittaCent(anagrafica.getRescittaCent());
//			entity.setResprovinciaCent(anagrafica.getResprovinciaCent());
//			entity.setRescapCent(anagrafica.getRescapCent());
//			entity.setRestelefonoCent(anagrafica.getRestelefonoCent());
//			entity.setCellulareCent(anagrafica.getCellulareCent());
//			entity.setResnumerocivicoCent(anagrafica.getResnumerocivicoCent());
//			entity.setCodiceanagraficamedicoCent(anagrafica.getCodiceanagraficamedicoCent());
//			entity.setTipoconfermaanag(anagrafica.getTipoconfermaanag());
//			entity.setDataconfermaanag(anagrafica.getDataconfermaanag());
//			entity.setIdutenteconfermaanag(anagrafica.getIdutenteconfermaanag());
//			entity.setAutoritaconfermaanag(anagrafica.getAutoritaconfermaanag());
		}
	}
	
	@Override
	public void deleteAnagraficaById(int id) {
		dao.deleteAnagraficaById(id);
	}

	@Override
	public List<Anagrafica> findAllAnagraficas() {
		return dao.findAllAnagraficas();
	}
	
	@Override
	public List<Anagrafica> findAllAnagraficas(Anagrafica anagrafica) {
		return dao.findAllAnagraficas(anagrafica);
	}
	
	@Override
	public Tipizza getTipizza(int id) {
		return dao.getTipizza(id);
	}


//	@Override
//	public boolean isAnagraficaCodeUnique(String code) {
//		return dao.findAnagraficaById(code) ;
//	}
//
//
//	@Override
//	public Anagrafica findAnagraficaByNome(String nome) {
//		return null;
//	}

}