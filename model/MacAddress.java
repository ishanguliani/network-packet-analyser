/**
 * filename: {@link model.MacAddress}
 * author: @ishanguliani aka ig5859
 *
 * version:     1
 *
 * revision:    1
 *
 * description:
 * A model class to determine Mac Address from the network packet
 */


package model;

import helper.Helper;

import java.util.ArrayList;
import java.util.List;

public class MacAddress {
    List<Byte> macAddressModel = new ArrayList<Byte>();
    String address;

    public MacAddress(List<Byte> model) {
        this.macAddressModel = model;
        convertToString();
    }

    /**
     * Return the mac address in string format
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * Convert the given mac address to string representation
     * and store the result in the instance variable
     */
    private void convertToString()  {
        StringBuilder builder = new StringBuilder();
        for( Byte b : macAddressModel) {
            builder.append(Helper.BitwiseManager.convertByteToHex(b) + ":");
        }
        this.address = builder.toString().substring(0, builder.toString().length()-1);
    }

    @Override
    public String toString()    {
        return this.address;
    }
}
