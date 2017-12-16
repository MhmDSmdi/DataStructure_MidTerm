package DataStructure;

import Models.AgencyData;
import Models.OrderData;
import Models.ServiceData;

public class Agencies {
    private MyNode first;
    private Services servicesGList;

    public Agencies(Services servicesGList) {
        this.servicesGList = servicesGList;
    }

    public MyNode getFirst() {
        return first;
    }

    /*
    BestCase : O(1)
    WorstCase : O(n)
    AverageCase : O(n)
    */
    public MyNode getAgency(String agencyName) {
        MyNode root = first;
        while (root != null){
            if (root.getData().getDataName().equals(agencyName)){
                return root;
            }
            else
                root = root.getLink();
        }
        return null;
    }

    /*
    Complexity : O(n)
    */
    public boolean addAgency(String agencyName) {
        AgencyData agencyData = new AgencyData(agencyName);
        if (first == null) {
            first = new MyNode(null, null, agencyData);
            return true;
        }
        else {
            MyNode root = first;
            if (!isExistAgency(agencyData.getDataName())){
                while(!root.isFPnull())
                    root = root.getLink();
                MyNode newAgency = new MyNode(null, null, agencyData);
                root.setLink(newAgency);
                return true;
            }
            else
                return false;
        }
    }

    /*
    Complexity : O(n)
   */
    public boolean isExistAgency(String agencyName) {
        MyNode root = first;
        while(root != null) {
            if (root.getData().getDataName().equals(agencyName))
                return true;
            else
                root = root.getLink();
        }
        return false;
    }

    public boolean addOrderToAgency(OrderData orderData, String agencyName) {
        MyNode agency = getAgency(agencyName);
        if (agency == null || !((AgencyData)agency.getData()).isContainService(orderData.getService().getDataName()))
            return false;
        else {
            ((AgencyData) agency.getData()).addOrder(orderData);
            return true;
        }
    }

    /*
    Complexity : O(n)
    */
    public boolean addOfferToAgency(String serviceName, String agencyName) {
        MyNode agency = getAgency(agencyName);
        MyNode service = servicesGList.getMainServiceNode(serviceName);
        if (agency != null && service != null && !((AgencyData)agency.getData()).getAgencyServiceList().contains(service)) {
            ((AgencyData) agency.getData()).addOffer(service);
            ((AgencyData) agency.getData()).printOffers();
            System.out.println();
            return true;
        }
        else
            return false;
    }

    /*
     BestCase : O(1)
     WorstCase : O(n)
     AverageCase : O(n)
    */
    public boolean delete(String serviceName, String agencyName) {
        MyNode agency = getAgency(agencyName);
        MyNode service = servicesGList.getMainServiceNode(serviceName);
        if (agency == null || !((AgencyData)agency.getData()).isContainService(serviceName) || service == null)
            return false;
        else {
            ((AgencyData) agency.getData()).removeServiceFromAgency(service);
            ((ServiceData) service.getData()).decresaeUsrCount();
            if (((ServiceData) service.getData()).isFreeUsage()) {
                servicesGList.removeNode(service);
            }
            ((AgencyData)agency.getData()).printOffers();
            return true;
        }
    }

    public void print(MyNode root) {
        System.out.print(root.getData().getDataName());
        if (root.isSuperNode()) {
            System.out.print(",");
            System.out.print("<");
            print(root.getdLink());
            System.out.print(">");
        }
        if (!root.isFPnull()) {
            System.out.print(",");
            print(root.getLink());
        }
    }
}
