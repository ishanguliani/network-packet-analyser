/**
 * filename: {@link model.EthernetFrameModel}
 * author: @ishanguliani aka ig5859
 *
 * version:     1
 *
 * revision:    1
 *
 * description:
 * A model class to determine Ethernet Frame from the network packet
 */


package model;

import helper.StringManager;

import java.util.ArrayList;
import java.util.List;

public class EthernetFrameModel implements OnPacketReadyListener{
    List<Byte> ethernetModelList = new ArrayList <Byte>();
    private MacAddress sourceAddress;
    private MacAddress destinationAddress;
    private ProtocolType protocolType;
    private int packetSize;

    public EthernetFrameModel(List<Byte> dataByteList, int packetSize) {
        this.ethernetModelList = dataByteList.subList(0, 14);
        this.packetSize = packetSize;
        preparePacket();
    }

    /**
     * Prepare the granular details of the eth frame in the corresponding
     * class member variables. Eg -
     *  - destination mac
     *  - source mac
     *  - protocol
     */
    @Override
    public void preparePacket() {
        // map the destination mac address
        this.destinationAddress = new MacAddress(ethernetModelList.subList(0, 6));
        this.sourceAddress = new MacAddress(ethernetModelList.subList(6, 12));
        this.protocolType = new ProtocolType(ethernetModelList.subList(12, 14));
    }

    @Override
    public String toString() {
        return StringManager.ETHERNET_FRAME;
    }

    public MacAddress getSourceAddress() {
        return sourceAddress;
    }

    public MacAddress getDestinationAddress() {
        return destinationAddress;
    }

    public ProtocolType getProtocolType() {
        return protocolType;
    }

    public int getPacketSize() {
        return packetSize;
    }

    /**
     * Return a detailed list of all the parameters of the eth frame
     * @return
     */
    @Override
    public String getPacketDetails() {
        return this
                + StringManager.ETHERNET_FRAME_LABEL +     "Packet Size    =\t" + getPacketSize() + " bytes"
                + StringManager.ETHERNET_FRAME_LABEL +     "Destination    =\t" + getDestinationAddress()
                + StringManager.ETHERNET_FRAME_LABEL +     "Source         =\t" + getSourceAddress()
                + StringManager.ETHERNET_FRAME_LABEL +     "Ethertype      =\t"  + getProtocolType()
                + StringManager.ETHERNET_FRAME_LABEL;
    }
}
