import java.io.IOException;
import java.io.*;
import java.util.*;
import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static ArrayList<Socket> ClientSockets;
    int amtclients = 0;

    Server() throws IOException {
        Date dt = new Date();
        System.out.println("Server started at " + String.format("%tc", dt));
        System.out.println();

        ServerSocket server = new ServerSocket(1111);
        ClientSockets = new ArrayList<Socket>();

        while (true) {
            Socket client = server.accept();
            AcceptClient acceptClient = new AcceptClient(client);
            System.out.println("Connection from Socket " + "[address: " + client.getLocalAddress() + ",port: "
                    + client.getPort() + ",localport: " + client.getLocalPort() + "] at " + String.format("%tc", dt));
            System.out.println();

        }
    }
    public static void main(String[] args) throws IOException {
        Server server = new Server();
    }

    class AcceptClient extends Thread {
        Socket ClientSocket;
        DataInputStream din;
        DataOutputStream dout;

        AcceptClient(Socket client) throws IOException {
            ClientSocket = client;
            din = new DataInputStream(ClientSocket.getInputStream());
            dout = new DataOutputStream(ClientSocket.getOutputStream());
            amtclients++;
            ClientSockets.add(ClientSocket);
                start();
            }
            public void run () {
            try {
                while (true) {
                    String clientmsg = din.readUTF();
                    System.out.println(clientmsg);
                    for (int i = 0; i < ClientSockets.size(); i++) {
                        Socket pSocket = (Socket) ClientSockets.get(i);
                        if (ClientSocket.equals(pSocket)) {
                            continue;
                        }
                        DataOutputStream pOut = new DataOutputStream(pSocket.getOutputStream());
                        pOut.writeUTF(clientmsg);
                        pOut.flush();
                    }
                }
            }
            catch (IOException e) {
            }
        }
    }
}
