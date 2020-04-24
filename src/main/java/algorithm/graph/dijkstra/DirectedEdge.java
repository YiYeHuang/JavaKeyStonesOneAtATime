package algorithm.graph.dijkstra;

public class DirectedEdge {
    public Vertex from;
    public Vertex to;
    public int cost;

    public DirectedEdge(Vertex from, Vertex to, int cost){
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    public boolean isSame(DirectedEdge e){
        if (e.from == this.from && e.to == this.to){
            return true;
        } else {
            return false;
        }
    }

    public Vertex getDirection(Vertex from){
        if (from == this.from){
            return to;
        }
        return to;
    }

    public String toString() {
        return "[From " + from + " to " + to + " - c: " + cost + "]";
    }
}
