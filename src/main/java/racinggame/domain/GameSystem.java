package racinggame.domain;

import java.util.List;

public class GameSystem {

    private final Cars cars;
    private final NumberOfAttempts numberOfAttempts;

    public GameSystem(final List<Car> cars, final int numberOfAttempts) {
        this(new Cars(cars), new NumberOfAttempts(numberOfAttempts));
    }

    public GameSystem(final Cars cars, final NumberOfAttempts numberOfAttempts) {
        this.cars = cars;
        this.numberOfAttempts = numberOfAttempts;
    }

    public List<Car> getCars() {
        return cars.list();
    }

    public boolean isFinished() {
        return numberOfAttempts.isNothingLeft();
    }
}
