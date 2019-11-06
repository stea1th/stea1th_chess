package stea1th.chess;

import java.util.Map;

class ChessBoard {

    void play(Map<Boolean, String> players) {
        Game game = new Game(players);
        game.play();
    }
}
