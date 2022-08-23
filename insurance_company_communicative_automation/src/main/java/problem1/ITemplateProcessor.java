package problem1;

import java.io.IOException;
import java.util.Map;

/**
 * interface of the TemplateProcessor
 */
public interface ITemplateProcessor {

  /**
   * handling writing and generate files into the given directory
   * @param data - entries from a CSV file, reformat and re-organize data, as a Map Items.
   * @param template - the template, as String Array
   * @param outputDir - the given directory, as String
   * @throws IOException when the file does not exist
   */
  void write (Map<String, String> data, String[] template, String outputDir) throws IOException;

  /** reading the input template
   * @param templateFile name as a string
   * @return template as a String Array of lines in the input file.
   * @throws IOException when the file does not exist
   */
  String[] read(String templateFile) throws IOException;

}
