package za.co.wethinkcode.robotworld.commands;

import org.json.simple.JSONObject;
import za.co.wethinkcode.robotworld.Server;

public class Dump extends Command{
    public Dump(JSONObject args) {
        super(args);
    }

    @Override
    public JSONObject execute() {

//        if (Server.clients.size() == 8){
//            return createWorldIsFullResponse();
//        }
//        else if (checkClientsHaveSameName()){
//            return createTooManyInWorldResponse();
//        }
//        else {
//            return launchRobot();
        return null;
        }
    }

