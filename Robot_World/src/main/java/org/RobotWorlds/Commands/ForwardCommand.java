package org.RobotWorlds.Commands;

import org.RobotWorlds.Server.Server;

public class ForwardCommand extends Command {
    public ForwardCommand(String argument) {
        super("forward", argument);
    }

    @Override
    public boolean execute(Server target) {
        return false;
    }
    /*
     * This super command allows client to move the
     * robot forward based on the direction they're facing.
     */
}
