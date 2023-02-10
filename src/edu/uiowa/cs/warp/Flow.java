package edu.uiowa.cs.warp;

import java.util.ArrayList;

/**
 * Class that represents
 * @author sgoddard
 *
 */
public class Flow extends SchedulableObject implements Comparable<Flow> {

	/**
	 * Integer that represents an undefined value.
	 */
	private static final Integer UNDEFINED = -1;
	
	/**
	 * The default number of faults tolerated in the flow.
	 */
	private static final Integer DEFAULT_FAULTS_TOLERATED = 0;
	
	/**
	 * The default index value.
	 */
	private static final Integer DEFAULT_INDEX = 0;
	
	/**
	 * The default period value.
	 */
	private static final Integer DEFAULT_PERIOD = 100;
	
	/**
	 * The default deadline.
	 */
	private static final Integer DEFAULT_DEADLINE = 100;
	
	/**
	 * The default phase.
	 */
	private static final Integer DEFAULT_PHASE = 0;
	
	/**
	 * The priority that the scheduler will give to this flow.
	 */
	Integer initialPriority = UNDEFINED;
	
	/**
	 * index represents the order that the node was read from the Graph file.
	 * index also represents the priority of the flow.
	 */
	Integer index;
	
	/**
	 * This is determined by the fault model.
	 * idk what this is.
	 * I think it is the maximum time per like node.
	 */
	Integer numTxPerLink;
	
	/**
	 * List of nodes that represent the flow.
	 * First element is the source, and the sink is the last element.
	 */
	ArrayList<Node> nodes;
	
	/*
	 * nTx needed for each link to reach E2E reliability target. Indexed by src node
	 * of the link. Last entry is total worst-case E2E Tx cost for schedulability
	 * analysis
	 */
	ArrayList<Integer> linkTxAndTotalCost;
	
	/**
	 * List of all the edges that connect the nodes.
	 * This is used in the partition and scheduling.
	 */
	ArrayList<Edge> edges;
	
	/**
	 * The predecessor Node.
	 * idk what this is
	 */
	Node nodePredecessor;
	
	/**
	 * The predecessor edge.
	 * idk what this is
	 */
	Edge edgePredecessor;

	/*
	 * Constructor that sets name, priority, and index
	 */
	Flow(String name, Integer priority, Integer index) {
		super(name, priority, DEFAULT_PERIOD, DEFAULT_DEADLINE, DEFAULT_PHASE);
		this.index = index;
		/*
		 * Default numTxPerLink is 1 transmission per link. Will be updated based on
		 * flow updated based on flow length and reliability parameters
		 */
		this.numTxPerLink = DEFAULT_FAULTS_TOLERATED + 1;
		this.nodes = new ArrayList<>();
		this.edges = new ArrayList<>();
		this.linkTxAndTotalCost = new ArrayList<>();
		this.edges = new ArrayList<>();
		this.nodePredecessor = null;
		this.edgePredecessor = null;
	}

	/*
	 * Default constructor
	 * Everything is set to the default value, null or an empty ArrayList.
	 */
	Flow() {
		super();
		this.index = DEFAULT_INDEX;
		/*
		 * Default numTxPerLink is 1 transmission per link. Will be updated based on
		 * flow updated based on flow length and reliability parameters
		 */
		this.numTxPerLink = DEFAULT_FAULTS_TOLERATED + 1;
		this.nodes = new ArrayList<>();
		this.linkTxAndTotalCost = new ArrayList<>();
		this.edges = new ArrayList<>();
		this.nodePredecessor = null;
		this.edgePredecessor = null;
	}

	/**
	 * <h1>Returns the initial priority of the flow.</h1>
	 * @return the initialPriority
	 */
	public Integer getInitialPriority() {
		return initialPriority;
	}

	/**
	 * <h1>Returns the index of the flow.</h1>
	 * @return the index
	 */
	public Integer getIndex() {
		return index;
	}

	/**
	 * <h1>Returns the numTxPerLink</h1>
	 * @return the numTxPerLink
	 */
	public Integer getNumTxPerLink() {
		return numTxPerLink;
	}

	/**
	 * <h1>Returns an ArrayList of all the nodes in the flow.</h1>
	 * @return the nodes
	 */
	public ArrayList<Node> getNodes() {
		return nodes;
	}

	/**
	 * <h1>Returns an ArrayList of all the edges in the flow.</h1>
	 * @return the edges
	 */
	public ArrayList<Edge> getEdges() {
		return edges;
	}

	/**
	 * <h1>Adds an edge to the flow.</h1>
	 * <p>Sets the edges predecessor to the current flow edgePredecessor. 
	 * Then adds the edge to the edge ArrayList. Finally updates the
	 * flow attribute edgePredecessor to be the newly added edge.</p>
	 * @param edge Edge to add to the flow.
	 */
	public void addEdge(Edge edge) {
		/* set predecessor and add edge to flow */
		edge.setPredecessor(edgePredecessor);
		edges.add(edge);
		/* update predecessor for next edge added */
		edgePredecessor = edge;
	}

	/**
	 * <h1>Adds a node to the flow.</h1>
	 * <p>Sets the nodes predecessor to the current flow nodePredecessor. 
	 * Then adds the node to the node ArrayList. Finally updates the
	 * flow attribute nodePredecessor to be the newly added node.</p>
	 * @param node Node to add to the flow
	 */
	public void addNode(Node node) {
		/* set predecessor and add edge to flow */
		node.setPredecessor(nodePredecessor);
		nodes.add(node);
		/* update predecessor for next edge added */
		nodePredecessor = node;
	}

	/**
	 * <h1>Returns the linkTxAndTotalCost ArrayList.</h1>
	 * @return the linkTxAndTotalCost
	 */
	public ArrayList<Integer> getLinkTxAndTotalCost() {
		return linkTxAndTotalCost;
	}

	/**
	 * <h1>Sets the initialPriority of the flow.</h1>
	 * @param initialPriority the initialPriority to set
	 */
	public void setInitialPriority(Integer initialPriority) {
		this.initialPriority = initialPriority;
	}

	/**
	 * <h1>Sets the index of the flow.</h1>
	 * @param index the index to set
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}

	/**
	 * <h1>Sets the numTxPerLink of the flow.</h1>
	 * @param numTxPerLink the numTxPerLink to set
	 */
	public void setNumTxPerLink(Integer numTxPerLink) {
		this.numTxPerLink = numTxPerLink;
	}

	/**
	 * <h1>Overwrites the current list of nodes with a new list of nodes.</h1>
	 * @param nodes the nodes to set
	 */
	public void setNodes(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}

	/**
	 * <h1>Overwrites the current linkTxAndTotalCost with a new linkTxAndTotalCost.</h1>
	 * @param linkTxAndTotalCost the linkTxAndTotalCost to set
	 */
	public void setLinkTxAndTotalCost(ArrayList<Integer> linkTxAndTotalCost) {
		this.linkTxAndTotalCost = linkTxAndTotalCost;
	}
	
	@Override
	public int compareTo(Flow flow) {
		// ascending order (0 is highest priority)
		return flow.getPriority() > this.getPriority() ? -1 : 1;
	}

	@Override
	public String toString() {
		return getName();
	}

}
