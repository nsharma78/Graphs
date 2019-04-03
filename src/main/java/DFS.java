/*
 * DFS traversal
 */

package main.java;
import main.java.GraphUtils.GraphNode;

import java.util.*;

public class DFS {

    public static void main(String[] args) {
        GraphNode root = GraphUtils.createGraph();
        System.out.println("DFS Traversal");
        dfsTraversal(root);
    }

    private static void dfsTraversal(GraphNode node) {
        if (node == null)
            return;

        node.visited = true;
        System.out.println(node);

        List<GraphNode> neighbors = node.neighbors;
        if (neighbors != null && !neighbors.isEmpty()) {
            for (GraphNode neighbor : neighbors)
                    if (!neighbor.visited)
                        dfsTraversal(neighbor);
        }
    }

}
