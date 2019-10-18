package stea1th.chess;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractFigure {

    private String name;
    private Integer position;
    private boolean white;
    private boolean alive;

    private final static Map<String, String> REGISTERED_FIGURES = new HashMap<>();

    public abstract void register();

    public abstract void move(int position);

    public abstract boolean isTurnValid(String newPosition);
}
