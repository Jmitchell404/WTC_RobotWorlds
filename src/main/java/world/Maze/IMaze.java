package world.Maze;

import robot.Position;
import world.Obstacles.IObstacle;
//import World.Pits.IPit;
import java.util.List;

/**
 * Interface to represent a maze. A World will be loaded with a Maze, and will delegate the work to check if a path is blocked by certain obstacles etc to this maze instance.
 */
public interface IMaze {
    /**
     * @return the list of obstacles, or an empty list if no obstacles exist.
     */
    List<IObstacle> getObstacles();

    /**
     * @return the list of pits, or an empty list if no obstacles exist.
     */
//    List<IPit> getPits();

    /**
     * Checks if this maze has at least one obstacle that blocks the path that goes from coordinate (x1, y1) to (x2, y2).
     * Since our robot can only move in horizontal or vertical lines (no diagonals yet), we can assume that either x1==x2 or y1==y2.
     * @param a first position
     * @param b second position
     * @return `true` if there is an obstacle is in the way
     */
    boolean blocksPath(Position a, Position b);

    /**
     * Generates the obstacles in to be stores in commands.
     * @param TOP_LEFT Position TOP_LEFT
     * @param BOTTOM_RIGHT Position BOTTOM_RIGHT
     */
    void generateObstacles(Position TOP_LEFT, Position BOTTOM_RIGHT);

    /**
     * Resets obstacles in maze to new empty array.
     */
    void resetMaze();

}
