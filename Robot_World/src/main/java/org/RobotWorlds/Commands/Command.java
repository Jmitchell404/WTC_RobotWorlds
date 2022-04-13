package org.RobotWorlds.Commands;

import org.RobotWorlds.Server.Server;

public abstract class Command {
    private  String name;
    private  String argument;


    public abstract boolean execute(Server target);

    public Command(String name){
        this.name = name;
    }
    public Command(String name, String argument) {
        this(name);
        this.argument = argument.trim();
    }

    public String getName() {
        return this.name;
    }

    public String getArgument() {
        return this.argument;
    }

    public static Command create(String instruction) {
        String[] args = instruction.toLowerCase().trim().split(" ");
        switch (args[0]){
            case "quit":
                return new QuitCommand();
            case "help":
                return new HelpCommand();
            case "dump":
                return new DumpCommand();
            case "launch":
                return new LaunchCommand();
            case "look":
                return new LookCommand();
            case "state":
                return new StateCommand();
//            case "forward":
//                return new ForwardCommand(args[1]);
            default:
                throw new IllegalArgumentException("Unsupported command: " + instruction);
        }
    }
}
