package it.mesis.tmm.service;

import java.util.List;

import it.mesis.tmm.model.Centritrasf;
import it.mesis.tmm.model.Utenti;

public interface UtentiService {

	Utenti findById(short ID);
	
	void saveUtenti(Utenti utenti);
	
	void updateUtenti(Utenti utenti);
	
	void deleteUtentiById(short ID);

	List<Utenti> findAllUtentis(); 
	
	Utenti findByUsername(String userName);
	
	boolean isUserNameUnique(Short id, String userName);
	
	void deleteByUserName(String userName);
	
	Centritrasf centritrasfUnique(String userName);
	
	List<Centritrasf> centritrasf(String userName);
}