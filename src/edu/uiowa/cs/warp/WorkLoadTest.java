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

	@Test
	void testAddFlow() {
		WorkLoad wld = new WorkLoad(0.9,0.99,"Example1a.txt");
		String flowName = "F0";
		FlowMap flows = new FlowMap();
		
		
		wld.addFlow(flowName);
		assertTrue(flows.containsKey(flowName));
		assertEquals(flows.size(), 1);
		
		
	}

	@Test
	void testaddNodeToFlow() {
		
		WorkLoad wld = new WorkLoad(0.9,0.99,"Example1a.txt");
		String flowName = "F0";
		String nodeName = "A";
				
		
		wld.addNodeToFlow(flowName, nodeName);
		assertTrue(wld.getFlows().containsKey("A"));
		
		
		
		
	}
	@Test
	void testSetFlowPriority() {
		WorkLoad wld = new WorkLoad(0.9, 0.99, "Example1a.txt");
		String flowName = "F0";
		Integer priority = 0;
		
		wld.setFlowPriority(flowName, priority);
		
		assertEquals(priority, wld.getFlowPriority(flowName));
		fail("Not yet implemented");
	}

	@Test 
	void testGetFlowTxAttemptsPerLink() {
		WorkLoad wld = new WorkLoad(0.9, 0.99, "Example1a.txt");
		String flowName = "F0";
		
		int numAttempts = wld.getFlowTxAttemptsPerLink(flowName);
		int expected = 5;
		
		
		assertEquals(expected,numAttempts);
		
	}
	
	
	@Test
	void testSetFlowDeadline() {
		fail("Not yet implemented");
	}

	@Test
	void testGetFlowPriorityString() {
		fail("Not yet implemented");
	}

	@Test
	void testGetFlowNames() {
		fail("Not yet implemented");
	}

	@Test
	void testGetNodeIndex() {
		fail("Not yet implemented");
	}

	@Test
	void testGetNodesInFlow() {
		fail("Not yet implemented");
	}

	@Test
	void testGetHyperPeriod() {
		fail("Not yet implemented");
	}

	/**
	 * Tests a flow not found in the file.
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

		String expectedErrorMessage = "Index -1 out of bounds for length 0";
		String actualErrorMessage = thrown.getMessage();

		// Checking to make sure the error message from the exception is what I expected
		assertEquals(expectedErrorMessage, actualErrorMessage, String.format(
				"Error message is incorrect. Expected %s but got %s", expectedErrorMessage, actualErrorMessage));
	}

	/**
	 * Tests a flow in the file.
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
	 * Tests a flow in the file.
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
	 * Tests a flow in the file with a specified numFaults.
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

	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testGetFlowPriorityCustomPriority1() {
		// Create the WorkLoad and flowName
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
	
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testSetFlowsInRMOrder1() {
		// Create the WorkLoad and flowName
		WorkLoad wld = new WorkLoad(0.9, 0.99, "Example1a.txt");
		
		// We are testing that flows are sorted in order of period then priority as the second key
		wld.setFlowsInRMorder();
		String[] expectedArray = {"F0", "F1"};
		List<String> expected = new ArrayList<String>(Arrays.asList(expectedArray));
		// The actual contains a list of the names of the flows
		List<String> actual = wld.getFlowNamesInPriorityOrder();
		
		int expectedLength = expected.size();
		int actualLength = actual.size();
		assertSame(expectedLength, actualLength, String.format("Length of expected array is %d but we got %d", expectedLength, actualLength));
		
		for(int i = 0; i < expectedLength; i++) {
			String expectedString = expected.get(i);
			String actualString = actual.get(i);
			assertEquals(expectedString, actualString, String.format("Expected %s but got %s.", expectedString, actualString));
		}
	}
	
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
		
		int expectedLength = expected.size();
		int actualLength = actual.size();
		assertSame(expectedLength, actualLength, String.format("Length of expected array is %d but we got %d", expectedLength, actualLength));
		
		for(int i = 0; i < expectedLength; i++) {
			String expectedString = expected.get(i);
			String actualString = actual.get(i);
			assertEquals(expectedString, actualString, String.format("Expected %s but got %s.", expectedString, actualString));
		}
	}
	
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testSetFlowsInRMOrderSamePeriod1() {
		// Create the WorkLoad and flowName
		WorkLoad wld = new WorkLoad(0.9, 0.99, "SamePeriod.txt");
		
		// We are testing that flows are sorted in order of period then priority as the second key
		// Since the period for this file is the same we must check the priority
		wld.setFlowsInRMorder();
		String[] expectedArray = {"SamePeriod3", "SamePeriod2", "SamePeriod1"};
		List<String> expected = new ArrayList<String>(Arrays.asList(expectedArray));
		// The actual contains a list of the names of the flows
		List<String> actual = wld.getFlowNamesInPriorityOrder();
		
		int expectedLength = expected.size();
		int actualLength = actual.size();
		assertSame(expectedLength, actualLength, String.format("Length of expected array is %d but we got %d", expectedLength, actualLength));
		
		for(int i = 0; i < expectedLength; i++) {
			String expectedString = expected.get(i);
			String actualString = actual.get(i);
			assertEquals(expectedString, actualString, String.format("Expected %s but got %s.", expectedString, actualString));
		}
	}
	
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
		
		int expectedLength = expected.size();
		int actualLength = actual.size();
		assertSame(expectedLength, actualLength, String.format("Length of expected array is %d but we got %d", expectedLength, actualLength));
		
		for(int i = 0; i < expectedLength; i++) {
			String expectedString = expected.get(i);
			String actualString = actual.get(i);
			assertEquals(expectedString, actualString, String.format("Expected %s but got %s.", expectedString, actualString));
		}
	}
	
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testGetNodeNamesOrderedAlphabetically() {
		// Create the WorkLoad and flowName
		WorkLoad wld = new WorkLoad(0.9, 0.99, "Example.txt");
		
		String[] expected = {"A", "B", "C"};
		String[] actual = wld.getNodeNamesOrderedAlphabetically();
		
		assertArrayEquals(expected, actual, String.format("Expected %s but got %s", printArrayHelper(expected), printArrayHelper(actual)));
	}
	
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testGetNodeNamesOrderedAlphabeticallyNumberedNodes() {
		// Create the WorkLoad and flowName
		WorkLoad wld = new WorkLoad(0.9, 0.99, "NumberedNodes.txt");
		
		String[] expected = {"1", "2", "3"};
		String[] actual = wld.getNodeNamesOrderedAlphabetically();
		
		assertArrayEquals(expected, actual, String.format("Expected %s but got %s", printArrayHelper(expected), printArrayHelper(actual)));
	}
	
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testGetNodeNamesOrderedAlphabeticallyMixedNodes() {
		// Create the WorkLoad and flowName
		WorkLoad wld = new WorkLoad(0.9, 0.99, "MixedNodes.txt");
		
		String[] expected = {"1A", "A1", "1B", "B1"};
		String[] actual = wld.getNodeNamesOrderedAlphabetically();
		
		assertArrayEquals(expected, actual, String.format("Expected %s but got %s", printArrayHelper(expected), printArrayHelper(actual)));
	}
	
	
	

	@Test
	void testGetNumTxAttemptsPerLink() {
		fail("Not yet implemented");
	}

	@Test
	void testMaxFlowLength() {
		fail("Not yet implemented");
	}
	
	String printArrayHelper(Object[] arr) {
		StringBuilder str = new StringBuilder().append("[");
		for(int i = 0; i < arr.length; i++) {
			str.append((arr[i].toString() + ", "));
		}
		str.append("]");
		return str.toString();
	}

}
