package racinggame.domain.car;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cars {

    private static final int MIN_CARS_SIZE = 2;

    private final CarCollection carCollection;

    public Cars(final List<Car> cars) {
        this(() -> cars);
    }

    public Cars(final CarCollection carCollection) {
        validateNull(carCollection);
        validateSize(carCollection);
        this.carCollection = carCollection;
    }

    private void validateNull(final CarCollection carCollection) {
        if (carCollection == null || carCollection.list() == null) {
            throw new IllegalArgumentException("자동차들이 존재하지 않습니다.");
        }
    }

    private void validateSize(final CarCollection carCollection) {
        List<Car> cars = carCollection.list();

        if (cars.size() < MIN_CARS_SIZE) {
            throw new IllegalArgumentException(String.format("%d대 이상의 자동차가 존재해야 합니다.", MIN_CARS_SIZE));
        }
    }

    public List<Car> list() {
        return Collections.unmodifiableList(carCollection.list());
    }

    public Cars race(final MoveStrategy moveStrategy) {
        List<Car> cars = new ArrayList<>();

        for (Car car : this.carCollection.list()) {
            Car movedCar = car.move(moveStrategy);
            cars.add(movedCar);
        }

        return new Cars(cars);
    }

    public List<Car> findWinners() {
        Position maxPosition = findMaxPosition();
        List<Car> winners = new ArrayList<>();

        for (Car car : this.carCollection.list()) {
            addWinner(winners, car, maxPosition);
        }

        return Collections.unmodifiableList(winners);
    }

    private Position findMaxPosition() {
        int minPositionValue = 0;
        Position maxPosition = Position.valueOf(minPositionValue);

        for (Car car : this.carCollection.list()) {
            maxPosition = car.getFartherPosition(maxPosition);
        }

        return maxPosition;
    }

    private void addWinner(final List<Car> winners, final Car car, final Position maxPosition) {
        if (car.isSamePosition(maxPosition)) {
            winners.add(car);
        }
    }
}
