package racinggame.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PositionTest {

    @Test
    @DisplayName("위치 객체를 반환한다.")
    void valueOf() {
        //when
        Position position = Position.valueOf(0);

        //then
        assertThat(position.getPosition()).isZero();
    }

    @Test
    @DisplayName("0 미만일 경우, 예외가 발생한다.")
    void valueOf_fail_with_min_position() {
        //given
        int invalidPosition = -1;

        //when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Position.valueOf(invalidPosition))
                .withMessageMatching("위치는 최소 \\d+ 이상이여야 합니다.");
    }

    @Test
    @DisplayName("위치가 1 증가한다.")
    void increase() {
        //given
        Position position = Position.valueOf(0);

        //when
        position = position.increase();

        //then
        assertThat(position.getPosition()).isEqualTo(1);
    }

    @ParameterizedTest
    @CsvSource(value = {"0, true", "1, false"})
    @DisplayName("더 멀리 위치해있는지 비교한다.")
    void isFartherThan(int positionValue, boolean expected) {
        //given
        Position positionToCompare = Position.valueOf(positionValue);
        Position position = Position.valueOf(1);

        //when
        boolean isFartherPosition = position.isFartherThan(positionToCompare);

        //then
        assertThat(isFartherPosition).isEqualTo(expected);
    }
}
