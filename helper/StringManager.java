/**
 * filename: @{@link helper.StringManager}
 *
 * author: @ishanguliani aka ig5859
 *
 * version:     1
 *
 * revision:    1
 *
 * description:
 * A String helper class to maintain project wide Strings centrally
 */

/**
 * A String class to maintain project wide string variables
 */

package helper;

public class StringManager {
    public static final java.lang.String ERROR_READING_INTO_BUFFER = "Something went wrong while reading bytes into buffer";
    public static final java.lang.String ERROR_COMMAND_LINE_ARGUMENTS = "Need command line arguments ! Enter a filename please ";
    public static final String EXCEPTION_BUFFER_NOT_INITIALISED = "byte buffer not initialised";

    public static final String ETHERNET_FRAME = "\nETHER:\t----- Ether Header -----\nETHER:";
    public static final String ETHERNET_FRAME_LABEL = "\nETHER:\t";

    public static final String IP_FRAME = "\nIP:\t----- IP Header -----\nIP:";
    public static final String IP_FRAME_LABEL = "\nIP:\t";
    public static final String TCP_FRAME = "\nTCP:\t----- TCP Header -----\nTCP:";
    public static final String TCP_FRAME_LABEL = "\nTCP:\t";
    public static final String UDP_FRAME = "\nUDP:\t----- UDP Header -----\nUDP:";
    public static final String UDP_FRAME_LABEL = "\nUDP:\t";
    public static final String ICMP_FRAME = "\nICMP:\t----- ICMP Header -----\nICMP:";
    public static final String ICMP_FRAME_LABEL = "\nICMP:\t";
    public static final String ERROR_FILE_NOT_FOUND = "\nOops! File not found. Please try running again with a valid file name. " +
            "Check that the file exists in the working directory.";
}
