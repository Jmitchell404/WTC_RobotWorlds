package world.Maze;

import robot.Position;
import server.ServerConfig;
import world.Obstacles.IObstacle;
import world.Obstacles.SquareObstacle;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomMaze extends AbstractMaze {

    private Position TOP_LEFT;
    private Position BOTTOM_RIGHT;

    private Random random = new Random();
    private List<IObstacle> obstacles;

    private int obstacleNumber = 10;
    private int pitNumber = 10;


     // Creates new empty obstacles list

    public RandomMaze(ServerConfig config) {
        obstacles = new ArrayList<>();
        TOP_LEFT = new Position((-config.getWidth() /2), (config.getHeight() /2));
        BOTTOM_RIGHT = new Position((config.getWidth() /2), (-config.getHeight() /2));
        generateObstacles(TOP_LEFT, BOTTOM_RIGHT);
    }


    /**
     * Generates the obstacles in to be stores in commands.
     * @param TOP_LEFT Position TOP_LEFT
     * @param BOTTOM_RIGHT Position BOTTOM_RIGHT
     */
    public void generateObstacles(Position TOP_LEFT, Position BOTTOM_RIGHT){

        for (int i = 0; i < obstacleNumber; i++){
            int[] randomCoordinates = generateCoordinates(TOP_LEFT, BOTTOM_RIGHT);
            this.obstacles.add(new SquareObstacle(randomCoordinates[0],randomCoordinates[1]));
        }
        setObstacles(this.obstacles);
    }


    /**
     * Generates random co-ordinates within the bounds of the boundaries.
     * @param TOP_LEFT Position TOP_LEFT
     * @param BOTTOM_RIGHT Position BOTTOM_RIGHT
     */
    private int[] generateCoordinates(Position TOP_LEFT, Position BOTTOM_RIGHT){

        int x = random.nextInt((BOTTOM_RIGHT.getX()-4)-TOP_LEFT.getX()) - BOTTOM_RIGHT.getX();
        int y = random.nextInt((TOP_LEFT.getY()-4)-BOTTOM_RIGHT.getY()) - TOP_LEFT.getY();
        if (!validCoordinates(x,y)) {
            return generateCoordinates(TOP_LEFT, BOTTOM_RIGHT);
        }
        return new int[] {x,y};
    }

    private boolean validCoordinates(int x, int y){
        for (IObstacle obstacle : obstacles) {
            if (obstacle.blockedPosition(new Position(x,y))
                    || obstacle.blockedPosition(new Position(x+obstacle.getSize(),y+obstacle.getSize()))){
                return false;
            }
        }
        return true;
    }

    public void setObstacleNumber(int number){this.obstacleNumber = number;}

    public void setPitNumber(int number){this.pitNumber = number;}
}
