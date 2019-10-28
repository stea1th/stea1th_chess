package stea1th.chess.rules;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.Getter;
import stea1th.chess.figures.Figure;
import stea1th.chess.figures.FigureFactory;
import stea1th.chess.to.Move;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class GameController {

    @Getter
    private final Map<Integer, Figure> FIGURES_IN_GAME = new HashMap<>();

    private Map<Figure, Map<Integer, Move>> ALL_MOVES = new HashMap<>();

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
                        FIGURES_IN_GAME.put(figure.getPosition(), figure);
                    });
                }
            });
        }
    }

    private void setAllFiguresInactive() {
        FIGURES_IN_GAME.values().forEach(i -> i.setActive(false));
    }

    private Map<Figure, Map<Integer, Move>> collectAllMoves() {
        Map<Figure, Map<Integer, Move>> allMoves = new HashMap<>();
        for (Map.Entry<Integer, Figure> entry : FIGURES_IN_GAME.entrySet()) {
            Figure figure = entry.getValue();
            figure.setFiguresInGame(FIGURES_IN_GAME);
            allMoves.put(figure, figure.getRule().getAllPossibleMoves());
        }
        return allMoves;
    }

    public boolean moveFigure(Integer[] positions, boolean isWhite) {
        if (positions == null) return false;
        setFiguresActive(isWhite);
        ALL_MOVES = collectAllMoves();
        int from = positions[0];
        int to = positions[1];
        Figure figure = FIGURES_IN_GAME.get(from);
        boolean isAccepted = figure != null && figure.isActive();
        if (isAccepted) {
            Map<Integer, Move> moves = ALL_MOVES.get(figure);
            Move move = moves.get(to);
            isAccepted = move != null;
            if (isAccepted) {
                killEnemy(to);
                moveIt(from, to, figure);
            }
        }
        return isAccepted;
    }

    private void killEnemy(int to) {
        Figure anotherFigure = FIGURES_IN_GAME.get(to);
        killIt(to, anotherFigure);
    }

    private void moveIt(int from, int to, Figure figure) {
        figure.incrementMove();
        FIGURES_IN_GAME.remove(from);
        figure.setPosition(to);
        FIGURES_IN_GAME.put(to, figure);
    }

    private void killIt(int to, Figure figure) {
        if (figure != null) {
            FIGURES_IN_GAME.remove(to);
            figure.setAlive(false);
            FIGURES_IN_GAME.put(count.incrementAndGet(), figure);
        }
    }

    private List<Figure> getFiguresByName(String name) {
        return FIGURES_IN_GAME.values().stream().filter(i -> i.getName().equals(name.toLowerCase())).collect(Collectors.toList());
    }

    private Map<Boolean, Figure> getKings() {
        Map<Boolean, Figure> kingsPositions = new HashMap<>();
        getFiguresByName("King").forEach(i -> kingsPositions.put(i.isWhite(), i));
        return kingsPositions;
    }

    private void setFiguresActive(boolean isWhite) {
        setAllFiguresInactive();
        Figure king = getKings().get(isWhite);
        Map<Integer, Integer> kingAttackerMoves = getKingAttackedWays(king);
        if(kingAttackerMoves.isEmpty()) {
            FIGURES_IN_GAME.values()
                    .stream()
                    .filter(i -> i.isWhite() == isWhite && i.isAlive())
                    .forEach(i -> i.setActive(true));
        } else {
            System.out.println("King Attacked!!!!!");
        }

    }

    private Map<Integer, Integer> getKingAttackedWays(Figure king) {
        Map<Integer, Integer> kingAttackerMoves = new HashMap<>();
        ALL_MOVES.entrySet()
                .stream()
                .filter(i -> i.getKey().isWhite() != king.isWhite() && i.getValue().get(king.getPosition()) != null)
                .forEach(i-> {
                    i.getValue().values().stream().filter(move -> i.getValue().get(king.getPosition()).getDirection() == move.getDirection())
                            .forEach(c-> kingAttackerMoves.put(c.getNewPosition(), c.getNewPosition()));
                });
        return kingAttackerMoves;
    }


//    public boolean doMovement(boolean isAccepted, int fromPosition, int toPosition) {
//        if (isAccepted) {
////            FIGURES_IN_GAME.remove(fromPosition);
//            Map<String, Move> moves = ALL_MOVES.get
//
//        }
//    }

//    public void setFiguresActiveToProtect(boolean isWhite) {
//        setAllFiguresInactive();
//
//
//    }

//    private void isEnemyKingAttacked(boolean isWhite) {
//        List<Figure> figures = new ArrayList<>(figuresInGame.values());
//        Figure enemyKing =  figures.stream().filter(i-> i instanceof King && i.isWhite() != isWhite).findFirst().get();
//        int kingPosition = enemyKing.getPosition();
//        figures.forEach(i-> i.setFiguresInGame(figuresInGame));
//        figures.stream().filter(i-> i.isActive() && i.getRule().scanForPosition(kingPosition)).forEach(i-> System.out.println(i.getName() + " -> " + i.getPosition()));
//    }
}
