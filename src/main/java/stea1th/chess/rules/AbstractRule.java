package stea1th.chess.rules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import stea1th.chess.figures.Figure;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public abstract class AbstractRule implements Rule {

    private final static Map<String, String> REGISTERED_RULES = new HashMap<>();

    @Getter
    private final Map<String, Integer> allPossibleMoves = new HashMap<>();

    public abstract void register();

    public void addToRegisteredRules(String key, String value) {
        REGISTERED_RULES.put(key, value);
    }

    @SuppressWarnings(value = "unchecked")
    public static <T extends AbstractRule> T newInstance(Figure figure) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String clazzName = REGISTERED_RULES.get(figure.getNotation());
        return (T) Class.forName(clazzName != null ? clazzName : REGISTERED_RULES.get(" ")).newInstance();
    }

    abstract void allPossibleMoves(int position);

    void addToPossibleMoves(int position) {
        allPossibleMoves.put("" + position, position);
    }



}
