package racinggame.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import racinggame.domain.car.Car;
import racinggame.domain.car.Cars;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.tuple;

public class RacingGameTest {

    @Test
    @DisplayName("자동차 게임 시스템 객체를 생성한다.")
    void create() {
        //given
        int defaultPosition = 0;
        String name1 = "name1";
        String name2 = "name2";
        Car car1 = new Car(name1);
        Car car2 = new Car(name2);

        //when
        RacingGame racingGame = new RacingGame(Arrays.asList(car1, car2), 1);

        //then
        assertThat(racingGame.getCars())
                .extracting("name", "position")
                .containsExactly(tuple(name1, defaultPosition)
                        , tuple(name2, defaultPosition));
    }

    @Test
    @DisplayName("시도 횟수가 null일 경우, 예외가 발생한다.")
    void create_fail_null_number_of_attempts() {
        //given
        String name1 = "name1";
        String name2 = "name2";
        Car car1 = new Car(name1);
        Car car2 = new Car(name2);
        Cars cars = new Cars(Arrays.asList(car1, car2));

        //when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new RacingGame(cars, null))
                .withMessage("시도 횟수가 존재하지 않습니다.");
    }

    @Test
    @DisplayName("자동차 목록이 null일 경우, 예외가 발생한다.")
    void create_fail_null_cars() {
        //given
        int numberOfAttempts = 1;

        //when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new RacingGame(null, numberOfAttempts))
                .withMessage("자동차들이 존재하지 않습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"1, true", "0, false"})
    @DisplayName("게임이 진행 상태인지 확인한다.")
    void isNotFinished(int numberOfAttempts, boolean expected) {
        //given
        String name1 = "name1";
        String name2 = "name2";
        Car car1 = new Car(name1);
        Car car2 = new Car(name2);
        RacingGame racingGame = new RacingGame(Arrays.asList(car1, car2), numberOfAttempts);

        //when
        boolean isNotFinished = racingGame.isNotFinished();

        //then
        assertThat(isNotFinished).isEqualTo(expected);
    }

    @Test
    @DisplayName("한 시도 횟수만큼 게임을 진행한다.")
    void play() {
        //given
        String name1 = "name1";
        String name2 = "name2";
        int position1 = 1;
        int position2 = 2;
        Car car1 = new Car(name1, position1);
        Car car2 = new Car(name2, position2);
        int numberOfAttempts = 1;
        RacingGame racingGame = new RacingGame(Arrays.asList(car1, car2), numberOfAttempts);

        //when
        RacingGame playedRacingGame = racingGame.play(() -> true);

        //then
        int incrementUnit = 1;
        assertThat(playedRacingGame.isNotFinished()).isFalse();
        assertThat(playedRacingGame.getCars())
                .extracting("name", "position")
                .containsExactly(tuple(name1, position1 + incrementUnit),
                        tuple(name2, position2 + incrementUnit));
    }

    @Test
    @DisplayName("우승자들을 반환한다.")
    void getWinners() {
        //given
        String name1 = "name1";
        String name2 = "name2";
        String name3 = "name3";
        int loserPosition = 1;
        int winnerPosition = 2;
        Car car1 = new Car(name1, loserPosition);
        Car car2 = new Car(name2, winnerPosition);
        Car car3 = new Car(name3, winnerPosition);
        int numberOfAttempts = 1;
        RacingGame racingGame = new RacingGame(Arrays.asList(car1, car2, car3), numberOfAttempts);

        //when
        List<Car> winners = racingGame.getWinners();

        //then
        assertThat(winners)
                .extracting("name", "position")
                .containsExactly(tuple(name2, winnerPosition),
                        tuple(name3, winnerPosition));
    }
}
