package racinggame.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

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
}
