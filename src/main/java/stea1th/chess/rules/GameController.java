package stea1th.chess.rules;

import lombok.Getter;
import stea1th.chess.pieces.Bishop;
import stea1th.chess.pieces.Pawn;
import stea1th.chess.pieces.Piece;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class GameController {

    @Getter
    private Map<Integer, Piece> figuresInGame = new HashMap<>();

    private static final AtomicInteger count = new AtomicInteger(100);

    public GameController() {
        init();
    }

    public void init() {
        Piece pawn = new Pawn(53, true);
        Piece pawn2 = new Pawn(54, true);
        Piece pawn3 = new Pawn(45, false);
        Piece bishop = new Bishop(59, true);
        figuresInGame.put(pawn.getPosition(), pawn);
        figuresInGame.put(pawn2.getPosition(), pawn2);
        figuresInGame.put(pawn3.getPosition(), pawn3);
        figuresInGame.put(bishop.getPosition(), bishop);
    }

    public boolean moveFigure(Integer[] positions) {
        Integer fromPosition = positions[0];
        Piece piece = figuresInGame.get(fromPosition);
        if(piece != null){
            piece.setFiguresInGame(figuresInGame);
            figuresInGame.remove(fromPosition);
            if(piece.move(positions[1])) {
                killEnemy(piece);
            }
            figuresInGame.put(piece.getPosition(), piece);
            return true;
        }
        return false;
    }

    private void killEnemy(Piece piece) {
        int newPosition = piece.getPosition();
        Piece anotherPiece = figuresInGame.get(newPosition);
        if(anotherPiece != null && anotherPiece.isWhite() != piece.isWhite()) {
            figuresInGame.remove(newPosition);
            anotherPiece.setAlive(false);
            figuresInGame.put(count.addAndGet(anotherPiece.getPosition()), anotherPiece);
        }
    }
}
