package Models.Mapping;

public class WordAttrib {
    private String colName;
    private int colValue;

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public int getColValue() {
        return colValue;
    }

    public void setColValue(int colValue) {
        this.colValue = colValue;
    }

    public WordAttrib(String colName, int colValue) {
        this.colName = colName;
        this.colValue = colValue;
    }

    @Override
    public String toString() {
        return "WordAttrib{" +
                "colName='" + colName + '\'' +
                ", colValue=" + colValue +
                '}';
    }
}
