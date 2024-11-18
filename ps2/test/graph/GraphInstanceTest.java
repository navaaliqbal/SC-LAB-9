package graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
    // Testing strategy
    // - testInitialVerticesEmpty(): test that a new graph has no vertices
    // - testAddVertex(): add vertices and check if they exist in the graph
    // - testAddDuplicateVertex(): add a vertex that already exists and check no duplication
    // - testSetEdge(): add edges and check if they exist with correct weights
    // - testRemoveVertex(): remove a vertex and verify it no longer exists
    // - testSourcesAndTargets(): check sources and targets for directed edges
    
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    @Test
    public void testInitialVerticesEmpty() {
        Graph<String> graph = emptyInstance();
        assertEquals("Expected new graph to have no vertices", Collections.emptySet(), graph.vertices());
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
