package za.co.wethinkcode.robotworld.commands;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public abstract class Command {

    public JSONObject args;

    public Command(JSONObject args){
        this.args = args;
    }

    public abstract JSONObject execute();

    public static Command create(String instruction) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject args = (JSONObject) parser.parse(instruction);


//        String[] args = instruction.toLowerCase().trim().split(" ");
        switch (args.get("command").toString()){
//            case "left":
//                return new Left();
//            case "right":
//                return new Right();
//            case "help":
//                return new Help();
//            case "fire":
//                return new Fire();
//            case "look":
//                return new Look();
            case "quit":
                return new Quit(args);
            case "state":
                return new State(args);
            case "dump":
                return new Dump(args);
//            case "robots":
//                return new Robots();
//            case "forward":
//                return new Forward(args[1]);
//            case "replay":
//                return new Back(args[1]);
            case "launch":
                return new Launch(args);
            default:
                throw new IllegalArgumentException("Unsupported command: " + instruction);
        }
    }

}
