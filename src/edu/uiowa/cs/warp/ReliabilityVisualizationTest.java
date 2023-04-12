package edu.uiowa.cs.warp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import static org.junit.jupiter.api.Assertions.*;


import edu.uiowa.cs.warp.SystemAttributes.ScheduleChoices;



public class ReliabilityVisualizationTest {
	
	/**
	 *The default timeout value for all test cases
	 */
	private static final int DEFAULT_TIMEOUT = 2;

	/**
	 * The header of reliability analysis .ra files
	 */
	private static final String HEADER_BOILERPLATE = "Reliability Analysis for graph ";
	
	/**
	 * The name of the schedule choice selected
	 */
	private static final String SCHEDULER_BOILERPLATE = "Scheduler Name: ";
	
	/**
	 * The names of file used to test the methods
	 */
	private static final String[] FILE_NAMES = {"CustomWorkloadByZach", "EmptyWorkload", "Example", "Example1a", "Example2", "Example3", "Example4", "ISPN2021figure2", "ISPN2021figure4", "ExampleX", "LongChain", "MixedNodes", "NumberedNodes", "Preempt1", "SamePeriod", "SeeSpray", "StressTest", "StressTest4", "Test1", "WARP_MIX_Schedule0-WarpInput", "WARP_MIX_Schedule1-WarpInput", "WARP-WAHU-MIX"}; 
	
	// Note to self on creating the ReliabilityVisualization
	// the constructor take a WarpInteface, the WarpSystem implements this interface
	// to build a WarpSystem you need a WorkLoad, numChannels, ScheduleChoices
	// to build a WorkLoad their are two constructors
	// 1. Double m, Double e2e, String inputFileName
	// 2. Integer numFaults, same as previous after
	/**
	 * This method creates a ReliabilityVisualization for testing
	 * @param m is the min packet reception rate
	 * @param e2e is the end-to-end reliability
	 * @param inputFileName is the input file
	 * @param numChannels is the number of channels in the program
	 * @param choice is the schedule to use
	 * @return a ReliabilityVisualization
	 */
	public ReliabilityVisualization getReliabilityVisualization(
			Double m, Double e2e, String inputFileName, Integer numChannels, ScheduleChoices choice) {
		return new ReliabilityVisualization(new WarpSystem(new WorkLoad(m, e2e, inputFileName), numChannels, choice));
	}
	
	/**
	 * This method creates a ReliabilityVisualization for testing
	 * @param numFaults is the number of faults in the WorkLoad's flows
	 * @param m is the min packet reception rate
	 * @param e2e is the end-to-end reliability
	 * @param inputFileName is the input file
	 * @param numChannels is the number of channels in the program
	 * @param choice is the schedule to use
	 * @return a ReliabilityVisualization
	 */
	public ReliabilityVisualization getReliabilityVisualization(
			Integer numFaults, Double m, Double e2e, String inputFileName, Integer numChannels, ScheduleChoices choice) {
		return new ReliabilityVisualization(new WarpSystem(new WorkLoad(numFaults, m, e2e, inputFileName), numChannels, choice));
	}
	
	// TESTING getHeader
	/**
	 * Tests the getHeader method with Example.txt as the input file
	 */
	@Test
	@Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
	public void testGetTitleExample() {
		String fileName = "Example";
		String filePath = fileName + ".txt";
		ReliabilityVisualization viz = getReliabilityVisualization(0.9, 0.99, filePath, 16, ScheduleChoices.PRIORITY);
		
		String expected = HEADER_BOILERPLATE + fileName + "\n";
		String actual = viz.getTitle();
		String message = String.format("ERROR graph headers do not match. Expected graph name to be %s but it was actually %s.", expected, actual);
		
		assertEquals(expected, actual, message);
	}
	
	/**
	 * Tests the getHeader method with numFaults defined and StressTest4.txt as the input file
	 */
	@Test
	@Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
	public void testGetTitleStressTest4WithNumFaults() {
		String fileName = "StressTest4";
		String filePath = fileName + ".txt";
		ReliabilityVisualization viz = getReliabilityVisualization(5, 0.9, 0.99, filePath, 16, ScheduleChoices.PRIORITY);
		
		String expected = HEADER_BOILERPLATE + fileName + "\n";
		String actual = viz.getTitle();
		String message = String.format("ERROR graph headers do not match. Expected graph name to be %s but it was actually %s.", expected, actual);
		
		// We cannot test a non existent graphName because System.exit(-1) is called so the program terminates
		assertEquals(expected, actual, message);
	}
	
	/**
	 * Tests the getHeader method using different graph names
	 */
	@Test
	@Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
	public void testGetHeaderDiffer() {
		String fileName = "Example1a";
		String filePath = fileName + ".txt";
		ReliabilityVisualization viz = getReliabilityVisualization(5, 0.9, 0.99, filePath, 16, ScheduleChoices.PRIORITY);
		
		String expectedGraphName = "Example1A";
		String expected = HEADER_BOILERPLATE + expectedGraphName + "\n";
		String actual = viz.getTitle();
		String message = String.format("ERROR graph headers do not match. Expected graph name to be %s but it was actually %s.", expected, actual);
		
		// We cannot test a non existent graphName because System.exit(-1) is called so the program terminates
		assertEquals(expected, actual, message);
	}
	
	/**
	 * Tests the getScheduler method for the Priority scheduler
	 */
	//TESTING getScheduler
	@Test
	@Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
	public void testGetSchedulerPriority() {
		ScheduleChoices choice = ScheduleChoices.PRIORITY;
		ReliabilityVisualization viz = getReliabilityVisualization(5, 0.9, 0.99, "ExampleX.txt", 16, choice);
		
		String expected = SCHEDULER_BOILERPLATE + "Priority\n";
		String actual = viz.getScheduler();
		String message = String.format("ERROR Schedulers do not match. Expected %s but it was actual %s", expected, actual);
		
		assertEquals(expected, actual, message);
	}
	
	/**
	 * Tests the getScheduler method for the RateMonotonic scheduler
	 */
	@Test
	@Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
	public void testGetSchedulerRM() {
		ScheduleChoices choice = ScheduleChoices.RM;
		ReliabilityVisualization viz = getReliabilityVisualization(5, 0.9, 0.99, "MixedNodes.txt", 16, choice);
		
		String expected = SCHEDULER_BOILERPLATE + "RateMonotonic\n";
		String actual = viz.getScheduler();
		String message = String.format("ERROR Schedulers do not match. Expected %s but it was actual %s", expected, actual);
		
		assertEquals(expected, actual, message);
	}
	
	/**
	 * Tests the getScheduler method for the DeadlineMonotonic scheduler
	 */
	@Test
	@Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
	public void testGetSchedulerDM() {
		ScheduleChoices choice = ScheduleChoices.DM;
		ReliabilityVisualization viz = getReliabilityVisualization(5, 0.9, 0.99, "StressTest4.txt", 16, choice);
		
		String expected = SCHEDULER_BOILERPLATE + "DeadlineMonotonic\n";
		String actual = viz.getScheduler();
		String message = String.format("ERROR Schedulers do not match. Expected %s but it was actual %s", expected, actual);
		
		assertEquals(expected, actual, message);
	}
	
	/**
	 * Tests the getM method with Example.txt as the input file
	 */
	@Test
	@Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
	public void testGetMExample() {
		Double M = 0.9;
		ReliabilityVisualization viz = getReliabilityVisualization(M, 0.99, "Example.txt", 16, ScheduleChoices.PRIORITY);
		String expected = "M: 0.90\n";
		String actual = viz.getM();
		String message = String.format("ERROR M does not match. Expected %s but was actually %s", expected, actual);
		
		assertEquals(expected, actual, message);

	}
	
	/**
	 * Tests the getM method with StressTest4.txt as the input file
	 */
	@Test
	@Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
	public void testGetMStressTest4MultipleDecimalsPlaces() {
		Double M = 0.314159;
		ReliabilityVisualization viz = getReliabilityVisualization(M, 0.99, "StressTest4.txt", 16, ScheduleChoices.PRIORITY);
		// our get M methods formats to two decimals places
		String expected = "M: 0.31\n";
		String actual = viz.getM();
		String message = String.format("ERROR M does not match. Expected %s but was actually %s", expected, actual);
		
		assertEquals(expected, actual, message);

	}
	
	/**
	 * Tests the getM method with numFaults defined
	 */
	@Test
	@Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
	public void testGetMWithNumFaultsAndGreaterThanOne() {
		Double M = 1.55;
		ReliabilityVisualization viz = getReliabilityVisualization(5, M, 0.99, "Example.txt", 16, ScheduleChoices.PRIORITY);
		String expected = "M: 1.55\n";
		String actual = viz.getM();
		String message = String.format("ERROR M does not match. Expected %s but was actually %s", expected, actual);
		
		assertEquals(expected, actual, message);

	}
	
	/**
	 * Tests the getM method with a non-standard M
	 */
	@Test
	@Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
	public void testGetMNegative() {
		Double M = -0.765;
		ReliabilityVisualization viz = getReliabilityVisualization(M, 0.99, "Example.txt", 16, ScheduleChoices.PRIORITY);
		String expected = "M: -0.77\n";
		String actual = viz.getM();
		String message = String.format("ERROR M does not match. Expected %s but was actually %s", expected, actual);
		
		assertEquals(expected, actual, message);

	}
	
	/**
	 * Tests the getE2E method with Example.txt as the input file
	 */
	@Test
	@Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
	public void testGetE2EExample() {
		Double e2e = 0.99;
		ReliabilityVisualization viz = getReliabilityVisualization(0.9, e2e, "Example.txt", 16, ScheduleChoices.PRIORITY);
		String expected = "E2E: 0.99\n";
		String actual = viz.getE2E();
		String message = String.format("ERROR E2E does not match. Expected %s but was actually %s", expected, actual);
		
		assertEquals(expected, actual, message);

	}
	
	/**
	 * Tests the getE2E method with StressTest4.txt as the input file
	 */
	@Test
	@Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
	public void testGetE2EStressTest4() {
		Double e2e = 0.70;
		ReliabilityVisualization viz = getReliabilityVisualization(0.9, e2e, "StressTest4.txt", 16, ScheduleChoices.PRIORITY);
		String expected = "E2E: 0.70\n";
		String actual = viz.getE2E();
		String message = String.format("ERROR E2E does not match. Expected %s but was actually %s", expected, actual);
		
		assertEquals(expected, actual, message);

	}
	
	/**
	 * Tests the getE2E method with numFaults defined
	 */
	@Test
	@Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
	public void testGetE2EWithNumFaultsMultipleDecimalPlaces() {
		Double e2e = 0.123456789;
		ReliabilityVisualization viz = getReliabilityVisualization(5, 0.9, e2e, "Example.txt", 16, ScheduleChoices.PRIORITY);
		String expected = "E2E: 0.12\n";
		String actual = viz.getE2E();
		String message = String.format("ERROR E2E does not match. Expected %s but was actually %s", expected, actual);
		
		assertEquals(expected, actual, message);

	}
	
	/**
	 * Tests the getE2E method with a non-standard E2E
	 */
	@Test
	@Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
	public void testGetE2EOtherNegative() {
		Double e2e = -0.99;
		ReliabilityVisualization viz = getReliabilityVisualization(0.9, e2e, "Example.txt", 16, ScheduleChoices.PRIORITY);
		String expected = "E2E: -0.99\n";
		String actual = viz.getE2E();
		String message = String.format("ERROR E2E does not match. Expected %s but was actually %s", expected, actual);
		
		assertEquals(expected, actual, message);
		
	}
	
	/**
	 * Tests the getnChannels method with a non-standard M and E2E and with Example.txt as the input file 
	 * to get the number of channels of the program
	 */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    void testGetnChannels() {
        Integer numChannels = 3;
        ReliabilityVisualization visualization = getReliabilityVisualization(1.0, 1.0, "Example.txt", numChannels, ScheduleChoices.PRIORITY);
        String expectedNChannels = String.format("nChannels: %d\n", numChannels);
        String actualNChannels = visualization.getnChannels();
        String message = String.format("ERROR Number of channels does not match. Expected %s but was actually %s", expectedNChannels, expectedNChannels);
        
        assertEquals(expectedNChannels, actualNChannels, message);
    }
	
    /**
     * Tests the getFlowsWithNodes method with a standard M and E2E and with Example.txt as the input file
     * to get the flows and its flow nodes
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    void testGetFlowsWithNodes() {
        WorkLoad wld = new WorkLoad(0.9, 0.99, "Example.txt");
        WarpSystem customWarpSystem = new WarpSystem(wld, 1, ScheduleChoices.PRIORITY);
        ReliabilityVisualization visualization = new ReliabilityVisualization(customWarpSystem);
        String expectedFlowsWithNodes = "F0:A\tF0:B\tF0:C\tF1:C\tF1:B\tF1:A\t\n";
        String actualFlowsWithNodes = visualization.getFlowsWithNodes();
        String message = String.format("ERROR Flows with nodes does not match. Expected %s but was actually %s", 
        		expectedFlowsWithNodes, actualFlowsWithNodes);
        
        assertEquals(expectedFlowsWithNodes, actualFlowsWithNodes, message);
    }

    /**
     * Tests the getFlowsAndNodes method with a standard M and E2E and with Example.txt as the input file
     * to return an array list representing the flows and its nodes
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    void testGetFlowsAndNodes() {
        WorkLoad wld = new WorkLoad(0.9, 0.99, "Example.txt");
        WarpSystem customWarpSystem = new WarpSystem(wld, 1, ScheduleChoices.PRIORITY);
        ReliabilityVisualization visualization = new ReliabilityVisualization(customWarpSystem);
        List<String> expectedFlowsAndNodes = Arrays.asList("F1:C", "F1:B", "F1:A");
        List<String> actualFlowsAndNodes = visualization.getFlowsAndNodes(Arrays.asList("F1"));
        String message = String.format("ERROR Flows and nodes does not match. Expected %s but was actually %s", 
        		expectedFlowsAndNodes.toString(), actualFlowsAndNodes.toString());
        
        assertEquals(expectedFlowsAndNodes, actualFlowsAndNodes, message);
    }
	
    /**
     * Tests the inStandardForm method with a non-standard E2E and with Example.txt as the input file
     * to return a boolean indicating whether the program's flow names are named in the standard naming convention.
     * The standard naming convention of flows should be "F<int>". 
     * The method should return true if flows are in the standard naming form and false otherwise
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    void testInStandardForm() {
		ReliabilityVisualization visualization = getReliabilityVisualization(0.9, 0.5, "Example.txt", 16, ScheduleChoices.PRIORITY);
    	
        List<String> flowsInStandardForm = Arrays.asList("F1", "F2", "F3");
        List<String> flowsNotInStandardForm = Arrays.asList("F1", "Flow2", "F3");

        assertTrue(visualization.inStandardForm(flowsInStandardForm), "ERROR Flow names should be in standard form of F<int>");
        assertFalse(visualization.inStandardForm(flowsNotInStandardForm), "ERROR Flow names are not in standard form");
    }

    /**
     * Tests the sortFlows method with a non-standard E2E and with Example.txt as the input file
     * to return an array list of flows whereby the flow numbers are sorted in increasing order
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    void testSortFlows() {
		ReliabilityVisualization visualization = getReliabilityVisualization(0.9, 0.5, "Example.txt", 16, ScheduleChoices.PRIORITY);

        List<String> unsortedFlows = Arrays.asList("F5", "F2", "F10", "F1");
        List<String> sortedFlows = Arrays.asList("F1", "F2", "F5", "F10");

        visualization.sortFlows(unsortedFlows);
        assertEquals(sortedFlows, unsortedFlows, "ERROR Flows in the array list should be sorted in increasing order of flow numbers");
    }

    /**
     * Tests the listToStringArray method with a non-standard E2E and with Example.txt as the input file
     * to get a string of flow elements formatted with tabs in between each element that is converted from a string array.
     * 
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    void testListToStringArray() {
		ReliabilityVisualization visualization = getReliabilityVisualization(0.9, 0.5, "Example.txt", 16, ScheduleChoices.PRIORITY);

        String[] inputArray = {"A", "B", "C"};
        String expectedOutput = "A\tB\tC\t\n";
        String actualOutput = visualization.listToString(inputArray);
        String message = String.format("ERROR The string of flow elements converted from a string array does not match. Expected %s but was actually %s", 
        		expectedOutput, actualOutput);
        
        assertEquals(expectedOutput, actualOutput, message);
    }

    /**
     * Tests the listToStringList method with a non-standard E2E and with Example.txt as the input file
     * to get a string of flow elements formatted with tabs in between each element that is converted from a list.
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    void testListToStringList() {
		ReliabilityVisualization visualization = getReliabilityVisualization(0.9, 0.5, "Example.txt", 16, ScheduleChoices.PRIORITY);

        List<String> inputList = Arrays.asList("A", "B", "C");
        String expectedOutput = "A\tB\tC\t\n";
        String actualOutput = visualization.listToString(inputList);
        String message = String.format("ERROR The string of flow elements converted from a list does not match. Expected %s but was actually %s", 
        		expectedOutput, actualOutput);

        assertEquals(expectedOutput, actualOutput, message);
    }

    /**
     * Tests the reliabilityTableToDescription method with a non-standard E2E and with Example.txt as the input file
     * to get the correct description of reliabilities information that is converted from the ReliabilityTable
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    void testReliabiltyTableToDescription() {
		ReliabilityVisualization visualization = getReliabilityVisualization(0.9, 0.5, "Example.txt", 16, ScheduleChoices.PRIORITY);

        ReliabilityTable table = new ReliabilityTable();
        ReliabilityRow row1 = new ReliabilityRow();
        row1.add(0.90);
        row1.add(0.85);
        ReliabilityRow row2 = new ReliabilityRow();
        row2.add(0.80);
        row2.add(0.95);
        table.add(row1);
        table.add(row2);

        Description expected = new Description();
        expected.add("0.90\t0.85\t\n");
        expected.add("0.80\t0.95\t\n");

        Description actual = visualization.reliabiltyTableToDescription(table);
        assertEquals(expected, actual, "ERROR String description from reliability table does not match");
    }

    /**
     * Tests the getReliabilities method with a non-standard E2E and with Example.txt as the input file
     * to get the reliabilities table containing the correct reliability values
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    void testGetReliabilities() {
		ReliabilityVisualization visualization = getReliabilityVisualization(0.9, 0.5, "Example.txt", 16, ScheduleChoices.PRIORITY);

        ReliabilityTable table = visualization.getReliabilities();
        
        assertNotNull(table, "ERROR Expected reliabilities data table values to not be null");
        assertFalse(table.isEmpty(), "ERROR Expected reliabilities table should not be empty ");
    }

    /**
     * Tests the getFakeDataTable method with a non-standard E2E and with Example.txt as the input file
     * to return ReliabilityTable with reliability values
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    void testGetFakeDataTable() {
		ReliabilityVisualization visualization = getReliabilityVisualization(0.9, 0.5, "Example.txt", 16, ScheduleChoices.PRIORITY);
        ReliabilityTable table = visualization.getFakeDataTable();

        assertNotNull(table, "ERROR Expected reliabilities fake data table values to not be null");
        assertFalse(table.isEmpty(), "ERROR Expected reliabilities fake data table should not be empty");
    }

	/**
	 * Tests the getnChannelsLargeNumberEdgeCase method using a large number of channels with non-standard M and E2E and with
	 * Example.txt as the input file 
	 */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    void testGetnChannelsLargeNumberEdgeCase() {
        Integer numChannels = 1000;
        ReliabilityVisualization visualization = getReliabilityVisualization(1.0, 1.0, "Example.txt", numChannels, ScheduleChoices.PRIORITY);
        String expectedNChannels = String.format("nChannels: %d\n", numChannels);
        String actualNChannels = visualization.getnChannels();
        String message = String.format("ERROR Number of channels does not match. Expected %s but was actually %s", expectedNChannels, actualNChannels);

        assertEquals(expectedNChannels, actualNChannels, message);
    }

    /**
     * Tests the getFlowsWithNodesEmptyInput method with EmptyWorkload.txt as the input file 
     * to return a newline character when the input file has zero flows.
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    void testGetFlowsWithNodesEmptyInput() {
        WorkLoad wld = new WorkLoad(0.9, 0.99, "EmptyWorkload.txt");
        WarpSystem customWarpSystem = new WarpSystem(wld, 1, ScheduleChoices.PRIORITY);
        ReliabilityVisualization visualization = new ReliabilityVisualization(customWarpSystem);
        String expectedFlowsWithNodes = "\n";
        String actualFlowsWithNodes = visualization.getFlowsWithNodes();
        String message = String.format("ERROR Flows with nodes does not match. Expected a newline character but was actually %s", actualFlowsWithNodes);
        
        assertEquals(expectedFlowsWithNodes, actualFlowsWithNodes, message);
    }

    /**
     * Tests the getFlowsAndNodesInvalidFlow method with Example.txt as the input file 
     * to return an empty array list if getFlowsAndNodes method has an "InvalidFlow".
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    void testGetFlowsAndNodesInvalidFlow() {
        WorkLoad wld = new WorkLoad(0.9, 0.99, "Example.txt");
        WarpSystem customWarpSystem = new WarpSystem(wld, 1, ScheduleChoices.PRIORITY);
        ReliabilityVisualization visualization = new ReliabilityVisualization(customWarpSystem);
        List<String> actualFlowsAndNodes = visualization.getFlowsAndNodes(Arrays.asList("InvalidFlow"));
        
        assertTrue(actualFlowsAndNodes.isEmpty(), "ERROR Invalid flow should result in an empty list");
    }

    /**
     * Tests the inStandardFormEdgeCase method with non standard E2E and with Example.txt as the input file
     * to return True when using inStandardForm method on an empty array list
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    void testInStandardFormEdgeCase() {
        ReliabilityVisualization visualization = getReliabilityVisualization(0.9, 0.5, "Example.txt", 16, ScheduleChoices.PRIORITY);
        List<String> emptyList = new ArrayList<>();
        
        assertTrue(visualization.inStandardForm(emptyList), "ERROR Empty list should be considered in standard form");
    }

    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    void testSortFlowsEdgeCase() {
        ReliabilityVisualization visualization = getReliabilityVisualization(0.9, 0.5, "Example.txt", 16, ScheduleChoices.PRIORITY);
        List<String> emptyList = new ArrayList<>();
        visualization.sortFlows(emptyList);
        assertTrue(emptyList.isEmpty(), "ERROR Sorting an empty list should result in an empty list");
    }

    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    void testListToStringArrayEdgeCase() {
        ReliabilityVisualization visualization = getReliabilityVisualization(0.9, 0.5, "Example.txt", 16, ScheduleChoices.PRIORITY);
        String[] emptyArray = {};
        String expectedOutput = "\n";
        String actualOutput = visualization.listToString(emptyArray);
        assertEquals(expectedOutput, actualOutput, "ERROR Empty input array should result in an empty string");
    }
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    void testListToStringListEdgeCase() {
        ReliabilityVisualization visualization = getReliabilityVisualization(0.9, 0.5, "Example.txt", 16, ScheduleChoices.PRIORITY);
        List<String> emptyList = new ArrayList<>();
        String expectedOutput = "\n";
        String actualOutput = visualization.listToString(emptyList);
        assertEquals(expectedOutput, actualOutput, "ERROR Empty input list should result in an empty string");
    }

    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    void testReliabiltyTableToDescriptionEdgeCase() {
        ReliabilityVisualization visualization = getReliabilityVisualization(0.9, 0.5, "Example.txt", 16, ScheduleChoices.PRIORITY);
        ReliabilityTable emptyTable = new ReliabilityTable();
        Description expected = new Description();
        Description actual = visualization.reliabiltyTableToDescription(emptyTable);
        assertEquals(expected, actual, "ERROR Empty reliability table should result in an empty description");
    }

    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    void testGetReliabilitiesEdgeCase() {
        ReliabilityVisualization visualization = getReliabilityVisualization(0.9, 0.5, "EmptyWorkload.txt", 16, ScheduleChoices.PRIORITY);
        ReliabilityTable table = visualization.getReliabilities();
        assertNotNull(table, "Expected reliability table with empty workload to not be null");
    }

    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    void testGetFakeDataTableEdgeCase() {
        ReliabilityVisualization visualization = getReliabilityVisualization(0.9, 0.5, "EmptyWorkload.txt", 16, ScheduleChoices.PRIORITY);
        ReliabilityTable table = visualization.getFakeDataTable();
        assertNotNull(table, "Expected fake data table with empty workload to not be null");
    }

}

