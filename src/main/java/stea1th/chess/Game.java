package stea1th.chess;

import lombok.Getter;
import stea1th.chess.pieces.Bishop;
import stea1th.chess.pieces.Piece;
import stea1th.chess.pieces.Pawn;

import java.util.HashMap;
import java.util.Map;

import static stea1th.chess.helpers.ConsoleHelper.*;

public class Game {

    @Getter
    private Map<Integer, Piece> figuresInGame = new HashMap<>();

    public void init() {
        Piece pawn = new Pawn(53, true);
        Piece pawn2 = new Pawn(54, true);
        Piece pawn3 = new Pawn(14, false);
        Piece bishop = new Bishop(59, true);
        pawn.register();
        pawn2.register();
        pawn3.register();
        bishop.register();
        figuresInGame.put(pawn.getPosition(), pawn);
        figuresInGame.put(pawn2.getPosition(), pawn2);
        figuresInGame.put(pawn3.getPosition(), pawn3);
        figuresInGame.put(bishop.getPosition(), bishop);
    }

    public void play() {
        while (true) {
            printBoard(figuresInGame);
            moveFigure(parsePositions(readFromConsole()));
        }
    }

    public boolean moveFigure(Integer[] positions) {
        Integer fromPosition = positions[0];
        Piece piece = figuresInGame.get(fromPosition);
        if(piece != null){
            piece.setFiguresInGame(figuresInGame);
            figuresInGame.remove(fromPosition);
            piece.move(positions[1]);
            figuresInGame.put(piece.getPosition(), piece);
            return true;
        }
        return false;
    }
}
