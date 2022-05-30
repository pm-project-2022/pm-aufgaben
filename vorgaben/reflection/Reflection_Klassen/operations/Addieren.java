package operations;

@MathOperation(operation = "Addieren")
public class Addieren implements IOperation{

    @Override
    public int doOperation(int a, int b) {
        return a + b;
    }
}
