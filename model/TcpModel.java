/**
 * filename: @{@link model.TcpModel}
 * author: @ishanguliani aka ig5859
 *
 * version:     1
 *
 * revision:    1
 *
 * description:
 * A model class to determine TCP model from the network packet
 */

package model;

import helper.StringManager;

import java.util.ArrayList;
import java.util.List;

import static helper.Helper.BitwiseManager.convertByteToHex;

public class TcpModel implements OnPacketReadyListener{
    List<Byte> tcpModelList = new ArrayList<Byte>();
    private long sourcePort;
    private long destinationPort;
    private long sequenceNumber;
    private long acknowledgementNumber;
    private OptionsModel options;
    private Flag flags;
    private long window;
    private Checksum headerChecksum;
    private long urgentPointer;
    private DataModel data;
    private int packetSize;

    public TcpModel(List<Byte> tcpModelList, int totalPacketSize) {
        this.tcpModelList = tcpModelList;
        this.packetSize = totalPacketSize;
        preparePacket();
    }

    public long getSourcePort() {
        return sourcePort;
    }

    public long getDestinationPort() {
        return destinationPort;
    }

    public long getSequenceNumber() {
        return sequenceNumber;
    }

    public long getAcknowledgementNumber() {
        return acknowledgementNumber;
    }

    public Flag getFlags() {
        return flags;
    }

    public long getWindow() {
        return window;
    }

    public Checksum getHeaderChecksum() {
        return headerChecksum;
    }

    public long getUrgentPointer() {
        return urgentPointer;
    }

    public OptionsModel getOptions() {
        return options;
    }

    public DataModel getData() {
        return data;
    }

    /**
     * Prepare the granular details of the TCP header in the corresponding
     * class member variables. Eg -
     * TCP:
     * TCP:  Source port = 52566
     * TCP:  Destination port = 443
     * TCP:  Sequence number = 424114169
     * TCP:  Acknowledgement number = 2974881950
     * TCP:  Data offset = 8 bytes
     * TCP:  Flags = 0x18
     * TCP:        ..0. .... = No urgent pointer
     * TCP:        ...1 .... = Acknowledgement
     * TCP:        .... 1... = Push
     * TCP:        .... .0.. = No reset
     * TCP:        .... ..0. = No Syn
     * TCP:        .... ...0 = No Fin
     * TCP:  Window = 1832
     * TCP:  Checksum = 0x75b0
     * TCP:  Urgent pointer = 0
     * TCP:  No options
     * TCP:
     * TCP:  Data: (first 64 bytes)
     * TCP:  cd56 01bb 1947 77f9 b151...
     * TCP:  75b0 0000 0101 080a 0007...
     * TCP:  1703 0300 c300 0000 0000...
     * TCP:  b6ce fab3 6791 9e87 b9ac...
     *
     */

    @Override
    public void preparePacket() {
        this.sourcePort = Long.parseLong((convertByteToHex(tcpModelList.get(34))
                + convertByteToHex(tcpModelList.get(35))), 16);
        this.destinationPort = Long.parseLong((convertByteToHex(tcpModelList.get(36))
                + convertByteToHex(tcpModelList.get(37))), 16);
        this.sequenceNumber = Long.parseLong((convertByteToHex(tcpModelList.get(38))
                + convertByteToHex(tcpModelList.get(39))
                + convertByteToHex(tcpModelList.get(40))
                + convertByteToHex(tcpModelList.get(41))
        ), 16);
        this.acknowledgementNumber = Long.parseLong((convertByteToHex(tcpModelList.get(42))
                + convertByteToHex(tcpModelList.get(43))
                + convertByteToHex(tcpModelList.get(44))
                + convertByteToHex(tcpModelList.get(45))
        ), 16);
        this.flags = new Flag( convertByteToHex(tcpModelList.get(46)).substring(1)
                +  convertByteToHex(tcpModelList.get(47)), ProtocolType.TYPE_TCP);
        this.window = Long.parseLong((convertByteToHex(tcpModelList.get(48))
                + convertByteToHex(tcpModelList.get(49))), 16);
        this.headerChecksum = new Checksum(convertByteToHex(tcpModelList.get(50))
                + convertByteToHex(tcpModelList.get(51)));
        this.urgentPointer = Long.parseLong((convertByteToHex(tcpModelList.get(52))
                + convertByteToHex(tcpModelList.get(53))), 16);
        this.data = new DataModel(tcpModelList.subList(65, Math.min(130, this.packetSize)), ProtocolType.TYPE_TCP);
        this.options = new OptionsModel(tcpModelList.subList(54, 67));
    }



    @Override
    public String toString() {
        return StringManager.TCP_FRAME;
    }

    /**
     * Return a detailed list of all the parameters of the eth frame
     * @return
     */
    @Override
    public String getPacketDetails() {
        return this
                + StringManager.TCP_FRAME_LABEL+     "Source port       =\t" + getSourcePort()
                + StringManager.TCP_FRAME_LABEL+     "Destination port  =\t" + getDestinationPort()
                + StringManager.TCP_FRAME_LABEL+     "Sequence number   =\t" + getSequenceNumber()
                + StringManager.TCP_FRAME_LABEL+     "Acknowl. number   =\t" + getAcknowledgementNumber()
                + StringManager.TCP_FRAME_LABEL+     "Flags             =\t" + getFlags()
                + StringManager.TCP_FRAME_LABEL+     "Window            =\t" + getWindow()
                + StringManager.TCP_FRAME_LABEL+     "Header checksum   =\t" + getHeaderChecksum()
                + StringManager.TCP_FRAME_LABEL+     "Urgent pointer    =\t" + getUrgentPointer()
                + StringManager.TCP_FRAME_LABEL +    "Options (12 bytes)=\t" + getOptions()
                + StringManager.TCP_FRAME_LABEL+     "Data (first 64 B) =\t" + getData()
                + StringManager.TCP_FRAME_LABEL;
    }


}
