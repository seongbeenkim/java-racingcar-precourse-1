package racinggame.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class GameSystemTest {

    @Test
    @DisplayName("자동차 게임 시스템 객체를 생성한다.")
    void create() {
        //given
        int position = 0;
        String name1 = "nano1";
        String name2 = "nano2";
        Car car1 = new Car(name1);
        Car car2 = new Car(name2);
        int numberOfAttempts = 1;

        //when
        GameSystem gameSystem = new GameSystem(Arrays.asList(car1, car2), numberOfAttempts);

        //then
        assertThat(gameSystem.getCars())
                .extracting("name", "position")
                .containsExactly(tuple(name1, position)
                        , tuple(name2, position));
    }

    @ParameterizedTest
    @CsvSource(value = {"0, true", "1, false"})
    @DisplayName("게임이 종료된 상태인지 확인한다.")
    void isFinished(int numberOfAttempts, boolean expected) {
        //given
        String name1 = "nano1";
        String name2 = "nano2";
        Car car1 = new Car(name1);
        Car car2 = new Car(name2);
        GameSystem gameSystem = new GameSystem(Arrays.asList(car1, car2), numberOfAttempts);

        //when
        boolean isFinished = gameSystem.isFinished();

        //then
        assertThat(isFinished).isEqualTo(expected);
    }

    @Test
    @DisplayName("한 시도 횟수만큼 게임을 진행한다.")
    void play() {
        //given
        String name1 = "nano1";
        String name2 = "nano2";
        int position1 = 1;
        int position2 = 2;
        Car car1 = new Car(name1, position1);
        Car car2 = new Car(name2, position2);
        int numberOfAttempts = 1;
        GameSystem gameSystem = new GameSystem(Arrays.asList(car1, car2), numberOfAttempts);

        //when
        GameSystem playedGameSystem = gameSystem.play(() -> true);

        //then
        int incrementUnit = 1;
        assertThat(playedGameSystem.isFinished()).isTrue();
        assertThat(playedGameSystem.getCars())
                .extracting("name", "position")
                .containsExactly(tuple(name1, position1 + incrementUnit),
                        tuple(name2, position2 + incrementUnit));
    }
}
