package racinggame.controller.dto;

import racinggame.domain.car.Car;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WinnersResponse {

    private final List<String> names;

    public WinnersResponse(final List<Car> winners) {
        this.names = extractNames(winners);
    }

    private List<String> extractNames(final List<Car> winners) {
        List<String> names = new ArrayList<>();

        for (Car winner : winners) {
            names.add(winner.getName());
        }

        return names;
    }

    public List<String> getNames() {
        return Collections.unmodifiableList(names);
    }
}
