/**
 * 
 */
package edu.uiowa.cs.warp;

/**
 * @author sgoddard
 * @version 1.5
 * 
 */

/**
 * ProgramVisualization is a class that compiles data and
 * organizes it in a easily readable manner using a series of methods
 * to create different aspects of the visualization
 *
 */
public class ProgramVisualization extends VisualizationObject {

  private static final String SOURCE_SUFFIX = ".dsl";
  private ProgramSchedule sourceCode;
  private Program program;
  private Boolean deadlinesMet;
  
  
/**
 * Method that takes an existing warp interface and converts it in into 
 * a program that can be visualized.
 * @param warp interface that will be used for visualization
 */
  ProgramVisualization(WarpInterface warp) {
    super(new FileManager(), warp, SOURCE_SUFFIX);
    this.program = warp.toProgram();
    this.sourceCode = program.getSchedule();
    this.deadlinesMet = warp.deadlinesMet();
  }
  
  
/**
 * Method that creates a Gui with a title, header, and data that can be visualized.
 * @return a gui visualization listing all above info.
 */
  @Override
  public GuiVisualization displayVisualization() {
    return new GuiVisualization(createTitle(), createColumnHeader(), createVisualizationData());
  }

  
  /**
   * Initializes a header that displays the title,scheduler name, amount of faults
   * in the program, as well as other data, like the minimum packet reception rate,
   * the length from edge to edge, and channel of numbers.
   * @return header that displays all important info/data for the visualization
   */
  @Override
  protected Description createHeader() {
    Description header = new Description();

    header.add(createTitle());
    header.add(String.format("Scheduler Name: %s\n", program.getSchedulerName()));

    /* The following parameters are output based on a special schedule or the fault model */
    if (program.getNumFaults() > 0) { // only specify when deterministic fault model is assumed
      header.add(String.format("numFaults: %d\n", program.getNumFaults()));
    }
    header.add(String.format("M: %s\n", String.valueOf(program.getMinPacketReceptionRate())));
    header.add(String.format("E2E: %s\n", String.valueOf(program.getE2e())));
    header.add(String.format("nChannels: %d\n", program.getNumChannels()));
    return header;
  }
/**
 * Creates a new description called footer, which checks to see if deadlines have 
 * been met. If they have, tell the users deadlines have been met, if not
 * give a warning telling them that they haven't.
 * @return footer that shows deadlines have been met
 */
  @Override
  protected Description createFooter() {
    Description footer = new Description();
    String deadlineMsg = null;

    if (deadlinesMet) {
      deadlineMsg = "All flows meet their deadlines\n";
    } else {
      deadlineMsg = "WARNING: NOT all flows meet their deadlines. See deadline analysis report.\n";
    }
    footer.add(String.format("// %s", deadlineMsg));
    return footer;
  }

/**
 * Creates a Header for the columns, which names them as a Time Slot, 
 * which will be named for each node respectively.
 * @return columnNames for the header
 */
 
  @Override
  protected String[] createColumnHeader() {
    var orderedNodes = program.toWorkLoad().getNodeNamesOrderedAlphabetically();
    String[] columnNames = new String[orderedNodes.length + 1];
    columnNames[0] = "Time Slot"; // add the Time Slot column header first
    /* loop through the node names, adding each to the header */
    for (int i = 0; i < orderedNodes.length; i++) {
      columnNames[i + 1] = orderedNodes[i];
    }
    return columnNames;
  }
/**
 *Creates and Organizes the visualization data as a matrix,
 * being labeled by row and column
 * @return visualizationData a matrix representing the data in a readable form.
 */
  @Override
  protected String[][] createVisualizationData() {
    if (visualizationData == null) {
      int numRows = sourceCode.getNumRows();
      int numColumns = sourceCode.getNumColumns();
      visualizationData = new String[numRows][numColumns + 1];

      for (int row = 0; row < numRows; row++) {
        visualizationData[row][0] = String.format("%s", row);
        for (int column = 0; column < numColumns; column++) {
          visualizationData[row][column + 1] = sourceCode.get(row, column);
        }
      }
    }
    return visualizationData;
  }
/**
 * Creates a basic title for the graph, displaying the correct name 
 * for whatever graph is used.
 * @return the title of the graph.
 */
  private String createTitle() {
    return String.format("WARP program for graph %s\n", program.getName());
  }
}
