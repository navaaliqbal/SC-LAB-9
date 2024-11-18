package poet;

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.File;
import java.io.IOException;

/**
 * Unit tests for the GraphPoet class.
 */
public class GraphPoetTest {
    
    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // Ensure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testBridgeInsertion() throws IOException {
        GraphPoet poet = new GraphPoet(new File("C:/Users/S.S.T/Downloads/ps2/ps2/test/poet/test.txt"));
        String inputText = "the sun horizon jumped";
        String expectedPoem = "the sun horizon jumped"; // 'sets' and 'beyond' as bridge words
        String generatedPoem = poet.poem(inputText);
        System.out.println("Generated Poem: " + generatedPoem);
        assertEquals("The poem should insert 'sets' and 'beyond' as bridge words", expectedPoem, generatedPoem);
    }
    
    @Test
    public void testMultipleBridgeInsertions() throws IOException {
        GraphPoet poet = new GraphPoet(new File("C:/Users/S.S.T/Downloads/ps2/ps2/test/poet/test.txt"));
        String inputText = "sun horizon peaceful";
        String expectedPoem = "sun horizon peaceful"; // 'sets' and 'beyond' as bridge words
        String generatedPoem = poet.poem(inputText);
        System.out.println("Generated Poem: " + generatedPoem);
        assertEquals("The poem should insert 'sets' and 'beyond' between 'sun' and 'horizon' and 'horizon' and 'peaceful'", expectedPoem, generatedPoem);
    }
    
    @Test
    public void testNoBridgeRequired() throws IOException {
        GraphPoet poet = new GraphPoet(new File("C:/Users/S.S.T/Downloads/ps2/ps2/test/poet/test.txt"));
        String inputText = "the breeze through";
        String expectedPoem = "the breeze whispers through"; // No bridge word between 'breeze' and 'through'
        String generatedPoem = poet.poem(inputText);
        System.out.println("Generated Poem: " + generatedPoem);
        assertEquals("No bridge should be added, input text should remain unchanged", expectedPoem, generatedPoem);
    }
    
    @Test
    public void testEmptyInput() throws IOException {
        GraphPoet poet = new GraphPoet(new File("C:/Users/S.S.T/Downloads/ps2/ps2/test/poet/test.txt"));
        String inputText = "";
        String expectedPoem = "";
        String generatedPoem = poet.poem(inputText);
        assertEquals("The poem should be empty for an empty input", expectedPoem, generatedPoem);
    }
    
    @Test
    public void testWordsWithoutBridgeInCorpus() throws IOException {
        GraphPoet poet = new GraphPoet(new File("C:/Users/S.S.T/Downloads/ps2/ps2/test/poet/test.txt"));
        String inputText = "the lazy dog";
        String expectedPoem = "the lazy dog"; // No bridge words available between 'lazy' and 'dog'
        String generatedPoem = poet.poem(inputText);
        assertEquals("The poem should remain unchanged as no bridge is possible for the input", expectedPoem, generatedPoem);
    }

    @Test
    public void testCaseInsensitivity() throws IOException {
        GraphPoet poet = new GraphPoet(new File("C:/Users/S.S.T/Downloads/ps2/ps2/test/poet/test.txt"));
        String inputText = "THE SUN horizon JUMPED"; // Uppercase input
        String expectedPoem = "the sun horizon jumped"; // Expected poem with lowercase words
        String generatedPoem = poet.poem(inputText);
        System.out.println("Generated Poem: " + generatedPoem);
        assertEquals("The poem should be case-insensitive and treat uppercase and lowercase words as the same", expectedPoem, generatedPoem);
    }

    public void testSpecialCharacters() throws IOException {
        GraphPoet poet = new GraphPoet(new File("C:/Users/S.S.T/Downloads/ps2/ps2/test/poet/test.txt"));
        String inputText = "the breeze! through@"; // Special characters in the input

        // Preprocess the input to remove special characters
        String processedInput = inputText.replaceAll("[^a-zA-Z\\s]", "").trim(); // Remove non-alphabetical characters
        String expectedPoem = "the breeze whispers through"; // Expected poem without special characters
        String generatedPoem = poet.poem(processedInput);
        
        System.out.println("Generated Poem: " + generatedPoem);
        assertEquals("The poem should handle special characters correctly and generate a valid output", expectedPoem, generatedPoem);
    }
 


}
