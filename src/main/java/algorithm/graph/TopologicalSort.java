package algorithm.graph;

import algorithm.graph.obj.Graph;
import algorithm.graph.obj.NodeGraph;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Stack;


public class TopologicalSort<T>  {

    /**
     * Main method to be invoked to do topological sorting.
     */
    public Deque<Vertex<T>> topSort(Graph<T> graph) {
        Deque<Vertex<T>> stack = new ArrayDeque<>();
        Set<Vertex<T>> visited = new HashSet<>();
        for (Vertex<T> vertex : graph.getAllVertex()) {
            if (visited.contains(vertex)) {
                continue;
            }
            topSortUtil(vertex,stack,visited);
        }
        return stack;
    }

    private void topSortUtil(Vertex<T> vertex, Deque<Vertex<T>> stack,
                             Set<Vertex<T>> visited) {
        visited.add(vertex);
        for(Vertex<T> childVertex : vertex.getAdjacentVertexes()){
            if(visited.contains(childVertex)){
                continue;
            }
            topSortUtil(childVertex,stack,visited);
        }
        stack.offerFirst(vertex);
    }



    static Stack<NodeGraph> stack = new Stack<>();
    // Recursive toplogical Sort
    public static void toplogicalSort(NodeGraph node)
    {
        List<NodeGraph> neighbours = node.neighbors;
        for (int i = 0; i < neighbours.size(); i++) {
            NodeGraph n=neighbours.get(i);
            if(n!=null && !n.visited)
            {
                toplogicalSort(n);
                n.visited=true;
            }
        }
        stack.push(node);
    }


    public static void main(String args[]){
        Graph<Integer> graph = new Graph<>(true);
        graph.addEdge(1, 3);
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);
        graph.addEdge(5, 6);
        graph.addEdge(6, 3);
        graph.addEdge(3, 8);
        graph.addEdge(8, 11);

        TopologicalSort<Integer> sort = new TopologicalSort<Integer>();
        Deque<Vertex<Integer>> result = sort.topSort(graph);
        while(!result.isEmpty()){
            System.out.println(result.poll());
        }


        // method 2
        TopologicalSort topological = new TopologicalSort();
        NodeGraph node40 =new NodeGraph(40);
        NodeGraph node10 =new NodeGraph(10);
        NodeGraph node20 =new NodeGraph(20);
        NodeGraph node30 =new NodeGraph(30);
        NodeGraph node60 =new NodeGraph(60);
        NodeGraph node50 =new NodeGraph(50);
        NodeGraph node70 =new NodeGraph(70);

        node40.addneighbours(node10);
        node40.addneighbours(node20);
        node10.addneighbours(node30);
        node20.addneighbours(node10);
        node20.addneighbours(node30);
        node20.addneighbours(node60);
        node20.addneighbours(node50);
        node30.addneighbours(node60);
        node60.addneighbours(node70);
        node50.addneighbours(node70);

        System.out.println("Topological Sorting Order:");
        topological.toplogicalSort(node40);

        // Print contents of stack
        Stack<NodeGraph> resultStack=topological.stack;
        while (resultStack.empty()==false)
            System.out.print(resultStack.pop() + " ");
    }
}
