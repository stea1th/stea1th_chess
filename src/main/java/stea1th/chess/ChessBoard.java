package stea1th.chess;

import java.util.Map;

public class ChessBoard {

    public void play(Map<Boolean, String> players) {
        Game game = new Game(players);
        game.play();
    }
}
