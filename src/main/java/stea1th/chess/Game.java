package stea1th.chess;

import lombok.AllArgsConstructor;
import lombok.Data;
import stea1th.chess.helpers.TurnConverter;
import stea1th.chess.rules.GameController;

import java.util.Map;

import static stea1th.chess.helpers.ConsoleHelper.*;

@Data
@AllArgsConstructor
public class Game {

    private Map<Boolean, String> players;

    public void play() {
        GameController controller = new GameController(false);
        Boolean isWhite = true;
        while (!controller.isGameOver()) {
            for (Map.Entry<Boolean, String> entry : players.entrySet()) {
                if (isWhite.equals(entry.getKey())) {
                    printBoard(controller.getFiguresInGame(), isWhite);
                    controller.refresh(isWhite);
                    if (controller.isGameOver()) break;
                    isWhite = controller
                            .moveFigure(parsePositions(TurnConverter.convert(readFromConsole(entry.getValue())))) != isWhite;
                }
            }
        }
    }
}
