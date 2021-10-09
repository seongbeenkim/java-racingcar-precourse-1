package racinggame.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PositionTest {

    @Test
    @DisplayName("위치 객체를 생성한다.")
    void create() {
        //given
        int currentPosition = 0;

        //when
        Position position = new Position(currentPosition);

        //then
        assertThat(position.getPosition()).isEqualTo(currentPosition);
    }

    @Test
    @DisplayName("0 미만일 경우, 예외가 발생한다.")
    void create_fail_with_min_position() {
        //given
        int invalidPosition = -1;
        //when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Position(invalidPosition))
                .withMessageMatching("위치는 최소 \\d+ 이상이여야 합니다.");
    }

    @Test
    @DisplayName("위치가 1 증가한다.")
    void increase() {
        //given
        Position position = new Position(0);

        //when
        position = position.increase();

        //then
        assertThat(position.getPosition()).isEqualTo(1);
    }
}
