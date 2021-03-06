/*
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1].
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 *
 * For example, given 2 and [[1,0]], there are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.
 * For another example, given 2 and [[1,0],[0,1]], there are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 */

package main.java;
import java.util.*;

public class Courses {

    public static void main(String[] args) {
        int courses = 2;
        int[][] pre = {{1,0}, {0,1}};
        System.out.println("Can finish all courses? " + canFinish(courses, pre));
    }

    private static boolean canFinish(int numCourses, int[][] pre) {
        if(pre == null){
            throw new IllegalArgumentException("illegal prerequisites array");
        }
        int len = pre.length;

        if(numCourses == 0 || len == 0){
            return true;
        }

        // counter for number of prerequisites
        int[] pCounter = new int[numCourses];
        for(int i=0; i<len; i++){
            pCounter[pre[i][0]]++;
        }

        //store courses that have no prerequisites
        LinkedList<Integer> queue = new LinkedList<Integer>();
        for(int i=0; i<numCourses; i++){
            if(pCounter[i]==0){
                queue.add(i);
            }
        }

        // number of courses that have no prerequisites
        int numNoPre = queue.size();

        while(!queue.isEmpty()){
            int top = queue.remove();
            for(int i=0; i<len; i++){
                // if a course's prerequisite can be satisfied by a course in queue
                if(pre[i][1]==top){
                    pCounter[pre[i][0]]--;
                    if(pCounter[pre[i][0]]==0){
                        numNoPre++;
                        queue.add(pre[i][0]);
                    }
                }
            }
        }

        return numNoPre == numCourses;
    }
}
