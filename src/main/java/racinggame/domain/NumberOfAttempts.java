package racinggame.domain;

import java.util.Objects;

public class NumberOfAttempts {

    private static final int MIN_THRESHOLD = 0;
    private static final int DECREMENT_UNIT = 1;

    private final int numberOfAttempts;

    public NumberOfAttempts(final int numberOfAttempts) {
        validateMinimum(numberOfAttempts);
        this.numberOfAttempts = numberOfAttempts;
    }

    private void validateMinimum(final int numberOfAttempts) {
        if (numberOfAttempts < MIN_THRESHOLD) {
            throw new IllegalArgumentException(String.format("시도 횟수는 최소 %d이상이여야 합니다.", MIN_THRESHOLD));
        }
    }

    public NumberOfAttempts reduce() {
        if (isNothingLeft()) {
            throw new IllegalStateException("시도 횟수가 남아있지 않습니다.");
        }

        return new NumberOfAttempts(numberOfAttempts - DECREMENT_UNIT);
    }

    public boolean isNothingLeft() {
        return this.numberOfAttempts == MIN_THRESHOLD;
    }

    @Override
    public boolean equals(final Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final NumberOfAttempts numberOfAttempts1 = (NumberOfAttempts) o;
        return numberOfAttempts == numberOfAttempts1.numberOfAttempts;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfAttempts);
    }
}
