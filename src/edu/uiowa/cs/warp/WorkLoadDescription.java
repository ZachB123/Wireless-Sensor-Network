/**
 * 
 */
package edu.uiowa.cs.warp;

import java.util.Collections;

/**
 * Reads the input file, whose name is passed as input parameter to the constructor, and builds a
 * Description object based on the contents. Each line of the file is an entry (string) in the
 * Description object.
 * 
 * @author sgoddard
 * @version 1.4 Fall 2022
 */
public class WorkLoadDescription extends VisualizationObject {

  private static final String EMPTY = "";
  private static final String INPUT_FILE_SUFFIX = ".wld";

  private Description description;
  private String inputGraphString;
  private FileManager fm;
  private String inputFileName;

  WorkLoadDescription(String inputFileName) {
    super(new FileManager(), EMPTY, INPUT_FILE_SUFFIX); // VisualizationObject constructor
    this.fm = this.getFileManager();
    initialize(inputFileName);
  }

  @Override
  public Description visualization() {
    return description;
  }

  @Override
  public Description fileVisualization() {
    return description;
  }

  // @Override
  // public Description displayVisualization() {
  // return description;
  // }

  @Override
  public String toString() {
    return inputGraphString;
  }

  public String getInputFileName() {
    return inputFileName;
  }

  private void initialize(String inputFile) {
    // Get the input graph file name and read its contents
    InputGraphFile gf = new InputGraphFile(fm);
    inputGraphString = gf.readGraphFile(inputFile);
    this.inputFileName = gf.getGraphFileName();
    description = new Description(inputGraphString);
    
  }
  // Creating main method at bottom of class
  public static void main(String[] args) {
	  // instantiating WorkLoadDescription with StressTest.txt as the parameter
	  WorkLoadDescription workloadDescription = new WorkLoadDescription("StressTest.txt");
	  
	  Description description = workloadDescription.visualization();
	  
	  //TODO remove first line to get rid of unwanted name and brace
	  //TODO remove last line to get rid of last brace 
	  //TODO sort through remaining items
	  
	  //Possible edge case: If there are extra lines before description starts
	  
	  //Getting rid of the bracket in the first line by replacing it with an empty string
	  String graphName = description.get(0).replace("{","");
	  	  
	  System.out.print(graphName);
	  
	  //Removing last line so the brace at end is gone 
	  description.remove(description.size()-1);
	  description.remove(0);
	  
	  
	  
	  //Alphabetically sorting through all of the flows in description
	  Collections.sort(description);
	  
	  //Adding the string "Flow i :" To the beginning of each line in description
	  for (int i= 0; i<description.size(); i++) {
		  
		  String flows = "Flow" + " " + (i+1) + " " + ":";
		  description.set(i, flows + description.get(i));
		  
	  }
	  //Printing sorted description
	  System.out.print(description);
  }
  
}
