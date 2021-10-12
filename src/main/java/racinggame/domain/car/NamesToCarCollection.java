package racinggame.domain.car;

import java.util.ArrayList;
import java.util.List;

public class NamesToCarCollection implements CarCollection {

    private final List<Car> cars;

    public NamesToCarCollection(final List<String> names) {
        validateNullOrEmpty(names);
        this.cars = createCars(names);
    }

    private void validateNullOrEmpty(final List<String> names) {
        if (names == null || names.isEmpty()) {
            throw new IllegalArgumentException("하나 이상의 이름이 존재해야합니다.");
        }
    }

    private List<Car> createCars(final List<String> names) {
        List<Car> cars = new ArrayList<>();

        for (String name : names) {
            cars.add(new Car(name));
        }

        return cars;
    }

    @Override
    public List<Car> list() {
        return cars;
    }
}
