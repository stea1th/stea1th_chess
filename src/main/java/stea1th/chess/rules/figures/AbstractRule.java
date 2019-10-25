package stea1th.chess.rules.figures;

import lombok.*;
import stea1th.chess.figures.Figure;
import stea1th.chess.rules.enums.Direction;
import stea1th.chess.to.Move;

import java.util.*;

import static stea1th.chess.rules.enums.Direction.MAX;
import static stea1th.chess.rules.enums.Direction.MIN;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public abstract class AbstractRule implements Rule {

    @Setter
    @Getter
    Figure mainFigure;

    private final static Map<String, String> REGISTERED_RULES = new HashMap<>();

    @Getter
    private final Map<String, Move> allPossibleMoves = new HashMap<>();

    @Getter
    private final Set<Direction> directions;

    @Setter
    private Map<Integer, Figure> figuresInGame = new HashMap<>();

    public Map<String, Move> getAllPossibleMoves() {
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

//    void addToPossibleMoves(Integer newPosition, Integer oldPosition, Direction direction) {
//        if (newPosition != null)
//            allPossibleMoves.put("" + newPosition, new Move(newPosition, oldPosition, direction));
//    }

    Move getFirstPossibleMove() {
        return allPossibleMoves.values().stream().findFirst().orElse(null);
    }

    void addToDirections(Direction dir) {
        directions.add(dir);
    }

    private static boolean isInBorders(Integer position) {
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
        return (figure != null && !figure.isWhite() == mainFigure.isWhite());
    }

    private void clear(boolean clearThis) {
        if (clearThis)
            allPossibleMoves.clear();
    }

    private void clear() {
        clear(true);
    }

    private List<Move> oneCellTurn(Integer position, Direction direction) {
        Integer tempPosition = getAdjoiningPosition(position, direction);
        return tempPosition == null || (isPieceOnTheWay(tempPosition) && (isSameColor(tempPosition) || mainFigure.getNotation().equals("p"))) ? Collections.emptyList() : Collections.singletonList(new Move(tempPosition, position, direction));
    }

    private List<Move> moreCellsTurn(Integer position, Direction direction) {
        List<Move> moves = new ArrayList<>();
        Integer tempPosition = position;
        while (isInBorders(tempPosition)) {
            tempPosition = getAdjoiningPosition(tempPosition, direction);
            if(tempPosition == null) return moves;
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
        return isInBorders(result) ? result : null;
    }

    public abstract boolean scanForPosition(int enemyKingPosition);

//    boolean scanOneCellTurn(int anotherPosition) {
//        oneCellTurn(mainFigure.getPosition());
//        return allPossibleMoves.get("" + anotherPosition) != null;
//    }
//
//    boolean scanMoreCellsTurn(int anotherPosition) {
//        moreCellsTurn(mainFigure.getPosition());
//        return allPossibleMoves.get("" + anotherPosition) != null;
//    }

    private void addListToPossibleMoves(List<Move> moves) {
        moves.forEach(i -> allPossibleMoves.put(String.valueOf(i.getNewPosition()), i));
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
            myDirections.forEach(direction -> {
                possibleMoves.addAll(mainFigure.isOneTurn() ? oneCellTurn(position, direction) : moreCellsTurn(position, direction));
            });
        }
        return possibleMoves;
    }
}
