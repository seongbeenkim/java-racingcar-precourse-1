package racinggame.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cars {

    private final CarsCollection carsCollection;

    public Cars(final List<Car> cars) {
        this(() -> cars);
    }

    public Cars(final CarsCollection carsCollection) {
        this.carsCollection = carsCollection;
    }

    public List<Car> list() {
        return Collections.unmodifiableList(carsCollection.list());
    }

    public Cars race(final MoveStrategy moveStrategy) {
        List<Car> cars = new ArrayList<>();

        for (Car car : this.carsCollection.list()) {
            Car movedCar = car.move(moveStrategy);
            cars.add(movedCar);
        }

        return new Cars(cars);
    }

    public Position findMaxPosition() {
        int minPositionValue = 0;
        Position maxPosition = Position.valueOf(minPositionValue);

        for (Car car : this.carsCollection.list()) {
            maxPosition = car.getFartherPosition(maxPosition);
        }

        return maxPosition;
    }
}
