package world.Obstacles;

import robot.Position;

public abstract class AbstractObstacle implements IObstacle {

    public abstract int getBottomLeftY();

    public abstract int getBottomLeftX();

    public abstract int getSize();

    public abstract boolean blockedPosition(Position position);

    public abstract boolean blocksPath(Position a, Position b);

}

