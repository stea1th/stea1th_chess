package stea1th.chess.moves;

public class NordMove implements Move {

    @Override
    public int getAdjoiningPosition(int position) {
        return position - 8;
    }
}
