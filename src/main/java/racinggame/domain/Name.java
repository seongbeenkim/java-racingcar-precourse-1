package racinggame.domain;

public class Name {

    private static final int MAX_LENGTH = 5;

    private final String name;

    public Name(final String name) {
        validateNullOrEmpty(name);
        validateMaxLength(name);
        this.name = name;
    }

    private void validateNullOrEmpty(final String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("이름은 한 글자 이상이여야 합니다.");
        }
    }

    private void validateMaxLength(final String name) {
        if (name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(String.format("이름은 %d자 이하여야 합니다.", MAX_LENGTH));
        }
    }
}
