package edu.uiowa.cs.warp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import edu.uiowa.cs.utilities.Utilities;

class WorkLoadTest {
	
    
	/**
	 * Tests to see if a flow is correctly added to a workload
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testAddFlow() {
		
		//Initialize new workload and flowname
		WorkLoad wld = new WorkLoad(0.9,0.99,"Example1a.txt");
		String flowName = "F2";
		//Add flow to workload
		wld.addFlow(flowName);
		
		//check flows in the created workload, and return true if flowName
		//is in it
		assertTrue(wld.getFlows().containsKey(flowName), String.format("Expected true but got false"));
	}
	
	/**
	 * Tests if added flow with same name overwrites existing
	 * flow
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testAddFlowOverwritten() {
		WorkLoad wld = new WorkLoad(0.9,0.99,"Example1a.txt");
		String flowName = "F0";
		
		wld.addFlow(flowName);
		
		assertTrue(wld.getFlows().containsKey(flowName), String.format("Expected true but got false"));
		
	}
	
    /**
     * Tests that a node can be correctly added to a flow
     * in a workload
     */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testAddNodeToFlow() {
		//Initialize new workload, flowname, and nodename
		WorkLoad wld = new WorkLoad(0.9,0.99,"Example1a.txt");
		String flowName = "F0";
		String nodeName = "A";
				
		//Adds a node to the flow in workload
		wld.addNodeToFlow(flowName, nodeName);
		
		//Checking to see if the added node is present in workload.
		assertTrue(wld.getNodes().containsKey(nodeName),String.format("Expected true but got false"));
	}
	
	/**
	 * Tests to see if a node will successfully get added to a
	 * flow in an empty workload
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testAddNodeToFlowEmpty() {
		WorkLoad wld = new WorkLoad(0.9,0.99,"EmptyWorkload.txt");
		String flowName = "F0";
		String nodeName = "A";
		
		wld.addFlow(flowName);
		wld.addNodeToFlow(flowName, nodeName);
		
		assertTrue(wld.getNodes().containsKey(nodeName), String.format("Expected true but got false"));
	}
	
	/**
	 * Tests that a priority can be correctly set to
	 * flowName.
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testSetFlowPriority() {
		//Initialize new workload, flowname, and priority
		WorkLoad wld = new WorkLoad(0.9, 0.99, "Example1a.txt");
		String flowName = "F0";
		Integer priority = 0;
		//sets priority to given flow
		wld.setFlowPriority(flowName, priority);
	    int actual = wld.getFlowPriority(flowName);
		//checks to see if the priority has been correctly set to flowName
		assertEquals(priority, actual,String.format("Expected %d Flow Priority but got %d.", priority, actual));
	}
	
    /**
     * Tests to see if the amount of flow transmission attempts
     * per link is successfully gathered.
     */
	@Test 
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testGetFlowTxAttemptsPerLink() {
		WorkLoad wld = new WorkLoad(0.9, 0.99, "Example1a.txt");
		String flowName = "F0";
		
		int numAttempts = wld.getFlowTxAttemptsPerLink(flowName);
		int expected = 3;
		
		
		assertEquals(expected,numAttempts, String.format("Expected %d transmission attempts but got %d.", expected, numAttempts));
	}
	
    /**
     * Expects to accurately get flow names in the order they are 
     * originally in 
     */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testGetFlowNames() {
		WorkLoad wld = new WorkLoad(0.9,0.99, "Example1a.txt");
		String[] flowNames = {"F0", "F1"};
		
		List<String> expected = new ArrayList<String>(Arrays.asList(flowNames));
		
		List<String> actual = wld.getFlowNamesInOriginalOrder();
		
		assertEquals(expected, actual, String.format("Expected Flow names %s, but got %s", expected, actual));
	}
	
	/**
	 * Tests to see if any flow names are gotten in 
	 * an empty workload
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testGetFlowNamesEmpty() {
		WorkLoad wld = new WorkLoad(0.9,0.99,"EmptyWorkload.txt");
		String[] flowNames = {};
		
		List<String> expected = new ArrayList<String>(Arrays.asList(flowNames));
		
		List<String> actual = wld.getFlowNamesInOriginalOrder();
		
		assertEquals(expected, actual, String.format("Expected Flow names %s, but got %s", expected, actual));
	}
	
	/**
	 * Tests to get index of a node that already exists 
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testGetNodeIndexExisting() {
		WorkLoad wld = new WorkLoad(0.9,0.99,"Example1a.txt");
		
		String nodeName = "B";
		
		int actual = wld.getNodeIndex(nodeName);
		int expected = 1;
		
		assertEquals(expected,actual,String.format("Expected Node index %d but got %d.", expected, actual));
	}
	
	/**
	 * When testing for a node where the index doesn't exist
	 * in the flow, it should return 0
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testGetNodeIndexNonExisting() {
		WorkLoad wld = new WorkLoad(0.9,0.99,"Example1a.txt");
		
		String nodeName = "null";
		
		int actual = wld.getNodeIndex(nodeName);
		int expected = 0;
		
		assertEquals(expected,actual,String.format("Expected node index %d but got %d.", expected, actual));
	}
	
	/**
	 * Tests if the number of transmission attempts per link
	 * is accurate.
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testGetNumTxAttemptsPerLink() {
		WorkLoad wld = new WorkLoad(0.9,0.99, "Example1a.txt");
		String flowName = "F0";
		
		Integer[] expected = {3,3,0};
		Integer[] actual = wld.getNumTxAttemptsPerLink(flowName);

		assertArrayEquals(expected, actual, String.format("expected %s but got %s", printArrayHelper(expected), printArrayHelper(actual)));
	}	
	
	/**
	 * Tests to see if maxFlowLength accurately gets the right 
	 * flow that has the most nodes
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testMaxFlowLength() {
		//initialize new workLoad
		WorkLoad wld = new WorkLoad(0.9,0.99,"Example2.txt");
		//6 is the highest length of a flow in Example2.txt
		int expected = 6;
		int actual = wld.maxFlowLength();
		
		assertEquals(expected,actual);
	}
	
	/**
	 * Tests to see if 0 is gotten for the max length in
	 * an empty workload.
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testMaxFlowLengthEmpty() {
		//initialize new workLoad
		WorkLoad wld = new WorkLoad(0.9,0.99,"EmptyWorkload.txt");
		//6 is the highest length of a flow in Example2.txt
		int expected = 0;
		int actual = wld.maxFlowLength();
		
		assertEquals(expected,actual);
	}
	
	/**
	 * Tests a flow not found in the file.
	 * We expect an exception to be thrown
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testGetTotalTxAttemptsInFlowNonExistentFlow() {
		// Create the WorkLoad and flowName
		WorkLoad wld = new WorkLoad(0.9, 0.99, "Example.txt");
		String flowName = "Nonexistent Flow";

		// If the lambda does not throw an IndexOutOfBoundsException,
		// then the test will fail
		Exception thrown = assertThrows(IndexOutOfBoundsException.class, () -> wld.getTotalTxAttemptsInFlow(flowName),
				"An IndexOutOfBounds exception was expected but not thrown.");
		
		// expected and actual error messages
		String expectedErrorMessage = "Index -1 out of bounds for length 0";
		String actualErrorMessage = thrown.getMessage();

		// Checking to make sure the error message from the exception is what I expected
		assertEquals(expectedErrorMessage, actualErrorMessage, String.format(
				"Error message is incorrect. Expected %s but got %s", expectedErrorMessage, actualErrorMessage));
	}

	/**
	 * Tests that the number of transmission for a workload with 
	 * 0 numfaults
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testGetTotalTxAttemptsInFlowExistentFlow1() {
		// Create the WorkLoad and flowName
		// Default Num Faults is 0
		WorkLoad wld = new WorkLoad(0, 0.9, 0.99, "Example.txt");
		String flowName = "F0";

		// Create expected and actual values
		System.out.println(String.format("Num: %d", wld.getTotalTxAttemptsInFlow(flowName)));
		int expected = 4;
		int actual = wld.getTotalTxAttemptsInFlow(flowName);

		assertSame(expected, actual, String.format("Expected %d transmission attempts but got %d.", expected, actual));
	}

	/**
	 * Stress tests the number of transmissions in a workload
	 * 0 faults are set
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testGetTotalTxAttemptsInFlowExistentFlow2() {
		// Create the WorkLoad and flowName
		WorkLoad wld = new WorkLoad(0, 0.9, 0.99, "StressTest.txt");
		String flowName = "AF4";

		// Create expected and actual values
		int expected = 11;
		int actual = wld.getTotalTxAttemptsInFlow(flowName);

		assertSame(expected, actual, String.format("Expected %d transmission attempts but got %d.", expected, actual));
	}

	/**
	 * Tests a flow in the file with a specified numFaults.
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testGetTotalTxAttemptsInFlowExistentFlowNumFaults1() {
		// Create the WorkLoad and flowName
		// Default Num Faults is 0
		WorkLoad wld = new WorkLoad(6, 0.9, 0.99, "Example.txt");
		String flowName = "F0";

		// Create expected and actual values
		System.out.println(String.format("Num: %d", wld.getTotalTxAttemptsInFlow(flowName)));
		int expected = 14;
		int actual = wld.getTotalTxAttemptsInFlow(flowName);

		assertSame(expected, actual, String.format("Expected %d transmission attempts but got %d.", expected, actual));
	}

	/**
	 * Stress Tests a flow in the file with a specified numFaults.
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testGetTotalTxAttemptsInFlowExistentFlowNumFaults2() {
		// Create the WorkLoad and flowName
		WorkLoad wld = new WorkLoad(5, 0.9, 0.99, "StressTest.txt");
		String flowName = "AF4";

		// Create expected and actual values
		int expected = 42;
		int actual = wld.getTotalTxAttemptsInFlow(flowName);

		assertSame(expected, actual, String.format("Expected %d transmission attempts but got %d.", expected, actual));
	}

	/**
	 * Testing that the priority is set to the default priority
	 * if the flow does not exist
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testGetFlowPriorityNonExistentFlow() {
		// Create the WorkLoad and flowName
		WorkLoad wld = new WorkLoad(0.9, 0.99, "Example.txt");
		String flowName = "Nonexistent Flow";

		// Create expected and actual values
		// when a node does not exist a blank flow is created and
		// the priority is set by default to 0
		int expected = 0;
		int actual = wld.getFlowPriority(flowName);

		assertSame(expected, actual, String.format("Expected %d priority but got %d.", expected, actual));
	}

	/**
	 * If no priority is specified in the file we test that
	 * it is set to the default priority
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testGetFlowPriorityDefaultPriority() {
		// Create the WorkLoad and flowName
		WorkLoad wld = new WorkLoad(0.9, 0.99, "Example.txt");
		String flowName = "F0";

		// Create expected and actual values
		// 0 is the default priority and the priority is not specified in Example.txt
		int expected = 0;
		int actual = wld.getFlowPriority(flowName);

		assertSame(expected, actual, String.format("Expected %d priority but got %d.", expected, actual));
	}
	
	/**
	 * Here we test that the priorities specified in the workload file
	 * are properly transferred to the object
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testGetFlowPriorityCustomPriority1() {
		// Create the WorkLoad
		WorkLoad wld = new WorkLoad(0.9, 0.99, "Example1A.txt");
		
		// Create expected and actual values
		{
			String flowName = "F0";
			// 0 is the priority set for F0 in Example1A.txt
			int expected = 0;
			int actual = wld.getFlowPriority(flowName);

			assertSame(expected, actual, String.format("Expected %d priority but got %d.", expected, actual));
		}
		
		{
			String flowName = "F1";
			// 1 is the priority set for F1 in Example1A.txt
			int expected = 1;
			int actual = wld.getFlowPriority(flowName);
			
			assertSame(expected, actual, String.format("Expected %d priority but got %d.", expected, actual));
		}
		
	}
	
	/**
	 * We test more custom priorities with a file I made
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testGetFlowPriorityCustomPriority2() {
		// Create the WorkLoad and flowName
		WorkLoad wld = new WorkLoad(0.9, 0.99, "CustomWorkloadByZach.txt");
		
		// Create expected and actual values
		{
			String flowName = "RandomFlow1";
			// 70 is the priority set for RandomFlow1 in CustomWorkloadByZach.txt
			int expected = 70;
			int actual = wld.getFlowPriority(flowName);

			assertSame(expected, actual, String.format("Expected %d priority but got %d.", expected, actual));
		}
		
		{
			String flowName = "RandomFlow2";
			// 48 is the priority set for RandomFlow2 in CustomWOrkloadByZach.txt
			int expected = 48;
			int actual = wld.getFlowPriority(flowName);
			
			assertSame(expected, actual, String.format("Expected %d priority but got %d.", expected, actual));
		}
		
		{
			String flowName = "RandomFlow3";
			// 23 is the priority set for RandomFlow3 in CustomWOrkloadByZach.txt
			int expected = 23;
			int actual = wld.getFlowPriority(flowName);
			
			assertSame(expected, actual, String.format("Expected %d priority but got %d.", expected, actual));
		}
	}
	
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testGetFlowPriorityWithNodes() {
		// Create the WorkLoad and flowName
		WorkLoad wld = new WorkLoad(0.9, 0.99, "Example1a.txt");
		
		String nodeName = "B";
		{
			// Set up values
			String flowName = "F0";
			int expected = 0;
			int actual = wld.getFlowPriority(flowName, nodeName);
		
			assertSame(expected, actual, String.format("Expected %d priority but got %d.", expected, actual));
		}
		
		{
			// Set up values
			String flowName = "F1";
			int expected = 0;
			int actual = wld.getFlowPriority(flowName, nodeName);
		
			assertSame(expected, actual, String.format("Expected %d priority but got %d.", expected, actual));
		}
	}
	
	/**
	 * RMorder sorts the flows by period
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testSetFlowsInRMOrder1() {
		// Create the WorkLoad
		WorkLoad wld = new WorkLoad(0.9, 0.99, "Example1a.txt");
		
		// We are testing that flows are sorted in order of period then priority as the second key
		wld.setFlowsInRMorder();
		
		String[] expectedArray = {"F0", "F1"};
		// convert and array to arraylist
		List<String> expected = new ArrayList<String>(Arrays.asList(expectedArray));
		// The actual contains a list of the names of the flows
		List<String> actual = wld.getFlowNamesInPriorityOrder();
		
		// first check the sizes are equals so the loop won't crash
		int expectedLength = expected.size();
		int actualLength = actual.size();
		assertSame(expectedLength, actualLength, String.format("Length of expected array is %d but we got %d", expectedLength, actualLength));
		
		// check to make sure the content of each array is equal
		for(int i = 0; i < expectedLength; i++) {
			String expectedString = expected.get(i);
			String actualString = actual.get(i);
			assertEquals(expectedString, actualString, String.format("Expected %s but got %s.", expectedString, actualString));
		}
	}
	
	/**
	 * Another test on my customworkload to make sure that
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testSetFlowsInRMOrder2() {
		// Create the WorkLoad and flowName
		WorkLoad wld = new WorkLoad(0.9, 0.99, "CustomWorkloadByZach.txt");
		
		// We are testing that flows are sorted in order of period then priority as the second key
		wld.setFlowsInRMorder();
		String[] expectedArray = {"RandomFlow1", "RandomFlow3", "RandomFlow2"};
		List<String> expected = new ArrayList<String>(Arrays.asList(expectedArray));
		// The actual contains a list of the names of the flows
		List<String> actual = wld.getFlowNamesInPriorityOrder();
		
		// ensuring the lengths are the same so the loop won't crash
		int expectedLength = expected.size();
		int actualLength = actual.size();
		assertSame(expectedLength, actualLength, String.format("Length of expected array is %d but we got %d", expectedLength, actualLength));
		
		// making sure the arrays are actually equal
		for(int i = 0; i < expectedLength; i++) {
			String expectedString = expected.get(i);
			String actualString = actual.get(i);
			assertEquals(expectedString, actualString, String.format("Expected %s but got %s.", expectedString, actualString));
		}
	}
	
	/**
	 * The secondary key is priority so I created a workload
	 * that has all the same periods to test this
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testSetFlowsInRMOrderSamePeriod() {
		// Create the WorkLoad and flowName
		WorkLoad wld = new WorkLoad(0.9, 0.99, "SamePeriod.txt");
		
		// We are testing that flows are sorted in order of period then priority as the second key
		// Since the period for this file is the same we must check the priority
		wld.setFlowsInRMorder();
		String[] expectedArray = {"SamePeriod3", "SamePeriod2", "SamePeriod1"};
		List<String> expected = new ArrayList<String>(Arrays.asList(expectedArray));
		// The actual contains a list of the names of the flows
		List<String> actual = wld.getFlowNamesInPriorityOrder();
		
		// make sure the sizes are the same so it won't crash
		int expectedLength = expected.size();
		int actualLength = actual.size();
		assertSame(expectedLength, actualLength, String.format("Length of expected array is %d but we got %d", expectedLength, actualLength));
		
		// ensure the arrays are equal
		for(int i = 0; i < expectedLength; i++) {
			String expectedString = expected.get(i);
			String actualString = actual.get(i);
			assertEquals(expectedString, actualString, String.format("Expected %s but got %s.", expectedString, actualString));
		}
	}
	
	/**
	 * If period and priority are same then we test that the flows
	 * appear in the order that they are defined in the file
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testSetFlowsInRMOrderSamePeriodSamePriority() {
		// Create the WorkLoad and flowName
		WorkLoad wld = new WorkLoad(0.9, 0.99, "Example.txt");
		
		// We are testing that flows are sorted in order of period then priority as the second key
		// Since the period for this file is the same we must check the priority
		// Since priority same flows come in default order
		wld.setFlowsInRMorder();
		String[] expectedArray = {"F0", "F1"};
		List<String> expected = new ArrayList<String>(Arrays.asList(expectedArray));
		// The actual contains a list of the names of the flows
		List<String> actual = wld.getFlowNamesInPriorityOrder();
		
		// test if the arrays have the same lengths
		int expectedLength = expected.size();
		int actualLength = actual.size();
		assertSame(expectedLength, actualLength, String.format("Length of expected array is %d but we got %d", expectedLength, actualLength));
		
		// test that the arrays are equal
		for(int i = 0; i < expectedLength; i++) {
			String expectedString = expected.get(i);
			String actualString = actual.get(i);
			assertEquals(expectedString, actualString, String.format("Expected %s but got %s.", expectedString, actualString));
		}
	}
	
	/**
	 * Here we test to ensure that the nodes in a flow
	 * are returned alphabetically
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testGetNodeNamesOrderedAlphabetically() {
		// Create the WorkLoad
		WorkLoad wld = new WorkLoad(0.9, 0.99, "Example.txt");
		
		// abc is alphabetical order
		String[] expected = {"A", "B", "C"};
		String[] actual = wld.getNodeNamesOrderedAlphabetically();
		
		assertArrayEquals(expected, actual, String.format("Expected %s but got %s", printArrayHelper(expected), printArrayHelper(actual)));
	}
	
	/**
	 * This method checks that if the nodes are numbers that 
	 * they are also in order
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testGetNodeNamesOrderedAlphabeticallyNumberedNodes() {
		// Create the WorkLoad
		WorkLoad wld = new WorkLoad(0.9, 0.99, "NumberedNodes.txt");
		
		// creating expected and actual values
		String[] expected = {"1", "2", "3"};
		String[] actual = wld.getNodeNamesOrderedAlphabetically();
		
		assertArrayEquals(expected, actual, String.format("Expected %s but got %s", printArrayHelper(expected), printArrayHelper(actual)));
	}
	
	/**
	 * Here we test a mix of numbers and letters in the node names
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testGetNodeNamesOrderedAlphabeticallyMixedNodes() {
		// Create the WorkLoad
		WorkLoad wld = new WorkLoad(0.9, 0.99, "MixedNodes.txt");
		
		// creating expected and actual values
		String[] expected = {"1A", "1B", "A1", "B1"};
		String[] actual = wld.getNodeNamesOrderedAlphabetically();
		
		assertArrayEquals(expected, actual, String.format("Expected %s but got %s", printArrayHelper(expected), printArrayHelper(actual)));
	}
	
	/**
	 * Here we test a flow that doesn't exist
	 * In this case we expect an empty string array to be returned
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testGetNodesInFlowNonExistentFlow() {
		// Create the WorkLoad
		WorkLoad wld = new WorkLoad(0.9, 0.99, "Example.txt");
		
		// creating flowname, expected and actual values
		String flowName = "NonExistentFlow";
		String[] expected = new String[0];
		String[] actual = wld.getNodesInFlow(flowName);
		
		assertArrayEquals(expected, actual, String.format("Expected %s but got %s", printArrayHelper(expected), printArrayHelper(actual)));
	}
	
	/**
	 * Testing the method that gets the names of the flows
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testGetNodesInFlow1() {
		// Create the WorkLoad
		WorkLoad wld = new WorkLoad(0.9, 0.99, "Example.txt");
		
		// creates flowname and expected and actual values
		String flowName = "F0";
		String[] expected = {"A", "B", "C"};
		String[] actual = wld.getNodesInFlow(flowName);
		
		assertArrayEquals(expected, actual, String.format("Expected %s but got %s", printArrayHelper(expected), printArrayHelper(actual)));
	}
	
	/**
	 * Here we have a second test of getting the nodes
	 * this ensures that the nodes appear in the order that they are in
	 * the file
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testGetNodesInFlow2() {
		// Create the WorkLoad
		WorkLoad wld = new WorkLoad(0.9, 0.99, "CustomWorkloadByZach.txt");
		
		// creates flowname and expected and actual values
		String flowName = "RandomFlow3";
		String[] expected = {"D", "C", "A"};
		String[] actual = wld.getNodesInFlow(flowName);
		
		assertArrayEquals(expected, actual, String.format("Expected %s but got %s", printArrayHelper(expected), printArrayHelper(actual)));
	}
	
	/**
	 * We here we test the hyper period method but if a workload
	 * has no flows then the default is 1 so we check that here
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testGetHyperPeriodNoFlows() {
		// Create the WorkLoad
		WorkLoad wld = new WorkLoad(0.9, 0.99, "EmptyWorkload.txt");
		
		// creating expected and actual values
		Integer expected = 1;
		Integer actual = wld.getHyperPeriod();

		assertEquals(expected, actual, String.format("Expected %d but got %d.", expected, actual));
	}
	
	/**
	 * Testing the getHyperPeriod method
	 * In my custom workload the periods are 5, 12, and 9 so the
	 * lcm of those is 180
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testGetHyperPeriod() {
		// Create the WorkLoad
		WorkLoad wld = new WorkLoad(0.9, 0.99, "CustomWorkloadByZach.txt");
		
		// expected and actual values
		Integer expected = 180;
		Integer actual = wld.getHyperPeriod();

		assertEquals(expected, actual, String.format("Expected %d but got %d.", expected, actual));
	}
	
	/**
	 * Here we test getting the deadline for a flow that does
	 * not exist in the workload
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testGetFlowDeadlineNonExistentFlow() {
		// Create the WorkLoad
		WorkLoad wld = new WorkLoad(0.9, 0.99, "Example.txt");
		
		String flowName = "NonExistentFlow";
		// default flow deadline is 100
		Integer expected = 100;
		// if a flow doesn't exist it will create a default flow with default parameters
		Integer actual = wld.getFlowDeadline(flowName);
		
		assertEquals(expected, actual, String.format("expected %d but got %d", expected, actual));
	}
	
	/**
	 * This method tests getting the flow deadline for all flows in
	 * my custom workload
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testGetFlowDeadline() {
		// Create the WorkLoad
		WorkLoad wld = new WorkLoad(0.9, 0.99, "CustomWorkloadByZach.txt");
		
		// testing flow 1
		{
			//creating flowname and expected and actual values
			String flowName = "RandomFlow1";
			Integer expected = 10;
			Integer actual = wld.getFlowDeadline(flowName);
			
			// assertion
			assertEquals(expected, actual, String.format("expected %d but got %d", expected, actual));			
		}
		
		// testing flow 2
		{
			String flowName = "RandomFlow2";
			Integer expected = 0;
			Integer actual = wld.getFlowDeadline(flowName);
			
			assertEquals(expected, actual, String.format("expected %d but got %d", expected, actual));
		}
		
		// testing flow 3
		{
			String flowName = "RandomFlow3";
			Integer expected = 16;
			Integer actual = wld.getFlowDeadline(flowName);
			
			assertEquals(expected, actual, String.format("expected %d but got %d", expected, actual));
		}

	}
	
	/**
	 * Here we test setting RandomFlow3 from my custom workload
	 */
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testSetFlowDeadline() {
		// Create the WorkLoad
		WorkLoad wld = new WorkLoad(0.9, 0.99, "CustomWorkloadByZach.txt");
		
		// flow name to use
		String flowName = "RandomFlow3";
		
		// making sure that the initial values are what we expect them to be
		Integer initialExpectedDeadline = 16;
		Integer initialActualDeadline = wld.getFlowDeadline(flowName);
		
		assertEquals(initialExpectedDeadline, initialActualDeadline, String.format("Expected %d but got %d", initialExpectedDeadline, initialActualDeadline));
		
		// setting the deadline to 1234
		Integer expectedDeadline = 1234;
		wld.setFlowDeadline(flowName, expectedDeadline);
		
		// getting the new deadline which should have changed
		Integer actualDeadline = wld.getFlowDeadline(flowName);
		
		assertEquals(expectedDeadline, actualDeadline, String.format("Expected %d but got %d", expectedDeadline, actualDeadline));
	}
	
	/**
	 * This function is a helper method that takes in an array and returns a string representation of it
	 * @param arr an array of any type
	 * @return String representation of the array
	 */
	String printArrayHelper(Object[] arr) {
		StringBuilder str = new StringBuilder().append("[");
		for(int i = 0; i < arr.length; i++) {
			str.append((arr[i].toString() + ", "));
		}
		str.append("]");
		return str.toString();
	}

}
