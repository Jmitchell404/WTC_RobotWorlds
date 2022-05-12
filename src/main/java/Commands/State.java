package za.co.wethinkcode.robotworld.commands;

import org.json.simple.JSONObject;
import za.co.wethinkcode.robotworld.Server;

public class State extends Command{


    public State(JSONObject args) {
        super(args);
    }

    @Override
    public JSONObject execute() {

            return stateResponse();
    }

    private JSONObject stateResponse() {
        JSONObject status = new JSONObject();
        Command launch = new Launch(status);
        return null;
    }
}