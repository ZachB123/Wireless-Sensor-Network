package edu.uiowa.cs.warp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
	public ReliabilityVisualization getReliabilityVisualization(
			Double m, Double e2e, String inputFileName, Integer numChannels, ScheduleChoices choice) {
		return new ReliabilityVisualization(new WarpSystem(new WorkLoad(m, e2e, inputFileName), numChannels, choice));
	}
	
	public ReliabilityVisualization getReliabilityVisualization(
			Integer numFaults, Double m, Double e2e, String inputFileName, Integer numChannels, ScheduleChoices choice) {
		return new ReliabilityVisualization(new WarpSystem(new WorkLoad(numFaults, m, e2e, inputFileName), numChannels, choice));
	}
	
	// TESTING getHeader
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
	
	
	
	
	
	
	
	
	
	
}
