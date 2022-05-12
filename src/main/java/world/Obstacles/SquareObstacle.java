package world.Obstacles;

import robot.Position;

public class SquareObstacle extends AbstractObstacle {

    private Position obstacle;

    /**
     * Creates new Square Obstacle.
     * @param x X co-ordinate of obstacle.
     * @param y Y co-ordinate of obstacle.
     */
    public SquareObstacle(int x, int y) {
        obstacle = new Position(x,y);
    }

    /**
     * Checks if this obstacle blocks access to the specified position.
     * @param position the position to check
     * @return return `true` if the x,y coordinate falls within the obstacle's area
     */
    public boolean blockedPosition(Position position) {

        return (getBottomLeftX() <= position.getX() && position.getX() < getBottomLeftX() + getSize()) &&
                (getBottomLeftY() <= position.getY() && position.getY() < getBottomLeftY() + getSize());

    }

    /**
     * Check if the path is blocked on 2 co-ordinates.
     * Loops through co-ordinates changes from start to end position.
     * @param start starting co-ordinate.
     * @param end ending co-ordinate.
     * @param same unchanging axis co-ordinate.
     * @param axis the unchanged axis.
     * returns `true` if this obstacle is in the way
     */
    public boolean checkPath(int start,int end,int same,char axis){

        for ( ; start < end; start++){
            if (axis == 'x'){
                if (blockedPosition(new Position(start, same))){
                    return true;
                }
            }
            else {
                if (blockedPosition(new Position(same,start))){
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Checks if the obstacle is blocking the path on the coordinates (x1, y1) to (x2, y2).
     * a first position
     * b second position
     * returns `true` if this obstacle is in the way
     */
    public boolean blocksPath(Position a, Position b){

        if (b.getX() > a.getX()){
            return checkPath(a.getX(), b.getX(), a.getY(), 'x');
        }
        else if (a.getX() > b.getX()){
            return checkPath(b.getX(), a.getX(), a.getY(), 'x');
        }
        else if (b.getY() > a.getY()){
            return checkPath(a.getY(), b.getY(), a.getX(), 'y');
        }
        else {
            return checkPath(b.getY(), a.getY(), a.getX(), 'y');
        }
    }


    @Override
    public int getBottomLeftY() {
        return this.obstacle.getY();    }

    @Override
    public int getBottomLeftX() {
        return this.obstacle.getX();    }

    @Override
    public int getSize() {
        return 5;    }


    public Position getObstacle() { return obstacle; }
}
