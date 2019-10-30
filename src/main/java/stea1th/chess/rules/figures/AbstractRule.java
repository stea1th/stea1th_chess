package stea1th.chess.rules.figures;

import lombok.*;
import stea1th.chess.figures.Figure;
import stea1th.chess.rules.RestrictionRule;
import stea1th.chess.rules.enums.Direction;
import stea1th.chess.to.Move;

import java.util.*;

import static stea1th.chess.rules.RestrictionRule.*;
import static stea1th.chess.rules.enums.Direction.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = "mainFigure")
public abstract class AbstractRule implements Rule {

    @Setter
    @Getter
    Figure mainFigure;

    private final static Map<String, String> REGISTERED_RULES = new HashMap<>();

    private final Map<Integer, Move> allPossibleMoves = new HashMap<>();

    @Getter
    private final Set<Direction> directions;

    private Map<Integer, Figure> enemyNearby = new HashMap<>();

    @Setter
    private Map<Integer, Figure> figuresInGame = new HashMap<>();

    public Map<Integer, Move> getAllPossibleMoves() {
        addListToPossibleMoves(findAllPossibleMoves());
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

    void addToDirections(Direction dir) {
        directions.add(dir);
    }

    private static boolean isBetweenMinMax(Integer position) {
        return position != null && position > MIN.value && position < MAX.value;
    }

    private boolean isPieceOnTheWay(Integer position) {
        return figuresInGame.get(position) != null;
    }

    private boolean isSameColor(Integer position) {
        return figuresInGame.get(position).isWhite() == mainFigure.isWhite();
    }

    boolean isEnemyNearby(Integer position) {
        Figure figure = figuresInGame.get(position);
        boolean isNearby = figure != null && !figure.isWhite() == mainFigure.isWhite();
        if (isNearby)
            enemyNearby.put(position, figure);
        return isNearby;
    }

    private void clear(boolean clearThis) {
        if (clearThis)
            allPossibleMoves.clear();
    }

//    private void clear() {
//        clear(true);
//    }

    private List<Move> oneCellTurn(Integer position, Direction direction) {
        Integer tempPosition = getAdjoiningPosition(position, direction);
        return tempPosition == null || (isPieceOnTheWay(tempPosition)
                && (isSameColor(tempPosition) || (mainFigure.getNotation().equals("p")
                && (direction == NORTH || direction == SOUTH)))) ? Collections.emptyList() : Collections.singletonList(new Move(tempPosition, position, direction));
    }

    private List<Move> moreCellsTurn(Integer position, Direction direction) {
        List<Move> moves = new ArrayList<>();
        Integer tempPosition = position;
        while (isBetweenMinMax(tempPosition)) {
            tempPosition = getAdjoiningPosition(tempPosition, direction);
            if (tempPosition == null) return moves;
            if (isPieceOnTheWay(tempPosition)) {
                if (!isSameColor(tempPosition)) {
                    moves.add(new Move(tempPosition, position, direction));
                }
                return moves;
            }
            moves.add(new Move(tempPosition, position, direction));
        }
        return moves;
    }

    private static Integer getAdjoiningPosition(int position, Direction direction) {
        int result = position + direction.value;
        return
//                isInBorders(position, result) &&
                !isRestricted(position, direction) &&
                        isBetweenMinMax(result) ? result : null;
    }

    private static boolean isInBorders(int position, int result) {
        int factor = position / 8;
        return factor * 8 + 1 <= result && (factor + 1) * 8 >= result;
    }

    private void addListToPossibleMoves(List<Move> moves) {
        moves.forEach(i -> allPossibleMoves.put(i.getNewPosition(), i));
    }

    public List<Move> findAllPossibleMoves() {
        return findPossibleMoves(mainFigure.getPosition());
    }

    List<Move> findPossibleMoves(Integer position) {
        return findPossibleMoves(position, true);
    }

    List<Move> findPossibleMoves(Integer position, boolean clearThis) {
        clear(clearThis);
        return getMovesForDirections(position);
    }

    private List<Move> getMovesForDirections(Integer position) {
        return getMovesForDirections(position, directions);
    }

    List<Move> getMovesForDirections(Integer position, Set<Direction> myDirections) {
        List<Move> possibleMoves = new ArrayList<>();
        if (position != null) {
            myDirections.forEach(direction -> possibleMoves.addAll(mainFigure.isOneTurn() ? oneCellTurn(position, direction) : moreCellsTurn(position, direction)));
        }
        return possibleMoves;
    }
}
