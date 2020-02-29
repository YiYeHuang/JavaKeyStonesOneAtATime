package algorithm.graph.obj;

import java.util.List;

public class NodeGraph {
    public int val;
    public List<NodeGraph> neighbors;
    public boolean visited;

    public NodeGraph(int val) {
        this.val = val;
    }

    public NodeGraph(int val, List<NodeGraph> neighbors) {
        this.val = val;
        this.neighbors = neighbors;
    }

    public void addneighbours(NodeGraph neighbourNode)
    {
        this.neighbors.add(neighbourNode);
    }
    public void setNeighbours(List<NodeGraph> neighbours) {
        this.neighbors = neighbours;
    }
    public String toString()
    {
        return ""+val;
    }
}
