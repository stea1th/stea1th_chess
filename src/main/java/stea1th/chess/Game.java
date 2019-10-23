package stea1th.chess;

import stea1th.chess.helpers.TurnConverter;
import stea1th.chess.rules.GameController;

import static stea1th.chess.helpers.ConsoleHelper.*;

public class Game {

    public void play() {
        GameController controller = new GameController();
        boolean isWhite = true;
        while (true) {
            printBoard(controller.getFiguresInGame(), isWhite);
            isWhite = controller.moveFigure(parsePositions(TurnConverter.convert(readFromConsole()))) != isWhite;
        }
    }
}
