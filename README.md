# CS2820 Spring 2023 WARP Project Code
This code base will be used for the University of Iowa CS 2820 Introduction to Software
Development course. The code was developed by Steve Goddard for the WARP sensor network 
research project. It was first written in Swift and rewritten in Java. It was then 
rewritten again in an object-oriented programming style. It was a quick
hack, and it needs a lot of cleanup and refactoring. A perfect code base to teach
the value of software developement fundamentals!
<br>

* -v (verbose)
* -sch priority (priority scheduler)
* --all (all options)
* -i <input file>
* -o <output folder>

# Homework 1

For homework 1 I handled a variety of edge cases

1. empty lines at the beginning and of the file
2. extra spaces around filename and final curly bracket
3. empty lines scattered throughout the flows

# Homework 2

What each team member documented.

## Zach

* Flow.java
* a through k in Workload.java
* Warp.java
* Program.java

## Noah

* ProgramVisualization.java
* VisualizationImplementation.java
* l through w in Workload.java

# Homework 3

## Zach

* Tests c,d,g,h,k,l,o,p

## Noah

* Tests a,b,e,f,i,j,m,n

# Homework 4

* Created UML Diagrams for SchedulableObject, Workload, and all Reliability files

# Project - Team Flamingo
The objective of the project is to update the existing Warp codebase to add calculations and analysis of the reliabilities of flows in the program. We will develop code and add to the ReliabilityAnalysis and ReliabilityVisualization classes to create this functionality.

## Team Members
* Zach Buchholz (zbuchholz)
* Evan Frankmann (efrankmann)
* Abby Hawken (ahawken)
* Wei Ching Lim (weilim)

## Sprint 1
Tasks:
* Understand the problem (All)
* Create project plan (All)
* Create sequence diagram (Zach), check diagram (Wei Ching)
* Update ReadMe (Abby)
* Regenerate UML diagrams (Evan) (to make sure diagrams are correct after HW4)

Documents added: 
* Sequence Diagram of Warp program,  _WarpSequenceDiagram.pdf_ . You can also view the code in SequenceDiagramCode.txt and SequenceDiagramCode2.txt.
* Project Plan describing the team's initial plan for each sprint,  _WarpProjectPlans.pdf_ .

Collaboration:
* The team met on April 3rd to create the project plan, work through the transmission probability formula and understand the program, and discuss what/how we will add to the Warp code to meet the project requirements.

## Sprint 2
Tasks: 
* Update Readme with plans for Sprint 2 (Abby)
* JUnit tests for ReliabilityVisualization (Evan)
* Implement the ReliabilityVisualization class (Zach and Wei Ching)
* Document the ReliabilityVisualization class (Zach and Wei Ching)
* Update sequence Diagram to include ReliabilityVisualization (Abby)
* Update the UML Diagrams to reflect all the latest changes (Evan) 
* Plan for Sprint 3 (All)

Documents added:
* The sequence diagram was updated to show the visualization of the file
* Warp-Sprint2 was added documenting our plans
* Sprint2SequenceDiagramCode.txt
* Sprint2SequenceDiagram2.pdf

Collaboration:
* The team met on April 16 to work on the tasks and finish the sprint

## Sprint 3
Tasks:
* Update Readme for Sprint 3 (Abby)
* JUnit tests for new methods in ReliabilityAnalysis (Evan: 50%, everyone else: 50%)
* Implement getReliabilities (Zach)
* Implement verifyReliabilities (Abby)
* Document the ReliabilityAnalysis class (Wei Ching)
* Update sequence diagram to reflect changes (Abby or Zach ?)
* Update UML diagrams to reflect changes (Wei Ching)



