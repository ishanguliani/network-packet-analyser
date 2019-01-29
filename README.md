# Packet Analyser
A java program to explore tcp, udp and icmp packets in details. Runs as command line program.


## Sample execution -

`$ javac Main.java`

`$ java Main new_tcp_packet1.bin`

## Sample output -

`
ETHER:	----- Ether Header -----

ETHER:

ETHER:	Packet Size    =	266 bytes

ETHER:	Destination    =	00:00:0c:07:ac:01
ETHER:	Source         =	c0:14:3d:d5:72:8b
ETHER:	Ethertype      =	0800 (IP)
ETHER:	
IP:	----- IP Header -----
IP:
IP:	Version           =	4
IP:	Header length     =	20 bytes
IP:	Type of service   =	0x00
IP:		0000 00.. = Differentiated Services Codepoint: Default (0)	
IP:		.... ..00 = Explicit Congestion Notification: Not ECN-Capable Transport (0)	
IP:	Total length      =	252
IP:	Identification    =	32492
IP:	Flags             =	0x4000
IP:		.1.. .... = do not fragment	
IP:		..0. .... = last fragment	
IP:	Fragment offset   =	0 bytes
IP:	Time to live      =	64 seconds/hop
IP:	Protocol          =	6 (TCP)
IP:	Header checksum   =	0x4a9e
IP:	Source address    =	129.21.66.85
IP:	Destin. address   =	172.217.0.46
IP:	No Options
IP:	
TCP:	----- TCP Header -----
TCP:
TCP:	Source port       =	52566
TCP:	Destination port  =	443
TCP:	Sequence number   =	424114169
TCP:	Acknowl. number   =	2974881950
TCP:	Flags             =	0x018
TCP:		000. .... .... = Reserved: Not set	
TCP:		...0 .... .... = Nonce: Not set	
TCP:		.... 0... .... = Congestion Window Reduced (CWR): Not set	
TCP:		.... .0.. .... = ECN-Echo: Not set	
TCP:		.... ..0. .... = No urgent pointer	
TCP:		.... ...1 .... = acknowledgement set	
TCP:		.... .... 1... = Push set	
TCP:		.... .... .0.. = No reset	
TCP:		.... .... ..0. = No Syn	
TCP:		.... .... ...0 = No Fin	
TCP:	Window            =	1832
TCP:	Header checksum   =	0x75b0
TCP:	Urgent pointer    =	0
IP:	No Options
TCP:	Data (first 64 B) =	
TCP:		
TCP:		17 03 03 00 c3 00 00 00 00 00 00 00 
TCP:		3e fc 3b b6 ce fa b3 67 91 9e 87 b9 
TCP:		68 1c 26 96 dc 87 a4 e2 8f 7c bb b2 
TCP:		b3 bd a3 3f bf 37 b2 e1 9a 9d 8c a5 
`
