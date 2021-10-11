package racinggame.domain.car;

import nextstep.utils.Randoms;

public class RandomNumberMoveStrategy implements MoveStrategy {

    private static final int MIN_THRESHOLD = 0;
    private static final int MAX_THRESHOLD = 9;
    private static final int MIN_MOVABLE_CONDITION = 4;

    @Override
    public boolean canMove() {
        return generateRandomNumber() >= MIN_MOVABLE_CONDITION;
    }

    protected int generateRandomNumber() {
        return Randoms.pickNumberInRange(MIN_THRESHOLD, MAX_THRESHOLD);
    }
}
