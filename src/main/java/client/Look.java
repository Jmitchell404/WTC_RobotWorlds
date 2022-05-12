package client;

import org.json.simple.JSONObject;

public class Look extends Command{
    public Look(String[] args) {
        super(args);
    }

    @Override
    public void handleResponse(String response) {
        System.out.println(response);

    }

    @Override
    public JSONObject execute() {

        JSONObject command = new JSONObject();
        command.put("robot", args[2]);
        command.put("command", args[0]);

        return command;
    }

}

