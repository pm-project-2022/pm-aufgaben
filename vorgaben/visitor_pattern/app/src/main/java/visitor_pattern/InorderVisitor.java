package visitor_pattern;

import java.util.ArrayList;
import java.util.List;
import visitor_pattern.tree.Node;

public class InorderVisitor implements IVisitor {
    private final List<String> tree = new ArrayList<>();

    @Override
    public void visit(Node node) {
        if(node == null){
            return;
        }

        visit(node.getLeftChild());

        this.tree.add("Kartenname: " + node.getData().getName() + ", Preis: " + node.getData().getPrice() + "\n");

        visit(node.getRightChild());
    }

    public String getInorder(){
         String returnString = "";

         for (String string : tree) {
            returnString += string;
         }

         return returnString;
    }
    
}
