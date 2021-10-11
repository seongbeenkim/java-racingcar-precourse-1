package racinggame.controller;

import racinggame.domain.Cars;
import racinggame.domain.GameSystem;
import racinggame.domain.MoveStrategy;
import racinggame.domain.NamesToCarsCollection;
import racinggame.domain.NumberOfAttempts;
import racinggame.domain.RandomNumberMoveStrategy;
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
        GameSystem gameSystem = initializeGameSystem();
        MoveStrategy moveStrategy = new RandomNumberMoveStrategy();
        playGame(gameSystem, moveStrategy);
    }

    private GameSystem initializeGameSystem() {
        Cars cars = getCars();
        NumberOfAttempts numberOfAttempts = getNumberOfAttempts();
        return new GameSystem(cars, numberOfAttempts);
    }

    private Cars getCars() {
        try {
            List<String> carNames = inputView.getCarNames();
            return new Cars(new NamesToCarsCollection(carNames));
        } catch (NoSuchElementException e) {
            outputView.printError(e.getMessage());
            throw e;
        } catch (Exception e) {
            outputView.printError(e.getMessage());
            return getCars();
        }
    }

    private NumberOfAttempts getNumberOfAttempts() {
        try {
            int inputNumberOfAttempts = inputView.getNumberOfAttempts();
            return new NumberOfAttempts(inputNumberOfAttempts);
        } catch (NoSuchElementException e) {
            outputView.printError(e.getMessage());
            throw e;
        } catch (Exception e) {
            outputView.printError(e.getMessage());
            return getNumberOfAttempts();
        }
    }

    private void playGame(GameSystem gameSystem, final MoveStrategy moveStrategy) {
        outputView.printPlayResult();

        while (gameSystem.isNotFinished()) {
            gameSystem = gameSystem.play(moveStrategy);
            CarsDto carsDto = new CarsDto(gameSystem.getCars());
            outputView.printRoundResult(carsDto);
        }

        WinnersDto winnersDto = new WinnersDto(gameSystem.getWinners());
        outputView.printWinners(winnersDto);
    }
}
