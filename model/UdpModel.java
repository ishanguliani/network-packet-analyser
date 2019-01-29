/**
 * filename: @{@link model.UdpModel}
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

public class UdpModel implements OnPacketReadyListener{
    List<Byte> udpModelList = new ArrayList<Byte>();
    private long sourcePort;
    private long destinationPort;
    private long length;
    private Checksum headerChecksum;
    private DataModel data;
    private int packetSize;

    public UdpModel(List<Byte> udpModelList, int totalPacketSize) {
        this.udpModelList = udpModelList;
        this.packetSize = totalPacketSize;
        preparePacket();
    }

    public long getSourcePort() {
        return sourcePort;
    }

    public long getDestinationPort() {
        return destinationPort;
    }

    public long getLength() {
        return length;
    }

    public Checksum getHeaderChecksum() {
        return headerChecksum;
    }

    public DataModel getData() {
        return data;
    }

    /**
     * Prepare the granular details of the UDP header in the corresponding
     * class member variables. Eg -
     * UDP:  ----- UDP Header -----
     * UDP:
     * UDP:  Source port = 35632
     * UDP:  Destination port = 53
     * UDP:  Length = 41
     * UDP:  Checksum = 0x9c1d
     * UDP:
     * UDP:  Data: (first 64 bytes)
     * UDP:  8b30 0035 0029 9c1d f38e 0100 0001 0000
     * UDP:  0000 0000 0470 6c75 7306 676f 6f67 6c65
     * UDP:  0363 6f6d 0000 0100 01
     */

    @Override
    public void preparePacket() {
        this.sourcePort = Long.parseLong((convertByteToHex(udpModelList.get(34))
                + convertByteToHex(udpModelList.get(35))), 16);
        this.destinationPort = Long.parseLong((convertByteToHex(udpModelList.get(36))
                + convertByteToHex(udpModelList.get(37))), 16);
        this.length = Long.parseLong((convertByteToHex(udpModelList.get(38))
                + convertByteToHex(udpModelList.get(39))), 16);
        this.headerChecksum = new Checksum(convertByteToHex(udpModelList.get(40))
                + convertByteToHex(udpModelList.get(41)));
        this.data = new DataModel(udpModelList.subList(34, Math.min(98, this.packetSize-1)), ProtocolType.TYPE_UDP);
    }


    @Override
    public String toString() {
        return StringManager.UDP_FRAME;
    }

    /**
     * Return a detailed list of all the parameters of the eth frame
     * @return
     */
    @Override
    public String getPacketDetails() {
        return this
                + StringManager.UDP_FRAME_LABEL+     "Source port       =\t" + getSourcePort()
                + StringManager.UDP_FRAME_LABEL+     "Destination port  =\t" + getDestinationPort()
                + StringManager.UDP_FRAME_LABEL+     "Length            =\t" + getLength()
                + StringManager.UDP_FRAME_LABEL+     "Header checksum   =\t" + getHeaderChecksum()
                + StringManager.UDP_FRAME_LABEL+     "Data (first 64 B) =\t" + getData()
                + StringManager.UDP_FRAME_LABEL;
    }


}
