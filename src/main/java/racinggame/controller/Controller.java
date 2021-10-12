package racinggame.controller;

import racinggame.controller.dto.CarsResponse;
import racinggame.controller.dto.WinnersResponse;
import racinggame.domain.NumberOfAttempts;
import racinggame.domain.RacingGame;
import racinggame.domain.car.Cars;
import racinggame.domain.car.MoveStrategy;
import racinggame.domain.car.NamesToCarCollection;
import racinggame.domain.car.RandomNumberMoveStrategy;
import racinggame.view.InputView;
import racinggame.view.OutputView;

import java.util.List;
import java.util.NoSuchElementException;

public class Controller {

    private final InputView inputView;
    private final OutputView outputView;

    public Controller(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        RacingGame racingGame = initializeGame();
        MoveStrategy moveStrategy = new RandomNumberMoveStrategy();
        playGame(racingGame, moveStrategy);
    }

    private RacingGame initializeGame() {
        Cars cars = getCars();
        NumberOfAttempts numberOfAttempts = getNumberOfAttempts();
        return new RacingGame(cars, numberOfAttempts);
    }

    private Cars getCars() {
        try {
            List<String> carNames = inputView.getCarNames();
            return new Cars(new NamesToCarCollection(carNames));
        } catch (NoSuchElementException e) {
            outputView.printError(e.getMessage());
            throw e;
        } catch (RuntimeException e) {
            outputView.printError(e.getMessage());
            return getCars();
        }
    }

    private NumberOfAttempts getNumberOfAttempts() {
        try {
            int numberOfAttempts = inputView.getNumberOfAttempts();
            return new NumberOfAttempts(numberOfAttempts);
        } catch (NoSuchElementException e) {
            outputView.printError(e.getMessage());
            throw e;
        } catch (RuntimeException e) {
            outputView.printError(e.getMessage());
            return getNumberOfAttempts();
        }
    }

    private void playGame(RacingGame racingGame, final MoveStrategy moveStrategy) {
        outputView.printPlayResult();

        while (racingGame.isNotFinished()) {
            racingGame = racingGame.play(moveStrategy);
            CarsResponse carsResponse = new CarsResponse(racingGame.getCars());
            outputView.printRoundResult(carsResponse);
        }

        WinnersResponse winnersResponse = new WinnersResponse(racingGame.getWinners());
        outputView.printWinners(winnersResponse);
    }
}
