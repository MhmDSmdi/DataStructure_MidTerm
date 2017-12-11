package Models;

public abstract class Data {
    protected String dataName;

    public void setName(String dataName) {
        this.dataName = dataName;
    }

    public String getDataName() {
        return dataName;
    }
}
