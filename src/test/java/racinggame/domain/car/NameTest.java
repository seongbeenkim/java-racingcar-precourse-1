package racinggame.domain.car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class NameTest {

    @Test
    @DisplayName("이름 객체를 생성한다.")
    void create() {
        //given
        String carName = "name";

        //when
        Name name = new Name(carName);

        //then
        assertThat(name)
                .extracting("name")
                .isEqualTo(carName);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "123456"})
    @DisplayName("이름이 1자 미만, 5자 초과일 경우, 예외가 발생한다.")
    void create_fail_with_max_name_length(String name) {
        //when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Name(name))
                .withMessageMatching("이름은 \\d+자 이상 \\d+자 이하여야 합니다.");
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("null일 경우, 예외가 발생한다.")
    void create_fail_with_null(String name) {
        //when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Name(name))
                .withMessageContaining("이름이 존재하지 않습니다.");
    }
}
