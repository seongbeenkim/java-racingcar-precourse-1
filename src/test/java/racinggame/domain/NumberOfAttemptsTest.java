package racinggame.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NumberOfAttemptsTest {

    @Test
    @DisplayName("시도 횟수 객체를 생성한다.")
    void create() {
        //given
        int number = 1;

        //when
        NumberOfAttempts numberOfAttempts = new NumberOfAttempts(number);

        //then
        assertThat(numberOfAttempts)
                .extracting("numberOfAttempts")
                .isEqualTo(number);
    }

    @Test
    @DisplayName("0 미만일 경우, 예외가 발생한다.")
    void create_fail_min_threshold() {
        //when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new NumberOfAttempts(-1))
                .withMessageMatching("시도 횟수는 최소 \\d+이상이여야 합니다.");
    }

    @Test
    @DisplayName("시도 횟수가 차감된다.")
    void reduce() {
        //given
        NumberOfAttempts numberOfAttempts = new NumberOfAttempts(1);

        //when
        NumberOfAttempts leftNumberOfAttempts = numberOfAttempts.reduce();

        //then
        assertThat(leftNumberOfAttempts)
                .extracting("numberOfAttempts")
                .isEqualTo(0);
    }

    @Test
    @DisplayName("시도 횟수가 남아있지 않을 경우, 예외가 발생한다.")
    void reduce_fail_no_more_number_of_attempts() {
        //given
        NumberOfAttempts numberOfAttempts = new NumberOfAttempts(0);

        //when, then
        assertThatThrownBy(() -> numberOfAttempts.reduce())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("시도 횟수가 남아있지 않습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"0, true", "1, false"})
    @DisplayName("시도 횟수가 남아있지 않는 지 확인한다.")
    void isNoneLeft(int leftNumberOfAttempts, boolean expected) {
        //given
        NumberOfAttempts numberOfAttempts = new NumberOfAttempts(leftNumberOfAttempts);

        //when
        boolean isNoneLeft = numberOfAttempts.isNoneLeft();

        //then
        assertThat(isNoneLeft).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"1, true", "0, false"})
    @DisplayName("시도 횟수가 남아있는 지 확인한다.")
    void isRemained(int leftNumberOfAttempts, boolean expected) {
        //given
        NumberOfAttempts numberOfAttempts = new NumberOfAttempts(leftNumberOfAttempts);

        //when
        boolean isRemained = numberOfAttempts.isRemained();

        //then
        assertThat(isRemained).isEqualTo(expected);
    }
}
