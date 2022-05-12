package world.Maze;

//import World.Pits.IPit;
import robot.Position;
import world.Obstacles.IObstacle;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMaze implements IMaze {

    private List<IObstacle> obstacles;

    /**
     * Checks if this maze has at least one obstacle that blocks the path that goes from coordinate (x1, y1) to (x2, y2).
     * Since our robot can only move in horizontal or vertical lines (no diagonals yet), we can assume that either x1==x2 or y1==y2.
     * @param a first position
     * @param b second position
     * @return `true` if there is an obstacle is in the way
     */
    public boolean blocksPath(Position a, Position b) {

        for (IObstacle IObstacle : obstacles) {
            if (IObstacle.blocksPath(a, b)) {
                return true;
            }
        }
        return false;
    }

     // Resets obstacles in maze to new empty array.
    public void resetMaze(){
        obstacles = new ArrayList<>();
    }


    /**
     * Generates the obstacles in to be stores in commands.
     * @param TOP_LEFT Position TOP_LEFT
     * @param BOTTOM_RIGHT Position BOTTOM_RIGHT
     */
    public abstract void generateObstacles(Position TOP_LEFT, Position BOTTOM_RIGHT);


    /**
     * Sets the list of obstacles.
     * @param obstacles Obstacles to set to.
     */
    public void setObstacles(List<IObstacle> obstacles){
        this.obstacles = obstacles;
    }


     //@return the list of obstacles, or an empty list if no obstacles exist.

    public List<IObstacle> getObstacles(){
        return this.obstacles;
    }



}
