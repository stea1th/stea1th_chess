package stea1th.chess.rules;

public class PawnRule extends AbstractRule {

    @Override
    public void register() {
        addToRegisteredRules("p", this.getClass().getName());
    }
}
