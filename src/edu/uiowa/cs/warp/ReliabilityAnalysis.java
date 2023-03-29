package edu.uiowa.cs.warp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

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
	 * Set to true if we use the numFaults constructor
	 * Used to determine how to calculate numTxPerLinkAndTotalTxCost
	 */
	private boolean fixed = false;
	
	/**
	 * Class constructor specifying the program
	 * 
	 * @param program is the program schedule with flows to use
	 */
	public ReliabilityAnalysis(Program program) {
		this.program = program;
	}
	
	/**
	 * Class constructor specifying end-to-end reliability and minimum packet reception rate
	 * 
	 * @param e2e is a double representing the end-to-end reliability
	 * @param minPacketReceptionRate is a double representing the minimum packet reception rate
	 */
	public ReliabilityAnalysis(Double e2e, Double minPacketReceptionRate) {
		this.e2e = e2e;
		this.minPacketReceptionRate = minPacketReceptionRate;
	}
	
	/**
	 * Class constructor specifying the number of faults, sets fixed to true
	 * 
	 * @param numFaults is an Integer representing the number of faults
	 */
	public ReliabilityAnalysis(Integer numFaults) {
		this.numFaults = numFaults;
		this.fixed = true;
	}
	
	/**
	 * This method was generated with UML Lab in HW4
	 * 
	 * @return a ReliabilityTable containing reliabilities of a packet
	 *         reaching each node in a flow at each time slot
	 */
	public ReliabilityTable getReliabilities() {
		// TODO implement this operation
		throw new UnsupportedOperationException("not implemented");
	}

	/**
	 * This method declares whether all reliabilities are met
	 * 
	 * I don't know if this should be changed or if its supposed to always return true 
	 * if packets are always pushed again until the minimum link reliability is met?
	 * 
	 * @return Boolean true
	 */
	public Boolean verifyReliabilities() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * This method calculates a flow's numTxPerLinkAndTotalTxCost based on whether its fault tolerance is fixed
	 * 
	 * If fixed is true, calculates the maximum number of transmissions per link and maximum total transmissions
	 * If fixed is false, calculates the number of transmission attempts (pushes) per link and total transmission attempts
	 * 
	 * @param flow is the specified flow to calculate transmissions and cost for
	 * @return an ArrayList containing the number of transmissions per link and total cost of the specified flow
	 */
	public ArrayList<Integer> numTxPerLinkAndTotalTxCost(Flow flow) {
		if (fixed) {
//			return getFixedTxPerLinkAndTotalTxCost(flow);
			return getFixedTxPerLinkAndTotalTxCost(flow);
		}
//		return numTxAttemptsPerLinkAndTotalTxAttempts(flow);
		return getTxPerLinkAndTotalTxCost(flow);
	}
	
	/**
	 * This method takes a flow and adds its transmissions to an ArrayList. Each
	 * node has a max of numFaults +1. Once these are all added to the ArrayList,
	 * put the sum of the number of edges of the flow and the max faults in a flow
	 * into the ArrayList
	 * Method moved from Workload class
	 * 
	 * @param flow is a specified flow object
	 * @return an ArrayList containing number of edges and max faults in the
	 *         selected flow
	 */
	private ArrayList<Integer> getFixedTxPerLinkAndTotalTxCost(Flow flow) {
		var nodesInFlow = flow.nodes;
		var nNodesInFlow = nodesInFlow.size();
		ArrayList<Integer> txArrayList = new ArrayList<Integer>();
		/*
		 * Each node will have at most numFaults+1 transmissions. Because we don't know
		 * which nodes will send the message over an edge, we give the cost to each
		 * node.
		 */
		for (int i = 0; i < nNodesInFlow; i++) {
			txArrayList.add(numFaults + 1);
		}
		/*
		 * now compute the maximum # of TX, assuming at most numFaults occur on an edge
		 * per period, and each edge requires at least one successful TX.
		 */
		var numEdgesInFlow = nNodesInFlow - 1;
		var maxFaultsInFlow = numEdgesInFlow * numFaults;
		txArrayList.add(numEdgesInFlow + maxFaultsInFlow);
		return txArrayList;
	}

	/**
	 * This method takes in a flow and calculates the number of transmission attempts
	 * per link between nodes and the total transmission attempts
	 * Method moved from Workload class
	 * 
	 * @param flow is a specified flow object
	 * 
	 * @return an ArrayList of the number of pushes
	 */
	private ArrayList<Integer> getTxPerLinkAndTotalTxCost(Flow flow) {
		// I have no idea what this should be set to and we can't take it in as a 
		// parameter according to the homework
		Double M = 0.99;
		
		var nodesInFlow = flow.nodes;
		// The last entry will contain the worst-case cost of transmitting E2E in isolation
		var nNodesInFlow = nodesInFlow.size(); 
		// var nPushes = Array(repeating: 0, count: nNodesInFlow+1);
		// Array to track nPushes for each node in this flow (same as nTx per link)
		// Collections.nCopies initalized to 0
		var nPushes = new ArrayList<Integer>(Collections.nCopies(nNodesInFlow + 1, 0 ));  
		// deprecated
		//Arrays.fill(nPushes, 0); // initialize to all 0 values
		var nHops = nNodesInFlow - 1;
		// minLinkReliablityNeded is the minimum reliability needed per link in a flow
		// to hit E2E
		// reliability for the flow
		// use max to handle rounding error when e2e == 1.0
		Double minLinkReliablityNeded = Math.max(e2e, Math.pow(e2e, (1.0 / (double) nHops)));
		// Now compute reliability of packet reaching each node in the given time slot
		// Start with a 2-D reliability window that is a 2-D matrix of no size
		// each row is a time slot, stating at time 0
		// each column represents the reliability of the packet reaching that node at
		// the current time slot (i.e., the row it is in)
		// will add rows as we compute reliabilities until the final reliability is
		// reached for all nodes.
		var reliabilityWindow = new ReliabilityTable(); //Vector<Vector<Double>>();
		var newReliabilityRow = new ReliabilityRow();
		for (int i = 0; i < nNodesInFlow; i++) {
			// create the the row initialized with 0.0 values
			newReliabilityRow.add(0.0); 
		}
		// now add row to the reliability window, Time 0
		reliabilityWindow.add(newReliabilityRow); 
//		ReliabilityRow tmpVector = reliabilityWindow.get(0);
		// could be an issue here as idk if this needs to be copied or not
		var currentReliabilityRow = reliabilityWindow.get(0); //tmpVector.toArray(new Double[tmpVector.size()]);
		// var currentReliabilityRow = (Double[]) reliabilityWindow.get(0).toArray();
		// Want reliabilityWindow[0][0] = 1.0 (i.e., P(packet@FlowSrc) = 1
		// but I din't want to mess with the newReliablityRow vector I use below
		// So, we initialize this first entry to 1.0, wich is reliabilityWindow[0][0]
		// We will then update this row with computed values for each node and put it
		// back in the matrix
		currentReliabilityRow.set(0, 1.0); // initialize (i.e., P(packet@FlowSrc) = 1
		// the analysis will end when the e2e reliability matrix is met, initially the state 
		// is not met and will be 0 with this statement
		Double e2eReliabilityState = currentReliabilityRow.get(nNodesInFlow - 1); 
		var timeSlot = 0; // start time at 0
		// change to while and increment increment timeSlot because we don't know how long this schedule window will last
		while (e2eReliabilityState < e2e) { 
			var prevReliabilityRow = currentReliabilityRow;
			// would be reliabilityWindow[timeSlot] if working through a schedule
			currentReliabilityRow = newReliabilityRow; //.toArray(new Double[newReliabilityRow.size()]); 
			// Now use each flow:src->sink to update reliability computations
			// this is the update formula for the state probabilities
			// nextState = (1 - M) * prevState + M*NextHighestFlowState
			// use MinLQ for M in above equation
			// NewSinkNodeState = (1-M)*PrevSnkNodeState + M*PrevSrcNodeState
			// loop through each node in the flow and update the states for each line (i.e) sink->src pair
			for (int nodeIndex = 0; nodeIndex < (nNodesInFlow - 1); nodeIndex++) { 
				var flowSrcNodeindex = nodeIndex;
				var flowSnkNodeindex = nodeIndex + 1;
				var prevSrcNodeState = prevReliabilityRow.get(flowSrcNodeindex); //[flowSrcNodeindex];
				var prevSnkNodeState = prevReliabilityRow.get(flowSnkNodeindex); //[flowSnkNodeindex];
				Double nextSnkState;
				// do a push until PrevSnk state > e2e to ensure next node reaches target E2E BUT skip if
				// no chance of success (i.e, source doesn't have packet) 
				if ((prevSnkNodeState < minLinkReliablityNeded) && prevSrcNodeState > 0) { 
					// need to continue attempting to Tx, so update current state
					nextSnkState = ((1.0 - M) * prevSnkNodeState) + (M * prevSrcNodeState); 
					// increment the number of pushes for for this node to snk node
					nPushes.set(nodeIndex, nPushes.get(nodeIndex) + 1); //[nodeIndex] += 1; 
				} else {
					// snkNode has met its reliability. Thus move on to the next node and record the reliability met
					nextSnkState = prevSnkNodeState; 
				}
				// probabilities are non-decreasing so update if we were higher by carrying old value forward
				if (currentReliabilityRow.get(flowSrcNodeindex) < prevReliabilityRow.get(flowSrcNodeindex)) { 
					// carry forward the previous state for the src node, which may get overwritten later
					// by another instruction in this slot
					currentReliabilityRow.set(flowSrcNodeindex, prevReliabilityRow.get(flowSrcNodeindex)); 
				}
				currentReliabilityRow.set(flowSnkNodeindex, nextSnkState);
			}

			e2eReliabilityState = currentReliabilityRow.get(nNodesInFlow - 1);
			// so I think since we just use this reliabilityVector as the same thing as the currentReliabilityRow
			// we don't need it at all and can just replace currentReliabiliyVector with current ReliabilityRow
			ReliabilityRow currentReliabilityVector = new ReliabilityRow();
			// convert the row to a vector so we can add it to the reliability window
//			Collections.addAll(currentReliabilityVector, currentReliabilityRow);
			if (timeSlot < reliabilityWindow.size()) {
				reliabilityWindow.set(timeSlot, (currentReliabilityRow));
			} else {
				reliabilityWindow.add(currentReliabilityRow);
			}
			timeSlot += 1; // increase to next time slot
		}
		var size = reliabilityWindow.size();
		// The total (worst-case) cost to transmit E2E in isolation with specified reliability 
		// target is the number of rows in the reliabilityWindow
		nPushes.set(nNodesInFlow, size); //[nNodesInFlow] = size; 
		ArrayList<Integer> nPushesArrayList = new ArrayList<Integer>();
		nPushesArrayList.addAll(nPushes);
//		Collections.addAll(nPushesArrayList, nPushes);
		return nPushesArrayList;
	}


}
