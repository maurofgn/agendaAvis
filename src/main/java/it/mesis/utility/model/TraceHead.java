package it.mesis.utility.model;

import java.util.ArrayList;
import java.util.List;

public class TraceHead {
	
	
	private String name;
	private String tableName;
	private List<TraceRow> rows;
	
	public TraceHead(String name) {
		super();
		this.name = name;
		int pos = name.lastIndexOf(".");
		tableName = pos >= 0 ? name.substring(pos +1) : name;
		this.rows = new ArrayList<TraceRow>();
	}

	public String getName() {
		return name;
	}
	
	
	public String getTableName() {
		return tableName;
	}

	public List<TraceRow> getRows() {
		return rows;
	}
	
	public void addRow(TraceRow row) {
		rows.add(row);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((rows == null) ? 0 : rows.hashCode());
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
		TraceHead other = (TraceHead) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (rows == null) {
			if (other.rows != null)
				return false;
		} else if (!rows.equals(other.rows))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("TraceHead [name=" + name + "]");
//		getRows().forEach(row -> sb.append("\n" + row));

		for (TraceRow traceRow : rows) {
			sb.append("\n" + traceRow);
		}
		
		return sb.toString();
	}
	
//	public List<String> getcols() {
//		ArrayList<String> retValue = new ArrayList<String>();
//		rows.forEach(r -> retValue.add(r.getName()));
//		return retValue;
//	}

}
