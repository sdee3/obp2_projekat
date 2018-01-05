import java.io.*;
import java.net.Socket;
import java.util.Random;

public class ServerThread extends Thread {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    private int vremeDoPorudzbine;

    public ServerThread(Socket socket) {
        this.socket = socket;
        this.vremeDoPorudzbine = 0;

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter
                    (socket.getOutputStream())), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
         while (true){
            try {
                String request = in.readLine().replace('\t', '\n');
                System.out.println(request);
                vremeDoPorudzbine = new Random().nextInt(40) + 10;
                int vremeDoPorudzbineUSek = vremeDoPorudzbine * 60;
                out.println("Vreme do isporučenja Vaše porudžbine: " + vremeDoPorudzbine + " minuta.");

                if((vremeDoPorudzbineUSek % 60 == 0 || vremeDoPorudzbineUSek % 60 == 59 ) && vremeDoPorudzbineUSek > 0)
                    Server.azurirajFrejm(request, vremeDoPorudzbineUSek);

            } catch (IOException | InterruptedException e1) {
                e1.printStackTrace();
            }
         }
    }
}