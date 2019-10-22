package stea1th.chess;

import stea1th.chess.rules.GameController;

import static stea1th.chess.helpers.ConsoleHelper.*;

public class Game {

    public void play() {
        GameController controller = new GameController();
        while (true) {
            printBoard(controller.getFiguresInGame());
            controller.moveFigure(parsePositions(readFromConsole()));
        }
    }

}
