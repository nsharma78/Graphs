/*
 * BFS traversal
 */

package main.java;
import main.java.GraphUtils.GraphNode;

import java.util.*;

public class BFS {

    public static void main(String[] args) {
        GraphNode root = GraphUtils.createGraph();
        System.out.println("BFS Traversal");
        bfsTraversal(root);
    }

    private static void bfsTraversal(GraphNode root) {
        if (root == null)
            return;

        Queue<GraphNode> q = new LinkedList<>();
        Set<GraphNode> set = new HashSet<>();

        q.add(root);

        while (!q.isEmpty()) {
            GraphNode node = q.remove();
            node.visited = true;
            System.out.println(node);
            List<GraphNode> neighbors = node.neighbors;
            if (neighbors != null && !neighbors.isEmpty()) {
                for (GraphNode neighbor : neighbors)
                    if (!neighbor.visited && set.add(neighbor))
                        q.add(neighbor);
            }
        }
    }
}
