package racinggame.domain.car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.tuple;

public class CarsTest {

    @Test
    @DisplayName("모든 자동차가 담긴 객체를 생성한다.")
    void create() {
        //given
        int defaultPosition = 0;
        String name1 = "name1";
        String name2 = "name2";
        CarsCollection carsCollection = new NamesToCarsCollection(Arrays.asList(name1, name2));

        //when
        Cars cars = new Cars(carsCollection);

        //then
        assertThat(cars.list())
                .extracting("name", "position")
                .containsExactly(tuple(name1, defaultPosition),
                        tuple(name2, defaultPosition));
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("null일 경우, 예외가 발생한다.")
    void create_fail_with_cars_collection_null(CarsCollection nullCarsCollection) {
        //when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Cars(nullCarsCollection))
                .withMessage("자동차들이 존재하지 않습니다.");
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("null일 경우, 예외가 발생한다.")
    void create_fail_with_cars_list_null(List<Car> nullCars) {
        //when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Cars(nullCars))
                .withMessage("자동차들이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("자동차가 2대 미만일 경우, 예외가 발생한다.")
    void create_fail_with_cars_min_size() {
        //given
        Car car = new Car("name");
        List<Car> cars = Collections.singletonList(car);

        //when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Cars(cars))
                .withMessageMatching("\\d+대 이상의 자동차가 존재해야 합니다.");
    }

    @Test
    @DisplayName("모든 자동차가 경주한다.")
    void race() {
        //given
        int position1 = 1;
        int position2 = 2;
        String name1 = "name1";
        String name2 = "name2";
        Car car1 = new Car(name1, position1);
        Car car2 = new Car(name2, position2);
        Cars cars = new Cars(Arrays.asList(car1, car2));

        //when
        Cars movedCars = cars.race(() -> true);

        //then
        int incrementUnit = 1;
        assertThat(movedCars.list())
                .extracting("name", "position")
                .containsExactly(tuple(name1, position1 + incrementUnit),
                        tuple(name2, position2 + incrementUnit));
    }

    @Test
    @DisplayName("가장 많이 전진한 자동차들을 반환한다.")
    void findWinners() {
        //given
        Car car1 = new Car("lose1", 1);

        int maxPosition = 3;
        String winnerName1 = "win1";
        String winnerName2 = "win2";
        Car car2 = new Car(winnerName1, maxPosition);
        Car car3 = new Car(winnerName2, maxPosition);
        Cars cars = new Cars(Arrays.asList(car1, car2, car3));

        //when
        List<Car> winners = cars.findWinners();

        //then
        assertThat(winners)
                .extracting("name", "position")
                .containsExactly(tuple(winnerName1, maxPosition),
                        tuple(winnerName2, maxPosition));
    }
}
