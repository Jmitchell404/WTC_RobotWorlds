package org.RobotWorlds.Commands;


import org.RobotWorlds.Server.Server;

public class DumpCommand extends Command {
    public DumpCommand() {
        super("help");
    }

    @Override
    public boolean execute(Server target) {
        return false;
    }
    /*
     * This super command allows client to see a data dump of information
     * pertaining to the worlds' current composition (objects and relativity).
     */
}
