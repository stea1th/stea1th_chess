package stea1th.chess;

public class Rook extends AbstractFigure {

    public Rook(String notation, String name, Integer position, boolean white) {
        super(notation, name, position, white);
    }

    public Rook(Integer position, boolean white) {
        this("R", "Rook", position, white);
    }

    @Override
    public void register() {

    }

    @Override
    public boolean move(int position) {
        return false;
    }

    @Override
    public boolean isTurnValid(int newPosition) {
        return false;
    }
}
