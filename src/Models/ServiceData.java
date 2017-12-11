package Models;

public class ServiceData extends Data {

    private String carModel, note, cost;
    private int userCount = 0;

    public ServiceData(String serviceName, String carModel, String note, String cost) {
        dataName = serviceName;
        this.carModel = carModel;
        this.note = note;
        this.cost = cost;
    }

    public ServiceData(String serviceName) {
        dataName = serviceName;
        this.carModel = carModel;
        this.note = note;
        this.cost = cost;
    }

    public ServiceData() {}

    /*public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }*/

    public void plusUserCount(){
        userCount++;
    }

    public void decresaeUsrCount() {
        if (userCount > 0)
            userCount--;
    }

    public boolean isFreeUsage() {
        if (userCount == 0)
            return true;
        else
            return false;
    }
}
