package racinggame.domain.car;

@FunctionalInterface
public interface MoveStrategy {
    boolean canMove();
}
