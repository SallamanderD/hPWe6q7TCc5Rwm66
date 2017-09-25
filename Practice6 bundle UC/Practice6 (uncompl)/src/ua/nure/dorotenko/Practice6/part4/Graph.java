package ua.nure.dorotenko.Practice6.part4;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Graph<K extends Comparable<K>> {

    private HashMap<K, List<K>> map = new HashMap<>();

    public void addNode(K node) {
        if (!hasNode(node)) {
            map.put(node, new ArrayList<K>());
        }
    }

    public boolean hasNode(K node) {
        return map.containsKey(node);
    }

    public boolean hasEdge(K node1, K node2) {
        if (!hasNode(node1)) return false;
        List<K> edges = map.get(node1);
        return Collections.binarySearch(edges, node2) != -1;
    }

    public void addEdge(K node1, K node2) {
        if (!hasNode(node1)) {
            addNode(node1);
        }
        if (!hasNode(node2)) {
            addNode(node2);
        }
        List<K> edges1 = map.get(node1);
        List<K> edges2 = map.get(node2);
        edges1.add(node2);
        edges2.add(node1);
        Collections.sort(edges1);
        Collections.sort(edges2);
    }

    public HashMap<K, List<K>> getMap() {
        return map;
    }
}
