package racinggame.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class NamesToCarsCollectionTest {

    @Test
    @DisplayName("모든 자동차를 반환한다.")
    void list() {
        //given
        String name1 = "popo";
        String name2 = "bobo";
        int position = 0;
        CarsCollection carsCollection = new NamesToCarsCollection(Arrays.asList(name1, name2));

        //when
        List<Car> cars = carsCollection.list();

        //then
        assertThat(cars)
                .extracting("name", "position")
                .containsExactly(tuple(name1, position),
                        tuple(name2, position));
    }
}
