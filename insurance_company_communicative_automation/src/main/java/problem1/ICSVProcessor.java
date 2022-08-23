package problem1;

import java.util.ArrayList;
import java.util.Map;

/**
 * An Interface for the CSV Processor class
 * */
public interface ICSVProcessor {
  /**
   * processData method will extract all the data entries from a CSV file, reformat and re-organize
   * the data, and then return it in a List of Map Items.
   * @param file to be read, stored as a String.
   * @return reformatted and re-organized data entries, as an ArrayList of Map objects
   * @throws GenericCSVException for file handling errors.
   * */
  ArrayList<Map> processData(String file) throws GenericCSVException;
}
