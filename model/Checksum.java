/**
 * filename: @{@link model.Checksum}
 * author: @ishanguliani aka ig5859
 *
 * version:     1
 *
 * revision:    1
 *
 * description:
 * A model class to determine header checksum from the network packet
 */

package model;

public class Checksum {

    private String checksum;

    public Checksum(String checksum) {
        this.checksum = checksum;
    }

    @Override
    public String toString() {
        return "0x" + checksum;
    }
}
