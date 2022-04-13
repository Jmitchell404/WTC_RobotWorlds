package org.RobotWorlds.Commands;

import org.RobotWorlds.Server.Server;

public class LaunchCommand extends Command{
    public LaunchCommand() {
        super("help");
    }

    @Override
    public boolean execute(Server target) {
        return false;
    }
    /*
     * This super command allows client to launch a
     * robot into the world.
     */
}
