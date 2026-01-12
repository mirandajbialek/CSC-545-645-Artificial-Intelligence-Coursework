package mibi.assignment3.src;

import java.util.LinkedList;
import java.util.ArrayList;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class AStarSearch implements Search {

    public LinkedList<Vector2D> search(Environment environment) {
        LinkedList<Vector2D> solution = new LinkedList<Vector2D>();
        boolean found = false;
        Vector2D curLocation = environment.start;
        ArrayList<Vector2D> fringe;
        Vector2D minSumLocation = environment.start;

        double curSum = 0;

        solution.add(curLocation); // adding starting position to solution

        while (!found) {
            double minSum = Integer.MAX_VALUE;
            //System.out.println("\nCurrent location: " + curLocation.x + " " + curLocation.y);

            // check if node is the goal
            if (curLocation.equals(environment.goal)) {
                System.out.println("Solution found! Solution path is: ");
                found = true;
                return solution;
            }

            // find most optimal neighbor vertex to move to
            fringe = generateFringe(curLocation, environment, solution);
            for (Vector2D v : fringe) {
                curSum = costToNode(curLocation, v) + estCostToGoal(v, environment.goal);
                if (curSum < minSum) {
                    minSum = curSum;
                    minSumLocation = v;
                }
            }
            //System.out.println("Chosen step is vertex: " + minSumLocation.x + " " + minSumLocation.y);
            curLocation = minSumLocation;
            solution.add(minSumLocation);
        }
        return solution;
    }

    public String getName() {
        return "astar";
    }

    public double costToNode(Vector2D curLocation, Vector2D n) {
        return Point2D.distance(curLocation.x, curLocation.y, n.x, n.y);
    }

    // admissible heuristic to estimate cost: using SLD from node to goal to
    // estimate cost
    public double estCostToGoal(Vector2D n, Vector2D goal) {
        return Point2D.distance(n.x, n.y, goal.x, goal.y);
    }

    public ArrayList<Vector2D> generateFringe(Vector2D curLocation, Environment environment,
            LinkedList<Vector2D> solution) {
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

                if (!solution.contains(leftNeighbor)) {
                    fringe.add(leftNeighbor);
                }

                if (!solution.contains(rightNeighbor)) {
                    fringe.add(rightNeighbor);
                }

            } else {
                for (Vector2D v : p.vertices) {
                    if (v.equals(curLocation)) {
                        continue;
                    }

                    if (checkIntersection(curLocation, v, environment) && !solution.contains(v)) {
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
}
