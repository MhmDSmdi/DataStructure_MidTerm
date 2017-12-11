package Models;

import DataStructure.MyNode;
import DataStructure.PriorityQueue;

import java.util.ArrayList;

public class AgencyData extends Data {
    private ArrayList<MyNode> agencyServiceList;
    private PriorityQueue orderQueue;

    public AgencyData(String agencyName) {
        dataName = agencyName;
        agencyServiceList = new ArrayList<>();
        orderQueue = new PriorityQueue(1000);
    }

    public AgencyData() {
        agencyServiceList = new ArrayList<>();
        orderQueue = new PriorityQueue(1000);
    }

    public ArrayList<MyNode> getAgencyServiceList() {
        return agencyServiceList;
    }

    public PriorityQueue getOrderQueue() {
        return orderQueue;
    }

    public void addOrder(OrderData order) {
        orderQueue.insert(order);
    }

    public void addOffer(MyNode service) {
        agencyServiceList.add(service);
        ((ServiceData) service.getData()).plusUserCount();
    }

    public void printQueue() {
        orderQueue.enqueueList();
    }

    public boolean isContainService(String serviceName) {
        for (MyNode a : agencyServiceList) {
            if (a.getData().dataName.equals(serviceName))
                return true;
        }
        return false;
    }

    public void addServiceToAgency(MyNode service) {
        agencyServiceList.add(service);
    }

    public void removeServiceFromAgency(MyNode service) {
        agencyServiceList.remove(service);
    }
}
