package it.mesis.tmm.service;

import it.mesis.tmm.model.Anagrafica;
import it.mesis.util.model.Tipizza;

import java.util.List;

public interface AnagraficaService {

	Anagrafica findById(int id);
	
	void saveAnagrafica(Anagrafica anagrafica);
	
	void updateAnagrafica(Anagrafica anagrafica);
	
	void deleteAnagraficaById(int id);

	List<Anagrafica> findAllAnagraficas();

	List<Anagrafica> findAllAnagraficas(Anagrafica anagrafica); 
	
//	Anagrafica findAnagraficaByNome(String nome);
//
//	boolean isAnagraficaCodeUnique(String code);

	Tipizza getTipizza(int id);
}
