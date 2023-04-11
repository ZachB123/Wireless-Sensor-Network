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

	// TODO Auto-generated class stub for unimplemented visualization
	
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
		this.program = warp.toProgram(); //ra.getProgram();
	}
	
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
	 * This method creates a Description object for the visualization and adds a header and the WorkLoad's 
	 * information (scheduler, minimum packet reception rate, end-to-end reliability, number of channels,
	 * and flows), then adds the reliability probabilities for each flow
	 * @return a Description with all the program and reliability information
	 */
	public Description visualization() {
		// need to add \n to create new lines in the description
		// Reliability Analysis for graph <name>
		// Scheduler Name: <name>
		// M: <float>
		// E2E: <float>
		// nChannels: <int>
		// flows with nodes i.e F0:A F0:B F1:A ...
		// probabilities at each time 
		Description content = new Description();
//		content.add(getTitle());
//		content.add(getScheduler());
//		content.add(getM());
//		content.add(getE2E());
//		content.add(getnChannels());
		content.add(getFlowsWithNodes());
		content.addAll(reliabiltyTableToDescription(getReliabilities()));
		return content;
	}
	
	/**
	 * This method creates a header for the Reliability Analysis with the program's name
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
		return String.format("M: %.2f\n", this.ra.getM());
	}
	
	/**
	 * This method creates a string indicating the program's end-to-end reliability (E2E)
	 * @return E2E string
	 */
	public String getE2E() {
		// should be private
		return String.format("E2E: %.2f\n", this.ra.getE2E());
	}
	
	/**
	 * This method creates a string indicating the program's number of channels (nChannels)
	 * @return nChannels string
	 */
	public String getnChannels() {
		// should be private
		return String.format("nChannels: %d\n", program.nChannels);
	}
	
	
	/**
	 * This method creates a string of the program's flows and their nodes, 
	 * in order of flow and separated by tabs
	 * @return formatted string with flows and nodes
	 */
	public String getFlowsWithNodes() {
		//should be private
		// returns the a string of flows with nodes seperated by tabs in order like F1:A F2:D ... F9:C F10:A F11:B
		List<String> flows = program.workLoad.getFlowNamesInOriginalOrder();
		if (inStandardForm(flows)) {
			sortFlows(flows);	
		}
		List<String> flowsWithNodes = getFlowsAndNodes(flows);
		return listToString(flowsWithNodes);
	}
	
	/**
	 * This method creates an ArrayList of the program's flows and their nodes
	 * @param flows is a list of the program's flows
	 * @return flows and nodes ArrayList
	 */
	public List<String> getFlowsAndNodes(List<String> flows) {
		List<String> flowsWithNodes = new ArrayList<>();
		for (String flow : flows) {
			for (String node : program.workLoad.getNodesInFlow(flow)) {
				flowsWithNodes.add(String.format("%s:%s", flow, node));
			}
		}
		return flowsWithNodes;
	}
	
	/**
	 * This method returns a boolean indicating whether the names of the program's flows 
	 * are in the form "F<int>" (standard naming convention for flows)
	 * @param flows is a list of the program's flows
	 * @return true if all flow names are in standard form, false if not
	 */
	public boolean inStandardForm(List<String> flows) {
		// returns true if all the flow names are in the form F<int>
		for (String flow : flows) {
			try {
				Integer.parseInt(flow.substring(1));
			} catch(Exception e) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * This method sorts the flows by number in increasing order
	 * @param flows is a list of the program's flows
	 */
	public void sortFlows(List<String> flows) {
		// should be private, ensures proper order of numbers
		Collections.sort(flows, new Comparator<String>() {
			public int compare(String str1, String str2) {
				int val1 = Integer.parseInt(str1.substring(1));
				int val2 = Integer.parseInt(str2.substring(1));
				return val1 - val2;
			}
		});
	}
	
	/**
	 * This method creates a string from a string array, formatted with tabs between each array element
	 * @param arr is the array to convert
	 * @return a formatted string of the array elements
	 */
	public String listToString(String[] arr) {
		// should be private formats items with \t in between
		return listToString(Arrays.asList(arr));
	}
	
	/**
	 * This method creates a string from a list of strings, formatted with tabs between list element
	 * @param arr is the list to convert 
	 * @return a formatted string of the list elements
	 */
	public String listToString(List<String> arr) {
		// should be private formats items with \t in between
		String content = "";
		for (String str : arr) {
			content += String.format("%s\t", str);
		}
		content += "\n";
		return content;
	}
	
	/**
	 * This method converts a ReliabilityTable to a Description object
	 * @param r the ReliabilityTable to convert
	 * @return a Description with the information from the ReliabilityTable
	 */
	public Description reliabiltyTableToDescription(ReliabilityTable r) {
		// should be private
		Description data = new Description();
		for (ReliabilityRow row : r) {
			String content = "";
			for (Double value : row) {
				content += String.format("%.2f\t", value);
			}
			content += "\n";
			data.add(content);
		}
		return data;
	}
	
	/**
	 * This method creates a ReliabilityTable object with the reliabilities from the ReliabilityAnalysis
	 * @return table of reliabilities
	 */
	public ReliabilityTable getReliabilities() {
		// should be private
//		return ra.getReliabilities(); THIS IS WHAT NORMALLY SHOULD HAPPEN
		return getFakeDataTable();
	}
	
	/**
	 * For debugging?
	 * @return ReliabilityTable
	 */
	public ReliabilityTable getFakeDataTable() {
		// don't need to test this because this will never be used when Reliability Analysis is implemented
		List<String> flows = program.workLoad.getFlowNamesInOriginalOrder();
		if (inStandardForm(flows)) {
			sortFlows(flows);
		}
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

