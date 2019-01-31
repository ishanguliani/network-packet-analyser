/**
 * filename: @{@link model.IPModel}
 * author: @ishanguliani aka ig5859
 *
 * version:     1
 *
 * revision:    1
 *
 * description:
 * A model class to determine Internet Protocol skeleton from the network packet
 */

package model;

import java.util.ArrayList;
import java.util.List;
import helper.StringManager;
import helper.Helper;

import static helper.Helper.BitwiseManager.convertByteToHex;

public class IPModel implements OnPacketReadyListener{
    List<Byte> ipModelList = new ArrayList<Byte>();
    private int version;
    private int headerLength;
    private IPService serviceType;
    private int totalLength;
    private long identification;
    private Flag flags;
    private int fragmentOffset;
    private int timeToLive;
    private ProtocolType protocolType;
    private Checksum headerChecksum;
    private IPAddress sourceAddress;
    private IPAddress destinationAddress;

    public IPModel(List<Byte> ipModelList) {
        this.ipModelList = ipModelList;
        preparePacket();
    }

    /**
     * Prepare the granular details of the IP frame in the corresponding
     * class member variables. Eg -
     * IP:
     * IP:   Version = 4
     * IP:   Header length = 20 bytes
     * IP:   Type of service = 0x00
     * IP:         xxx. .... = 0 (precedence)
     * IP:         ...0 .... = normal delay
     * IP:         .... 0... = normal throughput
     * IP:         .... .0.. = normal reliability
     * IP:   Total length = 252 bytes
     * IP:   Identification = 32492
     * IP:   Flags = 0x4
     * IP:         .1.. .... = do not fragment
     * IP:         ..0. .... = last fragment
     * IP:   Fragment offset = 0 bytes
     * IP:   Time to live = 64 seconds/hops
     * IP:   Protocol = 6 (TCP)
     * IP:   Header checksum = 0x4a9e
     * IP:   Source address = 129.21.66.85
     * IP:   Destination address = 172.217.0.46
     * IP:   No options
     *
     */
    @Override
    public void preparePacket() {
        this.version = ipModelList.get(14) >> 4;
        this.headerLength =  ipModelList.subList(14, 34).size();
        this.serviceType = new IPService("0x" +  convertByteToHex(ipModelList.subList(15, 16).get(0)));
        this.totalLength = (ipModelList.get(16) + ipModelList.get(17)) & 0xff ;
        this.identification = Long.parseLong(convertByteToHex(ipModelList.get(18)) + convertByteToHex(ipModelList.get(19)), 16);
        this.flags = new Flag( convertByteToHex(ipModelList.get(20))
                +  convertByteToHex(ipModelList.get(21)), ProtocolType.TYPE_IP);
        this.fragmentOffset = (Integer.parseInt( convertByteToHex(ipModelList.get(20)) +  convertByteToHex(ipModelList.get(21)), 16) & 0b0001111111111111);
        this.timeToLive = ipModelList.get(22) & 0xff;
        this.protocolType = new ProtocolType(ipModelList.subList(23, 24));
        this.headerChecksum = new Checksum(convertByteToHex(ipModelList.get(24)) + convertByteToHex(ipModelList.get(25)));
        this.sourceAddress = new IPAddress(ipModelList.subList(26, 30));
        this.destinationAddress = new IPAddress(ipModelList.subList(30, 34));
    }

    public int getVersion() {
        return version;
    }

    public int getHeaderLength() {
        return headerLength;
    }

    public IPService getServiceType() {
        return serviceType;
    }

    public int getTotalLength() {
        return totalLength;
    }

    public long getIdentification() {
        return identification;
    }

    public Flag getFlags() {
        return flags;
    }

    public int getFragmentOffset() {
        return fragmentOffset;
    }

    public int getTimeToLive() {
        return timeToLive;
    }

    public ProtocolType getProtocolType() {
        return protocolType;
    }

    public Checksum getHeaderChecksum() {
        return headerChecksum;
    }

    public IPAddress getSourceAddress() {
        return sourceAddress;
    }

    public IPAddress getDestinationAddress() {
        return destinationAddress;
    }

    @Override
    public String toString() {
        return StringManager.IP_FRAME;
    }

    /**
     * Return a detailed list of all the parameters of the eth frame
     * @return
     */
    @Override
    public String getPacketDetails() {
        String toReturn = this
                + StringManager.IP_FRAME_LABEL +     "Version           =\t" + getVersion()
                + StringManager.IP_FRAME_LABEL +     "Header length     =\t" + getHeaderLength() + " bytes"
                + StringManager.IP_FRAME_LABEL +     "Type of service   =\t" + getServiceType()
                + StringManager.IP_FRAME_LABEL +     "Total length      =\t" + getTotalLength()
                + StringManager.IP_FRAME_LABEL +     "Identification    =\t" + getIdentification()
                + StringManager.IP_FRAME_LABEL +     "Flags             =\t" + getFlags()
                + StringManager.IP_FRAME_LABEL +     "Fragment offset   =\t" + getFragmentOffset() + " bytes"
                + StringManager.IP_FRAME_LABEL +     "Time to live      =\t" + getTimeToLive() + " seconds/hop"
                + StringManager.IP_FRAME_LABEL +     "Protocol          =\t" + getProtocolType()
                + StringManager.IP_FRAME_LABEL +     "Header checksum   =\t" + getHeaderChecksum()
                + StringManager.IP_FRAME_LABEL +     "Source address    =\t" + getSourceAddress()
                + StringManager.IP_FRAME_LABEL +     "Destin. address   =\t" + getDestinationAddress()
                + StringManager.IP_FRAME_LABEL +     "No Options"
                + StringManager.IP_FRAME_LABEL;
        return toReturn;
    }


}
