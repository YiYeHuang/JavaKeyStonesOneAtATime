package baseObj;

import java.util.List;

public class NodeGraph {
    public int val;
    public List<NodeGraph> neighbors;

    public NodeGraph() {}

    public NodeGraph(int val, List<NodeGraph> neighbors) {
        this.val = val;
        this.neighbors = neighbors;
    }
}
