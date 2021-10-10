package racinggame.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class CarsTest {

    @Test
    @DisplayName("모든 자동차가 담긴 객체를 생성한다.")
    void create() {
        //given
        int defaultPosition = 0;
        String name1 = "squid";
        String name2 = "game";
        CarsCollection carsCollection = new NamesToCarsCollection(Arrays.asList(name1, name2));

        //when
        Cars cars = new Cars(carsCollection);

        //then
        assertThat(cars.list())
                .extracting("name", "position")
                .containsExactly(tuple(name1, defaultPosition),
                        tuple(name2, defaultPosition));
    }

    @Test
    @DisplayName("모든 자동차가 경주한다.")
    void race() {
        //given
        String name1 = "squid";
        int position1 = 1;
        Car car1 = new Car(name1, position1);
        String name2 = "game";
        int position2 = 2;
        Car car2 = new Car(name2, position2);
        List<Car> carsToRace = Arrays.asList(car1, car2);
        Cars cars = new Cars(carsToRace);

        //when
        Cars movedCars = cars.race(() -> true);

        //then
        int incrementUnit = 1;
        assertThat(movedCars.list())
                .extracting("name", "position")
                .containsExactly(tuple(name1, position1 + incrementUnit),
                        tuple(name2, position2 + incrementUnit));
    }
}
