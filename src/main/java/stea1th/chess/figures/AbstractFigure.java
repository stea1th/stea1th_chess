package stea1th.chess.figures;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import stea1th.chess.rules.figures.FigureRuleFactory;
import stea1th.chess.rules.figures.Rule;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractFigure implements Figure {

    @Getter
    private String notation;
    private String view;
    private String name;
    private Integer position;
    private boolean white;
    private boolean alive;
    private boolean active;
    private int movesCount;
    private Rule rule;

    AbstractFigure(String notation, String name) {
        this.notation = notation;
        this.view = (white ? "w" : "b") + notation;
        this.name = name;
    }

    AbstractFigure(String notation, String name, Integer position, boolean white) {
        this.notation = notation;
        this.view = (white ? "w" : "b") + notation;
        this.name = name;
        this.position = position;
        this.white = white;
        this.alive = true;
        this.active = false;
        this.movesCount = 0;
        this.rule = loadRule();
    }

    private final static Map<String, String> REGISTERED_FIGURES = new HashMap<>();

    public void register() {
        addToRegisteredFigures(this.getName(), this.getClass().getName());
    }

    @SuppressWarnings(value = "unchecked")
    static <T extends AbstractFigure> T newInstance(String key, Integer position, boolean white) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        String clazzName = REGISTERED_FIGURES.get(key);
        return (T) Class.forName(clazzName).getConstructor(Integer.class, boolean.class).newInstance(position, white);
    }

    static Set<String> getFigureNames() {
        return REGISTERED_FIGURES.keySet();
    }

    public boolean move(int position) {
        boolean isValid = isTurnValid(position);
        if (isValid) {
            this.setPosition(position);
            movesCount++;
        }
        return isValid;
    }

    private void addToRegisteredFigures(String key, String value) {
        REGISTERED_FIGURES.put(key, value);
    }

    private boolean isTurnValid(int newPosition) {
        return rule.getAllPossibleMoves(this).get(String.valueOf(newPosition)) != null;
    }

    private Rule loadRule() {
        return FigureRuleFactory.createRule(this);
    }

    public void setFiguresInGame(Map<Integer, Figure> figuresInGame) {
        rule.setFiguresInGame(figuresInGame);
    }
}
