/*
 * Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed edge uv,
 * vertex u comes before v in the ordering. Topological Sorting for a graph is not possible if the graph is not a DAG.
 * For example, a topological sorting of the following graph is “5 4 2 3 1 0”. There can be more than one topological sorting for a graph.
 * For example, another topological sorting of the following graph is “4 5 2 3 1 0”.
 * The first vertex in topological sorting is always a vertex with in-degree as 0 (a vertex with no in-coming edges).
 *
 */
package main.java;

import java.util.LinkedList;
import java.util.Stack;

public class TopologicalSortDepResolving {

    static class DAG {

        private int V;   // No. of vertices
        private LinkedList<Integer> adj[]; // Adjacency List

        @SuppressWarnings("unchecked")
        public DAG(int v) {
            V = v;
            adj = new LinkedList[v];
            for (int i = 0; i < v; ++i)
                adj[i] = new LinkedList<Integer>();
        }

        // Function to add an edge into the graph
        void addEdge(int v,int w) {
            adj[v].add(w);
        }

        public void topologicalSort() {
            Stack<Integer> stack = new Stack<>();
            boolean visited[] = new boolean[V];

            for (int i = 0; i < V; i++)
                if (visited[i] == false)
                    topologicalSortUtil(i, visited, stack);

            // Print contents of stack
            while (!stack.isEmpty())
                System.out.print(stack.pop() + " ");

        }

        private void topologicalSortUtil(int v, boolean[] visited, Stack<Integer> stack) {
            // Mark the current node as visited.
            visited[v] = true;

            while (!adj[v].isEmpty())
            {
                int i = adj[v].remove();
                if (!visited[i])
                    topologicalSortUtil(i, visited, stack);
            }

            // Push current vertex to stack which stores result
            stack.push(new Integer(v));

        }

    }

    public static void main(String args[]) {

        DAG g = new DAG(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);

        System.out.println("Following is a Topological " +
                "sort of the given graph");
        g.topologicalSort();
    }

}
