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
	 * Tests the getTitle method using different graph names
	 */
	@Test
	@Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
	public void testGetTitleDiffer() {
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
    void testGetnChannelsWithLargeNumberOfChannelsAndNonStandardParamaters() {
        Integer numChannels = 1000;
        ReliabilityVisualization visualization = getReliabilityVisualization(1.0, 1.0, "Example.txt", numChannels, ScheduleChoices.PRIORITY);
        String expectedNChannels = String.format("nChannels: %d\n", numChannels);
        String actualNChannels = visualization.getnChannels();
        String message = String.format("ERROR Number of channels does not match. Expected %s but was actually %s", expectedNChannels, actualNChannels);

        assertEquals(expectedNChannels, actualNChannels, message);
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
        String message = "ERROR Invalid flow should result in an empty list";
        assertTrue(actualFlowsAndNodes.isEmpty(), message);
    }

    /**
     * Test the behavior of the getReliabilities() method with an empty workload.
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    void testGetReliabilitiesWithEmptyWorkload() {
        ReliabilityVisualization visualization = getReliabilityVisualization(0.9, 0.5, "EmptyWorkload.txt", 16, ScheduleChoices.PRIORITY);
        ReliabilityTable table = visualization.getReliabilities();
        String message = "ERROR Reliability table with empty workload should not be null";
        assertNotNull(table, message);
    }

    /**
     * Test the behavior of the getFakeDataTable() method with an empty workload.
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    void testGetFakeDataTableWithEmptyWorkload() {
        ReliabilityVisualization visualization = getReliabilityVisualization(0.9, 0.5, "EmptyWorkload.txt", 16, ScheduleChoices.PRIORITY);
        ReliabilityTable table = visualization.getFakeDataTable();
        String message = "ERROR Fake data table with empty workload should not be null";
        assertNotNull(table, message);    
    }
    
    /**
     * Tests the conversion of a populated ReliabilityTable to a 2D array of Strings.
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testReliabilityTableTo2dArray() {
        ReliabilityTable table = new ReliabilityTable();

        // Populate the table with data
        ReliabilityRow row1 = new ReliabilityRow();
        row1.add(0.5);
        row1.add(0.7);
        table.add(row1);

        ReliabilityRow row2 = new ReliabilityRow();
        row2.add(0.6);
        row2.add(0.8);
        table.add(row2);

        String[][] expectedArray = {
            {"0.5", "0.7"},
            {"0.6", "0.8"}
        };

        ReliabilityVisualization visualization = getReliabilityVisualization(0.9, 0.5, "Example.txt", 16, ScheduleChoices.PRIORITY);
        String[][] actualArray = visualization.reliabilityTableTo2dArray(table);
        String message = String.format("ERROR Number of channels does not match. Expected %s but was actually %s",
                Arrays.deepToString(expectedArray), Arrays.deepToString(actualArray));
        assertArrayEquals(expectedArray, actualArray, message);    
    }

    /**
     * Tests the creation of a header for the ReliabilityVisualization with a given file name.
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testCreateHeader() {
        Description expectedHeader = new Description();
        expectedHeader.add("Reliability Analysis for graph Example\n");
        expectedHeader.add("Scheduler Name: Priority\n");
        expectedHeader.add("M: 0.90\n");
        expectedHeader.add("E2E: 0.50\n");
        expectedHeader.add("nChannels: 16\n");
        ReliabilityVisualization visualization = getReliabilityVisualization(0.9, 0.5, "Example.txt", 16, ScheduleChoices.PRIORITY);
        Description actualHeader = visualization.createHeader();
        String message = String.format("ERROR Headers do not match. Expected %s but was actually %s",
                expectedHeader, actualHeader);
        assertEquals(expectedHeader, actualHeader, message);    
    }

    /**
     * Tests the creation of visualization data for a given input file.
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testCreateVisualizationData() {
        int expectedSize = 100;
        String[][] expectedData = new String[expectedSize][];

        ReliabilityVisualization visualization = getReliabilityVisualization(0.9, 0.5, "Example.txt", 16, ScheduleChoices.PRIORITY);
        String[][] actualData = visualization.createVisualizationData();

        String message = String.format("ERROR The size of the actual data does not match the expected size. Expected %s but was actually %s",
                expectedData.length, actualData.length);
        assertEquals(expectedData.length, actualData.length, message);
    }


    /**
     * Tests the creation of a column header for the ReliabilityVisualization with a given file name.
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testCreateColumnHeader() {
    	String[] expectedColumnHeader = {"F0:A", "F0:B", "F0:C", "F1:C", "F1:B", "F1:A"};
    	ReliabilityVisualization visualization = getReliabilityVisualization(0.9, 0.5, "Example.txt", 16, ScheduleChoices.PRIORITY);
        String[] actualColumnHeader = visualization.createColumnHeader();
        String message = String.format("ERROR The actual column header does not match the expected column header. Expected %s but was actually %s",
                Arrays.toString(expectedColumnHeader), Arrays.toString(actualColumnHeader));
        assertArrayEquals(expectedColumnHeader, actualColumnHeader, message);    
    }

    /**
     * Tests the conversion of an empty ReliabilityTable to a 2D array of Strings.
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testReliabilityTableTo2dArrayEmptyTable() {
        ReliabilityTable table = new ReliabilityTable();
        String[][] expectedArray = {};

        ReliabilityVisualization visualization = getReliabilityVisualization(0.9, 0.5, "Example.txt", 16, ScheduleChoices.PRIORITY);
        String[][] actualArray = visualization.reliabilityTableTo2dArray(table);
        String message = String.format("ERROR Number of channels does not match. Expected %s but was actually %s",
                Arrays.deepToString(expectedArray), Arrays.deepToString(actualArray));
        assertArrayEquals(expectedArray, actualArray, message);
    }

    /**
     * Tests the creation of a header for the ReliabilityVisualization with an empty file name.
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testCreateHeaderEmptyFileName() {
        Description expectedHeader = new Description();
        expectedHeader.add("Reliability Analysis for graph null\n");
        expectedHeader.add("Scheduler Name: Priority\n");
        expectedHeader.add("M: 0.90\n");
        expectedHeader.add("E2E: 0.50\n");
        expectedHeader.add("nChannels: 16\n");

        ReliabilityVisualization visualization = getReliabilityVisualization(0.9, 0.5, "", 16, ScheduleChoices.PRIORITY);
        Description actualHeader = visualization.createHeader();
        String message = String.format("ERROR Headers do not match. Expected %s but was actually %s",
                expectedHeader, actualHeader);
        assertEquals(expectedHeader, actualHeader, message);    
    }

    /**
     * Tests the creation of visualization data for an empty input file.
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testCreateVisualizationDataEmptyFile() {
        String[][] expectedData = {{}};

        ReliabilityVisualization visualization = getReliabilityVisualization(0.9, 0.5, "EmptyWorkload.txt", 16, ScheduleChoices.PRIORITY);
        String[][] actualData = visualization.createVisualizationData();

        String message = String.format("ERROR The size of the actual data does not match the expected size. Expected %s but was actually %s",
                Arrays.deepToString(expectedData), Arrays.deepToString(actualData));
        assertArrayEquals(expectedData, actualData, message);    
    }

    /**
     * Tests the creation of a column header for the ReliabilityVisualization with an empty input file.
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.SECONDS)
    public void testCreateColumnHeaderEmptyFile() {
        String[] expectedColumnHeader = {};

        ReliabilityVisualization visualization = getReliabilityVisualization(0.9, 0.5, "EmptyWorkload.txt", 16, ScheduleChoices.PRIORITY);
        String[] actualColumnHeader = visualization.createColumnHeader();

        String message = String.format("ERROR The actual column header does not match the expected column header. Expected %s but was actually %s",
                Arrays.toString(expectedColumnHeader), Arrays.toString(actualColumnHeader));
        assertArrayEquals(expectedColumnHeader, actualColumnHeader, message);    
    }

}

