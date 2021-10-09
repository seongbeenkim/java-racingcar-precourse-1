package racinggame.domain;

public class Car {

    private static final int MIN_MOVABLE_CONDITION = 4;

    private final Name name;
    private final Position position;

    public Car(final String name) {
        this(new Name(name), new Position());
    }

    public Car(final Name name, final Position position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name.getName();
    }

    public int getPosition() {
        return position.getPosition();
    }

    public Car move(final int randomNumber) {
        if (randomNumber >= MIN_MOVABLE_CONDITION) {
            return new Car(name, position.increase());
        }

        return this;
    }
}
