package racinggame.domain.car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RandomNumberMoveStrategyTest {

    @Test
    @DisplayName("전진 조건이 4 이상일 경우, 전진 가능하다.")
    void canMove() {
        //given
        RandomNumberMoveStrategy moveStrategy = new RandomNumberMoveStrategy() {
            @Override
            int generateRandomNumber() {
                return 4;
            }
        };

        //when
        boolean isMovable = moveStrategy.canMove();

        //then
        assertThat(isMovable).isTrue();
    }

    @Test
    @DisplayName("전진 조건이 3 이하일 경우, 전진 불가능하다.")
    void canMove_false() {
        //given
        RandomNumberMoveStrategy moveStrategy = new RandomNumberMoveStrategy() {
            @Override
            int generateRandomNumber() {
                return 3;
            }
        };

        //when
        boolean isMovable = moveStrategy.canMove();

        //then
        assertThat(isMovable).isFalse();
    }
}
