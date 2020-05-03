package leetcode.graph;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import leetcode.tag.level.Medium;
import leetcode.tag.type.Graph;

/*
207. Course Schedule

There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:

Input: 2, [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
             To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: 2, [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take.
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.

Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices.
Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.



210. Course Schedule II

There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs,
return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them.
If it is impossible to finish all courses, return an empty array.

Example 1:

Input: 2, [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
             course 0. So the correct course order is [0,1] .
Example 2:

Input: 4, [[1,0],[2,0],[3,1],[3,2]]
Output: [0,1,2,3] or [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
             courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
             So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices.
Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
 */

@Medium
@Graph
public class CourseSchedule {

  public static int[] findOrder(int numCourses, int[][] prerequisites) {
    int[] incLinkCounts = new int[numCourses];
    List<List<Integer>> edges = new ArrayList<>(numCourses);

    // build graph
    int n = incLinkCounts.length;
    while (n-- > 0) {
      edges.add(new ArrayList<>());
    }
    // build edges
    for (int[] edge : prerequisites) {
      int toNodeId = edge[0];
      incLinkCounts[toNodeId]++;

      // this is a directed graph, build edge with from node point to to node
      edges.get(edge[1]).add(edge[0]);
    }

    // topological sort bfs
    return solveByBFS(incLinkCounts, edges);
  }

  private static int[] solveByBFS(int[] incLinkCounts, List<List<Integer>> edges){
    int[] order = new int[incLinkCounts.length];

    // queue for BFS, which will only hold courses currently upon which no other courses depend
    Queue<Integer> toVisit = new ArrayDeque<>();

    for (int i = 0; i < incLinkCounts.length; i++) {
      // start from courses upon which no other courses depend. These courses should come last in the order list
      if (incLinkCounts[i] == 0) {
        toVisit.offer(i);
      }
    }

    // index counter
    int courseTaken = 0;

    while (!toVisit.isEmpty()) {
      int from = toVisit.poll();
      order[courseTaken] = from;
      courseTaken++;

      for (int to : edges.get(from)) {
        incLinkCounts[to]--;
        if (incLinkCounts[to] == 0) {
          // if the to node has dependency resolved, added to queue
          toVisit.offer(to);
        }
      }
    }

    return courseTaken == incLinkCounts.length ? order : new int[0];
  }

  /****
   * DFS =============================================================================
   *
   */
  public static int[] findOrder_DFS(int numCourses, int[][] prerequisites) {
    int[] incLinkCounts = new int[numCourses];
    List<List<Integer>> edges = new ArrayList<>(numCourses);

    // build graph
    int n = incLinkCounts.length;
    while (n-- > 0) {
      edges.add(new ArrayList<>());
    }
    // build edges
    for (int[] edge : prerequisites) {
      int toNodeId = edge[0];
      incLinkCounts[toNodeId]++;

      // this is a directed graph, build edge with from node point to to node
      edges.get(edge[1]).add(edge[0]);
    }

    // solve by dfs
    boolean[] visited = new boolean[numCourses];
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < numCourses; i++) {
      if (!solveByDFS(edges, i, stack, visited, new boolean[numCourses])) {
        return new int[0];
      }
    }

    int i = 0;
    int[] result = new int[numCourses];
    while (!stack.isEmpty()) {
      result[i++] = stack.pop();
    }
    return result;
  }

  private static boolean solveByDFS(List<List<Integer>> edges, int v, Stack<Integer> stack, boolean[] visited, boolean[] isCycle) {
    if (visited[v]) return true;
    if (isCycle[v]) return false;

    isCycle[v] = true;
    for (Integer u : edges.get(v)) {
      // dfs until it hits a cycle
      if (!solveByDFS(edges, u, stack, visited, isCycle)) {
        return false;
      }
    }

    visited[v] = true;
    stack.push(v);
    return true;
  }


  public static void main(String[] args) {
    int[][] test = {{1,0},{2,0},{3,1},{3,2},{0, 3}};

    findOrder_DFS(4, test);
  }
}
