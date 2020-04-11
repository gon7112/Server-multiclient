import java.net.Socket;
import java.util.Scanner;
import java.io.*;
import java.net.*;


public class Client implements Runnable{

    Socket sckt;
    // från client to Server
    DataOutputStream dout;
    // från servern till klient
    DataInputStream din;

//throws IOException som gör så att inputstream, outputstream samt socket kan användas
    Client() throws UnknownHostException, IOException {
        //deklarerationer sker nedan
        sckt = new Socket("127.0.0.1", 1111);
        dout = new DataOutputStream(sckt.getOutputStream());
        din = new DataInputStream(sckt.getInputStream());

        Thread thread;
        thread = new Thread(this);
        thread.start();

        BufferedReader br = null;
        String name = null;
        Scanner input = new Scanner(System.in);
        String Str = "";
        try {
            //Genom input från klienten då denna system.out.print visas så får man name vilket är namnet till klienten
            System.out.println("Client successfully connected to the server");
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
//main metoden kör själva klassen
    public static void main(String[] arg) throws UnknownHostException, IOException {
        Client client = new Client();
    }


    public void run() {
        while (true) {
            try {
                System.out.println( din.readUTF());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
