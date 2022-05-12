package za.co.wethinkcode.robotworld.commands;

import org.json.simple.JSONObject;
import za.co.wethinkcode.robotworld.Server;
import za.co.wethinkcode.robotworld.world.Position;


public class Launch extends Command {

    public Position position;

    public Launch(JSONObject args) {
        super(args);
    }

    @Override
    public JSONObject execute() {

        if (Server.clients.size() == 8){
            return createWorldIsFullResponse();
        }
        else if (checkClientsHaveSameName()){
            return createTooManyInWorldResponse();
        }
        else {
            return launchRobot();
        }
    }

    private JSONObject launchRobot() {
        JSONObject success = new JSONObject();

        success.put("result", "OK");
        success.put("data", createData());
        success.put("state", createState());
        return success;
    }

    public JSONObject createState() {

        JSONObject state = new JSONObject();
        state.put("position","[" + this.position.getX()+ ","+ this.position.getY()+"]" );
        state.put("direction", "NORTH");
        state.put("shields", "hits");
        state.put("shots","shots");
        state.put("status","NORMAL");
        return state;
    }

    private JSONObject createData() {
        JSONObject data = new JSONObject();
        data.put(this.position,"[" + this.position.getX()+ ","+ this.position.getY()+"]");
        data.put("visibility", "steps");
        data.put("reload","seconds");
        data.put("repair","seconds");
        data.put("shields", "hits");

        return data;
    }

    private JSONObject createTooManyInWorldResponse() {
//          TODO: Implement too many in world response
        JSONObject taken = new JSONObject();

        taken.put("result", "ERROR");
        taken.put("data", createTakenData());
        return taken;
    }

    private JSONObject createTakenData() {
        JSONObject msg = new JSONObject();
        msg.put("message","Too many of you in this world");
        return msg;
    }

    private boolean checkClientsHaveSameName() {
        for (String client: Server.clients.keySet()){
            if (client.equalsIgnoreCase(args.get("robot").toString())){
                return true;
            }
        }
        return false;
    }

    private JSONObject createWorldIsFullResponse() {
//        TODO: Implement World is full response
        JSONObject space = new JSONObject();

        space.put("result", "ERROR");
        space.put("data", createSpaceData());

        return space;
    }

    private JSONObject createSpaceData() {
        JSONObject msg = new JSONObject();
        msg.put("message","No more space in this world");
        return msg;

    }
}