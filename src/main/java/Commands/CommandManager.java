package Commands;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public abstract class CommandManager {

    public JSONObject args;

    public CommandManager(JSONObject args){
        this.args = args;
    }

    public abstract JSONObject execute();

    public static CommandManager create(String instruction) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject args = (JSONObject) parser.parse(instruction);


        switch (args.get("command").toString()){
            case "quit":
                return new Quit(args);
            case "state":
                return new State(args);
            case "dump":
                return new Dump(args);
           case "robots":
               return new Robots(args);
           case "forward":
               return new Forward(args);
           case "replay":
               return new Back(args);
            case "launch":
                return new Launch(args);
            default:
                throw new IllegalArgumentException("Unsupported command: " + instruction);
        }
    }

}

