package racinggame.controller;

import racinggame.domain.Car;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WinnersDto {

    private final List<String> names;

    public WinnersDto(final List<Car> winners) {
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
