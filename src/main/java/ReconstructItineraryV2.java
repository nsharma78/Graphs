/*
 * Given a list of tickets, find itinerary in order using the given list.
Example:
Input:
"Chennai" -> "Banglore"
"Bombay" -> "Delhi"
"Goa"    -> "Chennai"
"Delhi"  -> "Goa"
Output:
Bombay->Delhi, Delhi->Goa, Goa->Chennai, Chennai->Banglore,
It may be assumed that the input list of tickets is not cyclic and there is one ticket from every city except final destination.
 */

package main.java;
import java.util.*;

public class ReconstructItineraryV2 {

    public static void main(String[] args) {
        Map<String, String> tickets = new HashMap<String, String>();
        tickets.put("Chennai", "Banglore");
        tickets.put("Bombay", "Delhi");
        tickets.put("Goa", "Chennai");
        tickets.put("Delhi", "Goa");
        System.out.println("Itinerary: " + reconstructItinerary(tickets).toString());
    }

    private static Object reconstructItinerary(Map<String, String> tickets) {
        if(tickets == null)
            return Collections.emptyList();

        List<String> result = new ArrayList<>();

        //create reversemap
        Map<String, String> reverse = new HashMap<String, String>();
        for(Map.Entry<String, String> ticket : tickets.entrySet())
            reverse.put(ticket.getValue(), ticket.getKey());

        String from = null;
        //find starting point
        for(String ticket : tickets.keySet())
            if(!reverse.containsKey(ticket)) {
                from = ticket;
                result.add(from);
                break;
            }

        if(from == null)
            throw new IllegalArgumentException("Invalid start");

        String to = tickets.get(from);

        while(to != null){
            result.add(to);
            from = to;
            to = tickets.get(from);
        }
        return result;
    }
}
