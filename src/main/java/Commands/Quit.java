package Commands;

import org.json.simple.JSONObject;

public class Quit extends CommandManager {

    public Quit(JSONObject args) {
        super(args);
    }

    @Override
    public JSONObject execute() {
        return null;
        }

    }
