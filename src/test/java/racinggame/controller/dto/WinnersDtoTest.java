package racinggame.controller.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racinggame.domain.car.Car;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WinnersDtoTest {

    @Test
    @DisplayName("우승자들의 이름 정보 객체를 생성한다.")
    void create() {
        //given
        String name1 = "name1";
        String name2 = "name2";
        Car car1 = new Car(name1);
        Car car2 = new Car(name2);
        List<Car> cars = Arrays.asList(car1, car2);

        //when
        WinnersDto winnersDto = new WinnersDto(cars);

        //then
        assertThat(winnersDto.getNames())
                .containsExactly(name1, name2);
    }
}
