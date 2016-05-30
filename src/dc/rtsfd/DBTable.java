package dc.rtsfd;

import java.util.ArrayList;

public class DBTable {

	private String name;
	
	private ArrayList<DBTableColumn> dbtColumn;

	public ArrayList<DBTableColumn> getDbtColumn() {
		return dbtColumn;
	}

	public void setDbtColumn(ArrayList<DBTableColumn> dbtColumn) {
		this.dbtColumn = dbtColumn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
