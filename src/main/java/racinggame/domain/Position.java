package racinggame.domain;

public class Position {

    private static final int MIN_THRESHOLD = 0;

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
}
