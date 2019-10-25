package stea1th.chess.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import stea1th.chess.rules.enums.Direction;

@Data
@AllArgsConstructor
public class Move {
    private int newPosition;
    private int oldPosition;
    private Direction direction;
}
