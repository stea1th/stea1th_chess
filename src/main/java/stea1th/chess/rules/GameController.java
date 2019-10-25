package stea1th.chess.rules;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.Getter;
import stea1th.chess.figures.Figure;
import stea1th.chess.figures.FigureFactory;
import stea1th.chess.figures.King;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class GameController {

    @Getter
    private Map<Integer, Figure> figuresInGame = new HashMap<>();

    private static final AtomicInteger count = new AtomicInteger(100);

    public GameController() {
        init();
    }

    private void init() {
        Config config = ConfigFactory.parseResources("default.conf");
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

    public boolean moveFigure(Integer[] positions) {
        if (positions == null) return false;
        Integer fromPosition = positions[0];
        Figure figure = figuresInGame.get(fromPosition);
        boolean isAccepted = figure != null && figure.isActive();
        if (isAccepted) {
            figure.setFiguresInGame(figuresInGame);
            figuresInGame.remove(fromPosition);
            isAccepted = figure.move(positions[1]);
            killEnemy(figure, isAccepted);
            figuresInGame.put(figure.getPosition(), figure);
            isEnemyKingAttacked(figure.isWhite());
        }
        return isAccepted;
    }

    private void killEnemy(Figure figure, boolean isAccepted) {
        if(isAccepted) {
            int newPosition = figure.getPosition();
            Figure anotherFigure = figuresInGame.get(newPosition);
            if (anotherFigure != null && anotherFigure.isWhite() != figure.isWhite()) {
                figuresInGame.remove(newPosition);
                anotherFigure.setAlive(false);
                figuresInGame.put(count.incrementAndGet(), anotherFigure);
            }
        }
    }

    public void setFiguresActive(boolean isWhite) {
        setAllFiguresInactive();
        figuresInGame.values()
                .stream()
                .filter(i-> i.isWhite() == isWhite && i.isAlive())
                .forEach(i-> i.setActive(true));
    }

    private void setAllFiguresInactive() {
        figuresInGame.values().forEach(i-> i.setActive(false));
    }

    public void setFiguresActiveToProtect(boolean isWhite) {
        setAllFiguresInactive();


    }

    private void isEnemyKingAttacked(boolean isWhite) {
        List<Figure> figures = new ArrayList<>(figuresInGame.values());
        Figure enemyKing =  figures.stream().filter(i-> i instanceof King && i.isWhite() != isWhite).findFirst().get();
        int kingPosition = enemyKing.getPosition();
        figures.forEach(i-> i.setFiguresInGame(figuresInGame));
        figures.stream().filter(i-> i.isActive() && i.getRule().scanForPosition(kingPosition)).forEach(i-> System.out.println(i.getName() + " -> " + i.getPosition()));
    }
}
