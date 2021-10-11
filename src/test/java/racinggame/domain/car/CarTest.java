package racinggame.domain.car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
        assertThat(car.getPosition()).isZero();
    }

    @Test
    @DisplayName("전진 가능할 경우, 전진한다.")
    void move() {
        //given
        Car car = new Car("nano");
        MoveStrategy moveStrategy = () -> true;

        //when
        car = car.move(moveStrategy);

        //then
        assertThat(car.getPosition()).isEqualTo(1);
    }

    @Test
    @DisplayName("전진 불가능할 경우, 전진하지 않는다.")
    void move_fail() {
        //given
        Car car = new Car("nano");
        MoveStrategy moveStrategy = () -> false;

        //when
        car = car.move(moveStrategy);

        //then
        assertThat(car.getPosition()).isZero();
    }

    @ParameterizedTest
    @CsvSource(value = {"0, 1", "2, 2"})
    @DisplayName("다른 위치와 비교하여 더 멀리 있는 위치를 반환한다.")
    void getFartherPosition(int positionValue, int expectedPositionValue) {
        //given
        Car car = new Car("name", 1);
        Position position = Position.valueOf(positionValue);

        //when
        Position fartherPosition = car.getFartherPosition(position);

        //then
        assertThat(fartherPosition.getPosition()).isEqualTo(expectedPositionValue);
    }

    @ParameterizedTest
    @CsvSource(value = {"1, true", "2, false"})
    @DisplayName("같은 위치인지 확인한다.")
    void isSamePosition(int positionValue, boolean expected) {
        //given
        Car car = new Car("name", 1);
        Position position = Position.valueOf(positionValue);

        //when
        boolean isSamePosition = car.isSamePosition(position);

        //then
        assertThat(isSamePosition).isEqualTo(expected);
    }
}
