/**
 * filename: @{@link model.Flag}
 * author: @ishanguliani aka ig5859
 *
 * version:     1
 *
 * revision:    1
 *
 * description:
 * A model class to determine different types of flags from the network packet
 */

package model;

import helper.StringManager;

public class Flag {

    private String flagValue;
    private String packetType;
    public boolean hasUrgentPointer = false;

    public Flag(String flagValue, String packetType) {
        this.flagValue = flagValue;
        this.packetType = packetType;
    }

    @Override
    public String toString() {

        String toReturn = "0x" + flagValue;
        if( this.packetType.equals(ProtocolType.TYPE_IP)) {
            if (flagValue.matches("40\\d\\d")) {
                toReturn += StringManager.IP_FRAME_LABEL + "\t.1.. .... = do not fragment\t";
                toReturn += StringManager.IP_FRAME_LABEL + "\t..0. .... = last fragment\t";
            } else {
                toReturn += StringManager.IP_FRAME_LABEL + "\t.0.. .... = do not fragment\t";
                toReturn += StringManager.IP_FRAME_LABEL + "\t..0. .... = last fragment\t";
            }
        }else if( this.packetType.equals(ProtocolType.TYPE_TCP))    {

            if (flagValue.matches("0\\d\\d")) {
                toReturn += StringManager.TCP_FRAME_LABEL + "\t000. .... .... = Reserved: Not set\t";
                toReturn += StringManager.TCP_FRAME_LABEL + "\t...0 .... .... = Nonce: Not set\t";
                toReturn += StringManager.TCP_FRAME_LABEL + "\t.... 0... .... = Congestion Window Reduced (CWR): Not set\t";
                toReturn += StringManager.TCP_FRAME_LABEL + "\t.... .0.. .... = ECN-Echo: Not set\t";
            }

            // check urgent pointer bit
            if( (Long.parseLong(this.flagValue, 16) & 0b000000100000) != 32) {
                toReturn += StringManager.TCP_FRAME_LABEL + "\t.... ..0. .... = No urgent pointer\t";
            }else {
                toReturn += StringManager.TCP_FRAME_LABEL + "\t.... ..1. .... = urgent pointer set\t";
                this.hasUrgentPointer = true;
            }

            // check acknowledgement bit
            if( (Long.parseLong(this.flagValue, 16) & 0b000000010000) != 16) {
                toReturn += StringManager.TCP_FRAME_LABEL + "\t.... ...0 .... = No acknowledgement\t";
            }else {
                toReturn += StringManager.TCP_FRAME_LABEL + "\t.... ...1 .... = acknowledgement set\t";
            }

            // check push bit
            if( (Long.parseLong(this.flagValue, 16) & 0b000000001000) != 8) {
                toReturn += StringManager.TCP_FRAME_LABEL + "\t.... .... 0... = No push\t";
            }else {
                toReturn += StringManager.TCP_FRAME_LABEL + "\t.... .... 1... = Push set\t";
            }

            // check reset bit
            if( (Long.parseLong(this.flagValue, 16) & 0b000000000100) != 4) {
                toReturn += StringManager.TCP_FRAME_LABEL + "\t.... .... .0.. = No reset\t";
            }else {
                toReturn += StringManager.TCP_FRAME_LABEL + "\t.... .... .1.. = Reset set\t";
            }

            // check syn bit
            if( (Long.parseLong(this.flagValue, 16) & 0b000000000010) != 2) {
                toReturn += StringManager.TCP_FRAME_LABEL + "\t.... .... ..0. = No Syn\t";
            }else {
                toReturn += StringManager.TCP_FRAME_LABEL + "\t.... .... ..1. = Syn set\t";
            }
            // check fin bit
            if( (Long.parseLong(this.flagValue, 16) & 0b000000000001) != 1) {
                toReturn += StringManager.TCP_FRAME_LABEL + "\t.... .... ...0 = No Fin\t";
            }else {
                toReturn += StringManager.TCP_FRAME_LABEL + "\t.... .... ...1 = Fin set\t";
            }
        }
        return toReturn;
    }
}
