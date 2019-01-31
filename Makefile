PROGRAM = program
CLASS = classes
MAKEDIR = directory
MAINFILE = main

$(PROGRAM): $(CLASS)
	@echo "-------------------"
	@echo "Running the executable..."
	@echo "-------------------"
	@cd classes && java Main $(file)	

$(CLASS): $(MAKEDIR)
	@echo "-------------------"
	@echo "compiling java files..."
	@echo "-------------------" 
	@echo "Copying all model classes to model/"
	@echo "Copying all helper classes to helper/"
	@echo "-------------------"
	@echo "compilation completed without errors..." 
	@echo "-------------------"
	@echo "all class files copied to ./classes directory"
	@javac -d classes Main.java

all: $(PROGRAM)
	@echo "executing program..."
	@echo "-------------------"

clean:
	@echo "-------------------"
	@echo "cleaning all .class files in the classes directory, if applicable"
	@rm -rf classes ||:
	@echo "cleaning model..."
	@echo "cleaning helper..."
	@echo "-------------------"
	@echo "cleaned successfully"
	@echo "-------------------"

$(MAKEDIR): $(MAINFILE) clean
	@echo "--Creating directory 'classes' in the current directory--"
	@mkdir -p classes && mkdir -p helper && mkdir -p model && cp -n *.bin classes/
	@echo "--------------------"
	@echo "Creating directory 'model'"
	@echo "Creating directory 'helper'"
# @mv Checksum.java DataModel.java EthernetFrameModel.java Flag.java IPAddress.java IPModel.java IPService.java IcmpModel.java MacAddress.java MessageType.java OnPacketReadyListener.java OptionsModel.java ProtocolType.java TcpModel.java UdpModel.java model/
# @mv Helper.java StringManager.java helper/ 

$(MAINFILE): Main.java 
	@echo ""
	@echo "----Beginning make program------"
	@echo "--------------------------------"
	@echo "NOTE: You can start the JAVA program implicitly by appending the network packet filename to the make command." 
	@echo "NOTE: There are three sample binary files in this directory to test the program"
	@echo "" && echo "Sample execution-"
	@echo "TCP PACKET ANALYSIS:" && echo "	$$ make program file=new_tcp_packet1.bin"
	@echo "UDP PACKET ANALYSIS:" && echo "	$$ make program file=new_udp_packet1.bin"
	@echo "ICMP PACKET ANALYSIS:" && echo "	$$ make program file=new_icmp_packet2.bin"
	@echo "" && echo "NOTE: Only files that end in .bin format are supported at this point"
	@echo ""
	@echo "Preparing directory for execution"
	@echo "Looking for Main.java..."
