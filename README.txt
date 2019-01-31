Hello!
Thanks for testing out my program.

Here are some sample program executions -

# OPTION 1: If you have the 'make' program installed in your machine -
1. TCP packet analysis
	$ make program file=new_tcp_packet1.bin		# run program with sample tcp packet (.bin format)
2. UDP packet analysis					
	$ make program file=new_udp_packet1.bin		# run program with sample udp packet (.bin format)
3. ICMP packet analysis
	$ make program file=new_icmp_packet2.bin	# run program with sample icmp packet(.bin format)

# OPTION 2: If you do not have the 'make' program installed -
1. $ rm -rf classes ||:					# remove the classes directory if it exists
2. $ mkdir -p classes && cp -n *.bin classes/		# create a clean 'classes' directory and copy all .bin file to it
3. $ javac -d classes Main.java				# compile the program and copy all .class files to 'class' directory
4. $ cd classes && java Main <filename.bin> && cd ..	# change directory to 'classes' and run the program


# For your reference, three binary packet dumps have been copied to the project-
	1. new_tcp_packet1.bin
	2. new_udp_packet1.bin
	3  new_icmp_packet2.bin


# NOTE: If you wish to analyse your own packet with this program, here are the steps to do so -
## REQUIREMENTS -
	1. Your packet dump should be in .bin format
## STEPS -
	1. Copy your file to the project working directory (This is the same directory as the file 'Makefile')
	2. Run the make program as follows -	
		$ make program file=<my_new_file.bin>


Ishan Guliani
@ishanguliani : ig5859@rit.edu
