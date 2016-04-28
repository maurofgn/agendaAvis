package it.mesis.avis.enu;

public enum Sesso { 
	MASCHIO(1, "Maschio", 1),
	FEMMINA(2, "Femmina", 2),
	;

	public static final int TYPE_ID = 102;
	public static final String TYPE_NAME = "Sesso";

	private int index; 
	private String value; 
	private int pos; 
 
	public static Sesso getType(int index) { 
		switch (index) { 
		case 1: return MASCHIO; 
		case 2: return FEMMINA; 
		default: return null; 
		} 
	} 
 
	public static java.util.Set<Sesso> getListOrdered() { 
 
		java.util.TreeSet<Sesso> ordered = new java.util.TreeSet<Sesso>(new java.util.Comparator<Sesso>() { 
			@Override 
			public int compare(Sesso o1, Sesso o2) { 
				if (o1.getPos() > o2.getPos()) 
					return 1; 
				if (o1.getPos() < o2.getPos()) 
					return -1; 
				int c = o1.getValue().compareTo(o2.getValue()); 
				if (c != 0) 
					return c; 
				return o1.getIndex() - o2.getIndex(); 
			} 
		}); 
		ordered.addAll(java.util.Arrays.asList(Sesso.values())); 
		return ordered; 
	} 
 
	Sesso(int index, String value, int pos) { 
		this.index = index; 
		this.value = value; 
		this.pos = pos; 
	}; 
 
	public int getIndex() { 
		return index; 
	} 
 
	public String getValue() { 
		return value; 
	} 
 
	public int getPos() { 
		return pos; 
	} 
 
	@Override 
	public String toString() { 
		return value; 
	} 
} 
