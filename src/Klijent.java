import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Klijent extends JFrame implements ActionListener{

    private Container container;
    private JPanel picaPanel, detaljiPanel;
    private JComboBox<String> velicinaPice, vrstaPice, nacinPlacanja;
    private JTextField imePrezime, adresa, brojTelefona, napomena;
    private JButton naruciButton;
    private JMenuBar jMenuBar;
    private JMenu meniProgram;

    private boolean flagGreska;
    private Socket s;
    private BufferedReader in;
    private PrintWriter out;

    private Porudzbina porudzbina;

    public Klijent(){
        container = getContentPane();
        setSize(500, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Mokranjatzz Pizza");
        setLayout(new GridLayout(5, 2));

        porudzbina = new Porudzbina();
        flagGreska = true;

        generisiKomponente();
        dodajKomponente();

        konekcijaSaServerom();

        setVisible(true);
    }

    private void konekcijaSaServerom() {
        try {
            s = new Socket(InetAddress.getByName("127.0.0.1"), 9000);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())), true);
        } catch (IOException e1) {
            JOptionPane.showMessageDialog(this, "Greška pri konekciji!");
        }
    }

    private void dodajKomponente() {
        picaPanel.add(new JLabel("Veličina pice: "));
        picaPanel.add(velicinaPice);
        picaPanel.add(new JLabel("Vrsta pice: "));
        picaPanel.add(vrstaPice);
        picaPanel.add(new JLabel("Način plaćanja: "));
        picaPanel.add(nacinPlacanja);

        container.add(picaPanel);

        container.add(new JLabel("Podaci o Vama:"));

        detaljiPanel.add(new JLabel("Ime i prezime: "));
        detaljiPanel.add(imePrezime);
        detaljiPanel.add(new JLabel("Vaša adresa: "));
        detaljiPanel.add(adresa);
        detaljiPanel.add(new JLabel("Broj telefona: "));
        detaljiPanel.add(brojTelefona);
        detaljiPanel.add(new JLabel("Napomena: "));
        detaljiPanel.add(napomena);

        container.add(detaljiPanel);
        container.add(new JLabel(""));
        container.add(naruciButton);

        meniProgram.add("Kečap").addActionListener(this);
        meniProgram.add("Majonez").addActionListener(this);
        meniProgram.add("Masline").addActionListener(this);
        meniProgram.add("Origano").addActionListener(this);
        meniProgram.add("Jaja").addActionListener(this);
        jMenuBar.add(meniProgram);
        setJMenuBar(jMenuBar);
    }

    private void generisiKomponente() {
        picaPanel = new JPanel(new GridLayout(3,2));
        detaljiPanel = new JPanel(new GridLayout(4,2));

        jMenuBar = new JMenuBar();
        meniProgram = new JMenu("Dodaci");

        generisiComboBoxove();

        imePrezime = new JTextField();
        adresa = new JTextField();
        brojTelefona = new JTextField();
        napomena = new JTextField();

        naruciButton = new JButton("Naruči");
        naruciButton.addActionListener(this);
    }

    private void generisiComboBoxove() {
        velicinaPice = new JComboBox<>();
        velicinaPice.addItem("");
        velicinaPice.addItem("25''");
        velicinaPice.addItem("32''");
        velicinaPice.addItem("50''");

        vrstaPice = new JComboBox<>();
        vrstaPice.addItem("");
        vrstaPice.addItem("Capricciosa");
        vrstaPice.addItem("Funghi");
        vrstaPice.addItem("Greek Pizza");
        vrstaPice.addItem("Margarita");
        vrstaPice.addItem("Quatro Stagione");
        vrstaPice.addItem("Vegeteriana");

        nacinPlacanja = new JComboBox<>();
        nacinPlacanja.addItem("");
        nacinPlacanja.addItem("Pouzećem");
        nacinPlacanja.addItem("Putem pošte");
        nacinPlacanja.addItem("Kreditna kartica");

        velicinaPice.addActionListener(this);
        vrstaPice.addActionListener(this);
        nacinPlacanja.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Prva tri ComboBox-a
        if(e.getSource().equals(velicinaPice))
            if(velicinaPice.getSelectedIndex() != 0)
                porudzbina.setVelicinaPice(velicinaPice.getSelectedItem().toString());
        if(e.getSource().equals(vrstaPice))
            if(vrstaPice.getSelectedIndex() != 0)
                porudzbina.setVrstaPice(vrstaPice.getSelectedItem().toString());
        if(e.getSource().equals(nacinPlacanja))
            if(nacinPlacanja.getSelectedIndex() != 0)
                porudzbina.setNacinPlacanja(nacinPlacanja.getSelectedItem().toString());
        // Dugme naruči
        if(e.getActionCommand().equals("Naruči")){
            if(velicinaPice.getSelectedIndex() == 0 || vrstaPice.getSelectedIndex() == 0
                    || nacinPlacanja.getSelectedIndex()  == 0){
                prikaziError("Sva tri polja iznad moraju biti izabrana!");
            }
            if(imePrezime.getText().trim().length() < 5){
                prikaziError("Ime i prezime mora biti validno!");
                flagGreska = true;
            }
            else if(adresa.getText().trim().length() < 8){
                prikaziError("Adresa koju unosite mora biti validna!");
                flagGreska = true;
            }

            else if(brojTelefona.getText().trim().length() < 9) {
                prikaziError("Broj telefona koji unosite mora biti validan!");
                flagGreska = true;
            }
            else if(imePrezime.getText().trim().length() >= 5 && brojTelefona.getText().trim().length() >= 9
                    && adresa.getText().trim().length() >= 8){
                flagGreska = false;

                porudzbina.setAdresa(adresa.getText().trim());
                porudzbina.setImePrezime(imePrezime.getText().trim());
                porudzbina.setBrojTelefona(brojTelefona.getText().trim());
                porudzbina.setNapomena(napomena.getText().trim());

                prikaziPotvrdu();
                posaljiPorudzbinuServeru();

            }
        }

        if(e.getActionCommand().equals("Kečap"))
            porudzbina.addDodatak("Kečap");
        if(e.getActionCommand().equals("Majonez"))
            porudzbina.addDodatak("Majonez");
        if(e.getActionCommand().equals("Masline"))
            porudzbina.addDodatak("Masline");
        if(e.getActionCommand().equals("Origano"))
            porudzbina.addDodatak("Origano");
        if(e.getActionCommand().equals("Jaja"))
            porudzbina.addDodatak("Jaja");
    }

    private void posaljiPorudzbinuServeru() {
        out.println(porudzbina);
        try {
            String odgovor = in.readLine();
            JOptionPane.showMessageDialog(this, odgovor);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void prikaziError(String poruka) {
        JLabel label = new JLabel(poruka);
        label.setForeground(Color.RED);
        JOptionPane.showMessageDialog(this, label);
    }

    private void prikaziPotvrdu(){
        JLabel label = new JLabel("Vaša porudžbina je uspešno prosledjena!");
        label.setForeground(Color.BLUE);
        JOptionPane.showMessageDialog(this, label);
    }

    public static void main(String[] args) {
        Klijent klijent = new Klijent();
    }
}
