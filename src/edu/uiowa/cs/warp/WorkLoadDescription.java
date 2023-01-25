/**
 * 
 */
package edu.uiowa.cs.warp;

import java.text.Collator;
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

  // A Description object extends an arraylist
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
  
  public static void main(String[] args) {
	  WorkLoadDescription wld = new WorkLoadDescription("StressTest.txt");
	  Description content = wld.visualization();
	  // The title is the first word in the first index of the content
	  String title = content.remove(0).split(" ")[0];
	  // there is a } at the end so remove it
	  content.remove(content.size()-1);
	  // sort alphabetically
	  Collections.sort(content, Collator.getInstance());
	  System.out.println(title);
	  for (int i = 1; i <= content.size(); i++) {
		  System.out.print("Flow " + i + ": " + content.get(i-1));
	  }
  }
}
