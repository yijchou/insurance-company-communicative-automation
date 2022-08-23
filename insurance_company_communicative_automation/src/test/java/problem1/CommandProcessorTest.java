package problem1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandProcessorTest {

    private String[] testCommandLineInput;
    private CommandProcessor testCommandProcessor;

    @BeforeEach
    void setUp() throws InvalidArgumentException {
        testCommandLineInput = new String[]{"--email", "--email-template", "email-template.txt",
                "--output-dir", "emails", "--csv-file", "customer.csv"};
        testCommandProcessor = new CommandProcessor();
    }

    @Test
    void processArgsPassingArgs() throws InvalidArgumentException {
        try {
            testCommandProcessor.processArgs(testCommandLineInput);
        } catch (InvalidArgumentException e) {
            fail("An Exception should not have been thrown");
        }
    }

    @Test
    void processArgsMissingTemplate() throws InvalidArgumentException {
     String[] testCommandLineInput2 = new String[]{"--letter"};
        try {
            testCommandProcessor.processArgs(testCommandLineInput2);
            fail("An Exception should've been thrown");
        } catch (InvalidArgumentException e) {
            assertEquals("Letter needs a template!", e.getMessage());
        }

    }

    @Test
    void processArgsMissingArgumentLetter() throws InvalidArgumentException {
        String[] testCommandLineInput2 = new String[]{"--letter", "--letter-template"};
        try {
            testCommandProcessor.processArgs(testCommandLineInput2);
            fail("An Exception should've been thrown");
        } catch (InvalidArgumentException e) {
            assertEquals("Letter Template requires an argument!", e.getMessage());
        }

    }

    @Test
    void processArgsMissingArgumentEmail() {
        String[] testCommandLineInput2 = new String[]{"--email", "--email-template"};
        try {
            testCommandProcessor.processArgs(testCommandLineInput2);
            fail("An Exception should've been thrown");
        } catch (InvalidArgumentException e) {
            assertEquals("Email Template requires an argument!", e.getMessage());
        }

    }


    @Test
    void processArgsMissingArgumentCSV() {
        String[] testCommandLineInput2 = new String[]{"--csv-file"};
        try {
            testCommandProcessor.processArgs(testCommandLineInput2);
            fail("An Exception should've been thrown");
        } catch (InvalidArgumentException e) {
            assertEquals("CSV File requires an argument!", e.getMessage());
        }

    }

    @Test
    void getCommands() throws InvalidArgumentException {
        String[] testCommandLineInput2 = new String[]{"--output-dir", "emails", "--csv-file", "customer.csv"};
        testCommandProcessor.processArgs(testCommandLineInput2);
        assertEquals("[--csv-file, --output-dir]", testCommandProcessor.getCommands().toString());
    }

    @Test
    void getParameters() throws InvalidArgumentException {
        String[] testCommandLineInput2 = new String[]{"--output-dir", "emails", "--csv-file", "customer.csv"};
        testCommandProcessor.processArgs(testCommandLineInput2);
        assertEquals("{--csv-file=customer.csv, --output-dir=emails}", testCommandProcessor.getParameters().toString());
    }

    @Test
    void testHashCode() {
        assertEquals(961, testCommandProcessor.hashCode());
    }

    @Test
    void testEquals_valid() {
        CommandProcessor emptyCommandProcessor = new CommandProcessor();
        assertTrue(testCommandProcessor.equals(emptyCommandProcessor));
    }

    @Test
    void testEquals_equalsItself() {
        assertTrue(testCommandProcessor.equals(testCommandProcessor));
    }

    @Test
    void testEquals_differentObjects() {
        CommandProcessor testCommandProcessor = new CommandProcessor();
        assertFalse(testCommandProcessor.equals(testCommandLineInput));
    }

    @Test
    void testToString() {
        String expectedString = "CommandProcessor{commands=[], parameters={}}";
        assertEquals(expectedString, testCommandProcessor.toString());
    }




}