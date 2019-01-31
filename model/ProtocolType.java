/**
 * filename: {@link model.ProtocolType}
 * author: @ishanguliani aka ig5859
 *
 * version:     1
 *
 * revision:    1
 *
 * description:
 * A model class to determine Protocol Type from the network packet
 */

package model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import helper.StringManager;
import helper.Helper;

public class ProtocolType {
    public static final String TYPE_IP = "IP";
    public static final String TYPE_IPV6 = "IPv6";
    public static final String TYPE_TCP = "TCP";
    public static final String TYPE_UDP = "UDP";
    public static final String TYPE_ICMP = "ICMP";

    private List<Byte> protocolTypeModel = new ArrayList<Byte>();

    // hashtable to keep a map between the hex value and protocol type
    // example: 0800 (IP)
    private Hashtable<String, String> type = new Hashtable<>();

    public ProtocolType(List<Byte> protocolTypeModel) {
        this.protocolTypeModel = protocolTypeModel;
        prepareType();
    }

    /**
     * Determine the protocol type from the given protocol type model
     */
    private void prepareType() {
        String protocolType = Helper.BitwiseManager.convertByteToHex(protocolTypeModel.get(0));
        if( protocolTypeModel.size() == 1 ) {
            if( protocolType.equals("06"))  {
                this.type.put(String.valueOf(Integer.parseInt(protocolType, 16)), TYPE_TCP);
            }
            else if( protocolType.equals("11")) {
                this.type.put(String.valueOf(Integer.parseInt(protocolType, 16)), TYPE_UDP);
            }else if( protocolType.equals("01")) {
                this.type.put(String.valueOf(Integer.parseInt(protocolType, 16)), TYPE_ICMP);
            }
        }else {
            String halfHexByte1 = Helper.BitwiseManager.convertByteToHex(protocolTypeModel.get(0));
            String halfHexByte2 = Helper.BitwiseManager.convertByteToHex(protocolTypeModel.get(1));
            if ((halfHexByte1 + halfHexByte2).equals("0800")) {
                this.type.put("0800", TYPE_IP);
            } else {
                this.type.put(halfHexByte1 + halfHexByte2, TYPE_IPV6);
            }
        }
    }

    @Override
    public String toString() {
        return type.toString()
                .replace("=", " (")
                .replace("{", "")
                .replace("}", ")");
    }
}
