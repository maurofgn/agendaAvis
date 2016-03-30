package it.mesis.tmm.service;

import it.mesis.tmm.dao.UtentiDao;
import it.mesis.tmm.model.Centritrasf;
import it.mesis.tmm.model.Utenti;
import it.mesis.tmm.model.UtentiCt;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("utentiService")
@Transactional
public class UtentiServiceImpl implements UtentiService {
	
	@Autowired
	private UtentiDao dao;

	@Override
	public Utenti findById(short id) {
		return dao.findById(id);
	}

	@Override
	public void saveUtenti(Utenti utenti) {
		dao.saveUtenti(utenti);
	}

	@Override
	public void updateUtenti(Utenti utenti) {
		Utenti entity = dao.findById(utenti.getId());
		if (entity != null){
//			try {
//				BeanUtils.copyProperties(entity, anagrafica);
//			} catch (IllegalAccessException | InvocationTargetException e) {
//				e.printStackTrace();
//			}
			entity.setNomeUtente(utenti.getNomeUtente());
			entity.setNomeCompleto(utenti.getNomeCompleto());
			entity.setDescrizione(utenti.getDescrizione());
			entity.setDataUltAccesso(utenti.getDataUltAccesso());
			entity.setDataUltCambioPsw(utenti.getDataUltCambioPsw());
			entity.setCambiamentoObblPwd(utenti.getCambiamentoObblPwd());
			entity.setNoCambiamentoPwd(utenti.getNoCambiamentoPwd());
			entity.setNoScadenzaPwd(utenti.getNoScadenzaPwd());
			entity.setDisabilitato(utenti.getDisabilitato());
			entity.setDataCreazione(utenti.getDataCreazione());
			entity.setAccessoUnaPostazione(utenti.getAccessoUnaPostazione());
			entity.setTipoutente(utenti.getTipoutente());
			entity.setPermessi(utenti.getPermessi());
			entity.setAdminUser(utenti.getAdminUser());
			entity.setNonvisibile(utenti.getNonvisibile());
			entity.setStorico(utenti.getStorico());
			entity.setAccessoconfig(utenti.getAccessoconfig());
			entity.setPassword(utenti.getPassword());
			entity.setUtentewindows(utenti.getUtentewindows());
			entity.setDominio(utenti.getDominio());
			entity.setNome(utenti.getNome());
			entity.setCognome(utenti.getCognome());
			entity.setCodicefiscale(utenti.getCodicefiscale());
			entity.setTitolo(utenti.getTitolo());
			entity.setUtenteauto(utenti.getUtenteauto());
			entity.setSubjectdn(utenti.getSubjectdn());
			entity.setIdgruppo(utenti.getIdgruppo());
			entity.setDisabilitatoauto(utenti.getDisabilitatoauto());
			entity.setAccessocrtmm(utenti.getAccessocrtmm());
			entity.setTipoaccessocrweb(utenti.getTipoaccessocrweb());
			entity.setNumeroaccessiweb(utenti.getNumeroaccessiweb());
		}
	}
	
	@Override
	public void deleteUtentiById(short id) {
		dao.deleteUtentiById(id);
	}

	@Override
	public List<Utenti> findAllUtentis() {
		return dao.findAllUtentis();
	}

	@Override
	public Utenti findByUsername(String userName) {
		return dao.findByUsername(userName);
	}

	@Override
	public boolean isUserNameUnique(Short id, String userName) {
		return dao.isUserNameUnique(id, userName) ;
	}

	@Override
	public void deleteByUserName(String userName) {
		dao.deleteByUserName(userName);
	}

	@Override
	public Centritrasf centritrasfUnique(String userName) {
		Utenti utente = findByUsername(userName);
		return utente != null ? utente.getCentritrasfUnique() : null;
	}

	@Override
	public List<Centritrasf> centritrasf(String userName) {
		Utenti utente = findByUsername(userName);
		if (utente == null)
			return null;
		
		Set<UtentiCt> s = utente.getUtentiCts();
		List<Centritrasf> retValue = new ArrayList<Centritrasf>(s.size());
		for (UtentiCt utentiCt : s) {
			retValue.add(utentiCt.getCentritrasf());
		}
		
		return retValue;
	}

}
