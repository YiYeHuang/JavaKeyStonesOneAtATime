package algorithm.graph.scc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

public class Graph {
    private int size;   // No. of vertices
    private ArrayList<Integer>[] vertices; //Adjacency List

    PriorityQueue<Integer> queue = new PriorityQueue<>((x, y) -> x - y);

    //Constructor
    public Graph(int size) {
        this.size = size;
        this.vertices = new ArrayList[size + 1];
        for (int i=0; i< size + 1; ++i) {
            vertices[i] = new ArrayList<>();
        }
    }

    //Function to add an edge into the graph
    public void addEdge(int v, int w) {
        // System.out.println("add " + w + " to " + v);
        vertices[v].add(w);
    }

    // The main function that finds and prints all strongly
    // connected components
    public void findSCCs()
    {
        Stack<Integer> stack = new Stack<>();
        // Mark all the vertices as not visited (For first DFS)
        boolean[] visited = new boolean[size];
        for(int i = 0; i < size; i++) {
            visited[i] = false;
        }

        // Fill vertices in stack according to their finishing times
        for (int i = 0; i < size; i++) {
            if (!visited[i]) {
                DFSVisit(i, visited, stack);
            }
        }

        // Create a reversed graph
        Graph gRev = getTranspose();

        // Mark all the vertices as not visited (For second DFS on gRev)
        for (int i = 0; i < size; i++) {
            visited[i] = false;
        }

        // Now process all vertices in order defined by Stack
        while (!stack.empty())
        {
            // go reverse visit on gRev use node order from g
            int v = stack.pop();

            // Print Strongly connected component of the popped vertex
            if (!visited[v]) {
                int size = gRev.DFSUtil(v, visited);
                // System.out.print(size);

                queue.add(size);
                if (queue.size() > 10) {
                   queue.poll();
                }
                // System.out.println();
            }
        }
    }

    // A recursive function to print DFS starting from v
    private int DFSUtil(int v, boolean[] visited) {
        // Mark the current node as visited and print it
        visited[v] = true;
        //System.out.print(v + " ");

        // Recur for all the vertices adjacent to this vertex
        for (Integer neighbour : vertices[v]) {
            if (!visited[neighbour]) {
                return 1 + DFSUtil(neighbour, visited);
            }
        }

        return 1;
    }

    // Function that returns reverse (or transpose) of this graph
    private Graph getTranspose()
    {
        Graph g = new Graph(size);
        for (int node = 0; node < size; node++) {
            // Recur for all the vertices adjacent to this vertex
            for (Integer neighbour : vertices[node]) {
                g.vertices[neighbour].add(node);
            }
        }
        return g;
    }

    private void DFSVisit(int v, boolean[] visited, Stack<Integer> stack) {
        // Mark the current node as visited and print it
        visited[v] = true;

        // Recur for all the vertices adjacent to this vertex
        for (int n : vertices[v]) {
            if (!visited[n]) {
                DFSVisit(n, visited, stack);
            }
        }

        // All vertices reachable from v are processed by now,
        // push v to Stack
        stack.push(v);
    }


    // Driver method
    public static void main(String[] args) throws IOException {
        // Create a graph given in the above diagram
        Graph g = new Graph(875714);

        // The name of the file to open.
        String fileName = "resource/scc.txt";
        // This will reference one line at a time
        String line;
        BufferedReader bufferedReader = null;
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            bufferedReader = new BufferedReader(fileReader);

            int counter=0;
            while((line = bufferedReader.readLine()) != null) {
                try {
                    String[] edges = line.split(" ");
                    int v = Integer.parseInt(edges[0]);
                    int w = Integer.parseInt(edges[1]);
                    g.addEdge(v, w);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            bufferedReader.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        } finally {
            bufferedReader.close();
        }

        g.findSCCs();
        while(!g.queue.isEmpty()) {
            System.out.println(g.queue.poll());
        }
        int check = 0;
    }
}
