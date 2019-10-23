package stea1th.chess.rules;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.Getter;
import stea1th.chess.figures.Figure;
import stea1th.chess.figures.FigureFactory;

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
        if (figure != null) {
            figure.setFiguresInGame(figuresInGame);
            figuresInGame.remove(fromPosition);
            if (figure.move(positions[1])) {
                killEnemy(figure);
            }
            figuresInGame.put(figure.getPosition(), figure);
        }
        return figure != null;
    }

    private void killEnemy(Figure figure) {
        int newPosition = figure.getPosition();
        Figure anotherFigure = figuresInGame.get(newPosition);
        if (anotherFigure != null && anotherFigure.isWhite() != figure.isWhite()) {
            figuresInGame.remove(newPosition);
            anotherFigure.setAlive(false);
            figuresInGame.put(count.get(), anotherFigure);
        }
    }
}
