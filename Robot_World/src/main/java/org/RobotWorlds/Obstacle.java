package org.RobotWorlds;

import java.util.ArrayList;
import java.util.List;

public  class Obstacle{
    private final Position TOP_LEFT = new Position(-200,100);
    private final Position BOTTOM_RIGHT = new Position(100,-200);
    private Position CENTRE = new Position(0,0);
    private Position position = CENTRE;
    private Direction currentDirection = Direction.NORTH;
    private List<Obstacle> obstacles = new ArrayList<>();


    public Position getTOP_LEFT() {
        return TOP_LEFT;
    }

    public Position getBOTTOM_RIGHT() {
        return BOTTOM_RIGHT;
    }

    public Position getCENTRE() {
        return CENTRE;
    }

    public void setCENTRE(Position CENTRE) {
        this.CENTRE = CENTRE;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public void setObstacles(List<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    public Obstacle(Position CENTRE, Position position, Direction currentDirection, List<Obstacle> obstacles) {
        this.CENTRE = CENTRE;
        this.position = position;
        this.currentDirection = currentDirection;
        this.obstacles = obstacles;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Obstacle obstacle = (Obstacle) o;

        if (TOP_LEFT != null ? !TOP_LEFT.equals(obstacle.TOP_LEFT) : obstacle.TOP_LEFT != null) return false;
        if (BOTTOM_RIGHT != null ? !BOTTOM_RIGHT.equals(obstacle.BOTTOM_RIGHT) : obstacle.BOTTOM_RIGHT != null)
            return false;
        if (CENTRE != null ? !CENTRE.equals(obstacle.CENTRE) : obstacle.CENTRE != null){
            return false;
        }
        if (position != null ? !position.equals(obstacle.position) : obstacle.position != null){
            return false;
        }
        if (currentDirection != obstacle.currentDirection){
            return false;
        }
        return obstacles != null ? obstacles.equals(obstacle.obstacles) : obstacle.obstacles == null;
    }

    @Override
    public int hashCode() {
        int result = TOP_LEFT != null ? TOP_LEFT.hashCode() : 0;
        result = 31 * result + (BOTTOM_RIGHT != null ? BOTTOM_RIGHT.hashCode() : 0);
        result = 31 * result + (CENTRE != null ? CENTRE.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (currentDirection != null ? currentDirection.hashCode() : 0);
        result = 31 * result + (obstacles != null ? obstacles.hashCode() : 0);
        return result;
    }

}