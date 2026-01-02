JAVAC=/usr/bin/javac
JAVA=/usr/bin/java
JFLAGS = -g
CLASSPATH = -cp .
.SUFFIXES: .java .class

.java.class:
	$(JAVAC) $(JFLAGS) $*.java

CLASSES = \
	State.java \
	Node.java \
	Main.java

MAIN = Main

default: classes

classes: $(CLASSES:.java=.class)

run: $(MAIN).class
	$(JAVA) $(CLASSPATH) $(MAIN)

clean:
	$(RM) *.class