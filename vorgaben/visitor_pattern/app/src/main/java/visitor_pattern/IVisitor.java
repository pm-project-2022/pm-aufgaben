package visitor_pattern;

import visitor_pattern.tree.Node;

public interface IVisitor {
    public void visit(Inorder inorder);
    public void visit(Postorder postorder);
    public void visit(Node node);
}
