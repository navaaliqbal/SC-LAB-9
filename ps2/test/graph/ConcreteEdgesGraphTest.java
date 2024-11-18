package graph;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Map;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    
    // Testing strategy for ConcreteEdgesGraph.toString()
    // TODO: test that toString() correctly represents the graph state

    @Test
    public void testInitialVerticesEmpty() {
        Graph<String> graph = emptyInstance();
        assertTrue("Expected new graph to have no vertices", graph.vertices().isEmpty());
    }

    @Test
    public void testAddVertex() {
        Graph<String> graph = emptyInstance();
        assertTrue("Expected true when adding new vertex", graph.add("A"));
        assertTrue("Expected vertex A to be in graph", graph.vertices().contains("A"));
    }

    @Test
    public void testAddDuplicateVertex() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        assertFalse("Expected false when adding duplicate vertex", graph.add("A"));
        assertEquals("Expected only 1 vertex in the graph", 1, graph.vertices().size());
    }

    @Test
    public void testSetEdge() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        int prevWeight = graph.set("A", "B", 5);
        assertEquals("Expected previous weight to be 0", 0, prevWeight);
        Map<String, Integer> targets = graph.targets("A");
        assertTrue("Expected target B in targets of A", targets.containsKey("B"));
        assertEquals("Expected edge weight from A to B to be 5", (Integer) 5, targets.get("B"));
    }

    @Test
    public void testRemoveVertex() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        assertTrue("Expected true when removing existing vertex", graph.remove("A"));
        assertFalse("Expected vertex A to be removed", graph.vertices().contains("A"));
    }

    @Test
    public void testSourcesAndTargets() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 5);
        Map<String, Integer> sources = graph.sources("B");
        assertTrue("Expected source A in sources of B", sources.containsKey("A"));
        assertEquals("Expected edge weight from A to B to be 5", (Integer) 5, sources.get("A"));
        Map<String, Integer> targets = graph.targets("A");
        assertTrue("Expected target B in targets of A", targets.containsKey("B"));
        assertEquals("Expected edge weight from A to B to be 5", (Integer) 5, targets.get("B"));
    }
}
