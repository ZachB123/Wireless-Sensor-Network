package edu.uiowa.cs.warp;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    private static final Double m = 0.9;
    private static final Double e2e = 0.99;
    private static final String fileName = "Example";
    private static final String filePath = fileName + ".txt";
    private static final Integer numChannels = 3;
    private static final ScheduleChoices choice = ScheduleChoices.PRIORITY;
    private ReliabilityAnalysis reliabilityAnalysis;
	
    @Before
    public void setup() {
        reliabilityAnalysis = getReliabilityAnalysis(m, e2e, filePath, numChannels, choice);
    }
    
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
        assertNotNull(reliabilityAnalysis.getReliabilities());
    }

    /**
     * Tests the getColumnIndicesofFlow method with Example.txt as the input file to get the 
     * ordered flow nodes to represent the column indices for the reliability table
     */
    @Test
	@Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testGetColumnIndicesOfFlow() {
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
        assertNotNull(reliabilityAnalysis.getFlowNodeToColumnAssociation());
    }

    /** 
     * Tests the getNumRows method with Example.txt as the input file to return 
     * the number of rows from the getReliabilities table
     */
    @Test
	@Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testGetNumRows() {
        assertEquals(100, reliabilityAnalysis.getNumRows());
    }

    /**
	 * Tests the getNumColumns method with Example.txt as the input file to return 
     * the number of columns from the getReliabilities table
     */
    @Test
	@Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testGetNumColumns() {
        assertEquals(6, reliabilityAnalysis.getNumColumns());
    }

    /**
     * Tests the verifyReliabilities method with Example.txt as the input file to return 
     * a boolean True if all the flows have met their minimum end-to-end reliabilities.
     */
    @Test
	@Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testVerifyReliabilities() {
        assertTrue(reliabilityAnalysis.verifyReliabilities());
    }
    
    /**
     * 
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testGetFinalReliabilityRow() {
        ArrayList<Double> expected = new ArrayList<>();
        expected.add(1.0);
        expected.add(0.999);
        expected.add(0.9963);
        expected.add(1.0);
        expected.add(0.999);
        expected.add(0.9963);
        assertEquals(expected, reliabilityAnalysis.getFinalReliabilityRow());
    }
    
    /**
     * 
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testGetColumnToFlowNodeAssociation() {
        Map<Integer, String> expected = new HashMap<>();
        expected.put(0, "F0:A");
        expected.put(1, "F0:B");
        expected.put(2, "F0:C");
        expected.put(3, "F1:C");
        expected.put(4, "F1:B");
        expected.put(5, "F1:A");
        assertEquals(expected, reliabilityAnalysis.getColumnToFlowNodeAssociation());
    }

    /**
     * Tests the getProgram method with with Example.txt as the input file to check
     * that the value of the program's schedule choice is not null
     */
    @Test
	@Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testGetProgram() {
        assertNotNull(reliabilityAnalysis.getProgram());
    }
    
    /**
     * Tests the getM method with with Example.txt as the input file
     */
    @Test
	@Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testGetM() {
        assertEquals(0.9, reliabilityAnalysis.getM());
    }
    
    /**
     * Tests the getE2E method with with Example.txt as the input file
     */
    @Test
	@Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testGetE2E() {
        assertEquals(0.99, reliabilityAnalysis.getE2E(), 0.01);
    }

    /**
     * Tests the numTxPerLinkAndTotalTxCost method with Example.txt as the input file
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testNumTxPerLinkAndTotalTxCost() {
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
    
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testGetReliabilitiesEmptyWorkLoad() {
        ReliabilityAnalysis emptyReliabilityAnalysis = getReliabilityAnalysis(0.9, 0.99, "", 3, ScheduleChoices.PRIORITY);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            emptyReliabilityAnalysis.getReliabilities();
        }, "The method should throw an IndexOutOfBoundsException for an empty workload");
    }

    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testGetColumnIndicesOfFlowEmptyFlowName() {
        Map<String, Integer> columnMapping = new HashMap<>();
        columnMapping.put("F0:A", 0);
        columnMapping.put("F0:B", 1);
        columnMapping.put("F0:C", 2);

        List<Integer> columnIndices = reliabilityAnalysis.getColumnIndicesOfFlow("", columnMapping);
        assertTrue(columnIndices.isEmpty(), "The result should be an empty list for an empty flow name");
    }

    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testGetOldRowInvalidRowNumber() {
        Map<String, Integer> columnMapping = new HashMap<>();
        columnMapping.put("flow1:src", 0);
        columnMapping.put("flow1:node1", 1);
        columnMapping.put("flow1:node2", 2);
        columnMapping.put("flow1:snk", 3);

        ReliabilityTable reliabilityTable = new ReliabilityTable();
        reliabilityTable.add(new ReliabilityRow(4, 0.0));

        int rowNum = -1;

        assertThrows(IndexOutOfBoundsException.class, () -> {
            reliabilityAnalysis.getOldRow(rowNum, columnMapping, reliabilityTable);
        }, "The method should throw an IndexOutOfBoundsException for an invalid row number");
    }

    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testGetFlowNamesToResendNegativeCycle() {
        List<String> flowNamesToResend = reliabilityAnalysis.getFlowNamesToResend(-1);
        assertTrue(flowNamesToResend.isEmpty(), "The result should be an empty list for a negative cycle");
    }


    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testGetNumRowsEmptyWorkLoad() {
        ReliabilityAnalysis emptyReliabilityAnalysis = getReliabilityAnalysis(0.9, 0.99, "", 3, ScheduleChoices.PRIORITY);
        assertEquals(1, emptyReliabilityAnalysis.getNumRows());
    }

    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testGetNumColumnsEmptyWorkLoad() {
        ReliabilityAnalysis emptyReliabilityAnalysis = getReliabilityAnalysis(0.9, 0.99, "", 3, ScheduleChoices.PRIORITY);
        assertEquals(0, emptyReliabilityAnalysis.getNumColumns());
    }

    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testVerifyReliabilitiesEdgeCase() {
        // Come back to
    }


    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testGetFinalReliabilityRowEdgeCase() {
        // Come back to
    }

    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testGetColumnToFlowNodeAssociationEmptyWorkLoad() {
        ReliabilityAnalysis emptyReliabilityAnalysis = getReliabilityAnalysis(0.9, 0.99, "", 3, ScheduleChoices.PRIORITY);
        Map<Integer, String> columnToFlowNodeAssociation = emptyReliabilityAnalysis.getColumnToFlowNodeAssociation();
        
        assertTrue(columnToFlowNodeAssociation.isEmpty(), "The result should be an empty map for an empty workload");
    }
    
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testGetFlowNodeToColumnAssociationEmptyWorkLoad() {
        ReliabilityAnalysis emptyReliabilityAnalysis = getReliabilityAnalysis(0.9, 0.99, "", 3, ScheduleChoices.PRIORITY);
        assertTrue(emptyReliabilityAnalysis.getFlowNodeToColumnAssociation().isEmpty(), "The result should be an empty map for an empty workload");
    }

    /**
     * Tests the getColumnIndicesOfFlow method with a non-existent flow name.
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testGetColumnIndicesOfFlowNonExistentFlow() {
        Map<String, Integer> columnMapping = new HashMap<>();
        columnMapping.put("F0:A", 0);
        columnMapping.put("F0:B", 1);
        columnMapping.put("F0:C", 2);
        
        List<Integer> columnIndices = reliabilityAnalysis.getColumnIndicesOfFlow("NonExistentFlow", columnMapping);

        assertTrue(columnIndices.isEmpty(), "The result should be an empty list for a non-existent flow");
    }

    /**
     * Tests the getOldRow method with a row number that is out of bounds.
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testGetOldRowRowOutOfBounds() {
        Map<String, Integer> columnMapping = new HashMap<>();
        columnMapping.put("flow1:src", 0);
        columnMapping.put("flow1:node1", 1);
        columnMapping.put("flow1:node2", 2);
        columnMapping.put("flow1:snk", 3);

        ReliabilityTable reliabilityTable = new ReliabilityTable();
        reliabilityTable.add(new ReliabilityRow(4, 0.0));

        int rowNum = 2; // Out of bounds

        assertThrows(IndexOutOfBoundsException.class, () ->
            reliabilityAnalysis.getOldRow(rowNum, columnMapping, reliabilityTable),
            "The getOldRow method should throw an IndexOutOfBoundsException");
    }

    /**
     * Tests the getFlowNamesToResend method with an invalid time step.
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testGetFlowNamesToResendInvalidTimeStep() {
        List<String> flowNamesToResend = reliabilityAnalysis.getFlowNamesToResend(-1);
        assertTrue(flowNamesToResend.isEmpty(), "The result should be null");
    }

    /**
     * Tests the verifyReliabilities method with a WorkLoad that does not meet the minimum end-to-end reliability.
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testVerifyReliabilitiesNotMet() {
        ReliabilityAnalysis unmetReliabilityAnalysis = getReliabilityAnalysis(0, 0.9, 1.0, filePath, numChannels, choice);
        assertFalse(unmetReliabilityAnalysis.verifyReliabilities());
    }
}
