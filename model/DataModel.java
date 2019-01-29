/**
 * filename: @{@link model.DataModel}
 * author: @ishanguliani aka ig5859
 *
 * version:     1
 *
 * revision:    1
 *
 * description:
 * A model class to determine data model inside TCP header from the network packet
 */

package model;

import helper.Helper;
import helper.StringManager;

import java.util.ArrayList;
import java.util.List;

public class DataModel {

    private final int BYTES_PER_ROW = 13;
    private List<Byte> dataModelList = new ArrayList<Byte>();
    private String data;
    private String protocolType;

    public DataModel(List<Byte> subList, String protocolType) {
        this.dataModelList = subList;
        this.protocolType = protocolType;
        prepareData();
    }

    /**
     * Prepare the IP address from the list of bytes obtained
     */
    private void prepareData() {
        StringBuilder builder = new StringBuilder();
        for( byte elem: dataModelList)  {
            builder.append(Helper.BitwiseManager.convertByteToDecimal(elem)).append(" ");
        }
        this.data = builder.toString();
    }

    @Override
    public String toString() {
        String toReturn = "";
        StringBuilder builder = new StringBuilder();
        for( int i = 0; i < dataModelList.size(); i++)  {
            if( i % BYTES_PER_ROW == 0 ) {
                if( this.protocolType.equals(ProtocolType.TYPE_TCP))    {
                    toReturn += StringManager.TCP_FRAME_LABEL;
                }else if( this.protocolType.equals(ProtocolType.TYPE_UDP)) {
                    toReturn += StringManager.UDP_FRAME_LABEL;
                }

                toReturn += "\t" + builder.toString();
                // reset the string builder
                builder = new StringBuilder();
            }else   {
                builder.append(Helper.BitwiseManager.convertByteToHex(dataModelList.get(i)) + " ");
            }
        }
        return toReturn;
    }
}
