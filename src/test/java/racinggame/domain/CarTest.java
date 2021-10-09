package racinggame.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CarTest {

    @Test
    @DisplayName("자동차 객체를 생성한다")
    void create() {
        //given
        String name = "nano";

        //when
        Car car = new Car(name);

        //then
        assertThat(car.getName()).isEqualTo(name);
        assertThat(car.getPosition()).isEqualTo(0);
    }

    @Test
    @DisplayName("전진 조건이 4 이상일 경우, 전진한다.")
    void move() {
        //given
        Car car = new Car("nano");

        //when
        car = car.move(4);

        //then
        assertThat(car.getPosition()).isEqualTo(1);
    }

    @Test
    @DisplayName("전진 조건이 3 이하일 경우, 전진하지 않는다.")
    void move_fail() {
        //given
        Car car = new Car("nano");

        //when
        car = car.move(3);

        //then
        assertThat(car.getPosition()).isEqualTo(0);
    }
}
