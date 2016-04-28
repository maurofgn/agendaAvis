package it.mesis.util.model;

import it.mesis.avis.enu.DonaType;

import java.util.Date;

public class DonaStatusType {
	
	DonaType type;
	boolean suspended;
	Date last;
	Date next;
	
	public DonaStatusType(DonaType type, Date last, Date next) {
		this(type, false, last, next);
	}
	
	public DonaStatusType(DonaType type, boolean suspended, Date last, Date next) {
		super();
		this.type = type;
		this.suspended = suspended;
		this.last = last;
		this.next = next;
	}
	
	public DonaType getType() {
		return type;
	}

	public boolean isSuspended() {
		return suspended;
	}
	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}

	public Date getLast() {
		return last;
	}
	public Date getNext() {
		return next;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
//		result = prime * result + (enable ? 1231 : 1237);
//		result = prime * result + ((last == null) ? 0 : last.hashCode());
//		result = prime * result + ((next == null) ? 0 : next.hashCode());
//		result = prime * result + (suspended ? 1231 : 1237);
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
		DonaStatusType other = (DonaStatusType) obj;
		if (last == null) {
			if (other.last != null)
				return false;
		} else if (!last.equals(other.last))
			return false;
		if (next == null) {
			if (other.next != null)
				return false;
		} else if (!next.equals(other.next))
			return false;
		if (suspended != other.suspended)
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DonaStatusType [type=" + type 
				+ ", suspended=" + suspended + ", last=" + last + ", next="
				+ next + "]";
	}
	
}
