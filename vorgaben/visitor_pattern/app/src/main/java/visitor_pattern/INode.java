package visitor_pattern;

public interface INode {
    public void accept(IVisitor visitor);
}
