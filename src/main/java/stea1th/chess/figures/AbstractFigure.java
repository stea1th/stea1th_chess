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

    }

    private final static Map<String, String> REGISTERED_FIGURES = new HashMap<>();

    public void register() {
        this.rule = loadRule();
    }

    public boolean move(int position){
        boolean isValid = isTurnValid(position);
        if (isValid)
            this.setPosition(position);
        return isValid;
    }

    public boolean isTurnValid(int newPosition) {
        return rule.getAllPossibleMoves(this.getPosition()).get(String.valueOf(newPosition)) != null;
    }

    private Rule loadRule() {
        return RuleFactory.createRule(this);
    }
}
