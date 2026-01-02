package mibi.assignment3.src;

public class Node implements Comparable<Node> {
    public Vector2D location;  // Location in the grid
    public double cost;        // Cost to reach this node from the start node
    public Node parent;  // Parent node for backtracking
    
    // Constructor
    public Node(Vector2D location, double cost, Node parent) {
        this.location = location;
        this.cost = cost;
        this.parent = parent;
    }

    // For ordering nodes in PriorityQueue
    @Override
    public int compareTo(Node other) {
        return Double.compare(this.cost, other.cost);
    }
}