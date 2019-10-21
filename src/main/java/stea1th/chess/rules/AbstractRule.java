package stea1th.chess.rules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import stea1th.chess.enums.Direction;
import stea1th.chess.figures.Figure;
import stea1th.chess.moves.MoveFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static stea1th.chess.enums.Direction.MAX;
import static stea1th.chess.enums.Direction.MIN;

@Data
@AllArgsConstructor
public abstract class AbstractRule implements Rule {

    private final static Map<String, String> REGISTERED_RULES = new HashMap<>();

    @Getter
    private final Map<String, Integer> allPossibleMoves = new HashMap<>();

    @Getter
    private final Set<Direction> directions;

    public Map<String, Integer> getAllPossibleMoves(int position) {
        allPossibleMoves(position);
        return allPossibleMoves;
    }

    public abstract void register();

    void addToRegisteredRules(String key, String value) {
        REGISTERED_RULES.put(key, value);
    }

    @SuppressWarnings(value = "unchecked")
    static <T extends AbstractRule> T newInstance(Figure figure) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String clazzName = REGISTERED_RULES.get(figure.getNotation());
        return (T) Class.forName(clazzName != null ? clazzName : REGISTERED_RULES.get(" ")).newInstance();
    }

    public abstract void allPossibleMoves(int position);

    private void addToPossibleMoves(Integer position) {
        if (position != null)
            allPossibleMoves.put("" + position, position);
    }

    public static boolean isInBorders(Integer position) {
        return position != null && position > MIN.value && position < MAX.value;
    }

    private void clear() {
        allPossibleMoves.clear();
    }

    void oneCellTurn(Integer position) {
        clear();
        getDirections().forEach(i -> {
            addToPossibleMoves(MoveFactory.getAdjoiningPosition(position, i));
        });
    }

    void allCellsTurn(Integer position) {
        clear();
        getDirections().forEach(i -> {
            Integer tempPosition = position;
            while (isInBorders(tempPosition)) {
                tempPosition = MoveFactory.getAdjoiningPosition(tempPosition, i);
                addToPossibleMoves(tempPosition);
            }
        });
    }


}
