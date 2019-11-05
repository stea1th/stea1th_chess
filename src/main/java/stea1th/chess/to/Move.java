package stea1th.chess.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import stea1th.chess.rules.enums.Direction;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Move implements Serializable {
    private int newPosition;
    private int oldPosition;
    private Direction direction;
    private boolean isCastling;

    public Move(int newPosition, int oldPosition, Direction direction) {
        this.newPosition = newPosition;
        this.oldPosition = oldPosition;
        this.direction = direction;
        this.isCastling = false;
    }
}
