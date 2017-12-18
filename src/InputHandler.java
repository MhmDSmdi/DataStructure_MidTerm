import Models.Command;
import Models.CommandType;

import java.util.Scanner;

public class InputHandler {

    public static final String ADD_SERVICE = "add service";
    public static final String ADD_SUB_SERVICE = "add subservice";
    public static final String ADD_OFFER = "add offer";
    public static final String ADD_AGENCY = "add agency";
    public static final String LIST_AGENCIES = "list agencies";
    public static final String LIST_SERVICES = "list services";
    public static final String LIST_ORDERS = "list orders";
    public static final String LIST_SERVICES_FROM = "list services from";
    public static final String DELETE = "delete";
    public static final String ORDER = "order";

    public InputHandler() {
        System.out.println("Commands: ");
        System.out.println("\t*add service \"Service_Name\" \"Car Model\" \"Note\" \"Cost\"");
        System.out.println("\t*add subservice \"Service_Name\" \"Car Model\" \"Note\" \"Cost\" to \"Service_Name\"");
        System.out.println("\t*add offer \"Service_Name\" to \"Agency_Name\"");
        System.out.println("\t*delete \"Service_Name\" from \"Agency_Name\"");
        System.out.println("\t*add agency \"Agency_Name\"");
        System.out.println("\t*list agencies");
        System.out.println("\t*list services");
        System.out.println("\t*list services from \"Service_Name\"");
        System.out.println("\t*order \"Service_Name\" to \"Agency_Name\" by \"Customer_Name\" with \"priority\"");
        System.out.print("\t*list orders \"Agency_Name\"");
    }

    private String[] getCommand() {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine().trim().toLowerCase();
        return command.split(" ");
    }

    public Command parseCommand() {
        String[] input = getCommand();
        if(input.length >= 2) {
            if (input[0].equals(DELETE))
                return new Command(CommandType.DELETE, input);
            else if (input[0].equals(ORDER))
                return new Command(CommandType.ORDER, input);
            else if (input.length > 2 && (input[0] + " " + input[1] + " " + input[2]).equals(LIST_SERVICES_FROM))
                return new Command(CommandType.LIST_SERVICES_FROM, input);
            else {
                switch (input[0] + " " + input[1]) {
                    case ADD_SERVICE:
                            return new Command(CommandType.ADD_SERVICE, input);

                    case ADD_SUB_SERVICE:
                        return new Command(CommandType.ADD_SUB_SERVICE, input);

                    case ADD_OFFER:
                        return new Command(CommandType.ADD_OFFER, input);

                    case ADD_AGENCY:
                        return new Command(CommandType.ADD_AGENCY, input);

                    case LIST_AGENCIES:
                        return new Command(CommandType.LIST_AGENCIES, input);

                    case LIST_SERVICES:
                        return new Command(CommandType.LIST_SERVICES, input);

                    case LIST_ORDERS:
                        return new Command(CommandType.LIST_ORDERS, input);
                }
            }
        }
        return null;
    }
}
