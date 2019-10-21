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

@Data
@AllArgsConstructor
public abstract class AbstractRule implements Rule {

    private final static Map<String, String> REGISTERED_RULES = new HashMap<>();

    @Getter
    private final Map<String, Integer> allPossibleMoves = new HashMap<>();

    private final Set<Direction> directions;

    public Map<String, Integer> getAllPossibleMoves(int position) {
        allPossibleMoves(position);
        return allPossibleMoves;
    }

    public abstract void register();

    public void addToRegisteredRules(String key, String value) {
        REGISTERED_RULES.put(key, value);
    }

    @SuppressWarnings(value = "unchecked")
    public static <T extends AbstractRule> T newInstance(Figure figure) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String clazzName = REGISTERED_RULES.get(figure.getNotation());
        return (T) Class.forName(clazzName != null ? clazzName : REGISTERED_RULES.get(" ")).newInstance();
    }

    public void allPossibleMoves(int position) {
        directions.forEach(i-> addToPossibleMoves(MoveFactory.getAdjoiningPosition(position, i)));
    }

    void addToPossibleMoves(int position) {
        allPossibleMoves.put("" + position, position);
    }



}
