JAVAC=/usr/bin/javac
JAVA=/usr/bin/java
JFLAGS = -g
CLASSPATH = -cp .
.SUFFIXES: .java .class

.java.class:
	$(JAVAC) $(JFLAGS) -cp . $*.java

CLASSES = \
	EnvironmentObject.java \
	AgentProgram.java \
	Agent.java \
	Environment.java \
	AbstractEnvironment.java \
	Action.java \
	Condition.java \
	EnvironmentListener.java \
	EnvironmentView.java \
	Notifier.java \
	ObjectWithDynamicAttributes.java \
	Percept.java \
	Rule.java \
	SimpleReflexAgentProgram.java \
	SimpleReflexVacuumAgent.java \
	State.java \
	Util.java \
	VacuumEnvironment.java \
	VacuumEnvironmentState.java \
	VacuumPercept.java \
	Main.java

MAIN = Main

default: classes

classes: $(CLASSES:.java=.class)

run: $(MAIN).class
	$(JAVA) $(CLASSPATH) mibi.assignment1.$(MAIN)

clean:
	$(RM) *.class