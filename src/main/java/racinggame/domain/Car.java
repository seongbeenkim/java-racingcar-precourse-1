package racinggame.domain;

public class Car {

    private final Name name;
    private final Position position;

    public Car(final String name) {
        this(name, Position.MIN_THRESHOLD);
    }

    public Car(final String name, final int position) {
        this.name = new Name(name);
        this.position = new Position(position);
    }

    public String getName() {
        return name.getName();
    }

    public int getPosition() {
        return position.getPosition();
    }
}
