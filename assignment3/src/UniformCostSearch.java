package mibi.assignment3.src;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D;

public class UniformCostSearch implements Search {

  public LinkedList<Vector2D> search(Environment environment) {
    PriorityQueue<Node> priorityQueue = new PriorityQueue<>(new NodeComparator());
    HashSet<Vector2D> visited = new HashSet<>();

    priorityQueue.add(new Node(environment.start, 0, null));

    while (!priorityQueue.isEmpty()) {
        Node current = priorityQueue.poll();
        //System.out.println("\nEvaluating location: " + current.location.x + " " + current.location.y);

        if (current.location.equals(environment.goal)) {
          System.out.println("Solution found!");
          LinkedList<Vector2D> path = new LinkedList<>();
          Node temp = current;
          while (temp != null) {
            path.addFirst(temp.location);  // adding to front to backtrack the path
            temp = temp.parent;
          }
          return path;  // return path, which will be the solution
        }

        if (visited.contains(current.location)) {
            continue;
        }
        visited.add(current.location);

        ArrayList<Vector2D> fringe = generateFringe(current.location, environment, visited);
        for (Vector2D f : fringe) {
            double tentativeCost = current.cost + costToNode(current.location, f);
            priorityQueue.add(new Node(f, tentativeCost, current));
        }
    }
    return null;  // Goal not found.
}

    public String getName() {
        return "uniformcost";
    }

    public double costToNode(Vector2D curLocation, Vector2D n) {
        return Point2D.distance(curLocation.x, curLocation.y, n.x, n.y);
    }

    public ArrayList<Vector2D> generateFringe(Vector2D curLocation, Environment environment, HashSet<Vector2D> visited)
    {
        ArrayList<Vector2D> fringe = new ArrayList<Vector2D>();
        // for each polygon in env, for each vertex on the polygon, do you have to cross
        // any polygon sides to get to it?

        for (Polygon p : environment.obstacles) {

            // if v is on the same polygon as curLocation, add 2 nearest neighbors to fringe
            if (p.vertices.contains(curLocation)) {
                int targetIndex = p.vertices.indexOf(curLocation);
                int size = p.vertices.size();

                Vector2D leftNeighbor = p.vertices.get((targetIndex - 1 + size) % size);
                Vector2D rightNeighbor = p.vertices.get((targetIndex + 1) % size);

                if (!visited.contains(leftNeighbor)) {
                    fringe.add(leftNeighbor);
                }

                if (!visited.contains(rightNeighbor)) {
                    fringe.add(rightNeighbor);
                }

            } else {
                for (Vector2D v : p.vertices) {
                    if (v.equals(curLocation)) {
                        continue;
                    }

                    if (checkIntersection(curLocation, v, environment) && !visited.contains(v)) {
                        fringe.add(v);
                    }
                }
            }
        }

        if (checkIntersection(curLocation, environment.goal, environment)) {
            fringe.add(environment.goal);
        }

        //System.out.println("this fringe is: "); //could uncomment if you want to see the fringe values
        // for (int i = 0; i < fringe.size(); i++) {
        //     System.out.println(i + ". " + fringe.get(i).x + ", " + fringe.get(i).y);
        // }
        return fringe;
    }

    public boolean checkIntersection(Vector2D curLocation, Vector2D potentialLocation, Environment environment) {
        Vector2D v2;
        for (Polygon p : environment.obstacles) {
            for (int i = 0; i < p.sides; i++) {
                Vector2D v1 = p.vertices.get(i);

                if (i == p.sides - 1) {
                    v2 = p.vertices.get(0);
                } else {
                    v2 = p.vertices.get(i + 1);
                }

                if (v1.equals(curLocation) || v1.equals(potentialLocation) || v2.equals(curLocation)
                        || v2.equals(potentialLocation)) {
                    continue;
                }

                if (Line2D.linesIntersect(curLocation.x, curLocation.y, potentialLocation.x, potentialLocation.y, v1.x,
                        v1.y, v2.x, v2.y)) {
                    return false;
                }
            }
        }
        return true;
    }

    public class NodeComparator implements Comparator<Node> {
      @Override
      public int compare(Node node1, Node node2) {
        return Double.compare(node1.cost, node2.cost);
      }
    }
  }
