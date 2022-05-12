package Commands;

import org.json.simple.JSONObject;

public class State extends CommandManager {


    public State(JSONObject args) {
        super(args);
    }

    @Override
    public JSONObject execute() {

            return stateResponse();
    }

    private JSONObject stateResponse() {
        JSONObject status = new JSONObject();
        CommandManager launch = new Launch(status);
        return null;
    }
}