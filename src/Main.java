import DataStructure.Agencies;
import DataStructure.MyNode;
import DataStructure.Services;
import Models.AgencyData;
import Models.Command;
import Models.OrderData;
import Models.ServiceData;

public class Main {
    public static void main(String[] args) {

        Services services = new Services();
        Agencies agencies = new Agencies(services);

        InputHandler inputHandler = new InputHandler();
        while (true) {
            Command userCommand = inputHandler.parseCommand();
            if (userCommand == null)
                System.out.println("Command Wrong");
            else
                switch (userCommand.getType()) {
                    // Complexity : O(n)
                    case ADD_SERVICE:
                        if(!services.addService(new ServiceData(userCommand.getCommand()[2])))
                            System.out.println("Service is Exist in GList");
                        System.out.print("<");
                        services.printServiceGList();
                        System.out.print(">");
                        System.out.println();
                        break;
                        /*
                         BestCase : O(1)
                         WorstCase : O(n)
                         AverageCase : O(n)
                         */
                    case ADD_SUB_SERVICE:
                        MyNode parent = services.getExistServiceNode(userCommand.getCommand()[4]);
                        ServiceData newService = new ServiceData(userCommand.getCommand()[2]);
                        if (parent != null) {
                            if (!services.addSubToService(parent, newService))
                                System.out.println("Child Service is Wrong");
                        }
                        else
                            System.out.println("Parent_Service Not Found");
                        System.out.print("<");
                        services.printServiceGList();
                        System.out.print(">");
                        System.out.println();
                        break;
                        /*
                         BestCase : O(1)
                         WorstCase : O(n)
                         AverageCase : O(n)
                        */
                    case ADD_OFFER:
                        if (!agencies.addOfferToAgency(userCommand.getCommand()[2], userCommand.getCommand()[4]))
                            System.out.println("Service Or Agency Not Found");
                        break;
                        /*
                         BestCase : O(1)
                         WorstCase : O(n)
                         AverageCase : O(n)
                        */
                    case DELETE:
                        if (!agencies.delete(userCommand.getCommand()[1], userCommand.getCommand()[3]))
                            System.out.println("Delete is not Valid");
                        break;
                        /*
                        Complexity : O(n)
                        */
                    case ADD_AGENCY:
                        agencies.addAgency(userCommand.getCommand()[2]);
                        agencies.print(agencies.getFirst());
                        System.out.println();
                        break;
                    case LIST_AGENCIES:
                        System.out.print("<");
                        agencies.print(agencies.getFirst());
                        System.out.print(">");
                        break;
                    case LIST_SERVICES:
                        System.out.print("<");
                        services.printServiceGList();
                        System.out.print(">");
                        break;
                    case LIST_SERVICES_FROM:
                        MyNode service = services.getExistServiceNode(userCommand.getCommand()[3]);
                        if (service != null)
                            services.print(service);
                        else
                            System.out.println("SubList Error");
                        break;
                    case ORDER:
                        Integer priority = Integer.parseInt(userCommand.getCommand()[7]);
                        MyNode temp = services.getExistServiceNode(userCommand.getCommand()[1]);
                        String customer = userCommand.getCommand()[5];
                        if (temp != null) {
                            OrderData order = new OrderData((ServiceData)temp.getData(), priority, customer);
                            agencies.addOrderToAgency(order, userCommand.getCommand()[3]);
                        }
                        break;
                    case LIST_ORDERS:
                        MyNode agency = agencies.getAgency(userCommand.getCommand()[2]);
                        if (agency != null)
                            ((AgencyData)agency.getData()).printQueue();
                        else
                            System.out.println("Agency not found!");
                        break;
                }
        }
    }
}
