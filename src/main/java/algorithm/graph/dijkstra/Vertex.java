package algorithm.graph.dijkstra;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    public int id;
    public List<DirectedEdge> edges;

    public Vertex(int id){
        this.id = id;
        this.edges = new ArrayList<>();
    }

    public String toString() {
        return "[Vid: " + id + "]";
    }
}
