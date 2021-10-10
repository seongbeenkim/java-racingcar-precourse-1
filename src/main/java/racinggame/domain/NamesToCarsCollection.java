package racinggame.domain;

import java.util.ArrayList;
import java.util.List;

public class NamesToCarsCollection implements CarsCollection {

    private final List<String> names;

    public NamesToCarsCollection(final List<String> names) {
        validateNullOrEmpty(names);
        this.names = new ArrayList<>(names);
    }

    private void validateNullOrEmpty(final List<String> names) {
        if (names == null || names.isEmpty()) {
            throw new IllegalArgumentException("하나 이상의 이름이 존재해야합니다.");
        }
    }

    @Override
    public List<Car> list() {
        List<Car> cars = new ArrayList<>();

        for (String name : names) {
            cars.add(new Car(name));
        }

        return cars;
    }
}
