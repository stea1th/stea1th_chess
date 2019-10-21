package stea1th.chess.pieces;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import stea1th.chess.rules.figures.FigureRule;
import stea1th.chess.rules.figures.FigureRuleFactory;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractPiece implements Piece {

    @Getter
    private String notation;
    private String name;
    private Integer position;
    private boolean white;
    private boolean alive;
    private int movesCount;
    private FigureRule figureRule;

    AbstractPiece(String notation, String name, Integer position, boolean white) {
        this.notation = notation;
        this.name = name;
        this.position = position;
        this.white = white;
        this.alive = true;
        this.movesCount = 0;

    }

    private final static Map<String, String> REGISTERED_FIGURES = new HashMap<>();

    public void register() {
        this.figureRule = loadRule();
    }

    public boolean move(int position){
        boolean isValid = isTurnValid(position);
        if (isValid)
            this.setPosition(position);
        return isValid;
    }

    private boolean isTurnValid(int newPosition) {
        return figureRule.getAllPossibleMoves(this).get(String.valueOf(newPosition)) != null;
    }

    private FigureRule loadRule() {
        return FigureRuleFactory.createRule(this);
    }

    public void setFiguresInGame(Map<Integer, Piece> figuresInGame) {
        figureRule.setFiguresInGame(figuresInGame);
    }
}
