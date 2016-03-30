package it.mesis.tmm.service;

import it.mesis.tmm.model.Comunicf;
import it.mesis.util.model.AutoCompleteData;

import java.util.List;

public interface ComunicfService {

	Comunicf findById(short id);
	
	void saveComunicf(Comunicf comunicf);
	
	void updateComunicf(Comunicf comunicf);
	
	void deleteComunicfById(short id);

	List<Comunicf> findAllComunicfs(); 
	
	List<AutoCompleteData> json(String nome);
	
//	Comunicf findComunicfByNome(String nome);
//
//	boolean isComunicfCodeUnique(String code);
	
}



