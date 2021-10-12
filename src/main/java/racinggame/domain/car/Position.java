package racinggame.domain.car;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position {

    static final int MIN_THRESHOLD = 0;
    private static final int MAX_CACHE_THRESHOLD = 30;
    private static final int INCREMENT_UNIT = 1;
    private static final Map<Integer, Position> CACHED_POSITIONS = createCachePositions();

    private final int position;

    private Position(final int position) {
        this.position = position;
    }

    private static Map<Integer, Position> createCachePositions() {
        HashMap<Integer, Position> positions = new HashMap<>();

        for (int i = MIN_THRESHOLD; i <= MAX_CACHE_THRESHOLD; i++) {
            positions.put(i, new Position(i));
        }

        return positions;
    }

    public static Position valueOf(final int position) {
        validateMinimum(position);
        return CACHED_POSITIONS.getOrDefault(position, new Position(position));
    }

    private static void validateMinimum(final int position) {
        if (position < MIN_THRESHOLD) {
            throw new IllegalArgumentException(String.format("위치는 최소 %d 이상이여야 합니다.", MIN_THRESHOLD));
        }
    }

    public Position increase() {
        return valueOf(position + INCREMENT_UNIT);
    }

    public int getPosition() {
        return position;
    }

    public boolean isFartherThan(final Position position) {
        return this.position > position.position;
    }

    public boolean isSame(final Position position) {
        return this.position == position.position;
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
