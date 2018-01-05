import java.io.*;
import java.net.Socket;
import java.util.Random;

public class ServerThread implements Runnable {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    private int vremeDoPorudzbine;

    public ServerThread(Socket socket) {
        this.socket = socket;
        this.vremeDoPorudzbine = new Random().nextInt(40) + 10;

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
        while(true){
            try {
                String request = in.readLine();
                request = request.replace('\t', '\n');
                System.out.println(request);

                out.println("Vreme do isporučenja Vaše porudžbine: " + vremeDoPorudzbine + " minuta.");

                new BrojacThread(vremeDoPorudzbine, request).run();

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
