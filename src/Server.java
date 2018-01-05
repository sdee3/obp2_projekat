import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;

public class Server extends JFrame implements Runnable{
    private static Container container;

    private static JTextArea porudzbinaTextArea;
    private static JLabel preostaloVremeLabel;
    private static JPanel[] jPaneli;

    private static int vremeDoPorudzbine, brojacPanela;

    public Server(){
        setTitle("Mokranjatzz 365 Pizza - Serverska aplikacija");
        setSize(600,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5,2));
        container = getContentPane();

        jPaneli = new JPanel[5];
        brojacPanela = 0;

        generisiFrejmove();
        setVisible(true);

        run();
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(9000);
            System.out.println("Server pokrenut...");
            while (true)
                new ServerThread(serverSocket.accept()).run();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void azurirajFrejm(String porudzbina, String preostaloMinuta) {

        JPanel tmpPanel = new JPanel();
        tmpPanel.setLayout(new GridLayout(1,2));

        porudzbinaTextArea = new JTextArea(porudzbina);
        preostaloVremeLabel = new JLabel(preostaloMinuta);
        preostaloVremeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        preostaloVremeLabel.setFont(new Font("Arial", Font.BOLD, 36));
        preostaloVremeLabel.setForeground(Color.GREEN);

        tmpPanel.add(porudzbinaTextArea);
        tmpPanel.add(preostaloVremeLabel);

        tmpPanel.setName(porudzbina);

        if(brojacPanela > 0 && tmpPanel.getName().equals(jPaneli[brojacPanela - 1].getName())) {
            container.remove(jPaneli[brojacPanela - 1]);
            jPaneli[brojacPanela - 1] = tmpPanel;
            container.add(jPaneli[brojacPanela - 1]);

            container.revalidate();
        }
        else{
            jPaneli[brojacPanela].setName(porudzbina);
            jPaneli[brojacPanela] = tmpPanel;
            container.add(jPaneli[brojacPanela++]);

            container.revalidate();
        }

    }

    private static void generisiFrejmove() {
        for(int i=0;i<5;i++){
            jPaneli[i] = new JPanel();
            jPaneli[i].setLayout(new GridLayout(1,2));
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
    }


}