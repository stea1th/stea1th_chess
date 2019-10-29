package stea1th.chess.rules;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.Getter;
import stea1th.chess.figures.Figure;
import stea1th.chess.figures.FigureFactory;
import stea1th.chess.figures.King;
import stea1th.chess.to.Move;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class GameController {

    @Getter
    private final Map<Integer, Figure> figuresInGame = new HashMap<>();

    private Map<Figure, Map<Integer, Move>> allMoves = new HashMap<>();

    private static final AtomicInteger count = new AtomicInteger(100);

    public GameController() {
        init();
    }

    private void init() {
        Config config = ConfigFactory.parseResources("default_test.conf");
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

    public boolean moveFigure(Integer[] positions, boolean isWhite) {
        if (positions == null) return false;
        refreshMoves();
        setFiguresActive(isWhite);
        int from = positions[0];
        int to = positions[1];
        Figure figure = figuresInGame.get(from);
        boolean isAccepted = figure != null && figure.isActive();
        if (isAccepted) {
            Map<Integer, Move> moves = allMoves.get(figure);
            Move move = moves.get(to);
            isAccepted = move != null;
            if (isAccepted) {
                killEnemy(to);
                moveIt(from, to, figure);
            }
            scanForEnemyKing(isWhite);
        }
        return isAccepted;
    }

    private void scanForEnemyKing(boolean isWhite) {
        King enemyKing = getKings().get(!isWhite);
        refreshMoves();
        enemyKing.setAttacked(!ifKingAttacked(enemyKing).isEmpty());
    }

    private Map<Integer, Integer> getMyKingAttackedWays(King king) {
        Map<Integer, Integer> kingAttackerMoves = new HashMap<>();
        ifKingAttacked(king)
                .forEach((key, value) -> {
                    value.values().stream().filter(move -> value.get(king.getPosition()).getDirection() == move.getDirection())
                            .forEach(c -> kingAttackerMoves.put(c.getNewPosition(), c.getNewPosition()));
                    kingAttackerMoves.put(key.getPosition(), key.getPosition());
                });
        return kingAttackerMoves;
    }

    private Map<Figure, Map<Integer, Move>> ifKingAttacked(King king) {
        return allMoves.entrySet()
                .stream()
                .filter(i -> i.getKey().isWhite() != king.isWhite() && i.getValue().get(king.getPosition()) != null)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private void refreshMoves() {
        allMoves = collectAllMoves();
    }

    private void killEnemy(int to) {
        Figure anotherFigure = figuresInGame.get(to);
        killIt(to, anotherFigure);
    }

    private void moveIt(int from, int to, Figure figure) {
        figure.incrementMove();
        figuresInGame.remove(from);
        figure.setPosition(to);
        figuresInGame.put(to, figure);
    }

    private void killIt(int to, Figure figure) {
        if (figure != null) {
            figuresInGame.remove(to);
            figure.setAlive(false);
            figuresInGame.put(count.incrementAndGet(), figure);
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
        if (!king.isAttacked()) {
            figuresInGame.values()
                    .stream()
                    .filter(i -> i.isWhite() == isWhite && i.isAlive())
                    .forEach(i -> i.setActive(true));
        } else {
            Map<Integer, Integer> kingAttackerMoves = getMyKingAttackedWays(king);
            setMovesForAttackedKing(king);
            Map<Figure, Map<Integer, Move>> possibleMoves = new HashMap<>();
            allMoves.forEach((key, value) -> {
                Map<Integer, Move> moves = value
                        .entrySet()
                        .stream()
                        .filter(i -> kingAttackerMoves.get(i.getKey()) != null)
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                if (!moves.isEmpty()) {
                    key.setActive(true);
                    possibleMoves.put(key, moves);
                }
            });
            allMoves.putAll(possibleMoves);
        }
    }

    private void setMovesForAttackedKing(King king) {
        king.setActive(true);
        Map<Integer, Move> kingMoves = allMoves.get(king);
        List<Integer> movesToRemove = new ArrayList<>();
        allMoves.entrySet()
                .stream()
                .filter(i -> i.getKey().isWhite() != king.isWhite())
                .forEach(i -> kingMoves.entrySet().stream().filter(k -> i.getValue().get(k.getKey()) != null)
                        .forEach(k -> movesToRemove.add(k.getKey())));
        movesToRemove.forEach(kingMoves::remove);
        allMoves.replace(king, kingMoves);
    }
}
