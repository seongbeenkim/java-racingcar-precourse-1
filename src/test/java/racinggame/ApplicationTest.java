package racinggame;

import nextstep.test.NSTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ApplicationTest extends NSTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    private static final String ERROR_MESSAGE = "[ERROR]";

    @BeforeEach
    void beforeEach() {
        setUp();
    }

    @Test
    void 전진_정지() {
        assertRandomTest(() -> {
            run("pobi,woni", "1");
            verify("pobi : -", "woni : ", "최종 우승자는 pobi 입니다.");
        }, MOVING_FORWARD, STOP);
    }

    @Test
    void 다수의_우승자() {
        assertRandomTest(() -> {
            run("pobi,woni,doni", "1");
            verify("pobi : -", "woni : -", "doni : ", "최종 우승자는 pobi, woni 입니다.");
        }, MOVING_FORWARD, MOVING_FORWARD, STOP);
    }

    @Test
    void 이름_5글자_초과에_대한_예외_처리() {
        assertSimpleTest(() -> {
            runNoLineFound("pobi,javaji");
            verify(ERROR_MESSAGE);
        });
    }

    @Test
    void 이름_공백에_대한_예외_처리() {
        assertSimpleTest(() -> {
            runNoLineFound("pobi,");
            verify(ERROR_MESSAGE);
        });
    }

    @Test
    void 자동차_2대_미만에_대한_예외_처리() {
        assertSimpleTest(() -> {
            runNoLineFound("pobi");
            verify(ERROR_MESSAGE);
        });
    }

    @Test
    void 시도_횟수_0_미만에_대한_예외_처리() {
        assertSimpleTest(() -> {
            runNoLineFound("pobi, toni, -1");
            verify(ERROR_MESSAGE);
        });
    }

    @AfterEach
    void tearDown() {
        outputStandard();
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
