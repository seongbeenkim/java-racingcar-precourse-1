package racinggame.domain;

import java.util.ArrayList;
import java.util.List;

public class NamesToCarsCollection implements CarsCollection {

    private final List<String> names;

    public NamesToCarsCollection(final List<String> names) {
        this.names = new ArrayList<>(names);
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
