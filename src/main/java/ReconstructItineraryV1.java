/*
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order.
 * All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
	Note:
	If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.
	For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
	All airports are represented by three capital letters (IATA code).
	You may assume all tickets form at least one valid itinerary.

	Example 1:
	tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
	Return ["JFK", "MUC", "LHR", "SFO", "SJC"].

	Example 2:
	tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
	Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
	Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.
 */

package main.java;
import java.util.*;

public class ReconstructItineraryV1 {

    static Map<String, PriorityQueue<String>> map = new HashMap<>();
    static List<String> result = new LinkedList<>();

    public static void main(String[] args) {
        //String[][] tickets = { {"MUC", "LHR"}, {"JFK", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"} };
        String[][] tickets = { {"JFK","SFO"}, {"JFK","ATL"}, {"SFO","ATL"}, {"ATL","JFK"}, {"ATL","SFO"} };
        System.out.println("Itinerary: " + reconstructItinerary(tickets).toString());
    }

    private static List<String> reconstructItinerary(String[][] tickets) {
        if(tickets == null)
            return Collections.emptyList();

        for(String[] ticket : tickets) {
            if(!map.containsKey(ticket[0])) {
                PriorityQueue<String> pq = new PriorityQueue<>();
                map.put(ticket[0], pq);
            }
            map.get(ticket[0]).add(ticket[1]);
        }

        dfs("JFK");
        return result;
    }

    private static void dfs(String s) {
        PriorityQueue<String> q = map.get(s);

        while (q != null && !q.isEmpty()) {
            dfs(q.poll());
        }

        ((LinkedList<String>) result).addFirst(s);
    }
}
