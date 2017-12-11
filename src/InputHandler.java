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


    private String[] getCommand() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine().trim().toLowerCase();
        return command.split(" ");
    }

    public Command parseCommand() {
        String[] input = getCommand();
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
        return null;
    }
}
