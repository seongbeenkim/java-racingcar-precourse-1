package racinggame.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class NameTest {

    @Test
    @DisplayName("이름 객체를 생성한다.")
    void create() {
        //given
        String personName = "jenny";

        //when
        Name name = new Name(personName);

        //then
        assertThat(name).extracting("name").isEqualTo(personName);
    }

    @Test
    @DisplayName("이름이 5자 초과일 경우, 예외가 발생한다.")
    void create_fail_with_max_name_length() {
        //when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Name("123456"))
                .withMessageMatching("이름은 \\d+자 이하여야 합니다.");
    }
}
