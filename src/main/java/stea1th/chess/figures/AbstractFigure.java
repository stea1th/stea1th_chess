package stea1th.chess.figures;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import stea1th.chess.rules.Rule;
import stea1th.chess.rules.RuleFactory;

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
    Rule rule;

    AbstractFigure(String notation, String name, Integer position, boolean white) {
        this.notation = notation;
        this.name = name;
        this.position = position;
        this.white = white;
        this.alive = true;
        this.movesCount = 0;
        this.rule = loadRule();
    }

    private final static Map<String, String> REGISTERED_FIGURES = new HashMap<>();

    public abstract void register();

    public abstract boolean move(int position);

    public abstract boolean isTurnValid(int newPosition);

    private Rule loadRule() {
        return RuleFactory.createRule(this);
    }
}
