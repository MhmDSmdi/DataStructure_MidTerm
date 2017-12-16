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
