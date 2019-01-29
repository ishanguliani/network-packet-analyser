/**
 * filename: @{@link model.OnPacketReadyListener}
 * author: @ishanguliani aka ig5859
 *
 * version:     1
 *
 * revision:    1
 *
 * description:
 * An interface that supplies essential methods that can be used with any
 * kind of network packet to prepare and print the fine details within it.
 */
package model;

public interface OnPacketReadyListener {
    String getPacketDetails();
    void preparePacket();
}
