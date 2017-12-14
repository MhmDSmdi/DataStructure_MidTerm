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

    public void setFirst(MyNode first) {
        this.first = first;
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
            first = new MyNode(null, null, agencyData, 0);
            return true;
        }
        else {
            MyNode root = first;
            if (!isExistAgency(agencyData.getDataName())){
                while(!root.isFPnull())
                    root = root.getLink();
                MyNode newAgency = new MyNode(null, null, agencyData, 0);
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
        if (servicesGList.getExistServiceNode(orderData.getService().getDataName()) == null || agency == null || !((AgencyData)agency.getData()).isContainService(orderData.getService().getDataName()))
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
        MyNode service = servicesGList.getExistServiceNode(serviceName);
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

    public static void main(String[] args) {
        Services services = new Services();

        ServiceData b = new ServiceData();
        b.setName("1");
        services.addService(b);

        ServiceData f = new ServiceData();
        f.setName("2");
        services.addService(f);

        ServiceData g = new ServiceData();
        g.setName("3");
        services.addService(g);


        ServiceData sub = new ServiceData();
        sub.setName("5");

        ServiceData sub1 = new ServiceData();
        sub1.setName("6");

        ServiceData sub2 = new ServiceData();
        sub2.setName("7");

        ServiceData sub3 = new ServiceData();
        sub3.setName("8");

        ServiceData sub4 = new ServiceData();
        sub4.setName("9");

        services.addSubToService(services.getExistServiceNode("1"), sub);
        services.addSubToService(services.getExistServiceNode("1"), sub1);
        services.addSubToService(services.getExistServiceNode("2"), sub);
        services.addSubToService(services.getExistServiceNode("2"), sub2);
        services.addSubToService(services.getExistServiceNode("3"), sub3);
        services.addSubToService(services.getExistServiceNode("3"), sub4);

       /* System.out.print("<");
        services.printServiceGList();
        System.out.print(">");
        System.out.println();*/

       Agencies a = new Agencies(services);

       /*AgencyData d1 = new AgencyData("ASGHAR1");
       a.addAgency(d1);
       AgencyData d2 = new AgencyData("ASGHAR2");
       a.addAgency(d2);
       AgencyData d3 = new AgencyData("ASGHAR3");
       a.addAgency(d3);
       AgencyData d4 = new AgencyData("ASGHAR4");
       a.addAgency(d4);
       AgencyData d5 = new AgencyData("ASGHAR5");
       a.addAgency(d5);*/

       System.out.print("<");
       a.print(a.getFirst());
       System.out.print(">");
    }

}
