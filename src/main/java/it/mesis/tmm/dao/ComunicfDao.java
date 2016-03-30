package it.mesis.tmm.dao;

import it.mesis.tmm.model.Comunicf;
import it.mesis.util.model.AutoCompleteData;

import java.util.List;

public interface ComunicfDao {

	Comunicf findById(short id);

	void saveComunicf(Comunicf comunicf);

	void deleteComunicfById(short id);

	List<Comunicf> findAllComunicfs();

	Comunicf findComunicfById(short id);
	
	List<AutoCompleteData> json(String nome);

}
