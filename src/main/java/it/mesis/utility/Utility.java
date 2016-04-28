package it.mesis.utility;

import it.mesis.utility.model.TraceHead;
import it.mesis.utility.model.TraceRow;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Random;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;

public class Utility {
	
	public static final SimpleDateFormat dfdmy = new SimpleDateFormat("dd/MM/yyyy");
	
	public static final String dateFormatSecondsPat = "dd/MM/yyyy HH:mm:ss.SS";
	public static final SimpleDateFormat dateFormatSeconds = new SimpleDateFormat(dateFormatSecondsPat);
	
	private static Random rand = new Random();
	
	public static Integer parseInteger(String i) {
		return getInteger(i);
	}
	
	public static Integer getInteger(String i) {
		
		Integer retValue;
		try {
			retValue = Integer.parseInt(i);
		} catch (Exception e) {
			retValue = 0;
		}
		return retValue;
	}
	
	
	public static Date parseDate(String s) {
		Date retValue = null;
		try {
			if (s != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				retValue = sdf.parse(s);
			}
		} catch (ParseException e) {
			//e.printStackTrace();
		}
		return retValue;
	}
	
	public static int randInt(int min, int max) {
	    return rand.nextInt((max - min) + 1) + min;
	}
	
	/**
	 * numeri casuali non duplicati
	 * @param caseNr nr di casi
	 * @param min minimo 
	 * @param max massimo
	 * @return elenco di numeri casuali senza ripetizioni ed ordinati
	 */
	public static Integer[] randInt(int caseNr, int min, int max) {
		
		int cn = Math.min(Math.abs(caseNr), Math.abs(max-min));
		if (cn <= 0)
			return null;
		
		if (cn == Math.abs(max-min)) {
			Integer[] retValue = new Integer[cn];
			for (int i = 0; i < cn; i++) {
				retValue[i] = min+i;
			}
			return retValue;
		}
		
		HashSet<Integer> used = new HashSet<Integer>(cn);
		
		for (int i = 0; i < cn; i++) {
			boolean added = false;
			while (!added) {
				added = used.add(randInt(min, max));
			}
		}
		
		Integer[] retValue = new Integer[cn];
		
		used.toArray(retValue);
		Arrays.sort(retValue);
		
		return retValue;
	}
	
	public static Date randDate(int yearMin, int yearMax) {
		GregorianCalendar d = new GregorianCalendar(randInt(yearMin, yearMax), randInt(1, 12),  randInt(1, 31));
	    return d.getTime();
	}
	
	public static Date randDate(Date min, int ggMax) {
		return randDate(min, ggMax, false);
	}
	
	public static Date randDate(Date min, int ggMax, boolean fineMese) {
		GregorianCalendar d = new GregorianCalendar();
		d.setTime(min);
		d.set(Calendar.HOUR_OF_DAY, 0);
		d.add(Calendar.DAY_OF_YEAR, randInt(0, ggMax));
		
		if (fineMese) {
			d.add(Calendar.MONTH, 1);
			d.set(Calendar.DAY_OF_MONTH, 0);
		}
		
	    return d.getTime();
	}

	public static boolean randBool() {		
	    return rand.nextInt((1 - 0) + 1) + 0 == 1;
	}
	
	public static String toCamelCase(String s) {
		return toCamelCase(s, "_");
	}
	
	public static String toCamelCase(String s, String separator) {
		String[] parts = s.split(separator);
		if (parts.length == 0)
			return s;
		String camelCaseString = "";
		for (String part : parts) {
			camelCaseString += (" ".equals(separator) ? " " : "") + toProperCase(part);
		}
		return camelCaseString.trim();
	}
	
//	public static String toCamelCaseSpace(String s) {
//		String[] parts = s.split(" ");
//		String camelCaseString = "";
//		for (String part : parts) {
//			camelCaseString = camelCaseString + " "  + toProperCase(part);
//		}
//		return camelCaseString.trim();
//	}

	public static String toProperCase(String s) {
		if (s.length() <= 1)
			return s.toUpperCase();
		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}

	/**
	 * 
	 * @param cognomecf cognome
	 * @param nomecf nome
	 * @param dataNascitacf data di nascita
	 * @param codReg codice regionale del comune
	 * @param maschio sesso, true per maschio
	 * @return codice fiscale
	 */
	public static String calcoloCodiceFiscale(String cognome, String nome, Date dataNascitacf, String codReg, Boolean maschio) {
		
		String cognomecf = cognome.replaceAll(" ", "").trim().toUpperCase();
		String nomecf = nome.replaceAll(" ", "").trim().toUpperCase();
		String codFis = "";
		
		/* calcolo prime 3 lettere */
		int cont = 0;
		/* caso cognome minore di 3 lettere */
		if (cognomecf.length() < 3) {
			codFis += cognomecf;
			while (codFis.length() < 3)
				codFis += "X";
			cont = 3;
		}
		/* caso normale */
		for (int i = 0; i < cognomecf.length(); i++) {
			if (cont == 3)
				break;
			if (cognomecf.charAt(i) != 'A' && cognomecf.charAt(i) != 'E'
					&& cognomecf.charAt(i) != 'I' && cognomecf.charAt(i) != 'O'
					&& cognomecf.charAt(i) != 'U') {
				codFis += Character.toString(cognomecf.charAt(i));
				cont++;
			}
		}
		/* nel casoci siano meno di 3 consonanti */
		while (cont < 3) {
			for (int i = 0; i < cognomecf.length(); i++) {
				if (cont == 3)
					break;
				if (cognomecf.charAt(i) == 'A' || cognomecf.charAt(i) == 'E'
						|| cognomecf.charAt(i) == 'I'
						|| cognomecf.charAt(i) == 'O'
						|| cognomecf.charAt(i) == 'U') {
					codFis += Character.toString(cognomecf.charAt(i));
					cont++;
				}
			}
		}
		/* lettere nome */
		cont = 0;
		/* caso nome minore di 3 lettere */
		if (nomecf.length() < 3) {
			codFis += nomecf;
			while (codFis.length() < 6)
				codFis += "X";
			cont = 3;
		}
		/* caso normale */
		for (int i = 0; i < nomecf.length(); i++) {
			if (cont == 3)
				break;
			if (nomecf.charAt(i) != 'A' && nomecf.charAt(i) != 'E'
					&& nomecf.charAt(i) != 'I' && nomecf.charAt(i) != 'O'
					&& nomecf.charAt(i) != 'U') {
				codFis += Character.toString(nomecf.charAt(i));
				cont++;
			}
		}
		/* nel caso ci siano meno di 3 consonanti */
		while (cont < 3) {
			for (int i = 0; i < nomecf.length(); i++) {
				if (cont == 3)
					break;
				if (nomecf.charAt(i) == 'A' || nomecf.charAt(i) == 'E'
						|| nomecf.charAt(i) == 'I' || nomecf.charAt(i) == 'O'
						|| nomecf.charAt(i) == 'U') {
					codFis += Character.toString(nomecf.charAt(i));
					cont++;
				}
			}
		}
		
		GregorianCalendar dn = new GregorianCalendar();
		dn.setTime(dataNascitacf);
		
		/* anno */
		int year = dn.get(GregorianCalendar.YEAR);
		int mese = dn.get(GregorianCalendar.MONTH)+1;
		int giorno = dn.get(GregorianCalendar.DAY_OF_MONTH);
		
		codFis += String.format("%02d", year % 100);
		codFis += getMeseAlfa(mese);
		codFis += String.format("%02d", giorno + (maschio ? 0 : 40));
		codFis += codReg;	/* comune nascita */
		codFis += calculateCheck(codFis);
		return codFis;
	}

	private static String calculateCheck(String codFis) {
		/* Carattere di controllo */
		int sommaPari = 0;
		for (int i = 1; i <= 13; i += 2) {
			switch (codFis.charAt(i)) {
			case '0': {
				sommaPari += 0;
				break;
			}
			case '1': {
				sommaPari += 1;
				break;
			}
			case '2': {
				sommaPari += 2;
				break;
			}
			case '3': {
				sommaPari += 3;
				break;
			}
			case '4': {
				sommaPari += 4;
				break;
			}
			case '5': {
				sommaPari += 5;
				break;
			}
			case '6': {
				sommaPari += 6;
				break;
			}
			case '7': {
				sommaPari += 7;
				break;
			}
			case '8': {
				sommaPari += 8;
				break;
			}
			case '9': {
				sommaPari += 9;
				break;
			}
			case 'A': {
				sommaPari += 0;
				break;
			}
			case 'B': {
				sommaPari += 1;
				break;
			}
			case 'C': {
				sommaPari += 2;
				break;
			}
			case 'D': {
				sommaPari += 3;
				break;
			}
			case 'E': {
				sommaPari += 4;
				break;
			}
			case 'F': {
				sommaPari += 5;
				break;
			}
			case 'G': {
				sommaPari += 6;
				break;
			}
			case 'H': {
				sommaPari += 7;
				break;
			}
			case 'I': {
				sommaPari += 8;
				break;
			}
			case 'J': {
				sommaPari += 9;
				break;
			}
			case 'K': {
				sommaPari += 10;
				break;
			}
			case 'L': {
				sommaPari += 11;
				break;
			}
			case 'M': {
				sommaPari += 12;
				break;
			}
			case 'N': {
				sommaPari += 13;
				break;
			}
			case 'O': {
				sommaPari += 14;
				break;
			}
			case 'P': {
				sommaPari += 15;
				break;
			}
			case 'Q': {
				sommaPari += 16;
				break;
			}
			case 'R': {
				sommaPari += 17;
				break;
			}
			case 'S': {
				sommaPari += 18;
				break;
			}
			case 'T': {
				sommaPari += 19;
				break;
			}
			case 'U': {
				sommaPari += 20;
				break;
			}
			case 'V': {
				sommaPari += 21;
				break;
			}
			case 'W': {
				sommaPari += 22;
				break;
			}
			case 'X': {
				sommaPari += 23;
				break;
			}
			case 'Y': {
				sommaPari += 24;
				break;
			}
			case 'Z': {
				sommaPari += 25;
				break;
			}
			}
		}
		
		if (codFis.length()<15) {
			System.out.println("Errore " + codFis);
		}
		
		int sommaDispari = 0;
		for (int i = 0; i <= 14; i += 2) {
			switch (codFis.charAt(i)) {
			case '0': {
				sommaDispari += 1;
				break;
			}
			case '1': {
				sommaDispari += 0;
				break;
			}
			case '2': {
				sommaDispari += 5;
				break;
			}
			case '3': {
				sommaDispari += 7;
				break;
			}
			case '4': {
				sommaDispari += 9;
				break;
			}
			case '5': {
				sommaDispari += 13;
				break;
			}
			case '6': {
				sommaDispari += 15;
				break;
			}
			case '7': {
				sommaDispari += 17;
				break;
			}
			case '8': {
				sommaDispari += 19;
				break;
			}
			case '9': {
				sommaDispari += 21;
				break;
			}
			case 'A': {
				sommaDispari += 1;
				break;
			}
			case 'B': {
				sommaDispari += 0;
				break;
			}
			case 'C': {
				sommaDispari += 5;
				break;
			}
			case 'D': {
				sommaDispari += 7;
				break;
			}
			case 'E': {
				sommaDispari += 9;
				break;
			}
			case 'F': {
				sommaDispari += 13;
				break;
			}
			case 'G': {
				sommaDispari += 15;
				break;
			}
			case 'H': {
				sommaDispari += 17;
				break;
			}
			case 'I': {
				sommaDispari += 19;
				break;
			}
			case 'J': {
				sommaDispari += 21;
				break;
			}
			case 'K': {
				sommaDispari += 2;
				break;
			}
			case 'L': {
				sommaDispari += 4;
				break;
			}
			case 'M': {
				sommaDispari += 18;
				break;
			}
			case 'N': {
				sommaDispari += 20;
				break;
			}
			case 'O': {
				sommaDispari += 11;
				break;
			}
			case 'P': {
				sommaDispari += 3;
				break;
			}
			case 'Q': {
				sommaDispari += 6;
				break;
			}
			case 'R': {
				sommaDispari += 8;
				break;
			}
			case 'S': {
				sommaDispari += 12;
				break;
			}
			case 'T': {
				sommaDispari += 14;
				break;
			}
			case 'U': {
				sommaDispari += 16;
				break;
			}
			case 'V': {
				sommaDispari += 10;
				break;
			}
			case 'W': {
				sommaDispari += 22;
				break;
			}
			case 'X': {
				sommaDispari += 25;
				break;
			}
			case 'Y': {
				sommaDispari += 24;
				break;
			}
			case 'Z': {
				sommaDispari += 23;
				break;
			}
			}
		}
		int interoControllo = (sommaPari + sommaDispari) % 26;
		String carattereControllo = "";
		switch (interoControllo) {
		case 0: {
			carattereControllo = "A";
			break;
		}
		case 1: {
			carattereControllo = "B";
			break;
		}
		case 2: {
			carattereControllo = "C";
			break;
		}
		case 3: {
			carattereControllo = "D";
			break;
		}
		case 4: {
			carattereControllo = "E";
			break;
		}
		case 5: {
			carattereControllo = "F";
			break;
		}
		case 6: {
			carattereControllo = "G";
			break;
		}
		case 7: {
			carattereControllo = "H";
			break;
		}
		case 8: {
			carattereControllo = "I";
			break;
		}
		case 9: {
			carattereControllo = "J";
			break;
		}
		case 10: {
			carattereControllo = "K";
			break;
		}
		case 11: {
			carattereControllo = "L";
			break;
		}
		case 12: {
			carattereControllo = "M";
			break;
		}
		case 13: {
			carattereControllo = "N";
			break;
		}
		case 14: {
			carattereControllo = "O";
			break;
		}
		case 15: {
			carattereControllo = "P";
			break;
		}
		case 16: {
			carattereControllo = "Q";
			break;
		}
		case 17: {
			carattereControllo = "R";
			break;
		}
		case 18: {
			carattereControllo = "S";
			break;
		}
		case 19: {
			carattereControllo = "T";
			break;
		}
		case 20: {
			carattereControllo = "U";
			break;
		}
		case 21: {
			carattereControllo = "V";
			break;
		}
		case 22: {
			carattereControllo = "W";
			break;
		}
		case 23: {
			carattereControllo = "X";
			break;
		}
		case 24: {
			carattereControllo = "Y";
			break;
		}
		case 25: {
			carattereControllo = "Z";
			break;
		}
		}
		
		return carattereControllo;
	}

	private static String getMeseAlfa(int mese) {
		
		String meseAlfa = "";
		
		switch (mese) {
		case 1: {
			meseAlfa += "A";
			break;
		}
		case 2: {
			meseAlfa += "B";
			break;
		}
		case 3: {
			meseAlfa += "C";
			break;
		}
		case 4: {
			meseAlfa += "D";
			break;
		}
		case 5: {
			meseAlfa += "E";
			break;
		}
		case 6: {
			meseAlfa += "H";
			break;
		}
		case 7: {
			meseAlfa += "L";
			break;
		}
		case 8: {
			meseAlfa += "M";
			break;
		}
		case 9: {
			meseAlfa += "P";
			break;
		}
		case 10: {
			meseAlfa += "R";
			break;
		}
		case 11: {
			meseAlfa += "S";
			break;
		}
		case 12: {
			meseAlfa += "T";
			break;
		}
		}
		
		return meseAlfa;
	}
	
	/**
	 * 
	 * @param file
	 * @return hash sha1 del file passato
	 */
	public String sha1(final File file)  {
		MessageDigest messageDigest = getMessageDigest("SHA1");

		try (InputStream is = new BufferedInputStream(new FileInputStream(file))) {
			final byte[] buffer = new byte[1024];
			for (int read = 0; (read = is.read(buffer)) != -1;) {
				messageDigest.update(buffer, 0, read);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return formatta(messageDigest.digest());

	}
	
	/**
	 * 
	 * @param s
	 * @return hash sha1 della stringa passata
	 */
	public static String sha1(String s) {
		MessageDigest messageDigest = getMessageDigest("SHA1");
		messageDigest.update(s.getBytes());
		return formatta(messageDigest.digest());
	}
	
	private static MessageDigest getMessageDigest(String type) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance(type);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return messageDigest;
	}
	
	// Convert the byte to hex format
	private static String formatta(byte[] bytes) {

		String retValue = null;
		try (Formatter formatter = new Formatter()) {
			for (final byte b : bytes) {
				formatter.format("%02x", b);
			}
			retValue = formatter.toString();
		}
		return retValue;
	}
	
	/**
	 * Replaces characters that may be confused by an SQL parser with their
	 * equivalent escape characters.
	 * <p>
	 * Any data that will be put in an SQL query should be be escaped. This is
	 * especially important for data that comes from untrusted sources such as
	 * Internet users.
	 * <p>
	 * For example if you had the following SQL query:<br>
	 * <code>"SELECT * FROM addresses WHERE name='" + name + "' AND private='N'"</code>
	 * <br>
	 * Without this function a user could give <code>" OR 1=1 OR ''='"</code> as
	 * their name causing the query to be:<br>
	 * <code>"SELECT * FROM addresses WHERE name='' OR 1=1 OR ''='' AND private='N'"</code>
	 * <br>
	 * which will give all addresses, including private ones.<br>
	 * Correct usage would be:<br>
	 * <code>"SELECT * FROM addresses WHERE name='" + StringHelper.escapeSQL(name) + "' AND private='N'"</code>
	 * <br>
	 * <p>
	 * Another way to avoid this problem is to use a PreparedStatement with
	 * appropriate placeholders.
	 *
	 * @param s
	 *            String to be escaped
	 * @return escaped String
	 * @throws NullPointerException
	 *             if s is null.
	 *
	 * @since ostermillerutils 1.00.00
	 */
	public static String escapeSQL(String s) {
		int length = s.length();
		int newLength = length;
		// first check for characters that might
		// be dangerous and calculate a length
		// of the string that has escapes.
		for (int i = 0; i < length; i++) {
			char c = s.charAt(i);
			switch (c) {
			case '\\':
			case '\"':
			case '\'':
			case '\0': {
				newLength += 1;
			}
				break;
			}
		}
		if (length == newLength) {
			// nothing to escape in the string
			return s;
		}
		
		StringBuffer sb = new StringBuffer(newLength);
		for (int i = 0; i < length; i++) {
			char c = s.charAt(i);
			switch (c) {
			case '\\': {
				sb.append("\\\\");
			}
				break;
			case '\"': {
				sb.append("\\\"");
			}
				break;
			case '\'': {
				sb.append("\\\'");
			}
				break;
			case '\0': {
				sb.append("\\0");
			}
				break;
			default: {
				sb.append(c);
			}
			}
		}
		return sb.toString();
	}
	
	/**
	 * JSON={"name":"stackoverflow","id":5}	--> JSONP=func({"name":"stackoverflow","id":5});
	 * @param json
	 * @return jsonp
	 */
	public String jsonp(String json) {
		return "func(" + json + ");";
	}
	
	public static void nullifyStrings( Object o ) {
		
		if (o == null)
			return;

	    for ( Field f : o.getClass().getDeclaredFields() ) {
	        f.setAccessible(true);
	        try {
	            if ( f.getType().equals( String.class ) ) {
	                String value = (String) f.get( o );
	                if ( value != null && value.trim().isEmpty() ) {
	                    f.set( o , null);
	                }
	            }
	        } catch ( Exception e ) { 
	            throw new RuntimeException(e);
	        }
	    }
	}
	
	public static Collection<String> getRequestParams(Enumeration<String> enumeration) {
		return getRequestParams(enumeration, false);
	}
	
	public static Collection<String> getRequestParams(Enumeration<String> enumeration, boolean excludeUnderscoreInitial) {
		
		Collection<String> fieldsToSave = new HashSet<String>();
		while (enumeration.hasMoreElements()) {
			String field = enumeration.nextElement();
			if (!excludeUnderscoreInitial || !field.startsWith("_"))
				fieldsToSave.add(field);
		}
		return fieldsToSave;
	}
	
	/**
	 * copies properties from one object to another
	 * 
	 * @param src
	 *            the source object
	 * @param dest
	 *            the destination object
	 * @param properties
	 *            a list of property names that are to be copied. Each value has
	 *            the format "srcProperty destProperty". For example,
	 *            "name fullName" indicates that you want to copy the src.name
	 *            value to dest.fullName. If both the srcProperty and
	 *            destProperty property have the same name, you can omit the
	 *            destProperty. For example, "name" indicates that you want to
	 *            copy src.name to dest.name.
	 * @return 
	 * 
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	public static TraceHead copyProperties(Object origin, Object dest, Collection<String> properties)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return copyProperties(origin, dest, properties.toArray(new String[properties.size()]));
	}
	
	/**
	 * copies properties from one object to another
	 * 
	 * @param origin
	 *            the source object
	 * @param dest
	 *            the destination object
	 * @param properties
	 *            a list of property names that are to be copied. Each value has
	 *            the format "srcProperty destProperty". For example,
	 *            "name fullName" indicates that you want to copy the src.name
	 *            value to dest.fullName. If both the srcProperty and
	 *            destProperty property have the same name, you can omit the
	 *            destProperty. For example, "name" indicates that you want to
	 *            copy src.name to dest.name.
	 * 
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	public static TraceHead copyProperties(Object origin, Object dest, String... properties) 
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		PropertyUtilsBean propUtils = new PropertyUtilsBean();
		TraceHead trace = new TraceHead(dest.getClass().getName());
		
        DateTimeConverter dtConverter = new DateConverter();
        dtConverter.setPattern(dateFormatSecondsPat);
        ConvertUtils.register(dtConverter, Date.class);
		
		for (String property : properties) {
			String[] arr = property.split(" ");
			String srcProperty;
			String destProperty;
			if (arr.length == 2) {
				srcProperty = arr[0];
				destProperty = arr[1];
			} else {
				srcProperty = property;
				destProperty = property;
			}
			
			Field f;
			try {
				f = dest.getClass().getDeclaredField(destProperty);
			} catch (NoSuchFieldException e) {
//				e.printStackTrace();
				continue;	//nome del campo di destinazione non presente nella classe di destinazione
			}
            Object propertyOrigin = propUtils.getProperty(origin, srcProperty);
            Object propertyDest = propUtils.getProperty(dest, destProperty);
            
            if ((propertyOrigin != null || propertyDest != null) && (propertyOrigin == null || propertyDest == null || !propertyOrigin.equals(propertyDest))) {
            	trace.addRow(new TraceRow(destProperty, f.getType().getSimpleName(), propertyDest, propertyOrigin));
            }

//            if (property1 == null && property2 == null || (property1 != null && property2 != null && property1.equals(property2))) {
//                System.out.println("  " + destProperty + " is equal --> [" + property1 + "=" + property2+"]");
//            } else {
//                System.out.println("> " + destProperty + " is different (oldValue=\"" + property1 + "\", newValue=\"" + property2 + "\")");
//                trace.addRow(new TraceRow(destProperty, f.getGenericType().getTypeName(), property1, property2));
//            }
            
            String oriStringValue = null;
            if (propertyDest instanceof Date)
            	oriStringValue = dateFormatSeconds.format(propertyOrigin);
            else
            	oriStringValue = propertyOrigin.toString();

			BeanUtils.setProperty(dest, destProperty, oriStringValue);
		}
		return trace;
	}
	
	/**
	 * 
	 * @param pswLength lunghezza della paswword
	 * @return psw
	 */
	public static String getNewPsw(int pswLength) {
//		String alfabeto = "ABCDEFGJHKILMNOPQRSTUVWXYZ0123456789";
        String alfabeto = "dJgWZMDGxnIROh5FkV7ozQqvSjbA1aY29rmH6U4PyKuXwL8cCEptNB03silTef";
		if (pswLength <= 0) 
			pswLength = 8;
		int nrGiri = pswLength+50;
		StringBuffer myPsw = new StringBuffer();
		for (int ii=0; ii<nrGiri; ii++) {
			int pos = getOneNumber(alfabeto.length());
			myPsw.append(alfabeto.substring(pos, pos+1));
		}
		int caso = getOneNumber(nrGiri-pswLength);

		return myPsw.substring(caso, caso+pswLength).toString();
	}

	/**Numero casuale intero compreso tra 0 e max (escluso)
	 *@param max valore massimo accettabile
	 *@return numero casuale intero compreso tra 0 e max (escluso)
	 */
	public static int getOneNumber(int max) {
		return (new Double((Math.random()*(Math.pow(10, getDigits(max)))) % max).intValue());
	}
	
	/**Dato un numero ritorna il numero di cifre che lo compone
	 *@param number numero per il quale si vuole sapere il numero di cifre che lo compone
	 *@return numero di cifre che compone il numero
	 */
	private static long getDigits(long number) {
		/*Il logaritmo a base X di un numero K è uguale al
		 log di K a base E diviso il logaritmo di X a base E
		 */
		return (Math.round( Math.log(number) / Math.log(10)  + 0.5));
	}
	
}
