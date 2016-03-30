package it.mesis.tmm.dao;

import java.util.List;

import it.mesis.tmm.model.Utenti;

public interface UtentiDao {

	Utenti findById(short ID);

	void saveUtenti(Utenti utenti);

	void deleteUtentiById(short ID);
	
	List<Utenti> findAllUtentis();

	Utenti findUtentiById(short ID);
	
	Utenti findByUsername(String userName);
	
	boolean isUserNameUnique(Short id, String userName);
	
	void deleteByUserName(String userName);

}