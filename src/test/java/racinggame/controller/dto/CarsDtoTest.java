package racinggame.controller.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racinggame.domain.car.Car;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CarsDtoTest {

    @Test
    @DisplayName("자동차들의 정보 객체를 생성한다.")
    void create() {
        //given
        String name1 = "name1";
        String name2 = "name2";
        int position1 = 1;
        int position2 = 2;
        Car car1 = new Car(name1, position1);
        Car car2 = new Car(name2, position2);
        List<Car> cars = Arrays.asList(car1, car2);

        //when
        CarsDto carsDto = new CarsDto(cars);

        //then
        assertThat(carsDto.getNames())
                .containsExactly(name1, name2);

        assertThat(carsDto.getPositions())
                .containsExactly(position1, position2);
    }
}
