package visitor_pattern;

import visitor_pattern.tree.Node;

public class Postorder implements INode{
    private Node root;

    public Postorder(Node root){
        this.root = root;
    }

    public Node getRoot() {
        return this.root;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
    
}
