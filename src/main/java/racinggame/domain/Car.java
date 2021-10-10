package racinggame.domain;

public class Car {

    private final Name name;
    private final Position position;

    public Car(final String name) {
        this(new Name(name), Position.valueOf(Position.MIN_THRESHOLD));
    }

    public Car(final String name, final int position) {
        this(new Name(name), Position.valueOf(position));
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

    public Position getFartherPosition(final Position position) {
        if (this.position.isFartherThan(position)) {
            return this.position;
        }

        return position;
    }

    public boolean isSamePosition(final Position position) {
        return this.position.isSame(position);
    }
}
