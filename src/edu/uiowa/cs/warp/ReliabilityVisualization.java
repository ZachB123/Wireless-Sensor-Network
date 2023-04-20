package edu.uiowa.cs.warp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ReliabilityVisualization creates the visualizations for
 * the reliability analysis of the WARP program.
 * 
 * CS2820 Spring 2023 Project: Implement this class to create
 * the file visualization that is requested in Warp.
 * 
 * @author sgoddard
 *
 */
public class ReliabilityVisualization extends VisualizationObject {
	
	private static final String SOURCE_SUFFIX = ".ra";
	private static final String OBJECT_NAME = "Reliability Analysis";
	private WarpInterface warp;
	private ReliabilityAnalysis ra;
	private Program program;
	
	/**
	 * This constructor calls the VisualizationObject constructor and creates a ReliabilityAnalysis object 
	 * @param warp the WarpInterface to use
	 */
	ReliabilityVisualization(WarpInterface warp) {
		super(new FileManager(), warp, SOURCE_SUFFIX);
		this.warp = warp;
		this.ra = warp.toReliabilityAnalysis();
		this.program = warp.toProgram(); 
	}
	
	/**
	 * This method creates all of the information required at the
	 * top of a .ra file.
	 * This includes, the name of the file the scheduler used
	 * the min packet reception, the end to end reliability,
	 * and the number of channels
	 * @return a Description object with an entry being on of the
	 * things mentioned above.
	 */
	public Description createHeader() {
		Description header = new Description();
		header.add(getTitle());
		header.add(getScheduler());
		header.add(getM());
		header.add(getE2E());
		header.add(getnChannels());
		return header;
	}
	
	/**
	 * This method obtains the reliability information for each flow and node.
	 * Then it is converted to a 2d String array
	 * @return a String[][] with the reliability information
	 */
	public String[][] createVisualizationData() {
		return reliabilityTableTo2dArray(getReliabilities());
	}
	
	/**
	 * This methods creates a list of all the flows with corresponding nodes
	 * that will be analyzed in the reliability analysis
	 * @return a String[] in the form <Flow name>:<Node in said flow>
	 */
	public String[] createColumnHeader() {
		List<String> flows = program.workLoad.getFlowNamesInPriorityOrder();
		List<String> listForm = getFlowsAndNodes(flows);
		String[] retVal = new String[listForm.size()];
		for (int i = 0; i < retVal.length; i++) {
			retVal[i] = listForm.get(i);
		}
		return retVal;
	}
	
	/**
	 * This method takes in a list of flows then adds the nodes to each flow
	 * in the format <flowname>:<nodeinflow> this is done for every node in
	 * all the flows inputed and collection in a String List
	 * @param flows is a list of the program's flows
	 * @return a list in the format <flowname>:<nodeinflow>
	 */
	public List<String> getFlowsAndNodes(List<String> flows) {
		// should be private
		List<String> flowsWithNodes = new ArrayList<>();
		for (String flow : flows) {
			for (String node : program.workLoad.getNodesInFlow(flow)) {
				flowsWithNodes.add(String.format("%s:%s", flow, node));
			}
		}
		return flowsWithNodes;
	}
	
	/**
	 * Converts a ReliabiltyTable object to a 2d String array object with the 
	 * same data
	 * @param ReliabilityTable table
	 * @return String[][]
	 */
	public String[][] reliabilityTableTo2dArray(ReliabilityTable table) {
		// should be private
		int rows = table.getNumRows();
		int cols = table.getNumColumns();
		String[][] stringArr = new String[rows][cols];
		for (int i = 0; i < rows; i++) {
			ReliabilityRow row = table.get(i);
			for (int j = 0; j < cols; j++) {
				stringArr[i][j] = row.get(j).toString();
			}
		}
		return stringArr;
	}
	
	/**
	 * This method uses the ReliabiltyAnalysis object to calculate the
	 * reliabilities of the workload then obtains the data in a ReliabilityTable
	 * object which it returns
	 * @return table of reliabilities
	 */
	public ReliabilityTable getReliabilities() {
		// should be private
		return ra.getReliabilities(); 
	}
	
	/**
	 * This method is for testing purposes since we have not yet implemented
	 * the method to calculate the proper reliabilities
	 * DEPRECATED
	 * @return ReliabilityTable of random data
	 */
	public ReliabilityTable getFakeDataTable() {
		// should be private
		// don't need to test this because this will never be used when Reliability Analysis is implemented
		List<String> flows = program.workLoad.getFlowNamesInPriorityOrder();
		int numColumns = getFlowsAndNodes(flows).size();
		int numRows = program.scheduleBuilt.size();
		ReliabilityTable data = new ReliabilityTable();
		for (int i = 0; i < numRows; i++) {
			ReliabilityRow dataRow = new ReliabilityRow();
			for (int j = 0; j < numColumns; j++) {
				dataRow.add((double) (((int) (Math.random() * 100)) / 100.0));
			}
			data.add(dataRow);
		}
		return data;
	}
	
	/**
	 * This method creates a Title for the Reliability Analysis with the program's name
	 * @return header for the description
	 */
	public String getTitle() {
		// should be private
		return String.format("Reliability Analysis for graph %s\n", program.workLoad.getName());
	}
	
	/**
	 * This method creates a string indicating the program's scheduler name
	 * @return scheduler name string
	 */
	public String getScheduler() {
		// should be private
		return String.format("Scheduler Name: %s\n", program.schedulerName);
	}
	
	/**
	 * This method creates a string indicating the program's minimum packet reception rate (M)
	 * @return M string
	 */
	public String getM() {
		// should be private
		return String.format("M: %s\n", this.ra.getM());
	}
	
	/**
	 * This method creates a string indicating the program's end-to-end reliability (E2E)
	 * @return E2E string
	 */
	public String getE2E() {
		// should be private
		return String.format("E2E: %s\n", this.ra.getE2E());
	}
	
	/**
	 * This method creates a string indicating the program's number of channels (nChannels)
	 * @return nChannels string
	 */
	public String getnChannels() {
		// should be private
		return String.format("nChannels: %d\n", program.nChannels);
	}
	
/* File Visualization for workload defined in Example.txt follows. Note
 * that your Authentication tag will be different from this example. The
 * rest of your output in the file ExamplePriority-0.9M-0.99E2E.ra
 * should match this output, where \tab characters are used a column
 * delimiters.
// Course CS2820 Authentication Tag: r3XWfL9ywZO36jnWMZcKC2KTB2hUCm3AQCGxREWbZRoSn4/XdrQ/QuNQvtzAxeSSw55bWTXwbI9VI0Om+mEhNd4JC2UzrBBrXnHmsbPxbZ8=
Reliability Analysis for graph Example created with the following parameters:
Scheduler Name:	Priority
M:	0.9
E2E:	0.99
nChannels:	16
F0:A	F0:B	F0:C	F1:C	F1:B	F1:A
1.0	0.9	0.0	1.0	0.0	0.0
1.0	0.99	0.81	1.0	0.0	0.0
1.0	0.999	0.972	1.0	0.0	0.0
1.0	0.999	0.9963	1.0	0.0	0.0
1.0	0.999	0.9963	1.0	0.9	0.0
1.0	0.999	0.9963	1.0	0.99	0.81
1.0	0.999	0.9963	1.0	0.999	0.972
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
1.0	0.999	0.9963	1.0	0.999	0.9963
*/
}

