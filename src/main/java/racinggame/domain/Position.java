package racinggame.domain;

import java.util.Objects;

public class Position {

    static final int MIN_THRESHOLD = 0;
    private static final int INCREMENT_UNIT = 1;

    private final int position;

    public Position(final int position) {
        validateMin(position);
        this.position = position;
    }

    private void validateMin(final int position) {
        if (position < MIN_THRESHOLD) {
            throw new IllegalArgumentException(String.format("위치는 최소 %d 이상이여야 합니다.", MIN_THRESHOLD));
        }
    }

    public int getPosition() {
        return position;
    }

    public Position increase() {
        return new Position(position + INCREMENT_UNIT);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Position position1 = (Position) o;
        return position == position1.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
