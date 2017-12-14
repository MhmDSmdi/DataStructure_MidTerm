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
        //orderQueue.print();
    }

    public void addOffer(MyNode service) {
        agencyServiceList.add(service);
        ((ServiceData) service.getData()).plusUserCount();
    }

    public void printQueue() {
        orderQueue.enqueueList();
    }

    /*
    Complexity : O(n)
    */
    public boolean isContainService(String serviceName) {
        for (MyNode a : agencyServiceList) {
            if (a.getData().dataName.equals(serviceName))
                return true;
            if (a.isSuperNode() && isContainService(serviceName, a.getdLink()))
                return true;
        }
        return false;

    }

    private boolean isContainService(String serviceName, MyNode root) {
        if (root.getData().dataName.equals(serviceName))
            return true;
        else {
            if(!root.isFPnull())
                 if (isContainService(serviceName, root.getLink()))
                     return true;
            if (root.isSuperNode())
                if (isContainService(serviceName, root.getdLink()))
                    return true;
        }
        return false;
    }

    public void printOffers() {
        System.out.print("<");
        for (MyNode a : agencyServiceList){
            System.out.print(a.getData().getDataName());
            System.out.print(",");
        }
        System.out.print(">");
    }

    public void addServiceToAgency(MyNode service) {
        agencyServiceList.add(service);
    }

    public void removeServiceFromAgency(MyNode service) {
        agencyServiceList.remove(service);
    }

    public static void main(String[] args) {
        AgencyData agencyData = new AgencyData();
    }
}
