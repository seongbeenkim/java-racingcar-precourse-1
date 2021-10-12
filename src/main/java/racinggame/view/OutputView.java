package racinggame.view;

import racinggame.controller.dto.CarsResponse;
import racinggame.controller.dto.WinnersResponse;

import java.util.List;

public class OutputView {

    private static final String WINNER_NAME_DELIMITER = ", ";
    private static final String POSITION_UNIT = "-";

    public void printError(final String message) {
        System.out.println("[ERROR] : " + message);
    }

    public void printPlayResult() {
        System.out.println("실행 결과");
    }

    public void printRoundResult(final CarsResponse carsResponse) {
        List<String> names = carsResponse.getNames();
        List<Integer> positions = carsResponse.getPositions();

        for (int i = 0; i < names.size(); i++) {
            System.out.printf("%s : %s%n", names.get(i), convertPositionValue(positions.get(i)));
        }

        System.out.println();
    }

    private String convertPositionValue(final Integer position) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < position; i++) {
            stringBuilder.append(POSITION_UNIT);
        }

        return stringBuilder.toString();
    }

    public void printWinners(final WinnersResponse winnersResponse) {
        List<String> winnerNames = winnersResponse.getNames();
        String joinedNames = String.join(WINNER_NAME_DELIMITER, winnerNames);
        System.out.printf("최종 우승자는 %s 입니다.%n", joinedNames);
    }
}
