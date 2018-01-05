import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server extends JFrame implements Runnable{
    private Container container;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    private JTextArea porudzbinaTextArea;
    private JLabel preostaloVremeLabel;

    private int vremeDoPorudzbine;
    private Random random;

    public Server(){
        setTitle("Mokranjatzz 365 Pizza - Serverska aplikacija");
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5,2));
        container = getContentPane();

        random = new Random();
        setVisible(true);
        konekcija();

        run();
    }

    private void konekcija() {
        try {
            ServerSocket serverSocket = new ServerSocket(9000);
            System.out.println("Server pokrenut...");

            Socket socket = serverSocket.accept();
            this.socket = socket;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter
                    (socket.getOutputStream())), true);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String request = in.readLine();
            System.out.println(request);
            vremeDoPorudzbine = random.nextInt(40) + 10;
            int vremeDoPorudzbineUSek = vremeDoPorudzbine * 60;
            out.println("Vreme do isporučenja Vaše porudžbine: " + vremeDoPorudzbine);

            while (vremeDoPorudzbineUSek > 0){
                vremeDoPorudzbineUSek--;
                Thread.sleep(1000);
                azurirajFrame(request, String.valueOf(vremeDoPorudzbineUSek / 60));
            }

            in.close();
            out.close();
            socket.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void azurirajFrame(String porudzbina, String preostaloMinuta) {
        porudzbinaTextArea = new JTextArea(porudzbina);
        preostaloVremeLabel = new JLabel(preostaloMinuta);

        container.add(porudzbinaTextArea);
        container.add(preostaloVremeLabel);
    }

    public static void main(String[] args) {
        Server server = new Server();
    }
}