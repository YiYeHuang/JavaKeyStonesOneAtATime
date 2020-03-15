package algorithm.graph.kargerMinCut;

/*
The file contains the adjacency list representation of a simple undirected graph.
There are 200 vertices labeled 1 to 200. The first column in the file represents the vertex label,
and the particular row (other entries except the first column) tells all the vertices that the vertex is adjacent to.
So for example, the 6^{th}6
th
 row looks like : "6	155	56	52	120	......". This just means that the vertex with label 6 is adjacent to
 (i.e., shares an edge with) the vertices with labels 155,56,52,120,......,etc

Your task is to code up and run the randomized contraction algorithm for the min cut problem and use it on
the above graph to compute the min cut. (HINT: Note that you'll have to figure out an implementation of edge contractions.
Initially, you might want to do this naively, creating a new graph from the old every time there's an edge contraction.
But you should also think about more efficient implementations.) (WARNING: As per the video lectures,
please make sure to run the algorithm many times with different random seeds, and remember the smallest cut that you ever find.)
Write your numeric answer in the space provided. So e.g., if your answer is 5, just type 5 in the space provided.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class KargerMinCutGraph {
    private Map<Integer, Vertex> vertices;
    private ArrayList<UndirectedEdge> edges;

    public KargerMinCutGraph(String inputFile) throws FileNotFoundException {
        String inputFileName = inputFile;
        vertices = new Hashtable<>();
        edges = new ArrayList<>();
        Scanner in = new Scanner(new File(inputFileName));
        //add all vertices
        while (in.hasNextLine()) {
            Scanner line = new Scanner(in.nextLine());
            int id = line.nextInt();
            Vertex v = new Vertex(id);
            vertices.put(id, v);
        }
        in = new Scanner(new File(inputFileName));
        //second pass add edges
        while (in.hasNextLine()) {
            Scanner line = new Scanner(in.nextLine());
            int idU = line.nextInt();
            Vertex u = vertices.get(idU);
            while (line.hasNextInt()) {
                int idV = line.nextInt();
                Vertex v = vertices.get(idV);
                if (u.id < v.id) {
                    addEdge(u, v, 1);
                }
            }
        }
    }

    /**
     * findMinCut is random, one repeat does not guarantee that it can find the correct result
     * change the value of nRepeat to a much smaller one would make the program run faster
     * However, n^2 * ln(n) times of repeat
     * would ensure a (1-1/n) rate of success on finding the minCut.
     * int nRepeat = (int) (Math.pow(n, 2) * Math.log(n))
     */
    private void randomContract() {
        Random generator = new Random();
        while (vertices.size() > 2) {
            int index = generator.nextInt(edges.size());
            UndirectedEdge toRemove = edges.get(index);
            int idV = toRemove.v.id;
            Vertex u = toRemove.u;
            Vertex v = toRemove.v;
            removeEdge(u, v);
            // for each chosen edge(u, v), remove V keep U
            // reconnect all v's neighbour to U
            while (v.neighbours.size() > 0) {
                Vertex w = v.neighbours.get(0).getAnother(v);
                addEdge(u, w, removeEdge(v, w));
            }
            // delete V
            vertices.remove(idV);
        }
    }

    private void addEdge(Vertex u, Vertex v, int count) {
        int idU = u.id;
        int idV = v.id;
        UndirectedEdge e = new UndirectedEdge(vertices.get(Math.min(idU, idV)), vertices.get(Math.max(idU, idV)));
        for (int i = 0; i < count; i++) {
            vertices.get(idU).neighbours.add(e);
            vertices.get(idV).neighbours.add(e);
            edges.add(e);
        }
    }

    private int removeEdge(Vertex u, Vertex v) {
        int count = 0;
        int idU = u.id;
        int idV = v.id;
        UndirectedEdge targetEdge = new UndirectedEdge(vertices.get(Math.min(idU, idV)), vertices.get(Math.max(idU, idV)));
        // remove all U's edge to V
        for (int i = 0; i < u.neighbours.size(); i++) {
            if (u.neighbours.get(i).isSame(targetEdge)) {
                u.neighbours.remove(i);
                i--;
            }
        }
        // remove all V's edge to U
        for (int i = 0; i < v.neighbours.size(); i++) {
            if (v.neighbours.get(i).isSame(targetEdge)) {
                v.neighbours.remove(i);
                i--;
            }
        }
        // remove edge from total edges
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).isSame(targetEdge)) {
                edges.remove(i);
                i--;
                count++;
            }
        }
        return count;
    }

    public void printGraph(){
        System.out.println("vertices:");

        for (Integer id : vertices.keySet()) {
            System.out.print(id + ": ");
            for (UndirectedEdge e : vertices.get(id).neighbours) {
                System.out.print(e.u.id + "-" + e.v.id + " ");
            }
            System.out.println();
        }
        System.out.println("edges:");
        for (UndirectedEdge e : edges){
            System.out.print(e.u.id + "-" + e.v.id + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) throws FileNotFoundException{
        //Graph g = new Graph("SimpleInput.txt");
        String inputFile = "resource/kargerMinCut.txt";
        KargerMinCutGraph g = new KargerMinCutGraph(inputFile);

        int minCut =  g.vertices.size();

        int nRepeat = minCut;
        for (int i = 0; i < nRepeat; i++) {
            KargerMinCutGraph nextRunG = new KargerMinCutGraph(inputFile);
            nextRunG.randomContract();
            int crossingEdges = nextRunG.edges.size();
            if (crossingEdges < minCut) {
                minCut = crossingEdges;
            }
        }

        System.out.println(minCut);
    }
}
