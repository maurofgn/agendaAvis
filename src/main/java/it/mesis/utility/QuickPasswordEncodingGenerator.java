package it.mesis.utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class QuickPasswordEncodingGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String utente = "mauro";
		String password = "mauro";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println("update utenti u set u.PASSWORD = '" + passwordEncoder.encode(password) + "' where NOME_UTENTE = '" + utente + "'");

			for (int i = 0; i < 10; i++) {
				System.out.println(password + "-->" + passwordEncoder.encode(password));
			}
	}

}
