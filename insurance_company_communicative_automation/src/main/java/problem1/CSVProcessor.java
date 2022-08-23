package problem1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A CSV Processor class for handling data entries stored in a CSV File
 * */
public class CSVProcessor implements ICSVProcessor{
  private static final Integer DEFAULT_HASH_CODE = 31;

  /**
   * An empty default constructor for the CSV Processor class
   * */
  public CSVProcessor() {
  }

  /**
   * processData method will extract all the data entries from a CSV file, reformat and re-organize
   * the data, and then return it in a List of Map Items.
   * @param file to be read, stored as a String.
   * @return reformatted and re-organized data entries, as an ArrayList of Map objects
   * @throws GenericCSVException for file handling errors.
   * */
  @Override
  public ArrayList<Map> processData(String file) throws GenericCSVException{
    Map<Integer, String> keys;                // use Map to store keys, ie. [[key]]
    ArrayList<Map> data= new ArrayList<>();   // use ArrayList to store data entries
    String s;
    try{                                      // start processing file
      BufferedReader r = new BufferedReader(new FileReader(file));
      s = r.readLine();
      if (s == null){
        // if first line = null, display message to user:
        throw new GenericCSVException("Can not read an empty CSV File.");
      }
      // else, use first line of the file to create keys:
      keys = this.createKeys(s);
      s = r.readLine();     // continue reading
      while (s != null) {
        data.add(this.matchKeyAndValues(keys, s));
        s = r.readLine();
      }
      r.close();
      // return re-formatted and re-organized data:
      return data;
    } catch (FileNotFoundException e) { // handling file not found exceptions:
      throw new GenericCSVException("Could not locate file: " + file + " in this directory.");
    } catch (IOException e) {           // handling file input output exceptions:
      throw new GenericCSVException("*** OOPS! An I/O error has occurred: " + e.getMessage());
    }
  }

  /**
   * reformat is a private helper method that will re-organize a String data entry into an Array
   * of String data elements.
   * @param s is a single data entry stored in a string
   * @return reformatted data, as a String array
   * */
  private String[] reformat(String s){
    /* 1. replace commas between entries with custom pointer (-split here-)
    * 2. get rid of quotation marks
    * 3. split using custom pointer (-split here-) */
    return s.replace("\",\"", "-split here-").replace("\"",
        "").split("-split here-");
  }

  /**
   * createKeys is a private helper method that will create keys using a standard column format.
   * @param s should be the first data entry in the file, stored as a String
   * @return reformatted keys, stored in a Map, i.e. index = [[key]]
   * */
  private Map<Integer, String> createKeys(String s){
    Map<Integer, String> keys = new HashMap<>();
    // turn input string into an array of attribute keys:
    String[] k = this.reformat(s);
    // use for loop to assign index values to each key:
    for (int i = 0; i < k.length; i++) {
      final String standardFormat = "[[" + k[i] + "]]";
      keys.put(i, standardFormat);
    }
    // return keys as a Map of index = key:
    return keys;
  }


  /**
   * matchKeysAndValues is a private helper method that will match attribute values to their keys.
   * @param keys for possible attributes, stored as a Map of Integer to String objects.
   * @param s should be the current data entry in the file, stored as a String.
   * @return corresponding [[key]]=value entries, stored as a Map of String to String objects.
   * */
  private Map<String, String> matchKeyAndValues (Map<Integer, String> keys, String s){
    Map<String, String> match = new HashMap<>();
    // turn input string into an array of data values:
    String[] v = this.reformat(s);
    // use for loop to match the key with the index, then match value with the key
    for (int i = 0; i < v.length; i++) {
      match.put(keys.get(i),v[i]);
    }
    // return matches as a Map of [[key]] = value:
    return match;
  }

  /**
   * hashCode method returns the hash code value of the CSV Processor class:
   * */
  @Override
  public int hashCode() {
    // no fields to hash:
    return DEFAULT_HASH_CODE;
  }

  /**
   * equals method checks if two objects are the same
   * @param o as another object
   * @return if objects are the same, as a Boolean.
   * */
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
   * toString method for the CSV Processor class
   * @return a String representation of the default empty constructor:
   * */
  @Override
  public String toString() {
    return "CSVProcessor{}";
  }
}