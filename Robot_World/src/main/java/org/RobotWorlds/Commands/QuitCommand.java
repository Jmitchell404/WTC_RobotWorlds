package org.RobotWorlds.Commands;


import org.RobotWorlds.Server.Server;

public class QuitCommand extends Command {
    public QuitCommand() {
        super("quit");
    }

    @Override
    public boolean execute(Server target) {
        return false;
    }
    /*
     * This super allows client to terminate
     * tether to the world thus ending the game.
     */
}
