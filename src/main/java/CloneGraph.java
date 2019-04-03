/*
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 * a queue is used to do breath first traversal,
 * and a map is used to store the visited nodes. It is the map between original node and copied node.
 */

package main.java;
import java.util.*;
import main.java.GraphUtils.GraphNode;

public class CloneGraph {

    public static void main(String[] args) {
        GraphNode root = GraphUtils.createGraph();
        System.out.println("BFS Traversal Before Cloning");
        GraphUtils.bfsTraversal(root);

        GraphNode newRoot = cloneGraph(root);
        System.out.println("BFS Traversal After Cloning");
        GraphUtils.bfsTraversal(newRoot);
    }

    private static GraphNode cloneGraph(GraphNode root) {
        if (root == null)
            return null;

        Map<GraphNode, GraphNode> map = new HashMap<>();
        Queue<GraphNode> queue = new LinkedList<>();
        queue.add(root);

        GraphNode newRoot = new GraphNode(root.val);
        map.put(root, newRoot);

        while(!queue.isEmpty()) {
            GraphNode curr = queue.remove();
            List<GraphNode> currNeighbors = curr.neighbors;

            for(GraphNode aNeighbor : currNeighbors) {
                if(!map.containsKey(aNeighbor)) {
                    GraphNode copy = new GraphNode(aNeighbor.val);
                    map.put(aNeighbor,copy);
                    if(map.get(curr).neighbors == null) {
                        map.get(curr).neighbors = new ArrayList<GraphNode>();
                        map.get(curr).neighbors.add(copy);
                    }
                    else {
                        map.get(curr).neighbors.add(copy);
                    }
                    queue.add(aNeighbor);
                }
                else {
                    if(map.get(curr).neighbors == null) {
                        map.get(curr).neighbors = new ArrayList<GraphNode>();
                        map.get(curr).neighbors.add(aNeighbor);
                    }
                    else {
                        map.get(curr).neighbors.add(map.get(aNeighbor));
                    }

                }
            }
        }

        return newRoot;
    }
}
