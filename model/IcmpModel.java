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

public class IcmpModel implements OnPacketReadyListener{
    List<Byte> icmpModelList = new ArrayList<Byte>();
    private MessageType type;
    private long code;
    private Checksum headerChecksum;
    private int packetSize;

    public IcmpModel(List<Byte> icmpModelList, int totalPacketSize) {
        this.icmpModelList = icmpModelList;
        this.packetSize = totalPacketSize;
        preparePacket();
    }

    public MessageType getType() {
        return type;
    }

    public long getCode() {
        return code;
    }

    public Checksum getHeaderChecksum() {
        return headerChecksum;
    }

    /**
     * Prepare the granular details of the UDP header in the corresponding
     * class member variables. Eg -
     * ICMP:
     * ICMP:  Type = 8 (Echo request)
     * ICMP:  Code = 0
     * ICMP:  Checksum = 0x60b1
     * ICMP:
     */

    @Override
    public void preparePacket() {
        this.type = new MessageType(icmpModelList.get(34));
        this.code = Long.parseLong((convertByteToHex(icmpModelList.get(35))), 16);
        this.headerChecksum = new Checksum(convertByteToHex(icmpModelList.get(36))
                + convertByteToHex(icmpModelList.get(37)));
    }


    @Override
    public String toString() {
        return StringManager.ICMP_FRAME;
    }

    /**
     * Return a detailed list of all the parameters of the eth frame
     * @return
     */
    @Override
    public String getPacketDetails() {
        return this
                + StringManager.ICMP_FRAME_LABEL+     "Type             =\t" + getType()
                + StringManager.ICMP_FRAME_LABEL+     "Length           =\t" + getCode()
                + StringManager.ICMP_FRAME_LABEL+     "Header checksum  =\t" + getHeaderChecksum()
                + StringManager.ICMP_FRAME_LABEL;
    }


}
