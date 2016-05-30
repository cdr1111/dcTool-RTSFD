package dc.rtsfd;

public class DBTableColumn {

	private int index;
	private String name;
	private String type;
	private String columnSize;
	private String memo;
	private String isNull;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getColumnSize() {
		return columnSize;
	}
	public void setColumnSize(String columnSize) {
		this.columnSize = columnSize;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getIsNull() {
		return isNull;
	}
	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
