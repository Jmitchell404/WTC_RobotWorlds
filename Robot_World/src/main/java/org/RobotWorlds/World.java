package org.RobotWorlds;

import java.util.ArrayList;
import java.util.List;

import static org.RobotWorlds.Direction.NORTH;

/**
 * Hello world! This is our new open world game. ROBOT WORLDS!
 */
public class World {
    public static final Position CENTRE = new Position(0,0);

    //     area limit , width and height
    private int min_y= -200, max_y =200;
    private int min_x= -100, max_x =100;
    private Position robotPosition = new Position(0,0);
    private Direction robotDirection = Direction.NORTH;
    private Obstacle obstacle;
//    The top left of the world is at [-width/2, height/2].
//    The bottom right is at [width/2, -height/2].


    /**Position
     * Changes the position of your robot in the world by moving the nrSteps in the robots current direction.
     * @param nSteps steps to move in current direction
     * @return true if this does not take the robot over the world's limits, or into an obstacle.
     */
    UpdateStatus changePosition(int nSteps){
        Position postion = new Position(robotPosition);
        switch(robotDirection){
            case NORTH:
            case SOUTH:
                postion.addY(nSteps);
                if (!(isNewPositionAllowed(postion))){
                    return UpdateStatus.OBSTRUCTED;
                }
                robotPosition.addY(nSteps);
                break;
            case EAST:
            case WEST:
                postion.addY(nSteps);
                if (!(isNewPositionAllowed(postion))){
                    return UpdateStatus.OBSTRUCTED;
                }
                robotPosition.addX(nSteps);
                break;
        }
        return UpdateStatus.SUCCESS;
    }

    /**
     * Updates the current direction your robot is facing in the world by cycling through the directions UP, RIGHT, BOTTOM, LEFT.
     * @param direction if true, then turn 90 degrees to the right, else turn left.
     */
    void changeDirection(Direction direction){
        robotDirection = direction;
    }

    /**
     * Retrieves the current position of the robot
     */
    Position getPosition(){
        return robotPosition;
    }

    /**
     * Gets the current direction the robot is facing in relation to a world edge.
     * @return Direction.UP, RIGHT, DOWN, or LEFT
     */
    Direction getCurrentDirection(){
        return robotDirection;
    }

    /**
     * Checks if the new position will be allowed, i.e. falls within the constraints of the world, and does not overlap an obstacle.
     * @param position the position to check
     * @return true if it is allowed, else false
     */
    boolean isNewPositionAllowed(Position position){

        // word boundaries
        if (position.getX() < min_x || position.getX() > max_x){
            return false;
        }
        if (position.getY() < min_y|| position.getY() > max_y){
            return false;
        }
        // obstacle avoidance
        if (!(position.getX() > obstacle.getBOTTOM_RIGHT().getX())){
            return false;
        }
        if (!(position.getX()> obstacle.getTOP_LEFT().getX())){
            return false;
        }
        if (!(position.getY()< obstacle.getBOTTOM_RIGHT().getY())){
            return false;
        }
        if (!(position.getY()< obstacle.getTOP_LEFT().getY())){
            return false;
        }
        return true;
    }
}
