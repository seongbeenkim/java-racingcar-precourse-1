package racinggame.view;

import nextstep.utils.Console;

import java.util.ArrayList;
import java.util.List;

public class InputView {

    private static final String NAME_DELIMITER = ",";

    public List<String> getCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String names = Console.readLine();

        validateBlank(names);
        validateDelimiter(names);

        String[] splitNames = names.split(NAME_DELIMITER);
        return trim(splitNames);
    }

    private void validateBlank(final String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("아무것도 입력하지 않으셨습니다.");
        }
    }

    private void validateDelimiter(final String names) {
        if (hasNotNameDelimiter(names)) {
            throw new IllegalArgumentException(String.format("입력한 이름 목록에 '%s'가 포함되지 않았습니다.", NAME_DELIMITER));
        }
    }

    private boolean hasNotNameDelimiter(final String names) {
        return !names.contains(NAME_DELIMITER);
    }

    private List<String> trim(final String[] tokens) {
        List<String> strings = new ArrayList<>();

        for (String token : tokens) {
            strings.add(token.trim());
        }

        return strings;
    }

    public int getNumberOfAttempts() {
        System.out.println("시도할 회수는 몇회인가요?");
        String round = Console.readLine();
        validateBlank(round);
        return toInteger(round);
    }

    private int toInteger(final String token) {
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력해주세요.");
        }
    }
}
