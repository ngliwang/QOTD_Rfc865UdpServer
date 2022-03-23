import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Rfc865UdpServer {

    static int QOTD_PORT = 17;
    static String QUOTE = "Goals transform a random walk into a chase. - Mihaly Csikszentmihalyi";

    public static void main(String[] argv) {
    //
    // 1. Open UDP socket at well-known port
    //
    DatagramSocket socket = null;
    try {
        socket = new DatagramSocket(QOTD_PORT);
    } catch (SocketException e) {e.printStackTrace();}
    
    while (true) {
        try {
        //
        // 2. Listen for UDP request from client
        //
        // Create a datagram packet to hold incoming request
        byte[] buf = new byte[256];

        DatagramPacket request = new DatagramPacket(buf, buf.length);
        System.out.println("Waiting for request");
        socket.receive(request);
        
        // print out content in request
        String content = new String(request.getData(), 0, request.getLength());
        System.out.println("Received request: " + content);

        String quote = QUOTE;
        byte[] buffer = quote.getBytes();


        //
        // 3. Send UDP reply to client
        //
        DatagramPacket reply = new DatagramPacket(buffer, buffer.length, request.getAddress(), request.getPort());
        System.out.println("Reply sent.");
        socket.send(reply);
        } catch (IOException e) {e.printStackTrace();}
        }
    }
    }
       

