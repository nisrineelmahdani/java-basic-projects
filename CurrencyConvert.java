import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

abstract class Montant {
    private double nombreAconvertit;

    public Montant(double nombreAconvertit) {
        this.nombreAconvertit = nombreAconvertit;
    }

    public double getnombreAconvertit() {
        return nombreAconvertit;
    }

    public void setnombreAconvertit(double nombreAconvertit) {
        this.nombreAconvertit = nombreAconvertit;
    }

    public abstract double ConvertoEUR();
    public abstract double ConvertoUSD();
    public abstract double ConvertoDH();
}

class MontantUSD extends Montant {
    public MontantUSD(double nombreAconvertit) {
        super(nombreAconvertit);
    }

    public double ConvertoEUR() {
        return this.getnombreAconvertit() * 0.915;
    }

    public double ConvertoDH() {
        return this.getnombreAconvertit() * 9.8;
    }

    @Override
    public double ConvertoUSD() {
        return this.getnombreAconvertit();
    }
}

class MontantEUR extends Montant {
    public MontantEUR(double nombreAconvertit) {
        super(nombreAconvertit);
    }

    public double ConvertoUSD() {
        return this.getnombreAconvertit() * 1.093;
    }

    public double ConvertoDH() {
        return this.getnombreAconvertit() * 10.7;
    }

    @Override
    public double ConvertoEUR() {
        return this.getnombreAconvertit();
    }
}

class MontantDH extends Montant {
    public MontantDH(double montantDH) {
        super(montantDH);
    }

    public double ConvertoUSD() {
        return this.getnombreAconvertit() * 0.27;
    }

    public double ConvertoEUR() {
        return this.getnombreAconvertit() * 0.092;
    }

    @Override
    public double ConvertoDH() {
        return this.getnombreAconvertit();
    }
}

public class CurrencyConvert extends JFrame implements ActionListener {
    JLabel nombreAconvertit = new JLabel("Montant à convertir:");
    JTextField jT1 = new JTextField(12);
    JButton converto = new JButton("Convert");
    DefaultListModel<String> model = new DefaultListModel<>();
    JList<String> liste1 = new JList<>(model);
    String[] currencies = { "USD", "EUR", "DH" };
    JComboBox<String> currencySelection = new JComboBox<>(currencies);

    public CurrencyConvert() {
        // Window settings
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Convertisseur de Monnaie");
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        // Styling components
        nombreAconvertit.setFont(new Font("Arial", Font.BOLD, 14));
        nombreAconvertit.setForeground(new Color(30, 144, 255));
        jT1.setFont(new Font("Arial", Font.PLAIN, 14));
        converto.setFont(new Font("Arial", Font.BOLD, 14));
        converto.setBackground(new Color(60, 179, 113));
        converto.setForeground(Color.WHITE);
        liste1.setFont(new Font("Arial", Font.PLAIN, 14));

        // Panel 1 (Input and Button)
        JPanel jp1 = new JPanel(new FlowLayout());
        jp1.add(nombreAconvertit);
        jp1.add(jT1);
        jp1.add(currencySelection);
        jp1.add(converto);

        // Panel 2 (Display Results)
        JPanel jp2 = new JPanel(new BorderLayout());
        liste1.setBackground(new Color(245, 245, 245)); // Light gray background for list
        jp2.add(new JScrollPane(liste1), BorderLayout.CENTER);

        // Add panels to frame
        this.add(jp1, BorderLayout.NORTH);
        this.add(jp2, BorderLayout.CENTER);

        // Action listener for button
        converto.addActionListener(this);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == converto) {
            String input = jT1.getText();
            String selectedCurrency = (String) currencySelection.getSelectedItem();
            try {
                double amount = Double.parseDouble(input);
                Montant montant = null;

                // Choose the currency class based on selection
                switch (selectedCurrency) {
                    case "USD":
                        montant = new MontantUSD(amount);
                        break;
                    case "EUR":
                        montant = new MontantEUR(amount);
                        break;
                    case "DH":
                        montant = new MontantDH(amount);
                        break;
                }

                // Display the conversion results
                String result = "Montant converti:\n";
                result += String.format("Vers USD: %.2f\n", montant.ConvertoUSD());
                result += String.format("Vers DH: %.2f\n", montant.ConvertoDH());
                result += String.format("Vers EUR: %.2f\n", montant.ConvertoEUR());
                model.addElement(result);  // Add result to list

                jT1.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new CurrencyConvert();
    }
}
