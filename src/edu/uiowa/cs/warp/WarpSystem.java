/**
 * 
 */
package edu.uiowa.cs.warp;

import edu.uiowa.cs.warp.SystemAttributes.ScheduleChoices;

/**
 * @author sgoddard
 * @version 1.4
 */
public class WarpSystem {

  // private static final String SOURCE_SUFFIX = ".dsl";

  private Program program;
  private WorkLoad workLoad;
  private ReliabilityAnalysis ra;
  private LatencyAnalysis la;
  private ChannelAnalysis ca;
  private Integer numChannels;
  private Boolean verboseMode = false;
  private Boolean latencyRequested = false;

  public WarpSystem(WorkLoad workLoad, Integer numChannels, ScheduleChoices choice) {
    this.workLoad = workLoad;
    this.numChannels = numChannels;
    createProgram(workLoad, numChannels, choice);
  }

  public WorkLoad toWorkload() {
    return workLoad;
  }

  public Program toProgram() {
    return program;
  }

  public ReliabilityAnalysis toReliabilityAnalysis() {
    // TODO Auto-generated method stub
    ra = new ReliabilityAnalysis(program);
    return ra;
  }

  public SimulatorInput toSimulator() {
    // TODO Auto-generated method stub
    return null;
  }

  public LatencyAnalysis toLatencyAnalysis() {
    // TODO Auto-generated method stub
    la = new LatencyAnalysis(this);
    return la;
  }

  public ChannelAnalysis toChannelAnalysis() {
    // TODO Auto-generated method stub
    ca = new ChannelAnalysis(this);
    return ca;
  }

  public Boolean reliabilitiesMet() {
    if (ra == null) {
      ra = new ReliabilityAnalysis(program);
    }
    return ra.verifyReliabilities();
  }

  public Boolean deadlinesMet() {
    Boolean result = true;
    if (program.deadlineMisses().size() > 0) {
      result = false;
    }
    return result;
  }


  private void createProgram(WorkLoad workLoad, Integer numChannels, ScheduleChoices choice) {
    program = new Program(workLoad, numChannels, choice, verboseMode, latencyRequested);

  }


  public Integer getNumChannels() {
    return numChannels;
  }

  public Integer getNumFaults() {
    return program.getNumFaults();
  }

  public Double getMinPacketReceptionRate() {
    return workLoad.getMinPacketReceptionRate();
  }

  public Double getE2e() {
    return workLoad.getE2e();
  }

  public String getName() {
    return workLoad.getName();
  }

  public String getSchedulerName() {
    return program.getSchedulerName();
  }

  public Integer getNumTransmissions() {
    return program.getNumTransmissions();
  }

  public Boolean getOptimizationFlag() {
    return program.getOptimizationFlag();
  }

  public void toSensorNetwork() {
    // TODO Auto-generated method stub

  }


}
