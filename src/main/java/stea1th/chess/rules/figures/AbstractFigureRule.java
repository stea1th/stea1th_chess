package stea1th.chess.rules.figures;

import lombok.*;
import stea1th.chess.pieces.Piece;
import stea1th.chess.rules.enums.Direction;

import java.util.*;

import static stea1th.chess.rules.enums.Direction.MAX;
import static stea1th.chess.rules.enums.Direction.MIN;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public abstract class AbstractFigureRule implements FigureRule {

    @Setter
    private Piece mainPiece;

    private final static Map<String, String> REGISTERED_RULES = new HashMap<>();

    @Getter
    private final Map<String, Integer> allPossibleMoves = new HashMap<>();

    @Getter
    private final Set<Direction> directions;

    @Setter
    private Map<Integer, Piece> figuresInGame = new HashMap<>();

    public Map<String, Integer> getAllPossibleMoves(Piece piece) {
        allPossibleMoves(piece);
        return allPossibleMoves;
    }

    public abstract void register();

    void addToRegisteredRules(String key, String value) {
        REGISTERED_RULES.put(key, value);
    }

    @SuppressWarnings(value = "unchecked")
    static <T extends AbstractFigureRule> T newInstance(Piece piece) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String clazzName = REGISTERED_RULES.get(piece.getNotation());
        return (T) Class.forName(clazzName != null ? clazzName : REGISTERED_RULES.get(" ")).newInstance();
    }

    public abstract void allPossibleMoves(Piece piece);

    void addToPossibleMoves(Integer position) {
        if (position != null)
            allPossibleMoves.put("" + position, position);
    }

    void addToDirections(Direction dir) {
        directions.add(dir);
    }

    void addToDirections(Direction[] dirs) {
        directions.addAll(Arrays.asList(dirs));
    }

    private static boolean isInBorders(Integer position) {
        return position != null && position > MIN.value && position < MAX.value;
    }

    private boolean isPieceOnTheWay(Integer position) {
        return figuresInGame.get(position) != null;
    }

    private boolean isSameColor(Integer position) {
        return figuresInGame.get(position).isWhite() == mainPiece.isWhite();
    }

    private void clear() {
        allPossibleMoves.clear();
    }

    void oneCellTurn(Integer position) {
        clear();
        getDirections().forEach(i -> {
            Integer tempPosition = getAdjoiningPosition(position, i);
            addToPossibleMoves(isPieceOnTheWay(tempPosition) && (isSameColor(tempPosition) || mainPiece.getNotation().equals("p")) ? null : tempPosition);
        });
    }

    boolean isEnemyNearby(Integer position) {
        Piece piece = figuresInGame.get(position);
        return (piece != null && !piece.isWhite() == mainPiece.isWhite());
    }

    void allCellsTurn(Integer position) {
        clear();
        getDirections().forEach(i -> {
            Integer tempPosition = position;
            while (isInBorders(tempPosition)) {
                tempPosition = getAdjoiningPosition(tempPosition, i);
                if (isPieceOnTheWay(tempPosition)) {
                    addToPossibleMoves(isSameColor(tempPosition) ? null : tempPosition);
                    break;
                }
                addToPossibleMoves(tempPosition);
            }
        });
    }

    static Integer getAdjoiningPosition(int position, Direction direction) {
        int result = position + direction.value;
        return isInBorders(result) ? result : null;
    }


}
