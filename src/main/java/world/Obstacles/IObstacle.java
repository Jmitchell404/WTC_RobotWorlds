package world.Obstacles;

import robot.Position;

public interface IObstacle {

    int getBottomLeftX();

    int getBottomLeftY();

    int getSize();

    boolean blockedPosition(Position position);

    boolean blocksPath(Position a, Position b);

    Position getObstacle();
}

