package Commands;

import org.json.simple.JSONObject;

public class RobotCommand extends CommandManager{
    public RobotCommand(JSONObject args) {
        super(args);
    }

    @Override
    public JSONObject execute() {
        return null;
    }
}
