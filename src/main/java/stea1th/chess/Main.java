package stea1th.chess;

import stea1th.chess.figures.AbstractFigure;
import stea1th.chess.figures.FigureFactory;
import stea1th.chess.helpers.ConsoleHelper;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<Boolean, String> players =  ConsoleHelper.createPlayers();
        ChessBoard board = new ChessBoard();
        board.play(players);
//        ConsoleHelper.printForWhiteNumbers();
//        FigureFactory.test();
//        AbstractFigure.test();
//        FigureFactory.getFigureNames().forEach(System.out::println);
    }
}
