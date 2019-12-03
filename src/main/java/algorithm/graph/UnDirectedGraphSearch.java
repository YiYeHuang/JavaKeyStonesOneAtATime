package algorithm.graph;

import baseObj.NodeGraph;

import java.util.*;

public class UnDirectedGraphSearch {

    public static void dfsPrint(NodeGraph start) {
        Stack<NodeGraph> stack = new Stack<>();
        Set<NodeGraph> seen = new HashSet<>();

        stack.push(start);

        while (!stack.empty()) {
            NodeGraph curr = stack.pop();

            if (!seen.contains(curr)) {
                seen.add(curr);
                System.out.println(curr.val);
            }

            for (NodeGraph adjcent : curr.neighbors) {
                if (!seen.contains(adjcent)) {
                    stack.push(adjcent);
                }
            }
        }
    }

    public static void bfsPrint(NodeGraph start) {
        Queue<NodeGraph> queue = new LinkedList<>();
        Set<NodeGraph> seen = new HashSet<>();

        queue.add(start);

        while (!queue.isEmpty()) {
            NodeGraph curr = queue.poll();

            if (!seen.contains(curr)) {
                seen.add(curr);
                System.out.println(curr.val);
            }

            for (NodeGraph adjcent : curr.neighbors) {
                if (!seen.contains(adjcent)) {
                    queue.add(adjcent);
                }
            }
        }
    }
}
