package stea1th.chess.rules;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.Getter;
import org.apache.commons.lang3.SerializationUtils;
import stea1th.chess.figures.Figure;
import stea1th.chess.figures.FigureFactory;
import stea1th.chess.figures.King;
import stea1th.chess.to.Move;
import stea1th.chess.to.RestoreData;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static stea1th.chess.helpers.GameHelper.exist;
import static stea1th.chess.helpers.GameHelper.isSameColor;

public class GameController {

    @Getter
    private final Map<Integer, Figure> figuresInGame = new HashMap<>();

    private Map<Figure, Map<Integer, Move>> allMoves = new HashMap<>();

    private static final AtomicInteger count = new AtomicInteger(100);

    private RestoreData restoreData;

    @Getter
    private boolean isGameOver;

    public GameController(boolean isGameOver) {
        this.isGameOver = isGameOver;
        init();
    }

    private void init() {
        Config config = ConfigFactory.parseResources("kid_checkmate.conf");
        String[] configParams = new String[]{".white.positions", ".black.positions"};
        for (String param : configParams) {
            FigureFactory.getFigureNames().forEach(i -> {
                List<String> list = config.getStringList(i + param);
                if (!list.isEmpty()) {
                    list.forEach(s -> {
                        Figure figure = FigureFactory.createFigure(i, Integer.valueOf(s), param.contains("white"));
                        assert figure != null;
                        figuresInGame.put(figure.getPosition(), figure);
                    });
                }
            });
        }
    }

    private void setAllFiguresInactive() {
        figuresInGame.values().forEach(i -> i.setActive(false));
    }

    private Map<Figure, Map<Integer, Move>> collectAllMoves() {
        Map<Figure, Map<Integer, Move>> allMoves = new HashMap<>();
        for (Map.Entry<Integer, Figure> entry : figuresInGame.entrySet()) {
            Figure figure = entry.getValue();
            figure.setFiguresInGame(figuresInGame);
            allMoves.put(figure, figure.getRule().getAllPossibleMoves());
        }
        return allMoves;
    }

    public void refresh(boolean isWhite) {
        refreshMoves();
        setFiguresActive(isWhite);
    }

    public boolean moveFigure(Integer[] positions) {
        if (!exist(positions)) return false;
        return move(positions[0], positions[1]);
    }

    private boolean existActiveFigures(boolean isWhite) {
        return figuresInGame.values().stream().filter(i -> isSameColor(i, isWhite)).anyMatch(Figure::isActive);
    }

    private boolean scanForKing(boolean isWhite) {
        King king = getKings().get(isWhite);
        refreshMoves();
        boolean attacked = !checkKingAttacked(king).isEmpty();
        king.setAttacked(attacked);
        return attacked;
    }

    private Map<Integer, Integer> getMyKingAttackedWays(King king) {
        Map<Integer, Integer> kingAttackerMoves = new HashMap<>();
        checkKingAttacked(king)
                .forEach((key, value) -> {
                    value.values().stream().filter(move -> value.get(king.getPosition()).getDirection() == move.getDirection())
                            .forEach(c -> kingAttackerMoves.put(c.getNewPosition(), c.getNewPosition()));
                    kingAttackerMoves.put(key.getPosition(), key.getPosition());
                });
        return kingAttackerMoves;
    }

    private Map<Figure, Map<Integer, Move>> checkKingAttacked(King king) {
        return allMoves.entrySet()
                .stream()
                .filter(entry -> !isSameColor(entry.getKey(), king) && exist(entry.getValue().get(king.getPosition())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private void refreshMoves() {
        allMoves = collectAllMoves();
    }

    private boolean moveIsPossible(Figure figure, Figure anotherFigure, int to) {

        restoreData = SerializationUtils.clone(new RestoreData(figure, anotherFigure, to));

        if (!exist(anotherFigure)) {
            return tryMove(figure, to);
        } else if (!isSameColor(anotherFigure, figure)) {
            restoreData.setAnotherFigurePosition(killIt(anotherFigure, to));
            return tryMove(figure, to);
        }
        return false;
    }

    private boolean move(int from, int to) {
        Figure figure = getFigure(from);
        if (!exist(figure) || !figure.isActive()) return false;
        Figure anotherFigure = figuresInGame.get(to);
        if (exist(figure.getMove(to)) && moveIsPossible(figure, anotherFigure, to))
            return moveIt(getFigure(from), anotherFigure, to);
        return false;
    }

    private boolean tryMove(Figure figure, int to) {
        moveFigureToNewPosition(figure, null, to);
        boolean isTrue = scanForKing(figure.isWhite());
        restore(restoreData);
        return !isTrue;
    }

    private boolean moveIt(Figure figure, Figure anotherFigure, int to) {
        moveFigureToNewPosition(figure, anotherFigure, to);
        figure.incrementMove();
        scanForKing(!figure.isWhite());
        return true;
    }

    private int killIt(Figure figure, int to) {
        figuresInGame.remove(to);
        figure.setAlive(false);
        int anotherPosition = count.incrementAndGet();
        figuresInGame.put(anotherPosition, figure);
        return anotherPosition;
    }

    private void moveFigureToNewPosition(Figure figure, Figure anotherFigure, int to) {
        if (exist(anotherFigure)) killIt(anotherFigure, to);
        figuresInGame.remove(figure.getPosition());
        figure.setPosition(to);
        figuresInGame.put(to, figure);
    }

    private void restore(RestoreData restoreData) {
        Figure anotherFigure = restoreData.getAnotherFigure();
        Figure figure = restoreData.getFigure();
        int to = restoreData.getNewPosition();
        figuresInGame.remove(to);
        figuresInGame.put(figure.getPosition(), figure);
        if (exist(anotherFigure)) {
            System.out.println(restoreData.getAnotherFigurePosition());
            figuresInGame.remove(restoreData.getAnotherFigurePosition());
            anotherFigure.setAlive(true);
            figuresInGame.put(to, anotherFigure);
        }
    }

    private List<Figure> getFiguresByName(String name) {
        return figuresInGame.values().stream().filter(i -> i.getName().equals(name.toLowerCase())).collect(Collectors.toList());
    }

    private Map<Boolean, King> getKings() {
        return getFiguresByName("King").stream().collect(Collectors.toMap(Figure::isWhite, i -> (King) i));
    }

    private void setFiguresActive(boolean isWhite) {
        setAllFiguresInactive();
        King king = getKings().get(isWhite);
        if (!king.isAttacked())
            setActiveByColor(isWhite);
        else {
            setActiveForKingProtect(king);
            if (!existActiveFigures(isWhite)) isGameOver = true;
        }
    }

    private void setActiveByColor(boolean isWhite) {
        figuresInGame.values()
                .stream()
                .filter(i -> i.isWhite() == isWhite && i.isAlive())
                .forEach(i -> i.setActive(true));
    }

    private void setActiveForKingProtect(King king) {
        Map<Integer, Integer> kingAttackerMoves = getMyKingAttackedWays(king);
        setMovesForAttackedKing(king);
        setMovesForProtectedFigures(kingAttackerMoves, king);
    }

    private void setMovesForProtectedFigures(Map<Integer, Integer> kingAttackerMoves, King king) {
        Map<Figure, Map<Integer, Move>> possibleMoves = new HashMap<>();
        allMoves.entrySet()
                .stream()
                .filter(entry -> isSameColor(entry.getKey(), king) && entry.getKey().isAlive())
                .forEach(entry -> {
                    Map<Integer, Move> moves = entry.getValue()
                            .entrySet()
                            .stream()
                            .filter(moveEntry -> exist(kingAttackerMoves.get(moveEntry.getKey())))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    Figure figure = entry.getKey();
                    moves.remove(king.getPosition());
//                    moves = moves.entrySet().stream().filter(k -> moveIsPossible(figure, getFigure(k.getKey()), k.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    if (!moves.isEmpty()) {
                        figure.setActive(true);
                        figure.setAllPossibleMoves(moves);
                        possibleMoves.put(figure, moves);
                    }
                });
        allMoves.putAll(possibleMoves);
    }

    private void setMovesForAttackedKing(King king) {
        Map<Integer, Move> kingMoves = allMoves.get(king);
        Set<Integer> movesToRemove = new HashSet<>();
        allMoves.entrySet()
                .stream()
                .filter(i -> !isSameColor(i.getKey(), king))
                .forEach(i -> kingMoves.entrySet()
                        .stream()
                        .filter(k -> exist(i.getValue().get(k.getKey())))
                        .forEach(k -> movesToRemove.add(k.getKey())));
        kingMoves.forEach((k, v) -> {
            if (isFriendOnWay(king, v.getNewPosition())) {
                movesToRemove.add(v.getNewPosition());
            }
        });
        movesToRemove.forEach(kingMoves::remove);
        allMoves.replace(king, kingMoves);
        if (!kingMoves.isEmpty()) king.setActive(true);
    }

    private Figure getFigure(int position) {
        return figuresInGame.get(position);
    }

    private boolean isFriendOnWay(Figure figure, int to) {
        Figure anotherFigure = getFigure(to);
        return exist(anotherFigure) && isSameColor(figure, anotherFigure);
    }
}
