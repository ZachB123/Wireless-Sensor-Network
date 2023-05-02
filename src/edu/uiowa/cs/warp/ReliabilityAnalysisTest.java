package edu.uiowa.cs.warp;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Timeout;

import edu.uiowa.cs.warp.SystemAttributes.ScheduleChoices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ReliabilityAnalysisTest {
	
	/**
	 *The default timeout value for all test cases
	 */
	private static final int DEFAULT_TIMEOUT = 2;
	
    /**
     * This method creates a ReliabilityAnalysis for testing
     * @param m is the min packet reception rate
     * @param e2e is the end-to-end reliability
     * @param inputFileName is the input file
     * @param numChannels is the number of channels in the program
     * @param choice is the schedule to use
     * @return a ReliabilityAnalysis
     */
    public ReliabilityAnalysis getReliabilityAnalysis(Double m, Double e2e, String inputFileName, Integer numChannels, ScheduleChoices choice) {
        // Read input file and create the WorkLoad object
        WorkLoad workLoad = new WorkLoad(m, e2e, inputFileName);

        // Create the flows
        Flow flow0 = new Flow("F0", 0, 0);
        flow0.addNode(new Node("src", 0, 0));
        flow0.addNode(new Node("node1", 1, 1));
        flow0.addNode(new Node("node2", 2, 2));
        flow0.addNode(new Node("snk", 3, 3));

        Flow flow1 = new Flow("F1", 1, 1);
        flow1.addNode(new Node("src", 0, 0));
        flow1.addNode(new Node("node1", 1, 1));
        flow1.addNode(new Node("node2", 2, 2));
        flow1.addNode(new Node("snk", 3, 3));

        // Add the flows to the WorkLoad object
        workLoad.addFlow("F0");
        workLoad.addFlow("F1");

        return new ReliabilityAnalysis(new WarpSystem(new WorkLoad(m, e2e, inputFileName), numChannels, choice).toProgram());
    }



    /**
     * This method creates a ReliabilityAnalysis for testing
     * @param numFaults is the number of faults in the WorkLoad's flows
     * @param m is the min packet reception rate
     * @param e2e is the end-to-end reliability
     * @param inputFileName is the input file
     * @param numChannels is the number of channels in the program
     * @param choice is the schedule to use
     * @return a ReliabilityAnalysis
     */
    public ReliabilityAnalysis getReliabilityAnalysis(
            Integer numFaults, Double m, Double e2e, String inputFileName, Integer numChannels, ScheduleChoices choice) {
        return new ReliabilityAnalysis(new WarpSystem(new WorkLoad(numFaults, m, e2e, inputFileName), numChannels, choice).toProgram());
    }


    /**
     * Tests getReliabilities method with Example.txt as the input file to check if actual value is not null
     */
    @Test
	@Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testGetReliabilities() {
    	Double m = 0.9;
        Double e2e = 0.99;
        String fileName = "Example";
		String filePath = fileName + ".txt";
		Integer numChannels = 3;
        ScheduleChoices choice = ScheduleChoices.PRIORITY;

        // Initialize the reliabilityAnalysis object before using it
        ReliabilityAnalysis reliabilityAnalysis = getReliabilityAnalysis(m, e2e, filePath, numChannels, choice);
        assertNotNull(reliabilityAnalysis.getReliabilities());
    }

    /**
     * Tests the getColumnIndicesofFlow method with Example.txt as the input file to get the 
     * ordered flow nodes to represent the column indices for the reliability table
     */
    @Test
	@Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testGetColumnIndicesOfFlow() {
        Double m = 0.9;
        Double e2e = 0.99;
        String fileName = "Example";
        String filePath = fileName + ".txt";
        Integer numChannels = 3;
        ScheduleChoices choice = ScheduleChoices.PRIORITY;

        // Initialize the reliabilityAnalysis object
        ReliabilityAnalysis reliabilityAnalysis = getReliabilityAnalysis(m, e2e, filePath, numChannels, choice);
        
        // Prepare a Map<String, Integer> object
        Map<String, Integer> columnMapping = new HashMap<>();
        columnMapping.put("F0:A", 0);
        columnMapping.put("F0:B", 1);
        columnMapping.put("F0:C", 2);

        String flowName = "F0";
        List<Integer> columnIndices = reliabilityAnalysis.getColumnIndicesOfFlow(flowName, columnMapping);

        assertNotNull(columnIndices, "The result should not be null");
        assertEquals(3, columnIndices.size(), "The result should have the correct number of column indices");
        assertEquals(0, columnIndices.get(0), "The first index should match the src node index");
        assertEquals(2, columnIndices.get(columnIndices.size() - 1), "The last index should match the snk node index");
    }

    /**
     * Tests the getOldRow method with Example.txt as the input file to get the previous 
     * rows from the reliability table to calculate the next row of reliability values
     */
    @Test
	@Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testGetOldRow() {
        Double m = 0.9;
        Double e2e = 0.99;
        String fileName = "Example";
        String filePath = fileName + ".txt";
        Integer numChannels = 3;
        ScheduleChoices choice = ScheduleChoices.PRIORITY;

        // Initialize the reliabilityAnalysis object
        ReliabilityAnalysis reliabilityAnalysis = getReliabilityAnalysis(m, e2e, filePath, numChannels, choice);

        // Prepare a Map<String, Integer> object
        Map<String, Integer> columnMapping = new HashMap<>();
        columnMapping.put("flow1:src", 0);
        columnMapping.put("flow1:node1", 1);
        columnMapping.put("flow1:node2", 2);
        columnMapping.put("flow1:snk", 3);

        // Initialize the ReliabilityTable object
        ReliabilityTable reliabilityTable = new ReliabilityTable();
        reliabilityTable.add(new ReliabilityRow(4, 0.0));

        int rowNum = 1;
        ReliabilityRow oldRow = reliabilityAnalysis.getOldRow(rowNum, columnMapping, reliabilityTable);

        assertNotNull(oldRow, "The result should not be null");
    }

    /**
     * Tests the getFlowNamesToResend method with Example.txt as the input file to determine
     * the flows that needs to resend the message based on the flow's period in one program cycle
     */
    @Test
	@Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testGetFlowNamesToResend() {
    	Double m = 0.9;
        Double e2e = 0.99;
        String fileName = "Example";
		String filePath = fileName + ".txt";
		Integer numChannels = 3;
        ScheduleChoices choice = ScheduleChoices.PRIORITY;

        // Initialize the reliabilityAnalysis object
        ReliabilityAnalysis reliabilityAnalysis = getReliabilityAnalysis(m, e2e, filePath, numChannels, choice);
        assertNotNull(reliabilityAnalysis.getFlowNamesToResend(5));
    }

    /**
     * Tests the getFlowNodeToColumnAssociation with Example.txt as the input file to
     * check each pair of flow name and node name association on the reliability table
     * column that is matched with the column header created by the ReliabilityVizualization class is not null
     */
    @Test
	@Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testGetFlowNodeToColumnAssociation() {
    	Double m = 0.9;
        Double e2e = 0.99;
        String fileName = "Example";
		String filePath = fileName + ".txt";
		Integer numChannels = 3;
        ScheduleChoices choice = ScheduleChoices.PRIORITY;

        // Initialize the reliabilityAnalysis object
        ReliabilityAnalysis reliabilityAnalysis = getReliabilityAnalysis(m, e2e, filePath, numChannels, choice);
        assertNotNull(reliabilityAnalysis.getFlowNodeToColumnAssociation());
    }

    /** 
     * Tests the getNumRows method with Example.txt as the input file to return 
     * the number of rows from the getReliabilities table
     */
    @Test
	@Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testGetNumRows() {
    	Double m = 0.9;
        Double e2e = 0.99;
        String fileName = "Example";
		String filePath = fileName + ".txt";
		Integer numChannels = 3;
        ScheduleChoices choice = ScheduleChoices.PRIORITY;

        // Initialize the reliabilityAnalysis object
        ReliabilityAnalysis reliabilityAnalysis = getReliabilityAnalysis(m, e2e, filePath, numChannels, choice);
        assertEquals(100, reliabilityAnalysis.getNumRows());
    }

    /**
	 * Tests the getNumColumns method with Example.txt as the input file to return 
     * the number of columns from the getReliabilities table
     */
    @Test
	@Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testGetNumColumns() {
    	Double m = 0.9;
        Double e2e = 0.99;
        String fileName = "Example";
		String filePath = fileName + ".txt";
		Integer numChannels = 3;
        ScheduleChoices choice = ScheduleChoices.PRIORITY;

        // Initialize the reliabilityAnalysis object
        ReliabilityAnalysis reliabilityAnalysis = getReliabilityAnalysis(m, e2e, filePath, numChannels, choice);
        assertEquals(6, reliabilityAnalysis.getNumColumns());
    }

    /**
     * Tests the verifyReliabilities method with Example.txt as the input file to return 
     * a boolean True if all the flows have met their minimum end-to-end reliabilities.
     */
    @Test
	@Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testVerifyReliabilities() {
    	Double m = 0.9;
        Double e2e = 0.99;
        String fileName = "Example";
		String filePath = fileName + ".txt";
		Integer numChannels = 3;
        ScheduleChoices choice = ScheduleChoices.PRIORITY;

        // Initialize the reliabilityAnalysis object
        ReliabilityAnalysis reliabilityAnalysis = getReliabilityAnalysis(m, e2e, filePath, numChannels, choice);
        assertTrue(reliabilityAnalysis.verifyReliabilities());
    }

    /**
     * Tests the getProgram method with with Example.txt as the input file to check
     * that the value of the program's schedule choice is not null
     */
    @Test
	@Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testGetProgram() {
    	Double m = 0.9;
        Double e2e = 0.99;
        String fileName = "Example";
		String filePath = fileName + ".txt";
		Integer numChannels = 3;
        ScheduleChoices choice = ScheduleChoices.PRIORITY;

        // Initialize the reliabilityAnalysis object
        ReliabilityAnalysis reliabilityAnalysis = getReliabilityAnalysis(m, e2e, filePath, numChannels, choice);
        assertNotNull(reliabilityAnalysis.getProgram());
    }
    
    /**
     * Tests the getM method with with Example.txt as the input file
     */
    @Test
	@Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testGetM() {
        Double m = 0.9;
        Double e2e = 0.99;
        String fileName = "Example";
		String filePath = fileName + ".txt";
		Integer numChannels = 3;
        ScheduleChoices choice = ScheduleChoices.PRIORITY;

        // Initialize the reliabilityAnalysis object
        ReliabilityAnalysis reliabilityAnalysis = getReliabilityAnalysis(m, e2e, filePath, numChannels, choice);

        assertEquals(m, reliabilityAnalysis.getM());
    }
    
    /**
     * Tests the getE2E method with with Example.txt as the input file
     */
    @Test
	@Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testGetE2E() {
    	Double m = 0.9;
        Double e2e = 0.99;
        String fileName = "Example";
		String filePath = fileName + ".txt";
		Integer numChannels = 3;
        ScheduleChoices choice = ScheduleChoices.PRIORITY;

        // Initialize the reliabilityAnalysis object
        ReliabilityAnalysis reliabilityAnalysis = getReliabilityAnalysis(m, e2e, filePath, numChannels, choice);
        assertEquals(0.99, reliabilityAnalysis.getE2E(), 0.01);
    }

    /**
     * Tests the numTxPerLinkAndTotalTxCost method with Example.txt as the input file
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testNumTxPerLinkAndTotalTxCost() {
        Double m = 0.9;
        Double e2e = 0.99;
        String fileName = "Example";
        String filePath = fileName + ".txt";
        Integer numChannels = 3;
        ScheduleChoices choice = ScheduleChoices.PRIORITY;

        // Initialize the reliabilityAnalysis object
        ReliabilityAnalysis reliabilityAnalysis = getReliabilityAnalysis(m, e2e, filePath, numChannels, choice);

        // Create a Flow object
        Flow testFlow = new Flow("TestFlow", 1, 0);
        // Add nodes to the flow
        testFlow.nodes.add(new Node("Node1", 1, 0));
        testFlow.nodes.add(new Node("Node2", 2, 1));
        testFlow.nodes.add(new Node("Node3", 3, 2));

        ArrayList<Integer> result = reliabilityAnalysis.numTxPerLinkAndTotalTxCost(testFlow);

        ArrayList<Integer> actual = new ArrayList<>(Arrays.asList(3, 3, 0, 4));

        assertNotNull(result, "The result should not be null");
        assertEquals(actual, result, "The result should match the expected values");
    }

    /**
     * Tests the getNumTxAttemptsPerLinkAndTotalTxAttempts method with Example.txt as the input file
     * to return the number of transmission attempts per link and the total transmission attempts of a flow
     * when the end-to-end reliability and minimum packet reception rate is specified by the user
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testGetNumTxAttemptsPerLinkAndTotalTxAttempts() {
        Double m = 0.9;
        Double e2e = 0.99;
        String fileName = "Example";
        String filePath = fileName + ".txt";
        Integer numChannels = 3;
        ScheduleChoices choice = ScheduleChoices.PRIORITY;

        // Initialize the reliabilityAnalysis object
        ReliabilityAnalysis reliabilityAnalysis = getReliabilityAnalysis(m, e2e, filePath, numChannels, choice);

        // Create a Flow object
        Flow testFlow = new Flow("TestFlow", 1, 0);
        // Add nodes to the flow
        testFlow.nodes.add(new Node("Node1", 1, 0));
        testFlow.nodes.add(new Node("Node2", 2, 1));
        testFlow.nodes.add(new Node("Node3", 3, 2));

        ArrayList<Integer> result = reliabilityAnalysis.getNumTxAttemptsPerLinkAndTotalTxAttempts(testFlow);

        assertNotNull(result, "The result should not be null");
        ArrayList<Integer> actual = new ArrayList<>(Arrays.asList(3, 3, 0, 4));
        assertEquals(result, actual);
    }

    /**
     * Tests the getFixedTxPerLinkAndTotalTxCost method with Example.txt as the input file
     * to return the total number of transmission per link and the total cost of the requested flow
     * when the number of faults is specified by the user
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testGetFixedTxPerLinkAndTotalTxCost() {
        Double m = 0.9;
        Double e2e = 0.99;
        String fileName = "Example";
        String filePath = fileName + ".txt";
        Integer numChannels = 3;
        ScheduleChoices choice = ScheduleChoices.PRIORITY;

        // Initialize the reliabilityAnalysis object
        ReliabilityAnalysis reliabilityAnalysis = getReliabilityAnalysis(m, e2e, filePath, numChannels, choice);

        // Create a Flow object
        Flow testFlow = new Flow("TestFlow", 1, 0);
        // Add nodes to the flow
        testFlow.nodes.add(new Node("Node1", 1, 0));
        testFlow.nodes.add(new Node("Node2", 2, 1));
        testFlow.nodes.add(new Node("Node3", 3, 2));

        ArrayList<Integer> result = reliabilityAnalysis.getFixedTxPerLinkAndTotalTxCost(testFlow);

        ArrayList<Integer> actual = new ArrayList<>(Arrays.asList(1, 1, 1, 2));

        assertNotNull(result, "The result should not be null");
        assertEquals(actual, result, "The result should match the expected values");
    }

}
