package org.RobotWorlds.Commands;

import org.RobotWorlds.Server.Server;

public class StateCommand extends Command{
    public StateCommand() {
        super("help");
    }

    @Override
    public boolean execute(Server target) {
        return false;
    }
    /*
     * This super allows client to see a data dump of information
     * pertaining to the robots current state (life, reloads, position,direction)
     */
}
