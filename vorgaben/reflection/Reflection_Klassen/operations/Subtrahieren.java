package operations;

@MathOperation(operation = "Subtrahieren")
public class Subtrahieren implements IOperation {

    @Override
    public int doOperation(int a, int b) {
        return a - b;
    }
}
