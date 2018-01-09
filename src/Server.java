import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;


public class Server extends JFrame implements Runnable{
    private static Container container;

    private static JPanel[] jPaneliTekucePorudzbine, jPaneliGotovePorudzbine;

    private static int indeksTekucePorudzbine, indeksGotovePorudzbine;

    public Server(){
        setTitle("Mokranjatzz 365 Pizza - Serverska aplikacija");
        setSize(750,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(12,1));
        container = getContentPane();

        jPaneliTekucePorudzbine = new JPanel[5];
        jPaneliGotovePorudzbine = new JPanel[5];
        indeksTekucePorudzbine = 0;
        indeksGotovePorudzbine = 0;

        generisiFrejmove();
        setVisible(true);

        run();
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(9000);
            System.out.println("Server pokrenut...");
            while (true) {
                if (indeksTekucePorudzbine > 4)
                    indeksTekucePorudzbine = 0;
                new ServerThread(serverSocket.accept(), this, indeksTekucePorudzbine++).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void azurirajFrejm(String porudzbina, int preostaloVremeUSek, int indeksTekucePorudzbine) throws InterruptedException {
        while (preostaloVremeUSek > 0){

            if(indeksGotovePorudzbine > 4)
                indeksGotovePorudzbine = 0;

            JPanel tmpPanel = new JPanel();
            tmpPanel.setLayout(new GridLayout(1,2));

            JTextArea porudzbinaTextArea = new JTextArea(porudzbina);
            JLabel preostaloVremeLabel = new JLabel(String.valueOf(preostaloVremeUSek / 60));

            porudzbinaTextArea.setFont(new Font("Arial", Font.PLAIN, 12));
            porudzbinaTextArea.setEditable(false);
            preostaloVremeLabel.setHorizontalAlignment(SwingConstants.CENTER);
            preostaloVremeLabel.setFont(new Font("Arial", Font.BOLD, 36));
            if(preostaloVremeUSek < 10)
                preostaloVremeLabel.setForeground(Color.RED);
            else
                preostaloVremeLabel.setForeground(Color.GREEN);

            tmpPanel.add(porudzbinaTextArea);
            tmpPanel.add(preostaloVremeLabel);

            tmpPanel.setName(porudzbina);

            rekreirajFrejmove(preostaloVremeUSek, indeksTekucePorudzbine, tmpPanel);

            preostaloVremeUSek-=5;
            Thread.sleep(20);
        }
    }

    private synchronized void rekreirajFrejmove(int preostaloVremeUSek, int indeksTekucePorudzbine, JPanel tmpPanel) {
        container.removeAll();
        container.revalidate();

        if(preostaloVremeUSek < 10)
            jPaneliGotovePorudzbine[indeksGotovePorudzbine++] = tmpPanel;
        else
            jPaneliTekucePorudzbine[indeksTekucePorudzbine] = tmpPanel;

        container.add(new JLabel("Tekuće porudžbine:"));
        for(int i=0;i<5;i++)
            container.add(jPaneliTekucePorudzbine[i]);

        container.add(new JLabel("Isporučene porudžbine:"));
        for(int i=0;i<5;i++)
            container.add(jPaneliGotovePorudzbine[i]);

        container.revalidate();
    }

    private static void generisiFrejmove() {
        for(int i=0;i<5;i++){
            jPaneliTekucePorudzbine[i] = new JPanel();
            jPaneliGotovePorudzbine[i] = new JPanel();
            jPaneliTekucePorudzbine[i].setLayout(new GridLayout(1,2));
            jPaneliGotovePorudzbine[i].setLayout(new GridLayout(1,2));
        }

        container.add(new JLabel("Tekuće porudžbine:"));
        for(int i=0;i<5;i++)
            container.add(jPaneliTekucePorudzbine[i]);
        container.add(new JLabel("Isporučene porudžbine:"));
        for(int i=0;i<5;i++)
            container.add(jPaneliGotovePorudzbine[i]);

        container.revalidate();
    }

    public static void main(String[] args) { new Server(); }

}