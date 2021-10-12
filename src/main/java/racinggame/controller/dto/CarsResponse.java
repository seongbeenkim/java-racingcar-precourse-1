package racinggame.controller.dto;

import racinggame.domain.car.Car;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarsResponse {

    private List<String> names;
    private List<Integer> positions;

    public CarsResponse(final List<Car> cars) {
        List<String> names = new ArrayList<>();
        List<Integer> positions = new ArrayList<>();

        for (Car car : cars) {
            names.add(car.getName());
            positions.add(car.getPosition());
        }

        this.names = names;
        this.positions = positions;
    }

    public List<String> getNames() {
        return Collections.unmodifiableList(names);
    }

    public List<Integer> getPositions() {
        return Collections.unmodifiableList(positions);
    }
}
