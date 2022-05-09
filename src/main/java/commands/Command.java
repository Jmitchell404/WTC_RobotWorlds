package commands;

public abstract class Command {
    private final String name;
    private String argument;

    public Command(String name){
        this.name = name.trim().toLowerCase();
        this.argument = "";
    }

    public Command(String name, String argument) {
        this(name);
        this.argument = argument.trim();
    }

    public String getName() {                                                                           //<2>
        return name;
    }

    public String getArgument() {
        return this.argument;
    }

    public static Command create(String instruction) {

        String[] args = instruction.toLowerCase().trim().split(" ");
        switch (args[0]){
            case "left":
                return new Left();
            case "right":
                return new Right();
            case "help":
                return new Help();
            case "fire":
                return new Fire();
            case "look":
                return new Look();
            case "state":
                return new State();
            case "dump":
                return new Dump();
            case "robots":
                return new Robots();
            case "forward":
                return new Forward(args[1]);
            case "replay":
                return new Back(args[1]);
            default:
                throw new IllegalArgumentException("Unsupported command: " + instruction);
        }
    }

}
