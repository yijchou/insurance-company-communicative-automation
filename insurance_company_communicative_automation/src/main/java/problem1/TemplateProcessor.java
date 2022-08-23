package problem1;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 * A TemplateProcessor class for writing template to .txt format as the output, and
 * generate all outputs to a given directory.
 */
public class TemplateProcessor implements ITemplateProcessor {
  private static final int HASH_CODE = 31;

  /**
   * An empty default constructor for the TemplateProcessor class
   */
  public TemplateProcessor() {
  }


  /** reading the input template
   * @param templateFile name as a string
   * @throws IOException for file handling errors
   */
  @Override
  public String[] read(String templateFile) throws IOException{
    String[] template = new String[0];
    Path in = Paths.get(templateFile);
    try {
      template = Files.readAllLines(in).toArray(new String[0]);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return template;
  }


  /**
   * handling writing and generate files into the given directory
   * @param data - entries from a CSV file, reformat and re-organize data, as a Map Items.
   * @param template - the template, as String Array
   * @param outputDir - the given directory, as String
   * @throws IOException for file handling errors
   */
  public void write (Map<String, String> data, String[] template, String outputDir)
      throws IOException {
    try {
      String fileName = data.get("[[first_name]]") + "_" + data.get("[[last_name]]") + ".txt";
      BufferedWriter writer=new BufferedWriter(new FileWriter(new File(outputDir,fileName)));
      for(String s: template){
        for(String key: data.keySet()){
          if(s.contains(key)){
            s = s.replace(key, data.get(key));
          }
        }
        writer.write(s + "\n");
      }
      writer.close();
    } catch (IOException ioe) {
      System.out.println("Something was wrong! : " + ioe.getMessage());
      throw ioe;
    }
  }

  /**
   * hashCode method
   * @returns the hash code value of the Template Processor class
   */
  @Override
  public int hashCode() {
    return HASH_CODE;
  }

  /**
   * equals method checks if two objects are the same
   * @param o as another object
   * @return if objects are the same, as boolean.
   */
  @Override
  public boolean equals(Object o) {
    if(this == o){
      return true;
    }
    if(o == null || this.getClass() != o.getClass()) {
      return false;
    }
    else return true;
  }

  /**
   * toString method for the TemplateProcessor class
   * @return a String representation of the default empty constructor:
   */
  @Override
  public String toString() {
    return "TemplateProcessor{}";
  }
}



