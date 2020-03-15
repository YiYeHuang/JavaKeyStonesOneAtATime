package algorithm.graph.kargerMinCut;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    public int id;
    public List<UndirectedEdge> neighbours;

    public Vertex(int id){
        this.id = id;
        this.neighbours = new ArrayList<>();
    }
}
