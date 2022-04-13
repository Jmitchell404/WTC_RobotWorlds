package org.RobotWorlds.Commands;

import org.RobotWorlds.Server.Server;

public class HelpCommand extends Command{
    public HelpCommand() {
        super("help");
    }

    @Override
    public boolean execute(Server target) {
        return false;
    }
//     target.setStatus("I can understand these commands:\n" +
//             "QUIT  - Shut down robot\n" +
//             "HELP - provide information about commands\n" +
//             "FORWARD - move forward by specified number of steps, e.g. 'FORWARD 10'");
//        return true;
}
