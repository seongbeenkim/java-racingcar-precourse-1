package racinggame.domain;

public class Car {

    private final Name name;
    private final Position position;

    public Car(final String name) {
        this(new Name(name), Position.valueOf(Position.MIN_THRESHOLD));
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

    public Car move(final MoveStrategy moveStrategy) {
        if (moveStrategy.canMove()) {
            return new Car(name, position.increase());
        }

        return this;
    }
}
