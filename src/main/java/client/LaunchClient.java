package client;


import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LaunchClient extends Command{


    public LaunchClient(String[] args){
        super( args);
    }

    @Override
    public JSONObject execute() {

        JSONObject command = new JSONObject();
        command.put("robot", args[2]);
        command.put("command", args[0]);
        command.put("arguments", createArguments());


        return command;
    }

    @Override
    public void handleResponse(String response) {

        System.out.println(response);
    }

    private List<String> createArguments() {
        List<String> argument = new ArrayList<>();
        argument.add(args[1]);
        argument.add("5");
        argument.add("5");
        return argument;
    }


}

