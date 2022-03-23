import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Rfc865UdpClient {
    // main

    static int QOTD_PORT = 17;
    static String QUOTE = "Goals transform a random walk into a chase. - Mihaly Csikszentmihalyi";

    public static void main(String[] args) {
        //
        // 1. Open UDP socket at well-known port
        //
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            // connect to server using specific name
            // InetAddress serverAddress = InetAddress.getByName("swlab2-c.scse.ntu.edu.sg");
            InetAddress serverAddress = InetAddress.getByName("localhost");

            socket.connect(serverAddress, QOTD_PORT);
            System.out.println("Connected to server" + serverAddress + ":" + QOTD_PORT + "." );

        } catch (Exception e) {
            e.printStackTrace();
        } 

        
        String content;
        try {
            //
            // 2. Send UDP request to server
            //
            content = "Requesting for a quote" + InetAddress.getLocalHost().getHostAddress();
            // content = "NG LI WANG, SSP4, 172.21.150.227";
            byte[] buffer = content.getBytes("UTF-8");
            System.out.println("Sending request: " + content);
            
            // send udp request to server
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
            socket.send(request);
            System.out.println("Request sent.");


            //
            // 3. Receive UDP reply from server
            //
            // receive udp reply from server
            byte[] replybuf = new byte[256];
            DatagramPacket reply = new DatagramPacket(replybuf, replybuf.length);
            socket.receive(reply);

            // process received request
            String replyContent = new String(replybuf, 0, reply.getLength());
            System.out.println("Received reply: " + replyContent);
        
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } finally{
            socket.close();
        }
    }
}
