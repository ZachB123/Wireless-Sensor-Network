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
	
	
	private static final String HEADER_BOILERPLATE = "Reliability Analysis for graph ";
	private static final String SCHEDULER_BOILERPLATE = "Scheduler Name: ";
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
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	public void testGetHeaderExample() {
		String fileName = "Example";
		String filePath = fileName + ".txt";
		ReliabilityVisualization viz = getReliabilityVisualization(0.9, 0.99, filePath, 16, ScheduleChoices.PRIORITY);
		
		String expected = HEADER_BOILERPLATE + fileName + "\n";
		String actual = viz.getHeader();
		String message = String.format("ERROR graph headers do not match. Expected graph name to be %s but it was actually %s.", expected, actual);
		
		assertEquals(expected, actual, message);
	}
	
	/**
	 * Tests the getHeader method with numFaults defined and StressTest4.txt as the input file
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	public void testGetHeaderStressTest4WithNumFaults() {
		String fileName = "StressTest4";
		String filePath = fileName + ".txt";
		ReliabilityVisualization viz = getReliabilityVisualization(5, 0.9, 0.99, filePath, 16, ScheduleChoices.PRIORITY);
		
		String expected = HEADER_BOILERPLATE + fileName + "\n";
		String actual = viz.getHeader();
		String message = String.format("ERROR graph headers do not match. Expected graph name to be %s but it was actually %s.", expected, actual);
		
		// We cannot test a non existent graphName because System.exit(-1) is called so the program terminates
		assertEquals(expected, actual, message);
	}
	
	/**
	 * Tests the getHeader method using different graph names
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	public void testGetHeaderDiffer() {
		String fileName = "Example1a";
		String filePath = fileName + ".txt";
		ReliabilityVisualization viz = getReliabilityVisualization(5, 0.9, 0.99, filePath, 16, ScheduleChoices.PRIORITY);
		
		String expectedGraphName = "Example1A";
		String expected = HEADER_BOILERPLATE + expectedGraphName + "\n";
		String actual = viz.getHeader();
		String message = String.format("ERROR graph headers do not match. Expected graph name to be %s but it was actually %s.", expected, actual);
		
		// We cannot test a non existent graphName because System.exit(-1) is called so the program terminates
		assertEquals(expected, actual, message);
	}
	
	/**
	 * Tests the getScheduler method for the Priority scheduler
	 */
	//TESTING getScheduler
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	public void testGetScheulerPriority() {
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
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	public void testGetScheulerRM() {
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
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	public void testGetScheulerDM() {
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
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	public void testGetMExample() {
		ReliabilityVisualization viz = getReliabilityVisualization(0.9, 0.99, "Example.txt", 16, ScheduleChoices.PRIORITY);
		String expected = "M: 0.90\n";
		String actual = viz.getM();
		String message = String.format("ERROR M does not match. Expected %s but was actually %s", expected, actual);
		
		assertEquals(expected, actual, message);

	}
	
	/**
	 * Tests the getM method with StressTest4.txt as the input file
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	public void testGetMStressTest4() {
		ReliabilityVisualization viz = getReliabilityVisualization(0.9, 0.99, "StressTest4.txt", 16, ScheduleChoices.PRIORITY);
		String expected = "M: 0.90\n";
		String actual = viz.getM();
		String message = String.format("ERROR M does not match. Expected %s but was actually %s", expected, actual);
		
		assertEquals(expected, actual, message);

	}
	
	/**
	 * Tests the getM method with numFaults defined
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	public void testGetMWithNumFaults() {
		ReliabilityVisualization viz = getReliabilityVisualization(5, 0.9, 0.99, "Example.txt", 16, ScheduleChoices.PRIORITY);
		String expected = "M: 0.90\n";
		String actual = viz.getM();
		String message = String.format("ERROR M does not match. Expected %s but was actually %s", expected, actual);
		
		assertEquals(expected, actual, message);

	}
	
	/**
	 * Tests the getM method with a non-standard M
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	public void testGetMOther() {
		ReliabilityVisualization viz = getReliabilityVisualization(0.5, 0.99, "Example.txt", 16, ScheduleChoices.PRIORITY);
		String expected = "M: 0.90\n";
		String actual = viz.getM();
		String message = String.format("ERROR M does not match. Expected %s but was actually %s", expected, actual);
		
		assertEquals(expected, actual, message);

	}
	
	/**
	 * Tests the getE2E method with Example.txt as the input file
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	public void testGetE2EExample() {
		ReliabilityVisualization viz = getReliabilityVisualization(0.9, 0.99, "Example.txt", 16, ScheduleChoices.PRIORITY);
		String expected = "E2E: 0.99\n";
		String actual = viz.getE2E();
		String message = String.format("ERROR E2E does not match. Expected %s but was actually %s", expected, actual);
		
		assertEquals(expected, actual, message);

	}
	
	/**
	 * Tests the getE2E method with StressTest4.txt as the input file
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	public void testGetE2EStressTest4() {
		ReliabilityVisualization viz = getReliabilityVisualization(0.9, 0.99, "StressTest4.txt", 16, ScheduleChoices.PRIORITY);
		String expected = "E2E: 0.99\n";
		String actual = viz.getE2E();
		String message = String.format("ERROR E2E does not match. Expected %s but was actually %s", expected, actual);
		
		assertEquals(expected, actual, message);

	}
	
	/**
	 * Tests the getE2E method with numFaults defined
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	public void testGetE2EWithNumFaults() {
		ReliabilityVisualization viz = getReliabilityVisualization(5, 0.9, 0.99, "Example.txt", 16, ScheduleChoices.PRIORITY);
		String expected = "E2E: 0.99\n";
		String actual = viz.getE2E();
		String message = String.format("ERROR E2E does not match. Expected %s but was actually %s", expected, actual);
		
		assertEquals(expected, actual, message);

	}
	
	/**
	 * Tests the getE2E method with a non-standard E2E
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	public void testGetE2EOther() {
		ReliabilityVisualization viz = getReliabilityVisualization(0.9, 0.5, "Example.txt", 16, ScheduleChoices.PRIORITY);
		String expected = "E2E: 0.99\n";
		String actual = viz.getE2E();
		String message = String.format("ERROR E2E does not match. Expected %s but was actually %s", expected, actual);
		
		assertEquals(expected, actual, message);
		
	}
	
	/**
	 * Tests the getnChannels method.
	 */
    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    void testGetnChannels() {
        Integer numChannels = 3;
        ReliabilityVisualization visualization = getReliabilityVisualization(1.0, 1.0, "Example.txt", numChannels, ScheduleChoices.PRIORITY);
        String expectedNChannels = String.format("nChannels: %d\n", numChannels);
        String actualNChannels = visualization.getnChannels();
        String message = "The actual number of channels does not match the expected value.";
        assertEquals(expectedNChannels, actualNChannels, message);
    }
	
    /**
     * Tests getting the flows with nodes.
     */
    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    void testGetFlowsWithNodes() {
        WorkLoad wld = new WorkLoad(0.9, 0.99, "Example.txt");
        WarpSystem customWarpSystem = new WarpSystem(wld, 1, ScheduleChoices.PRIORITY);
        ReliabilityVisualization visualization = new ReliabilityVisualization(customWarpSystem);
        String expectedFlowsWithNodes = "F0:A\tF0:B\tF0:C\tF1:C\tF1:B\tF1:A\t\n";
        String actualFlowsWithNodes = visualization.getFlowsWithNodes();
        String message = "The actual flows with nodes do not match the expected flows.";
        assertEquals(expectedFlowsWithNodes, actualFlowsWithNodes, message);
    }

    /**
     * Tests getting the flows and nodes.
     */
    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    void testGetFlowsAndNodes() {
        WorkLoad wld = new WorkLoad(0.9, 0.99, "Example.txt");
        WarpSystem customWarpSystem = new WarpSystem(wld, 1, ScheduleChoices.PRIORITY);
        ReliabilityVisualization visualization = new ReliabilityVisualization(customWarpSystem);
        List<String> expectedFlowsAndNodes = Arrays.asList("F1:C", "F1:B", "F1:A");
        List<String> actualFlowsAndNodes = visualization.getFlowsAndNodes(Arrays.asList("F1"));
        String message = "The actual flows and nodes do not match the expected flows and nodes.";
        assertEquals(expectedFlowsAndNodes, actualFlowsAndNodes, message);
    }
	
    /**
     * Tests if the flows are in standard form.
     */
    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    void testInStandardForm() {
		ReliabilityVisualization visualization = getReliabilityVisualization(0.9, 0.5, "Example.txt", 16, ScheduleChoices.PRIORITY);
    	
        List<String> flowsInStandardForm = Arrays.asList("F1", "F2", "F3");
        List<String> flowsNotInStandardForm = Arrays.asList("F1", "Flow2", "F3");

        assertTrue(visualization.inStandardForm(flowsInStandardForm), "All flows should be in standard form.");
        assertFalse(visualization.inStandardForm(flowsNotInStandardForm), "Not all flows are in standard form");
    }

    /**
     * Tests sorting of the flows.
     */
    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    void testSortFlows() {
		ReliabilityVisualization visualization = getReliabilityVisualization(0.9, 0.5, "Example.txt", 16, ScheduleChoices.PRIORITY);

        List<String> unsortedFlows = Arrays.asList("F5", "F2", "F10", "F1");
        List<String> sortedFlows = Arrays.asList("F1", "F2", "F5", "F10");

        visualization.sortFlows(unsortedFlows);
        String message = "The sorted flows do not match the expected sorted flows.";

        assertEquals(sortedFlows, unsortedFlows, message);
    }

    /**
     * Tests list to string conversion for arrays.
     */
    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    void testListToStringArray() {
		ReliabilityVisualization visualization = getReliabilityVisualization(0.9, 0.5, "Example.txt", 16, ScheduleChoices.PRIORITY);

        String[] inputArray = {"A", "B", "C"};
        String expectedOutput = "A\tB\tC\t\n";
        String actualOutput = visualization.listToString(inputArray);
        String message = "The converted string from the array does not match the expected output.";

        assertEquals(expectedOutput, actualOutput, message);
    }

    /**
     * Tests list to string conversion for lists.
     */
    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    void testListToStringList() {
		ReliabilityVisualization visualization = getReliabilityVisualization(0.9, 0.5, "Example.txt", 16, ScheduleChoices.PRIORITY);

        List<String> inputList = Arrays.asList("A", "B", "C");
        String expectedOutput = "A\tB\tC\t\n";
        String actualOutput = visualization.listToString(inputList);
        String message = "The converted string from the list does not match the expected output.";

        assertEquals(expectedOutput, actualOutput, message);
    }

    /**
     * Tests the conversion of a reliability table to a description.
     */
    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
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
        String message = "The actual description does not match the expected description.";

        assertEquals(expected, actual, message);
    }

    /**
     * Tests getting the reliabilities.
     */
    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    void testGetReliabilities() {
		ReliabilityVisualization visualization = getReliabilityVisualization(0.9, 0.5, "Example.txt", 16, ScheduleChoices.PRIORITY);

        ReliabilityTable table = visualization.getReliabilities();

        assertNotNull(table, "The reliability table should not be null.");
        assertFalse(table.isEmpty(), "The reliability table should not be empty.");
    }

    /**
     * Tests getting the fake data table.
     */
    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    void testGetFakeDataTable() {
		ReliabilityVisualization visualization = getReliabilityVisualization(0.9, 0.5, "Example.txt", 16, ScheduleChoices.PRIORITY);

        ReliabilityTable table = visualization.getFakeDataTable();

        assertNotNull(table, "The fake data table should not be null.");
        assertFalse(table.isEmpty(), "The fake data table should not be empty.");
    }
	
	
	
	
}
