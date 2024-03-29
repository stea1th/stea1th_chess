package stea1th.chess.rules.figures;

import lombok.*;
import stea1th.chess.figures.Figure;
import stea1th.chess.rules.enums.Direction;
import stea1th.chess.to.Move;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import static stea1th.chess.helpers.GameHelper.exist;
import static stea1th.chess.helpers.GameHelper.isSameColor;
import static stea1th.chess.rules.RestrictionRule.isBetweenMinMax;
import static stea1th.chess.rules.RestrictionRule.isRestricted;
import static stea1th.chess.rules.enums.Direction.NORTH;
import static stea1th.chess.rules.enums.Direction.SOUTH;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = "mainFigure")
public abstract class AbstractRule implements Rule, Serializable {

    Figure mainFigure;

    private final static Map<String, String> REGISTERED_RULES = new HashMap<>();

    private Map<Integer, Move> allPossibleMoves = new HashMap<>();

    private Map<Integer, Figure> enemyNearby = new HashMap<>();

    @Setter
    private Map<Integer, Figure> figuresInGame = new HashMap<>();

    @Getter
    private final Set<Direction> directions;

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
        return (T) Class.forName(exist(clazzName) ? clazzName : REGISTERED_RULES.get(" ")).newInstance();
    }

    void addToDirections(Direction dir) {
        directions.add(dir);
    }

    private boolean isPieceOnTheWay(Integer position) {
        return exist(figuresInGame.get(position));
    }

    boolean isEnemyNearby(Integer position) {
        Figure figure = figuresInGame.get(position);
        boolean isNearby = exist(figure) && !isSameColor(figure, mainFigure);
        if (isNearby)
            enemyNearby.put(position, figure);
        return isNearby;
    }

    private void clear(boolean clearThis) {
        if (clearThis)
            allPossibleMoves.clear();
    }

    private List<Move> oneCellTurn(Integer position, Direction direction) {
        Integer tempPosition = getAdjoiningPosition(position, direction);
        return !exist(tempPosition) || (isPieceOnTheWay(tempPosition)
                && ((mainFigure.getNotation().equals("p")
                && (direction == NORTH || direction == SOUTH)))) ? Collections.emptyList() : Collections.singletonList(new Move(tempPosition, position, direction));
    }

    private List<Move> moreCellsTurn(Integer position, Direction direction) {
        List<Move> moves = new ArrayList<>();
        Integer tempPosition = position;
        while (isBetweenMinMax(tempPosition)) {
            tempPosition = getAdjoiningPosition(tempPosition, direction);
            if (!exist(tempPosition)) return moves;
            if (isPieceOnTheWay(tempPosition)) {
                moves.add(new Move(tempPosition, position, direction));
                return moves;
            }
            moves.add(new Move(tempPosition, position, direction));
        }
        return moves;
    }

    private static Integer getAdjoiningPosition(int position, Direction direction) {
        int result = position + direction.value;
        return
                !isRestricted(position, direction) &&
                        isBetweenMinMax(result) ? result : null;
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

    List<Move> getMovesForDirection(Integer position, Direction myDirection) {
        return getMovesForDirections(position, Collections.singleton(myDirection));
    }

    List<Move> getMovesForDirections(Integer position, Set<Direction> myDirections) {
        List<Move> possibleMoves = new ArrayList<>();
        if (exist(position)) {
            myDirections.forEach(direction -> possibleMoves.addAll(mainFigure.isOneTurn() ?
                    oneCellTurn(position, direction) : moreCellsTurn(position, direction)));
        }
        return possibleMoves;
    }

    public Move getMove(int to) {
        return this.allPossibleMoves.get(to);
    }
}
