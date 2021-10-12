package racinggame.domain.car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.tuple;

class NamesToCarCollectionTest {

    @Test
    @DisplayName("모든 자동차를 반환한다.")
    void list() {
        //given
        String name1 = "name1";
        String name2 = "name2";
        int position = 0;
        CarCollection carCollection = new NamesToCarCollection(Arrays.asList(name1, name2));

        //when
        List<Car> cars = carCollection.list();

        //then
        assertThat(cars)
                .extracting("name", "position")
                .containsExactly(tuple(name1, position),
                        tuple(name2, position));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("null 또는 비어있을 경우, 예외가 발생한다.")
    void create_fail(List<String> names) {
        //when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new NamesToCarCollection(names))
                .withMessage("하나 이상의 이름이 존재해야합니다.");
    }
}
