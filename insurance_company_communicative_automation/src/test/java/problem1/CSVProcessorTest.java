package problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CSVProcessorTest {
  CSVProcessor testCSVProcessor;
  String expectedFile;

  @BeforeEach
  void setUp(){
    expectedFile = "insurance-company-members.csv";
    testCSVProcessor = new CSVProcessor();
  }

  @Test
  void processData_valid(){
    try {
      Map<String, String> expectedResults = new HashMap<>();
      expectedResults.put("[[first_name]]", "James");
      expectedResults.put("[[last_name]]", "Butt");
      expectedResults.put("[[company_name]]", "Benton, John B Jr");
      expectedResults.put("[[address]]", "6649 N Blue Gum St");
      expectedResults.put("[[city]]", "New Orleans");
      expectedResults.put("[[county]]", "Orleans");
      expectedResults.put("[[state]]", "LA");
      expectedResults.put("[[zip]]", "70116");
      expectedResults.put("[[phone1]]", "504-621-8927");
      expectedResults.put("[[phone2]]", "504-845-1427");
      expectedResults.put("[[email]]", "jbutt@gmail.com");
      expectedResults.put("[[web]]", "http://www.bentonjohnbjr.com");
      assertEquals(expectedResults,testCSVProcessor.processData(expectedFile).get(0));
    } catch (GenericCSVException e) {
      System.out.println("A Generic CSV Exception should not have been thrown here!");
    }
  }

  @Test
  void processData_invalidCSVName(){
    String fakeFile = "random-csv-name.csv";
    Exception exception = assertThrows(GenericCSVException.class, ()
        -> testCSVProcessor.processData(fakeFile));
    String expectedMessage = "Could not locate file: " + fakeFile + " in this directory.";
    assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void processData_emptyCSV(){
    String emptyFile = "emptyCSV.csv";
    Exception exception = assertThrows(GenericCSVException.class, ()
        -> testCSVProcessor.processData(emptyFile));
    String expectedMessage = "Can not read an empty CSV File.";
    assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void testHashCode() {
    assertEquals(31, testCSVProcessor.hashCode());
  }

  @Test
  void testEquals_valid() {
    CSVProcessor emptyCSVProcessor = new CSVProcessor();
    assertTrue(testCSVProcessor.equals(emptyCSVProcessor));
  }

  @Test
  void testEquals_equalsItself() {
    assertTrue(testCSVProcessor.equals(testCSVProcessor));
  }

  @Test
  void testEquals_differentObjects() {
    CommandProcessor testCommandProcessor = new CommandProcessor();
    assertFalse(testCSVProcessor.equals(testCommandProcessor));
  }

  @Test
  void testToString() {
    String expectedString = "CSVProcessor{}";
    assertEquals(expectedString, testCSVProcessor.toString());
  }
}