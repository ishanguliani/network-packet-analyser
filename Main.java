/**
 * filename: Main.java
 * author: @ishanguliani aka ig5859
 *
 * version:     1
 *
 * revision:    1
 *
 *
 * description:
 * A network packet analyser. This is the driver class of the whole program. It
 * accepts a valid network packet name as the command line argument in binary
 * format and reads it into the InputStream for processing.
 *
 * The data from the packet is the modelled against different model classes on
 * as-available basis.
 *
 * The following packets can be analysed using this program -
 *  ->  tcp
 *  ->  udp
 *  ->  icmp
 */

import helper.StringManager;
import model.*;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static helper.Helper.print;

public class Main {

    // list to store bytes of data from the packet
    private static List<Byte> byteDataList = new ArrayList<Byte>();

    /**
     * Driver function which accepts packet file name as input argument
     *
     * @param args the name of the packet to be processed
     *             should be given as a single argument
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println(StringManager.ERROR_COMMAND_LINE_ARGUMENTS);
            System.exit(1);
        }

        // extract the filename from the command line arguments
        String filename = args[0];

        // open an input stream linked to the file
        BufferedInputStream reader = null;
        try {
            reader = readFile(filename);
        } catch (FileNotFoundException e) {
            System.err.println(StringManager.ERROR_FILE_NOT_FOUND);
            System.exit(1);
        }

        // proceed if the reader has been initialised successfully
        if (reader != null) {
            try {
                // pass on control to the next method for processing bytes of data
                processBytes(reader);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println(StringManager.EXCEPTION_BUFFER_NOT_INITIALISED);
                System.exit(1);
            }
        }
    }

    /**
     * Return a BufferedInputStream linked with the given filename
     *
     * @param filename the filename to be accessed
     * @return BufferedInputStream linked with the file
     */
    public static BufferedInputStream readFile(String filename) throws FileNotFoundException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(filename));
        return in;
    }

    /**
     * Process the underlying bytes pointed to by the Input Stream
     *
     * @param reader The input stream from which bytes are read and processed
     */
    private static void processBytes(BufferedInputStream reader) throws IOException {

        // read the bytes from the input stream into a byte array
        byte[] arr = new byte[reader.available()];
        int readResult = -1;
        if ((readResult = reader.read(arr, 0, arr.length)) != -1) {
            for (int i = 0; i < arr.length; i++) {
                byteDataList.add(arr[i]);
            }
            // map bytes to Ethernet Frame
            EthernetFrameModel ethernetFrameModel = new EthernetFrameModel(byteDataList, byteDataList.size());
            // print the ethernet frame details
            print(ethernetFrameModel.getPacketDetails());
            // map bytes to IP Frame if it exists in the Ether Frame header
            if( ethernetFrameModel.getProtocolType().toString().contains(ProtocolType.TYPE_IP))  {
                IPModel ipModel = new IPModel(byteDataList);
                // print the ip frame details
                print(ipModel.getPacketDetails());
                // map bytes to TCP Frame if it exists in the IP Frame header
                if( ipModel.getProtocolType().toString().contains(ProtocolType.TYPE_TCP))  {
                    TcpModel tcpModel = new TcpModel(byteDataList, ethernetFrameModel.getPacketSize());
                    // print the tcp header details
                    print(tcpModel.getPacketDetails());
                }else if( ipModel.getProtocolType().toString().contains(ProtocolType.TYPE_UDP))  {
                    // map bytes to UDP Frame if it exists in the IP Frame header
                    UdpModel udpModel = new UdpModel(byteDataList, ethernetFrameModel.getPacketSize());
                    // print the udp header details
                    print(udpModel.getPacketDetails());
                }else if( ipModel.getProtocolType().toString().contains(ProtocolType.TYPE_ICMP))  {
                    // map bytes to ICMP Frame if it exists in the IP Frame header
                    IcmpModel icmpModel = new IcmpModel(byteDataList, ethernetFrameModel.getPacketSize());
                    // print the icmp header details
                    print(icmpModel.getPacketDetails());
                }
            }
            // close the input stream
            reader.close();

        }
    }
}
