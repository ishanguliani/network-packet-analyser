/**
 * filename: @{@link model.OptionsModel}
 * author: @ishanguliani aka ig5859
 *
 * version:     1
 *
 * revision:    1
 *
 * description:
 * A model class to determine options model inside TCP header from the network packet
 */

package model;

import java.util.ArrayList;
import java.util.List;
import helper.StringManager;
import helper.Helper;

public class OptionsModel {

    private List<Byte> optionsModelList = new ArrayList<Byte>();
    private int noOp1;
    private int noOp2;

    public OptionsModel(List<Byte> subList) {
        this.optionsModelList = subList;
        prepareOptions();
    }

    /**
     * Prepare the IP address from the list of bytes obtained
     */
    private void prepareOptions() {
        noOp1 = Integer.parseInt(Helper.BitwiseManager.convertByteToHex(optionsModelList.get(0)), 16) & 0x0f;
        noOp2 = Integer.parseInt(Helper.BitwiseManager.convertByteToHex(optionsModelList.get(1)), 16) & 0x0f;
    }

    @Override
    public String toString() {
        String toReturn = "";
        if( noOp1 == 1) {
            toReturn += StringManager.TCP_FRAME_LABEL + "\tTCP Option - No-Operation (NOP) - Set\t";
        }else {
            toReturn += StringManager.TCP_FRAME_LABEL + "\tTCP Option - No-Operation (NOP) - Not set\t";
        }

        if( noOp2 == 1)  {
            toReturn += StringManager.TCP_FRAME_LABEL + "\tTCP Option - No-Operation (NOP) - Set\t";
        }else   {
            toReturn += StringManager.TCP_FRAME_LABEL + "\tTCP Option - No-Operation (NOP) - Not set\t";
        }

        return toReturn;
    }
}
