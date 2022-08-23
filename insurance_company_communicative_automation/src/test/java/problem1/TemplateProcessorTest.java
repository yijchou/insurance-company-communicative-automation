package problem1;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TemplateProcessorTest {
  TemplateProcessor testTemplateProcessor;
  Map<String, String> data;
  String expectedTemplateName;
  String[] stringTemplate;

  @BeforeEach
  void setUp() throws IOException {
    testTemplateProcessor = new TemplateProcessor();
    data = new HashMap<>();
    data.put("[[first_name]]", "James");
    data.put("[[last_name]]", "Butt");
    data.put("[[company_name]]", "Benton, John B Jr");
    data.put("[[address]]", "6649 N Blue Gum St");
    data.put("[[city]]", "New Orleans");
    data.put("[[county]]", "Orleans");
    data.put("[[state]]", "LA");
    data.put("[[zip]]", "70116");
    data.put("[[phone1]]", "504-621-8927");
    data.put("[[phone2]]", "504-845-1427");
    data.put("[[email]]", "jbutt@gmail.com");
    data.put("[[web]]", "http://www.bentonjohnbjr.com");

    expectedTemplateName = "email-template.txt";
    stringTemplate = testTemplateProcessor.read(expectedTemplateName);
  }


  @Test
  void write() throws IOException{
    testTemplateProcessor.write(data, stringTemplate, "email");
  }

  @Test
  void testHashCode() {
    int expectedHashCode = testTemplateProcessor.hashCode();
    assertEquals(expectedHashCode, testTemplateProcessor.hashCode());
  }

  @Test
  void testEquals() {
    assertEquals(testTemplateProcessor, testTemplateProcessor);
  }

  @Test
  void testEquals_differentObjects() {
    CommandProcessor testCommandProcessor = new CommandProcessor();
    assertFalse(testTemplateProcessor.equals(testCommandProcessor));
  }

  @Test
  void testEquals_2() {
    assertFalse(testTemplateProcessor.equals(null));
  }

  @Test
  void testToString() {
    String expectedString = "TemplateProcessor{}";
    assertEquals(expectedString, testTemplateProcessor.toString());
  }
}