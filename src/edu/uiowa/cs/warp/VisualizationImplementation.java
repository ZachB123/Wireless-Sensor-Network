/**
 * 
 */
package edu.uiowa.cs.warp;

import java.io.File;

/**
 * @author sgoddard
 * @version 1.5
 */
public class VisualizationImplementation implements Visualization {

	private Description visualization;
	private Description fileContent;
	private GuiVisualization window;
	private String fileName;
	private String inputFileName;
	private String fileNameTemplate;
	private FileManager fm = null;
	private WorkLoad workLoad = null;
	private VisualizationObject visualizationObject;
	private WarpSystem warp;

	/**
	 * Initializes a new visualization by taking a warp interface, Output Directory
	 * and a SystemChoices enum and uses them to create a file name and
	 * visualization.
	 * 
	 * @param warp            interface in which workload will be retrieved
	 * @param outputDirectory a string representing the output directory for file
	 *                        name
	 * @param choice          the choice in which the user decides for creating the
	 *                        visualization
	 */
	public VisualizationImplementation(WarpSystem/*No type specified*/ warp, String outputDirectory, SystemChoices choice) {
		this.fm = new FileManager();
		this.warp = warp;
		inputFileName = warp.toWorkload().getInputFileName();
		this.fileNameTemplate = createFileNameTemplate(outputDirectory);
		visualizationObject = null;
		createVisualization(choice);
	}

	/**
	 * Initializes a new visualization by taking in a Workload, Output Directory,
	 * and a SystemChoices enum and uses them to create a file name and
	 * visualization.
	 * 
	 * @param workLoad        workLoad that will be used to get data for
	 *                        visualization
	 * @param outputDirectory string representing output directory for file name
	 * @param choice          choice that is decided by user in how to create
	 *                        implementation
	 */
	public VisualizationImplementation(WorkLoad workLoad, String outputDirectory, WorkLoadChoices choice) {
		this.fm = new FileManager();
		this.workLoad = workLoad;
		inputFileName = workLoad.getInputFileName();
		this.fileNameTemplate = createFileNameTemplate(outputDirectory);
		visualizationObject = null;
		createVisualization(choice);
	}

	/**
	 * <h1>If a visualization exists it will be displayed</h1>
	 */
	@Override
	public void toDisplay() {
		// System.out.println(displayContent.toString());
		window = visualizationObject.displayVisualization();
		if (window != null) {
			window.setVisible();
		}
	}

	/*
	 * <h1>Writes the description of the visualization to a file</h1>
	 */
	@Override
	public void toFile() {
		fm.writeFile(fileName, fileContent.toString());
	}

	/*
	 * <h1>Converts a visualization to a string in a readable manner.</h1>
	 * 
	 * @return a String containing the visualization content
	 */
	@Override
	public String toString() {
		return visualization.toString();
	}

	/**
	 * <h1>Creates a visualization based on the SystemChoice provided</h1>
	 * 
	 * @param choice can be set to different requests to change how visualization is
	 *               implemented
	 */
	private void createVisualization(SystemChoices choice) {
		switch (choice) { // select the requested visualization
		case SOURCE:
			createVisualization(new ProgramVisualization(warp));
			break;

		case RELIABILITIES:
			// TODO Implement Reliability Analysis Visualization
			createVisualization(new ReliabilityVisualization(warp));
			break;

		case SIMULATOR_INPUT:
			// TODO Implement Simulator Input Visualization
			createVisualization(new NotImplentedVisualization("SimInputNotImplemented"));
			break;

		case LATENCY:
			// TODO Implement Latency Analysis Visualization
			createVisualization(new LatencyVisualization(warp));
			break;

		case CHANNEL:
			// TODO Implement Channel Analysis Visualization
			createVisualization(new ChannelVisualization(warp));
			break;

		case LATENCY_REPORT:
			createVisualization(
					new ReportVisualization(fm, (SystemAttributes) warp, new LatencyAnalysis(warp).latencyReport(), "Latency"));
			break;

		case DEADLINE_REPORT:
			createVisualization(new ReportVisualization(fm, (SystemAttributes) warp, warp.toProgram().deadlineMisses(), "DeadlineMisses"));
			break;

		default:
			createVisualization(new NotImplentedVisualization("UnexpectedChoice"));
			break;
		}
	}

	/**
	 * <h1>Creates a visualization based on the WorkloadChoice provided</h1>
	 * 
	 * @param choice user choice for how visualization is represented
	 */
	private void createVisualization(WorkLoadChoices choice) {
		switch (choice) { // select the requested visualization
		case COMUNICATION_GRAPH:
			// createWarpVisualization();
			createVisualization(new CommunicationGraph(fm, workLoad));
			break;

		case GRAPHVIZ:
			createVisualization(new GraphViz(fm, workLoad.toString()));
			break;

		case INPUT_GRAPH:
			createVisualization(workLoad);
			break;

		default:
			createVisualization(new NotImplentedVisualization("UnexpectedChoice"));
			break;
		}
	}

	/**
	 * <h1>Creates a visualization based off a existing VisualizationObject</h1>
	 * 
	 * @param <T> Any object extending a Visualization object
	 * @param obj object of type T to be used for visualization
	 */
	private <T extends VisualizationObject> void createVisualization(T obj) {
		visualization = obj.visualization();
		fileContent = obj.fileVisualization();
		/* display is file content printed to console */
		fileName = obj.createFile(fileNameTemplate); // in output directory
		visualizationObject = obj;
	}

	/**
	 * <h1>Creates a file name template.</h1>
	 * <p>Uses a string outputDirectory to create a template for a filename that allows
	 * for easier formatting if the '/' is used in the file name.</p>
	 * 
	 * @param outputDirectory string used to make template for file name
	 * @return new template for created file name
	 */
	private String createFileNameTemplate(String outputDirectory) {
		String fileNameTemplate;
		var workingDirectory = fm.getBaseDirectory();
		var newDirectory = fm.createDirectory(workingDirectory, outputDirectory);
		// Now create the fileNameTemplate using full output path and input filename
		if (inputFileName.contains("/")) {
			var index = inputFileName.lastIndexOf("/") + 1;
			fileNameTemplate = newDirectory + File.separator + inputFileName.substring(index);
		} else {
			fileNameTemplate = newDirectory + File.separator + inputFileName;
		}
		return fileNameTemplate;
	}

}
