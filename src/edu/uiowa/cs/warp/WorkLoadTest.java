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

	@Test
	void testGetNumTxAttemptsPerLink() {
		fail("Not yet implemented");
	}

	@Test
	void testMaxFlowLength() {
		fail("Not yet implemented");
	}

}
