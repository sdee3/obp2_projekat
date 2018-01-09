import java.io.*;
import java.net.Socket;
import java.util.Random;

public class ServerThread extends Thread {

    private BufferedReader in;
    private PrintWriter out;
    private Server instanca;

    private int vremeDoPorudzbine, indeksTekucePorudzbine;

    ServerThread(Socket s, Server instanca, int indeksTekucePorudzbine) {
        this.vremeDoPorudzbine = 0;
        this.indeksTekucePorudzbine = indeksTekucePorudzbine;
        this.instanca = instanca;

        try {
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter
                    (s.getOutputStream())), true);
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
                    instanca.azurirajFrejm(request, vremeDoPorudzbineUSek, indeksTekucePorudzbine);
            } catch (IOException | InterruptedException e1) {
                e1.printStackTrace();
            }
         }
    }
}