import java.net.Socket;
import java.util.Scanner;
import java.io.*;
import java.net.*;

//Kommentarer till denna kod är samma som klassen Client om ni vill kika på de så är det bara till att öppna Client klassen :)
public class Client1 implements Runnable{

    Socket sckt;
    DataOutputStream dout;
    DataInputStream din;

    Client1() throws UnknownHostException, IOException {

        sckt = new Socket("127.0.0.1", 1111);
        dout = new DataOutputStream(sckt.getOutputStream());
        din = new DataInputStream(sckt.getInputStream());

        Thread thread;
        thread = new Thread(this);
        thread.start();

        BufferedReader br = null;
        String name = null;
        Scanner input = new Scanner(System.in);
        dout.writeUTF(name + " has entered the chat.");
        String Str = "";
        try {
            System.out.println("Client successfully connected to the server.");
            System.out.print("Enter you name: ");
            name = input.next();
            dout.writeUTF(name + " has entered the chat.");
            name += ": ";

            br = new BufferedReader(new InputStreamReader(System.in));
            while (!Str.equalsIgnoreCase("exit")) {
                Str = br.readLine();
                dout.writeUTF(name + Str);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] arg) throws UnknownHostException, IOException {
        Client client = new Client();
    }


    public void run() {
        while (true) {
            try {
                System.out.println( din.readUTF());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}