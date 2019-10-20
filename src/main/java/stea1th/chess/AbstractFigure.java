package stea1th.chess;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractFigure implements Figure {

    @Getter
    private String notation;
    private String name;
    private Integer position;
    private boolean white;
    private boolean alive;
    private int movesCount;

    AbstractFigure(String notation, String name, Integer position, boolean white) {
        this.notation = notation;
        this.name = name;
        this.position = position;
        this.white = white;
        this.alive = true;
        this.movesCount = 0;
    }

    private final static Map<String, String> REGISTERED_FIGURES = new HashMap<>();

    public abstract void register();

    public abstract boolean move(int position);

    public abstract boolean isTurnValid(int newPosition);
}
