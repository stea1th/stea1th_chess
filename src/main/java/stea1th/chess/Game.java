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
        GameController controller = new GameController();
        Boolean isWhite = true;
        while (true) {
            for (Map.Entry<Boolean, String> entry : players.entrySet()) {
                if (isWhite.equals(entry.getKey())) {
                    printBoard(controller.getFIGURES_IN_GAME(), isWhite);
//                    printBoard();
                    isWhite = controller.moveFigure(parsePositions(TurnConverter.convert(readFromConsole(entry.getValue()))), isWhite) != isWhite;
                }
            }
        }
    }
}
