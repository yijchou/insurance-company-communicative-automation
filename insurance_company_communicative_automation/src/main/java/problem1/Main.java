package problem1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * A Main Class for our Problem 1 Package
 * */
public class Main {
  /**
   * main method
   * @param args input from the users, stored in a String array
   * @throws InvalidArgumentException for handling command line errors
   * @throws GenericCSVException for handling CSV errors
   * @throws IOException for handing reading/writing errors
   * */
  public static void main(String[] args)
      throws InvalidArgumentException, GenericCSVException, IOException {
    // get commands from user input:
    CommandProcessor cmd = new CommandProcessor(args);

    // process csv file using command line arguments:
    CSVProcessor csv = new CSVProcessor();
    ArrayList<Map> data = csv.processData(cmd.getParameters().get("--csv-file"));

    // generating personalized statements
    TemplateProcessor template = new TemplateProcessor();
    // for writing emails:
    if(cmd.getCommands().contains("--email")){
      String[] emailTemplate = template.read(cmd.getParameters().get("--email-template"));
      for(Map<String, String> entry: data){
        template.write(entry, emailTemplate, cmd.getParameters().get("--output-dir"));
      }
    }
    // for writing letters
    if(cmd.getCommands().contains("--letter")){
      String[] letterTemplate = template.read(cmd.getParameters().get("--letter-template"));
      for(Map<String, String> entry: data){
        template.write(entry, letterTemplate, cmd.getParameters().get("--output-dir"));
      }
    }
  }
}
