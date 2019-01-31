/**
 * filename: {@link model.MessageType}
 * author: @ishanguliani aka ig5859
 *
 * version:     1
 *
 * revision:    1
 *
 * description:
 * A model class to determine message Type from the icmp network packet
 */

package model;

import java.util.Hashtable;
import helper.StringManager;
import helper.Helper;

public class MessageType {
    public static final String TYPE_ECHO = "Echo request";
    private Byte messageTypeModel;

    // hashtable to keep a map between the message type id and message text
    // example: Type = 8 (Echo request)
    private Hashtable<String, String> type = new Hashtable<>();

    public MessageType(Byte messageTypeModel) {
        this.messageTypeModel = messageTypeModel;
        prepareMessage();
    }

    /**
     * Determine the message type from the given protocol type model
     */
    private void prepareMessage() {
        String messageType = Helper.BitwiseManager.convertByteToHex(messageTypeModel);
        if( messageType.equals("08"))  {
            this.type.put(String.valueOf(Integer.parseInt(messageType, 16)), TYPE_ECHO);
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
