package algorithm.graph.kargerMinCut;

public class Edge {
    public Vertex u;
    public Vertex v;
    public Edge(Vertex u, Vertex v){
        this.u = u;
        this.v = v;
    }

    public boolean isSame(Edge e){
        if (e.u == this.u && e.v == this.v){
            return true;
        } else {
            return false;
        }
    }

    public Vertex getAnother(Vertex u){
        if (u == this.u){
            return v;
        } else {
            return this.u;
        }
    }
}
