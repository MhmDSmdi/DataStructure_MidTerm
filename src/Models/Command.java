package Models;

public class Command {
    private CommandType type;
    private String[] command;

    public Command(CommandType type, String[] command) {
        this.type = type;
        this.command = command;
    }

    public CommandType getType() {
        return type;
    }

    public String[] getCommand() {
        return command;
    }
}
