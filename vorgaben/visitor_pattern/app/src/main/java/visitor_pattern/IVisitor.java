package visitor_pattern;

import visitor_pattern.tree.Node;

public interface IVisitor {
    public void visit(Node node);
}
