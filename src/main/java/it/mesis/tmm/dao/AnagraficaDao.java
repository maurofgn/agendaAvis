package it.mesis.tmm.dao;

import it.mesis.tmm.model.Anagrafica;
import it.mesis.util.model.Tipizza;

import java.util.List;

public interface AnagraficaDao {

	Anagrafica findById(int id);

	void saveAnagrafica(Anagrafica anagrafica);

	void deleteAnagraficaById(int id);

	List<Anagrafica> findAllAnagraficas();

//	Anagrafica findAnagraficaById(int id);

	List<Anagrafica> findAllAnagraficas(Anagrafica anagrafica);
	
	Tipizza getTipizza(int id);

}