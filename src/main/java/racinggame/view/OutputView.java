package racinggame.view;

import racinggame.controller.dto.CarsDto;
import racinggame.controller.dto.WinnersDto;

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

    public void printRoundResult(final CarsDto carsDto) {
        List<String> names = carsDto.getNames();
        List<Integer> positions = carsDto.getPositions();

        for (int i = 0; i < names.size(); i++) {
            System.out.println(String.format("%s : %s", names.get(i), convertPositionValue(positions.get(i))));
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

    public void printWinners(final WinnersDto winnersDto) {
        List<String> winnerNames = winnersDto.getNames();
        String joinedNames = String.join(WINNER_NAME_DELIMITER, winnerNames);
        System.out.println(String.format("최종 우승자는 %s 입니다.", joinedNames));
    }
}
