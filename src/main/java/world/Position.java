package world;

/**
 * The behaviour of the Robot is encapsulated in the Robot object.
 * The robot position in virtual world are tracked through a cartesian system
 *     x plane
 *     y plane
 *     position relative to world constraints
 */
public class Position {
    // filed variables
    private final int x;
    private final int y;

    // position on x and y axis
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // X axis
    public int getX() {
        return x;
    }

    // Y axis
    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        return y == position.y;
    }

    // checking bounds
    public boolean isIn(Position topLeft, Position bottomRight) {
        boolean withinTop = this.y <= topLeft.getY();
        boolean withinBottom = this.y >= bottomRight.getY();
        boolean withinLeft = this.x >= topLeft.getX();
        boolean withinRight = this.x <= bottomRight.getX();
        return withinTop && withinBottom && withinLeft && withinRight;
    }
}
