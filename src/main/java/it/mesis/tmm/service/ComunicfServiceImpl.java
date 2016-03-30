package it.mesis.tmm.service;

import it.mesis.tmm.dao.ComunicfDao;
import it.mesis.tmm.model.Comunicf;
import it.mesis.util.model.AutoCompleteData;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("comunicfService")
@Transactional
public class ComunicfServiceImpl implements ComunicfService {
	
	@Autowired
	private ComunicfDao dao;

	@Override
	public Comunicf findById(short id) {
		return dao.findById(id);
	}

	@Override
	public void saveComunicf(Comunicf comunicf) {
		dao.saveComunicf(comunicf);
	}

	@Override
	public void updateComunicf(Comunicf comunicf) {
		Comunicf entity = dao.findById(comunicf.getId());
		if (entity != null){
//			try {
//				BeanUtils.copyProperties(entity, anagrafica);
//			} catch (IllegalAccessException | InvocationTargetException e) {
//				e.printStackTrace();
//			}
			entity.setId(comunicf.getId());
			entity.setDescrizione(comunicf.getDescrizione());
			entity.setProv(comunicf.getProv());
			entity.setCodfis(comunicf.getCodfis());
			entity.setDatamod(comunicf.getDatamod());
			entity.setRegione(comunicf.getRegione());
			entity.setNonvisibile(comunicf.getNonvisibile());
			entity.setPosizione(comunicf.getPosizione());
			entity.setCodistat(comunicf.getCodistat());
			entity.setDataaggiornamento(comunicf.getDataaggiornamento());
			entity.setDatainiziovalidita(comunicf.getDatainiziovalidita());
			entity.setDatafinevalidita(comunicf.getDatafinevalidita());
		}
	}
	
	@Override
	public void deleteComunicfById(short id) {
		dao.deleteComunicfById(id);
	}

	@Override
	public List<Comunicf> findAllComunicfs() {
		return dao.findAllComunicfs();
	}
	
	public List<AutoCompleteData> json(String nome) {
		return dao.json(nome);
	}

//	@Override
//	public boolean isComunicfCodeUnique(String code) {
//		return dao.findComunicfById(code) ;
//	}
//
//
//	@Override
//	public Comunicf findComunicfByNome(String nome) {
//		return null;
//	}

}



