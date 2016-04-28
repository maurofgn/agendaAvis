package it.mesis.utility.model;

import it.mesis.utility.Utility;

public class TraceRow {

	private String name;
	private String type;
	private Object oldValue;
	private Object newValue;
	
	public TraceRow(String name, String type, Object oldValue, Object newValue) {
		super();
		this.name = name;
		this.type = type;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public Object getOldValue() {
		return oldValue;
	}

	public Object getNewValue() {
		return newValue;
	}
	
	public String getOldValueString() {
		return type.equalsIgnoreCase("java.util.Date") ? Utility.dateFormatSeconds.format(oldValue) : oldValue.toString();
	}
	
	public String getNewValueString() {
		return type.equalsIgnoreCase("java.util.Date") ? Utility.dateFormatSeconds.format(newValue) : newValue.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((newValue == null) ? 0 : newValue.hashCode());
		result = prime * result + ((oldValue == null) ? 0 : oldValue.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TraceRow other = (TraceRow) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (newValue == null) {
			if (other.newValue != null)
				return false;
		} else if (!newValue.equals(other.newValue))
			return false;
		if (oldValue == null) {
			if (other.oldValue != null)
				return false;
		} else if (!oldValue.equals(other.oldValue))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TraceRow [name=" + name + ", type=" + type + ", oldValue="+ getOldValueString() + ", newValue=" + getNewValueString()+ "]";
	}
	
}
