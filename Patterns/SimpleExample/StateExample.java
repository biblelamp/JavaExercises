interface State {
    String getName();
    void freeze(StateContext context);
    void heat(StateContext context);
}

class SolidState implements State {
    private static final String NAME = "solid";

    public String getName() {
        return NAME;
    }

    public void freeze(StateContext context) {
        System.out.println("Nothing happens.");
    }

    public void heat(StateContext context) {
        context.setState(new LiquidState());
    }
}

class LiquidState implements State {
    private static final String NAME = "liquid";

    public String getName() {
        return NAME;
    }

    public void freeze(StateContext context) {
        context.setState(new SolidState());
    }

    public void heat(StateContext context) {
        context.setState(new GaseousState());
    }
}

class GaseousState implements State {
    private static final String NAME = "gaseous";

    public String getName() {
        return NAME;
    }

    public void freeze(StateContext context) {
        context.setState(new LiquidState());
    }

    public void heat(StateContext context) {
        System.out.println("Nothing happens.");
    }
}

class StateContext {
    private State state = new SolidState();

    public void freeze() {
        System.out.println("Freezing " + state.getName() + " substance...");
        state.freeze(this);
    }

    public void heat() {
        System.out.println("Heating " + state.getName() + " substance...");
        state.heat(this);
    }

    public void setState(State state) {
        System.out.println("Changing state to " + state.getName() + ".");
        this.state = state;
    }

    public State getState() {
        return state;
    }
}

public class StateExample {
    public static void main(String[] args) {
        StateContext context = new StateContext();
        context.heat();
        context.heat();
        context.heat();
        context.freeze();
        context.freeze();
        context.freeze();
    }
}