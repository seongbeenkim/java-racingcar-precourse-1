package racinggame.domain;

public class Name {

    private static final int MAX_LENGTH = 5;

    private final String name;

    public Name(final String name) {
        validateMaxLength(name);
        this.name = name;
    }

    private void validateMaxLength(final String name) {
        if (name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(String.format("이름은 %d자 이하여야 합니다.", MAX_LENGTH));
        }
    }
}
