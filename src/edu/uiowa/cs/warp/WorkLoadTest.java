package edu.uiowa.cs.warp;

import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class WorkLoadTest {

	@Test
	void testAddFlow() {
		fail("Not yet implemented");
	}

	@Test
	void testSetFlowPriority() {
		fail("Not yet implemented");
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
		Exception thrown = assertThrows(IndexOutOfBoundsException.class, 
				() -> wld.getTotalTxAttemptsInFlow(flowName),
				"An IndexOutOfBounds exception was expected but not thrown.");
		
		String expectedErrorMessage = "Index -1 out of bounds for length 0";
		String actualErrorMessage = thrown.getMessage();
		
		// Checking to make sure the error message from the exception is what I expected
		assertEquals(expectedErrorMessage, actualErrorMessage, 
				String.format("Error message is incorrect. Expected %s but got %s", 
						expectedErrorMessage, actualErrorMessage));
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
		
		// stresstest4 change numfaults
		// find relationship
		
		// Create expected and actual values
		System.out.println(String.format("Num: %d", wld.getTotalTxAttemptsInFlow(flowName))); 
		int expected = 4;
		int actual = wld.getTotalTxAttemptsInFlow(flowName);
		
		assertSame(expected, actual, String.format("Expected %d transmission attempts but got %d.", 
														expected, actual));
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
		
		assertSame(expected, actual, String.format("Expected %d transmission attempts but got %d.", 
				expected, actual));
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
		
		assertSame(expected, actual, String.format("Expected %d transmission attempts but got %d.", 
				expected, actual));
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
		
		assertSame(expected, actual, String.format("Expected %d transmission attempts but got %d.", 
				expected, actual));
	}
	
	@Test
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	void testGetFlowPriority() {
		
	}

	@Test
	void testGetNumTxAttemptsPerLink() {
		fail("Not yet implemented");
	}

	@Test
	void testMaxFlowLength() {
		fail("Not yet implemented");
	}

}
