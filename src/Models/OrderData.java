package Models;

import java.util.Date;

public class OrderData extends Data {

    private ServiceData service;
    //3 to highest and 1 to lowest
    private int priority = 1;
    //0 to n
    private long time = 0;
    private String customerName;

    public OrderData(ServiceData service, int priority, String customerName) {
        this.service = service;
        this.priority = priority;
        this.customerName = customerName;
        time = new Date().getTime();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ServiceData getService() {
        return service;
    }

    public void setService(ServiceData service) {
        this.service = service;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
