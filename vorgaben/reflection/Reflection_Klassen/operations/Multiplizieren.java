package operations;

@MathOperation(operation = "Multiplizieren")
public class Multiplizieren implements IOperation{

    @Override
    public int doOperation(int a, int b) {
        return a * b;
    }
}
