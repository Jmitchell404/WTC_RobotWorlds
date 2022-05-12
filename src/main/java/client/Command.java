package client;

import org.json.simple.JSONObject;


public abstract class Command {

    private String argument;
    public JSONObject command;
    public String[] args;

    public Command(String[] args){
        this.args = args;
    }


    public JSONObject execute() {
        return null;
    }

    public String getArgument() {
        return this.argument;
    }

    public static Command create(String instruction) {

        String[] args = instruction.toLowerCase().trim().split(" ");
        switch (args[0]){
           case "left":
               return new Left(args);
           case "right":
               return new Right(args);
           case "help":
               return new Help(args);
           case "fire":
               return new Fire(args);
           case "look":
               return new Look(args);
           case "state":
               return new State(args);
           case "robots":
               return new Robots(args);
           case "forward":
               return new Forward(args);
           case "back":
               return new Back(args);
            case "launch":
                return new LaunchClient(args);
            default:
                throw new IllegalArgumentException("Unsupported command: " + instruction);
        }
    }

    public abstract void handleResponse(String response);
}

