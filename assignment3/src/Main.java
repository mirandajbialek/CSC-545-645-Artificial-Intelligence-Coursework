package mibi.assignment3.src;

import java.io.FileNotFoundException;
import java.util.LinkedList;

public class Main {
  public static void main(String[] args) throws FileNotFoundException {
    Environment env = new Environment();
    env.readFromFile("mibi/assignment3/src/output/environment.txt");
    System.out.println("Loaded an environment with " + env.obstacles.size() + " obstacles.");

    Search[] searches = new Search[]{
      new UniformCostSearch(),
      new AStarSearch()
    };

    for (Search search : searches) {
      System.out.println("-------------------------------------------------------");
      System.out.println("Attempting " + search.getName());
      LinkedList<Vector2D> result = search.search(env);
      if (result != null) {
        for (int i = 0; i < result.size(); i++) {
          System.out.println(i + ". " + result.get(i).x + ", " + result.get(i).y);
      }
      } else {
        System.out.println("No path found for " + search.getName());
      }
    }
  }
}
