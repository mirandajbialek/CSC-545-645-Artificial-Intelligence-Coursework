package mibi.assignment2;

import java.util.ArrayList;

public class Node {
    public ArrayList<Node> children;
    public State state;

    public Node(State s){
        this.children = new ArrayList<>();
        this.state = s;
    }

    public void addChild(Node child) {
        children.add(child);
    }

    public int calculateDepth() {
        return calculateDepthRecursive(this, 0);
    }

    private static int calculateDepthRecursive(Node node, int currentDepth) {
        // Base case: If the node is null, return -1 (node not found).
        if (node == null) {
            return -1;
        }

        // Recursive case: Calculate depth for each child node.
        int maxChildDepth = -1;
        for (Node child : node.children) {
            int childDepth = calculateDepthRecursive(child, currentDepth + 1);
            maxChildDepth = Math.max(maxChildDepth, childDepth);
        }

        // Return the maximum child depth plus one (depth of the current node).
        return maxChildDepth + 1;
    }
}
