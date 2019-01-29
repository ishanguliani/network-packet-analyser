/**
 * filename: @{@link model.IPService}
 * author: @ishanguliani aka ig5859
 *
 * version:     1
 *
 * revision:    1
 *
 * description:
 * A model class to determine service type of the IP network packet
 *
 */

package model;

import helper.StringManager;

public class IPService {
    private String type;

    public IPService(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        String toReturn = type;
        if( type.equals("0x00") ) {
            toReturn += StringManager.IP_FRAME_LABEL + "\t0000 00.. = Differentiated Services Codepoint: Default (0)\t";
            toReturn += StringManager.IP_FRAME_LABEL + "\t.... ..00 = Explicit Congestion Notification: Not ECN-Capable Transport (0)\t";
        }else if( type.equals("0x10"))  {
            toReturn += StringManager.IP_FRAME_LABEL + "\t0001 00.. = Differentiated Services Codepoint: Unknown (4)\t";
            toReturn += StringManager.IP_FRAME_LABEL + "\t.... ..00 = Explicit Congestion Notification: Not ECN-Capable Transport (0)\t";
        }

        return toReturn;
    }

}
