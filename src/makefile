JC = javac
JCR = java

.SUFFIXES: .java .class
.java.class:
	$(JC) $*.java

CLASSES = \
	Server.java \
	ServerImplementation.java \
	WebServer.java \

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class *~
