package racinggame.domain;

import java.util.List;

public class GameSystem {

    private final Cars cars;
    private final NumberOfAttempts numberOfAttempts;

    public GameSystem(final List<Car> cars, final int numberOfAttempts) {
        this(new Cars(cars), new NumberOfAttempts(numberOfAttempts));
    }

    public GameSystem(final Cars cars, final NumberOfAttempts numberOfAttempts) {
        validateNull(cars, numberOfAttempts);
        this.cars = cars;
        this.numberOfAttempts = numberOfAttempts;
    }

    private void validateNull(final Cars cars, final NumberOfAttempts numberOfAttempts) {
        if (cars == null) {
            throw new IllegalArgumentException("자동차들이 존재하지 않습니다.");
        }

        if (numberOfAttempts == null) {
            throw new IllegalArgumentException("시도 횟수가 존재하지 않습니다.");
        }
    }

    public List<Car> getCars() {
        return cars.list();
    }

    public boolean isNotFinished() {
        return numberOfAttempts.isRemained();
    }

    public GameSystem play(final MoveStrategy moveStrategy) {
        NumberOfAttempts leftNumberOfAttempts = numberOfAttempts.reduce();
        Cars racedCars = cars.race(moveStrategy);

        return new GameSystem(racedCars, leftNumberOfAttempts);
    }

    public List<Car> getWinners() {
        return cars.findWinners();
    }
}
