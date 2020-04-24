package algorithm.graph.dijkstra;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/*
 * The file contains an adjacency list representation of an undirected weighted
 * graph with 200 vertices labeled 1 to 200. Each row consists of the node
 * tuples that are adjacent to that particular vertex along with the length of
 * that edge. For example, the 6th row has 6 as the first entry indicating that
 * this row corresponds to the vertex labeled 6. The next entry of this row
 * "141,8200" indicates that there is an edge between vertex 6 and vertex 141
 * that has length 8200. The rest of the pairs of this row indicate the other
 * vertices adjacent to vertex 6 and the lengths of the corresponding edges.
 *
 * Task:
 * Run Dijkstra's shortest-path algorithm on this graph, using 1 (the first
 * vertex) as the source vertex, and to compute the shortest-path distances
 * between 1 and every other vertex of the graph. If there is no path between a
 * vertex v and vertex 1, we'll define the shortest-path distance between 1 and
 * v to be 1000000.
 *
 * Output format:
 * Report the shortest-path distances to the following ten vertices, in order:
 * 7,37,59,82,99,115,133,165,188,197. You should encode the distances as a
 * comma-separated string of integers. So if you find that all ten of these
 * vertices except 115 are at distance 1000 away from vertex 1 and 115 is 2000
 * distance away, then your answer should be 1000,1000,1000,1000,1000,2000,1000,
 * 1000,1000,1000.
 *
 * Time complexity: O(n m) - In the "heap" directory, there is an optimized
 * O(m log(n)) algorithm.
 */
public class DijkstraShortestGraph {

    // A list of vertices each with a list of edge int[2] with from and to
    // private List<Vertex> vertices; // graph
    private Map<Integer, Vertex> verticesMap; // graph
    private Set<Integer> exploredVertices; // nodes that have been explored

    public DijkstraShortestGraph(String inputFileName) throws FileNotFoundException {
        // vertices = new ArrayList<>();
        verticesMap = new HashMap<>();
        Scanner in = new Scanner(new File(inputFileName));
        //add all vertices
        while (in.hasNextLine()){
            String[] line = in.nextLine().split("\t");
            int nodeId = Integer.parseInt(line[0]);
            verticesMap.put(nodeId, new Vertex(nodeId));
        }
        //add all edges
        in = new Scanner(new File(inputFileName));
        //add all vertices
        while (in.hasNextLine()){
            String[] line = in.nextLine().split("\t");
            int nodeId = Integer.parseInt(line[0]);
            Vertex vertexFrom = verticesMap.get(nodeId);

            for (int i = 1; i < line.length; i++){
                String[] edgeStr = line[i].split(",");
                int toVertexId = Integer.parseInt(edgeStr[0]);
                int toVertexCost = Integer.parseInt(edgeStr[1]);
                DirectedEdge edge = new DirectedEdge(
                        vertexFrom, verticesMap.get(toVertexId), toVertexCost);
                vertexFrom.edges.add(edge);
            }
        }
    }

    public int[] shortestPath() {
        int n = verticesMap.size();
        exploredVertices = new HashSet<>();
        int[] shortestPath = new int[n];
        exploredVertices.add(1); // node id 1
        // as Dijkstra is single point shortest path, the first node is the shortest path root
        shortestPath[0] = 0; // node to itself is 0

        while (exploredVertices.size() < n) {
            int nextPathNeighbourId = -1; // the chosen neighbour id
            int shortestCost = 1000000; // all neighbour shortest cost

            for (int node : exploredVertices) {
                // go through all edge
                List<DirectedEdge> edgeAll = verticesMap.get(node).edges;
                for (DirectedEdge edge : edgeAll) {
                    // if neighbour is not explored
                    if (!exploredVertices.contains(edge.to.id)) {
                        // on the first path, it would always be 0.
                        // find the neighbour with the lowest visit cost, store the nextPathNeighbourId index
                        if (shortestPath[node - 1] + edge.cost < shortestCost) {
                            nextPathNeighbourId = edge.to.id;

                            // update the current shortest path value
                            shortestCost = shortestPath[node - 1] + edge.cost;
                        }
                    }
                }
            }

            // there is a shortest path update found
            if (nextPathNeighbourId != -1) {
                System.out.println("Select node "+ nextPathNeighbourId + " to explore next");
                exploredVertices.add(nextPathNeighbourId);
                shortestPath[nextPathNeighbourId - 1] = shortestCost; // update the shortest path from node 1 to node nextPathNeighbourId
                printPath(shortestPath);
            } else {
                // no shortest path update on the node, mark all unexplored node as not reachable
                for (int i = 0; i < n; i++){
                    if (!exploredVertices.contains(i+1)){
                        shortestPath[i] = 1000000;
                    }
                }
                break;
            }
        }

        return shortestPath;
    }

    public static void printPath(int[] path) {
        System.out.print(" [");
        for (int i = 0; i < path.length; i++) {
            System.out.print(path[i] + " ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) throws FileNotFoundException{
        //Graph g = new Graph("SimpleInput.txt");
        DijkstraShortestGraph g = new DijkstraShortestGraph("resource/DijkstraSmall.txt");
        int[] paths = g.shortestPath();
//        System.out.println(paths[6] + "," + paths[36] + "," + paths[58] + "," +
//                paths[81] + "," + paths[98] + "," + paths[114] + "," +
//                paths[132] + "," + paths[164] + "," + paths[187] +
//                "," + paths[196]); //7,37,59,82,99,115,133,165,188,197
//        //System.out.println(Arrays.toString(paths));
    }
}
