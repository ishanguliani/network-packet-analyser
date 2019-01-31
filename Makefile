PROGRAM = program
CLASS = classes
MAKEDIR = directory
MAINFILE = main

$(PROGRAM): $(CLASS)
	@cd classes && java Main $(file)	

$(CLASS): $(MAKEDIR)
	@echo "-------------------"
	@echo "compiling java files..."
	@echo "-------------------" 
	@javac -d classes Main.java
	@echo "-------------------"
	@echo "compilation completed without errors..." 
	@echo "-------------------"
	@echo "all class files copied to ./classes directory"
	@echo "-------------------"
	@javac -d classes Main.java

all: $(PROGRAM)
	@echo "executing program..."
	@echo "-------------------"

clean:
	@echo "-------------------"
	@echo "cleaning all .class files in the classes directory, if applicable"
	@rm -rf classes
	@echo "cleaning model..."
	@echo "cleaning helper..."
	@echo "-------------------"
	@echo "-------------------"
	@echo "cleaned successfully"
	@echo "-------------------"

$(MAKEDIR): $(MAINFILE) clean
	@echo "--Creating directory 'classes' in the current directory--"
	@echo "Main.java file found!"
	@mkdir -p classes && cp -n *.bin classes/

$(MAINFILE): Main.java 
	@echo "NOTE: You can start the JAVA program implicitly by appending the network packet filename to the make command." 
	@echo "NOTE: Run the following command to forward a file to the JAVA program"
	@echo "NOTE:    $ make <filename>    OR    $ make program <filename>"
	@echo "NOTE: Only files that end in .bin format are supported at this point"
	@echo ""
	@echo "----Beginning make program------"
	@echo "--------------------------------"
	@echo ""
	@echo "Preparing directory for execution"
	@echo "Looking for Main.java..."
