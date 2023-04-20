package edu.uiowa.cs.warp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import edu.uiowa.cs.warp.SystemAttributes.ScheduleChoices;
import edu.uiowa.cs.warp.WarpDSL.InstructionParameters;


/**
 * ReliabilityAnalysis analyzes the end-to-end reliability of messages
 * transmitted in flows for the WARP system.
 * <p>
 * 
 * Let M represent the Minimum Packet Reception Rate on an edge in a flow. The
 * end-to-end reliability for each flow, flow:src->sink, is computed iteratively
 * as follows:<br>
 * (1)The flow:src node has an initial probability of 1.0 when it is released.
 * All other initial probabilities are 0.0. (That is, the reset of the nodes in
 * the flow have an initial probability value of 0.0.) <br>
 * (2) each src->sink pair probability is computed as NewSinkNodeState =
 * (1-M)*PrevSnkNodeState + M*PrevSrcNodeState <br>
 * This value represents the probability that the message as been received by
 * the node SinkNode. Thus, the NewSinkNodeState probability will increase each
 * time a push or pull is executed with SinkNode as a listener.
 * <p>
 * 
 * The last probability state value for any node is the reliability of the
 * message reaching that node, and the end-to-end reliability of a flow is the
 * value of the last Flow:SinkNode probability.
 * <p>
 * 
 * CS2820 Spring 2023 Project: Implement this class to compute the probabilities
 * the comprise the ReliablityMatrix, which is the core of the file
 * visualization that is requested in Warp.
 * <p>
 * 
 * To do this, you will need to retrieve the program source, parse the
 * instructions for each node, in each time slot, to extract the src and snk
 * nodes in the instruction and then apply the message success probability
 * equation defined above.
 * <p>
 * 
 * I recommend using the getInstructionParameters method of the WarpDSL class to
 * extract the src and snk nodes from the instruction string in a program
 * schedule time slot.
 * 
 * @author sgoddard
 * @version 1.5 *
 */
public class ReliabilityAnalysis {
	/**
	 * Default end-to-end reliability
	 */
	private static final Double DEFAULT_E2E = 0.99;
	/**
	 * Default minimum packet reception rate
	 */
	private static final Double DEFAULT_MINPACKETRECEPTIONRATE = 0.9;
	/**
	 * Value to initialize the first entry of Reliability Row.
	 */
	private static final double INITIAL_ENTRY = 1.0;
	/**
	 * The default number of faults on each node.
	 */
	private static final Integer DEFAULT_NUM_FAULTS = 0;

	/**
	 * The specified program scheduler
	 */
	private Program program = null;
	/**
	 * End-to-end reliability
	 */
	private Double e2e = DEFAULT_E2E;
	/**
	 * Minimum packet reception rate
	 */
	private Double minPacketReceptionRate = DEFAULT_MINPACKETRECEPTIONRATE;
	/**
	 * Number of faults in flow
	 */
	private Integer numFaults = 0;

	/**
	 * Status of the fault model (fixed or not)
	 * 
	 * Set to true if we use the numFaults constructor Used to determine how to
	 * calculate numTxPerLinkAndTotalTxCost
	 */
//	private boolean fixed = false;
	/**
	 * Sets True for calculating transmission cost using specified number of faults constructor and
	 * False when using specified e2e and minimum packet reception rate constructor.
	 */
	private boolean useFixedTx;


	/**
	 * Class constructor specifying the program
	 * 
	 * @param program is the program schedule with flows to use
	 */
	public ReliabilityAnalysis(Program program) {
		this.program = program;
		this.e2e = program.getE2e();
		this.minPacketReceptionRate = program.getMinPacketReceptionRate();
	}

	/**
	 * Constructor initializing the class with specified values for end-to-end
	 * reliability and minimum packet reception rate.
	 * 
	 * @param e2e End-to-end reliability
	 * @param minPacketReceptionRate Minimum packet reception rate
	 */
	public ReliabilityAnalysis(Double e2e, Double minPacketReceptionRate) {
		this.e2e = e2e;
		this.minPacketReceptionRate = minPacketReceptionRate;
		this.numFaults = DEFAULT_NUM_FAULTS;
		this.useFixedTx = false;
	}


	/**
	 * Constructor initializing the class with a specified number of faults for
	 * fixed transmission analysis.
	 * 
	 * @param numFaults Number of faults
	 */
	public ReliabilityAnalysis(Integer numFaults) {
		this.e2e = DEFAULT_E2E;
		this.minPacketReceptionRate = DEFAULT_MINPACKETRECEPTIONRATE;
		this.numFaults = numFaults;
		this.useFixedTx = true;
	}

	
	// for testing the implementation
	public static void main(String[] args) {
		double m = 0.75;
		double e2e = 0.99;
		String inputFileName = "Example1a.txt";
		int numChannels = 16;
		ScheduleChoices choice = ScheduleChoices.PRIORITY;
		WarpSystem warp = new WarpSystem(new WorkLoad(m, e2e, inputFileName), numChannels, choice);
		ReliabilityAnalysis ra = new ReliabilityAnalysis(warp.toProgram());
		ReliabilityTable reliabilities = ra.getReliabilities();
		for (ReliabilityRow row : reliabilities) {
			System.out.println(row);
		}
	}

	/**
	 * This method was generated with UML Lab in HW4
	 * 
	 * @return a ReliabilityTable containing reliabilities of a packet reaching each
	 *         node in a flow at each time slot
	 */
	public ReliabilityTable getReliabilities() {
		int numRows = getNumRows();
		Double M = this.minPacketReceptionRate;
		WarpDSL dsl = new WarpDSL();
		ProgramSchedule programSchedule = program.getSchedule();
		ReliabilityTable reliabilities = new ReliabilityTable(getNumRows(), getNumColumns());
		HashMap<String, Integer> flowNodeToColumnIndex = getFlowNodeToColumnAssociation();
		for (int row = 0; row < numRows; row++) {
			ReliabilityRow oldRow = getOldRow(row, flowNodeToColumnIndex, reliabilities); //reliabilities.get(row == 0 ? row : row - 1);
			ReliabilityRow newRow = new ReliabilityRow(oldRow.toArray(new Double[oldRow.size()]));
			InstructionTimeSlot instructionSlot = programSchedule.get(row);
			for (String instruction : instructionSlot) {
				List<InstructionParameters> instructionParams = dsl.getInstructionParameters(instruction);
				for (InstructionParameters instructionParam : instructionParams) { 
					String name = instructionParam.getName();
					if (!(name.equals("pull") || name.equals("push"))) {
						continue;
					}
					String flow = instructionParam.getFlow();                                                                                                        
					String src = instructionParam.getSrc();
					int srcColIndex = flowNodeToColumnIndex.get(String.format("%s:%s", flow, src));
					String snk = instructionParam.getSnk();
					int snkColIndex = flowNodeToColumnIndex.get(String.format("%s:%s", flow, snk));
					Double prevSrcNodeState = oldRow.get(srcColIndex);
					Double prevSnkNodeState = oldRow.get(snkColIndex);
					// THE FORUMULA!!!
					Double newSinkNodeState = (1-M) * prevSnkNodeState + M * prevSrcNodeState;
					newRow.set(snkColIndex, newSinkNodeState);
				}
			}
			reliabilities.set(row, newRow);
		}
		
		return reliabilities;
	}
	
	public List<Integer> getColumnIndicesOfFlow(String flow, HashMap<String, Integer> map) {
		// first position is the src and last is the snk
		List<Integer> columnIndices = new ArrayList<>();
		Flow flowObj = this.program.workLoad.getFlows().get(flow);
		if (flowObj == null) {return columnIndices;}
		List<Node> nodes = flowObj.getNodes();
		for (Node node : nodes) {
			columnIndices.add(map.get(String.format("%s:%s", flow, node.getName())));
		}
		return columnIndices;
	}
	
	public ReliabilityRow getOldRow(int row, HashMap<String, Integer> map, ReliabilityTable reliabilities) {
		int numCols = reliabilities.getNumColumns();
		List<String> resend = getFlowNamesToResend(row);
		if (resend.isEmpty()) { return reliabilities.get(row - 1); }
		ReliabilityRow oldRow = row == 0 ? new ReliabilityRow(numCols, 0.0) : new ReliabilityRow(reliabilities.get(row-1).toArray(new Double[numCols]));
		for (String flow : resend) {
			List<Integer> columnIndices = getColumnIndicesOfFlow(flow, map);
			for (int col : columnIndices) {
				oldRow.set(col, 0.0);
			}
			oldRow.set(columnIndices.get(0), 1.0);
		}
		return oldRow;
	}
	
	// return an arraylist of the flows that need to be reset give a row
	public List<String> getFlowNamesToResend(int row) {
		// if flow X has a period of 10 then it needs to be reset at row 9, 19, 29 etc
		List<String> flows = this.program.workLoad.getFlowNamesInPriorityOrder();
		List<String> resendList = new ArrayList<String>();
		for (String flow : flows) {
			if (row % this.program.workLoad.getFlows().get(flow).getPeriod() == 0) {
				resendList.add(flow);
			}
		}
		return resendList;
	}
	
	public HashMap<String, Integer> getFlowNodeToColumnAssociation() {
		// this returns a hashmap that maps <flowname>:<nodeinflow> to its corresponding column in the reliability table
		// should be private
		HashMap<String, Integer> association = new HashMap<String, Integer>();
		int columnIndex = 0;
		for (String flow : this.program.workLoad.getFlowNamesInPriorityOrder()) {
			for (String node : program.workLoad.getNodesInFlow(flow)) {
				association.put(String.format("%s:%s", flow, node), columnIndex);
				columnIndex++;
			}
		}
		return association;
	}
	
	public int getNumRows() {
		// should be private
		return this.program.getSchedule().size();
	}
	
	public int getNumColumns() {
		// should be private
		int num = 0;
		for (String flow : program.workLoad.getFlowNamesInPriorityOrder()) {
			num += program.workLoad.getNodesInFlow(flow).length;
		}
		return num;
	}

	/**
	 * This method declares whether all reliabilities are met
	 * 
	 * I don't know if this should be changed or if its supposed to always return
	 * true if packets are always pushed again until the minimum link reliability is
	 * met?
	 * 
	 * @return Boolean true
	 */
	public Boolean verifyReliabilities() {
		// TODO Auto-generated method stub
		return true;
	}
	
	/**
	 * This method gets the schedule choice of the program that is specified by the user
	 * 
	 * @return the specified schedule choice of the program
	 */
	public Program getProgram() {
		return this.program;
	}
	
	/**
	 * This method gets the minimum packet reception rate on an edge in a flow
	 * 
	 * @return the double of minimum packet reception rate of the edge in a flow
	 */
	public Double getM() {
		// should be private
		return this.minPacketReceptionRate;
	}
	
	/**
	 * This methods gets the end-to-end reliability of each flow
	 * 
	 * @return the double of the end-to-end reliability value of each flow
	 */
	public Double getE2E() {
		// should be private
		return this.e2e;
	}
	

	/**
	 * This method calculates a flow's numTxPerLinkAndTotalTxCost based on whether
	 * its fault tolerance is fixed
	 * 
	 * If fixed is true, calculates the maximum number of transmissions per link and
	 * maximum total transmissions If fixed is false, calculates the number of
	 * transmission attempts (pushes) per link and total transmission attempts
	 * 
	 * @param flow is the specified flow to calculate transmissions and cost for
	 * @return an ArrayList containing the number of transmissions per link and
	 *         total cost of the specified flow
	 */
	/**
	 * Calculates the number of transmissions per link and total transmission cost for the given flow.
	 * 
	 * @param flow Flow object representing the network flow
	 * @return ArrayList containing the number of transmissions per link and total transmission cost
	 */
	public ArrayList<Integer> numTxPerLinkAndTotalTxCost(Flow flow) {
		if (useFixedTx) {
			return getFixedTxPerLinkAndTotalTxCost(flow);
		} else {
			return getNumTxAttemptsPerLinkAndTotalTxAttempts(flow); 
		}
	}


	/**
	 * Returns the number of transmission attempts per link and the total transmission attempts 
	 * of a flow with the specified end-to-reliability and minimum packet reception rate.
	 * 
	 * @param flow Name of the requested flow
	 * @return ArrayList containing the flow nodes, number of transmission attempts per link 
	 *          and total number of transmission attempts
	 */
	private ArrayList<Integer> getNumTxAttemptsPerLinkAndTotalTxAttempts(Flow flow){ 
		ArrayList<Node> nodesInFlow = flow.nodes;

		/*
		 * The last entry will contain the worst-case cost of transmitting E2E in isolation.
		 */
		int nNodesInFlow = nodesInFlow.size();

		/* Array to track nPushes for each node in this flow (same as nTx per link). */
		Integer[] nPushes = new Integer[nNodesInFlow + 1];
		Arrays.fill(nPushes, 0); // initialize to all 0 values

		int nHops = nNodesInFlow - 1;
		
		/* minLinkReliablityNeded is the minimum reliability needed per link in a flow to hit E2E
		 * reliability for the flow
		 * Use max to handle rounding error when e2e == 1.0 . 
		 */
		Double minLinkReliablityNeded = Math.max(e2e, Math.pow(e2e, (1.0 / (double) nHops)));

		/*
		 * Now compute reliability of packet reaching each node in the given time slot.
		 * Start with a 2-D reliability window that is a 2-D matrix of no size. Each row
		 * is a time slot, stating at time 0. Each column represents the reliability of
		 * the packet reaching that node at the current time slot (i.e., the row it is
		 * in). Will add rows as we compute reliabilities until the final reliability is
		 * reached for all nodes.
		 */
		ReliabilityTable reliabilityWindow = new ReliabilityTable();
		ReliabilityRow newReliabilityRow = new ReliabilityRow(nNodesInFlow, 0.0);

		reliabilityWindow.add(newReliabilityRow); // now add row to the reliability window, Time 0

		ReliabilityRow tmpVector = reliabilityWindow.get(0);
		Double[] currentReliabilityRow = tmpVector.toArray(new Double[tmpVector.size()]);

		/*
		 * Want reliabilityWindow[0][0] = 1.0 (i.e., P(packet@FlowSrc) = 1 but I din't
		 * want to mess with the newReliablityRow vector I use below. So, we initialize
		 * this first entry to 1.0, which is reliabilityWindow[0][0]. We will then update
		 * this row with computed values for each node and put it back in the matrix.
		 */
		currentReliabilityRow[0] = INITIAL_ENTRY; // initialize (i.e., P(packet@FlowSrc) = 1

		/*
		 * The analysis will end when the e2e reliability matrix is met. 
		 * Initially the state is not met and will be 0 with this statement.
		 */
		Double e2eReliabilityState = currentReliabilityRow[nNodesInFlow - 1];
		int timeSlot = 0; // start time at 0

		/*
		 * Change to while and increment increment timeSlot because 
		 * we don't know how long this schedule window will last
		 */
		while (e2eReliabilityState < e2e) {
			Double[] prevReliabilityRow = currentReliabilityRow;
			/* Would be reliabilityWindow[timeSlot] if working through a schedule. */
			currentReliabilityRow = newReliabilityRow.toArray(new Double[newReliabilityRow.size()]);

      /* 
       * Now use each flow:src->sink to update reliability computations
       * this is the update formula for the state probabilities
       * nextState = (1 - M) * prevState + M*NextHighestFlowState
       * use MinLQ for M in above equation
       * NewSinkNodeState = (1-M)*PrevSnkNodeState + M*PrevSrcNodeState
       */

			/* Loop through each node in the flow and update the states for each link (i.e.,sink->src pair). */
			for (int nodeIndex = 0; nodeIndex < (nNodesInFlow - 1); nodeIndex++) {
				int flowSrcNodeindex = nodeIndex;
				int flowSnkNodeindex = nodeIndex + 1;

				Double prevSrcNodeState = prevReliabilityRow[flowSrcNodeindex];
				Double prevSnkNodeState = prevReliabilityRow[flowSnkNodeindex];
				Double nextSnkState;

				/*
         * Do a push until PrevSnk state > e2e to ensure next target E2E BUT skip if no chance of success
         *  (i.e., source doesn't have packet).
         */
				if ((prevSnkNodeState < minLinkReliablityNeded) && prevSrcNodeState > 0) {
					/* Need to continue attempting to Tx, so update current state. */
					nextSnkState = ((1.0 - minPacketReceptionRate) * prevSnkNodeState)
							+ (minPacketReceptionRate * prevSrcNodeState);
					nPushes[nodeIndex] += 1; // increment the number of pushes for for this node to snk node
				} else {
					/* The snkNode has met its reliability. Thus move on to the next node and record the reliability met. */
					nextSnkState = prevSnkNodeState;
				}

				/* Probabilities are non-decreasing so update if we were higher by carrying old value forward. */
				if (currentReliabilityRow[flowSrcNodeindex] < prevReliabilityRow[flowSrcNodeindex]) {
					/*
					 * Carry forward the previous state for the src node, which may get over written later
					 * by another instruction in this slot.
					 */
					currentReliabilityRow[flowSrcNodeindex] = prevReliabilityRow[flowSrcNodeindex]; //
				}
				currentReliabilityRow[flowSnkNodeindex] = nextSnkState;
			}

			e2eReliabilityState = currentReliabilityRow[nNodesInFlow - 1];
			ReliabilityRow currentReliabilityVector = new ReliabilityRow();

			// Convert the row to a vector so we can add it to the reliability window
			Collections.addAll(currentReliabilityVector, currentReliabilityRow);
			if (timeSlot < reliabilityWindow.size()) {
				reliabilityWindow.set(timeSlot, (currentReliabilityVector));
			} else {
				reliabilityWindow.add(currentReliabilityVector);
			}
			timeSlot += 1; // increase to next time slot
		}
		
		int size = reliabilityWindow.size();
		/* 
		 * The total (worst-case) cost to transmit E2E in isolation with
     * specified reliability target is the number of rows in the reliabilityWindow
     */
		nPushes[nNodesInFlow] = size;
		
		/* Now convert the array to the ArrayList needed to return */
		ArrayList<Integer> nPushesArrayList = new ArrayList<Integer>();
		Collections.addAll(nPushesArrayList, nPushes);
		
		return nPushesArrayList;
	}

	/**
	 * Returns the total transmissions per link and the total cost of the requested flow with 
	 * the specified number of faults.
	 * 
	 * @param flow Name of the requested flow
	 * @return ArrayList containing the flow nodes, number of transmission and total transmission cost
	 * 
	 */
	private ArrayList<Integer> getFixedTxPerLinkAndTotalTxCost(Flow flow) {
		ArrayList<Node> nodesInFlow = flow.nodes;
		int nNodesInFlow = nodesInFlow.size();
		/*
		 * Each node will have at most numFaults+1 transmissions. Because we don't know
		 * which nodes will send the message over an edge, we give the cost to each
		 * node. Re-factored by using Collections.ncopies instead of a for loop.
		 */
		ArrayList<Integer> txArrayList = new ArrayList<Integer>(Collections.nCopies(nNodesInFlow, numFaults + 1));
		/*
		 * Now compute the maximum # of TX, assuming at most numFaults occur on an edge per period, and
	     * each edge requires at least one successful TX.
		 */
		int numEdgesInFlow = nNodesInFlow - 1;
		int maxFaultsInFlow = numEdgesInFlow * numFaults;
		txArrayList.add(numEdgesInFlow + maxFaultsInFlow);
		return txArrayList;
	}


}
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------