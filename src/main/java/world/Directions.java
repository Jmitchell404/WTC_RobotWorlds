package world;

public enum Directions {
    NORTH, EAST, SOUTH, WEST;

    static
    private final Directions[] values = values();

    public Directions left() {
        return values[Math.floorMod(ordinal() - 1, values.length)];
    }

    public Directions right() {
        return values[Math.floorMod(ordinal() + 1, values.length)];
    }
}
