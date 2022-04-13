package org.RobotWorlds.Commands;

import org.RobotWorlds.Server.Server;

public class LookCommand extends Command{
    public LookCommand() {
        super("help");
    }

    @Override
    public boolean execute(Server target) {
        return false;
    }
    /*
     * This super allows client to see what is present
     * in the robots field of vision.
     */
}
