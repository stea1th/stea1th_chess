package stea1th.chess;

import stea1th.chess.figures.AbstractFigure;
import stea1th.chess.figures.FigureFactory;
import stea1th.chess.helpers.ConsoleHelper;

public class Main {

    public static void main(String[] args) {
//        ChessBoard board = new ChessBoard();
//        board.play();
//        ConsoleHelper.printForWhiteNumbers();
        FigureFactory.test();
        AbstractFigure.test();
    }
}
