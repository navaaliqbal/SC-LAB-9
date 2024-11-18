package graph;

import java.util.*;

/**
 * An implementation of Graph.
 */
public class ConcreteVerticesGraph implements Graph<String> {
    
    private final List<Vertex> vertices = new ArrayList<>();
    
    // Abstraction function:
    //   Represents a directed graph using a list of vertices, where each vertex contains its edges to other vertices.
    
    // Representation invariant:
    //   Each vertex in the vertices list is unique.
    
    // Safety from rep exposure:
    //   All fields are private and defensive copying is used when necessary.
    
    public ConcreteVerticesGraph() {
        checkRep();
    }
    
    // Check representation invariant
    private void checkRep() {
        Set<String> vertexNames = new HashSet<>();
        for (Vertex v : vertices) {
            assert v != null;
            assert !vertexNames.contains(v.getName());
            vertexNames.add(v.getName());
        }
    }
    
    @Override
    public boolean add(String vertex) {
        for (Vertex v : vertices) {
            if (v.getName().equals(vertex)) {
                return false; // Vertex already exists
            }
        }
        vertices.add(new Vertex(vertex));
        checkRep();
        return true;
    }
    
    @Override
    public int set(String source, String target, int weight) {
        Vertex src = findVertex(source);
        Vertex tgt = findVertex(target);

        if (src == null) {
            src = new Vertex(source);
            vertices.add(src);
        }
        if (tgt == null) {
            tgt = new Vertex(target);
            vertices.add(tgt);
        }

        int previousWeight = src.setTarget(target, weight);
        checkRep();
        return previousWeight;
    }
    
    @Override
    public boolean remove(String vertex) {
        Vertex v = findVertex(vertex);
        if (v == null) {
            return false;
        }
        vertices.remove(v);
        for (Vertex other : vertices) {
            other.removeTarget(vertex); // Remove all edges related to this vertex
        }
        checkRep();
        return true;
    }
    
    @Override
    public Set<String> vertices() {
        Set<String> vertexNames = new HashSet<>();
        for (Vertex v : vertices) {
            vertexNames.add(v.getName());
        }
        return Collections.unmodifiableSet(vertexNames);
    }
    
    @Override
    public Map<String, Integer> sources(String target) {
        Map<String, Integer> sources = new HashMap<>();
        for (Vertex v : vertices) {
            int weight = v.getTargetWeight(target);
            if (weight > 0) {
                sources.put(v.getName(), weight);
            }
        }
        return Collections.unmodifiableMap(sources);
    }
    
    @Override
    public Map<String, Integer> targets(String source) {
        Vertex src = findVertex(source);
        if (src == null) {
            return Collections.emptyMap();
        }
        return Collections.unmodifiableMap(src.getTargets());
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex v : vertices) {
            sb.append(v.toString()).append("\n");
        }
        return sb.toString();
    }

    private Vertex findVertex(String name) {
        for (Vertex v : vertices) {
            if (v.getName().equals(name)) {
                return v;
            }
        }
        return null;
    }
}

/**
 * Represents a single vertex in the graph with a name and a map of directed edges.
 */
class Vertex {
    private final String name;
    private final Map<String, Integer> targets = new HashMap<>();

    // Abstraction function:
    //   Represents a vertex with its outgoing edges and their weights.

    // Representation invariant:
    //   All target weights must be greater than zero.

    // Safety from rep exposure:
    //   The targets map is private and not exposed directly.

    public Vertex(String name) {
        this.name = name;
        checkRep();
    }

    private void checkRep() {
        for (int weight : targets.values()) {
            assert weight > 0; // Weights must be positive
        }
    }

    public String getName() {
        return name;
    }

    public int setTarget(String target, int weight) {
        if (weight == 0) {
            return targets.remove(target) != null ? targets.remove(target) : 0;
        }
        return targets.put(target, weight) != null ? targets.put(target, weight) : 0;
    }

    public void removeTarget(String target) {
        targets.remove(target);
    }

    public int getTargetWeight(String target) {
        return targets.getOrDefault(target, 0);
    }

    public Map<String, Integer> getTargets() {
        return new HashMap<>(targets); // Defensive copy
    }

    @Override
    public String toString() {
        return name + " -> " + targets.toString();
    }
}
