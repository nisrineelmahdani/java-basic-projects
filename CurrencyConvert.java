import java.util.List;

import javax.swing.*;
import java.awt.*;
// interface simple gui:
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 abstract class montant{
    private double nombreAconvertit;
         public montant(double nombreAconvertit){
            this.nombreAconvertit=nombreAconvertit;
        }
        public double getnombreAconvertit(){
            return nombreAconvertit;
        }
        public void setnombreAconvertit( double nombreAconvertit){
            this.nombreAconvertit= nombreAconvertit;
        }
        public abstract double ConvertoEUR();
        public abstract double ConvertoUSD();
        public abstract double ConvertoDH();
       
}
class montantUSD extends montant implements currencyConverter{
            public montantUSD( double nombreAconvertit){
                super(  nombreAconvertit);
                
             }

           
            public double ConvertoEUR() {
                return this.getnombreAconvertit() * 0.915;
            }
            public double ConvertoDH() {
                return this.getnombreAconvertit() * 9.8;
            }


            @Override
            public double ConvertoUSD() {
                return this.getnombreAconvertit(); // no changes
            }
}

class montantEUR extends montant implements currencyConverter{
     public montantEUR(double nombreAconvertit){
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
class montantDH extends montant implements currencyConverter{
     public montantDH(double montantDH){
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
    JLabel nombreAconvertit = new JLabel("Montant a convertir:");
    JTextField jT1 = new JTextField(12);
    JButton converto = new JButton("Convert");
    JList<String> liste1 = new JList<>();
  // Currency selection dropdown (JComboBox)
  String[] currencies = { "USD", "EUR", "DH" };
  JComboBox<String> currencySelection = new JComboBox<>(currencies);
    public CurrencyConvert() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        JPanel jp1 = new JPanel();
        jp1.setLayout(new FlowLayout());
        jp1.add(nombreAconvertit);
        jp1.add(jT1);
        jp1.add(currencySelection); 
        jp1.add(converto);
      

        JPanel jp2 = new JPanel();
        jp2.setLayout(new GridLayout(1, 2));
        jp2.add(liste1);

        this.add(jp1, BorderLayout.NORTH);
        this.add(jp2, BorderLayout.CENTER);

        converto.addActionListener(this);
        

        this.setBounds(10, 10, 500, 500);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == converto) {
            String input = jT1.getText();
            String selectedCurrency = (String) currencySelection.getSelectedItem();
            try {
                double amount = Double.parseDouble(input);

                // Assuming input is in EUR and converting it
                montant montant = null;
                switch (selectedCurrency) {
                    case "USD":
                        montant = new montantUSD(amount);
                        break;
                    case "EUR":
                        montant = new montantEUR(amount);
                        break;
                    case "DH":
                        montant = new montantDH(amount);
                        break;
                }

                String result = "Converted amount: \n";
                result += "To USD: " + montant.ConvertoUSD() + "\n";
                result += "To DH: " + montant.ConvertoDH() + "\n";
                result += "To EUR: " + montant.ConvertoEUR() + "\n";
                DefaultListModel<String> model = new DefaultListModel<>();
                model.addElement(result);
                liste1.setModel(model);

                jT1.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new CurrencyConvert();
    }
}


     
























/* 
public class CurrencyConvert {

    public static void main(String[] args) {
        montantDH montantDH = new montantDH(100);
        montantEUR montantEUR = new montantEUR(100);
        montantUSD montantUSD = new montantUSD(100);
        System.out.println("dh to euro:"+montantDH.ConvertoEUR()); 
        System.out.println("dh to usd"+montantDH.ConvertoUSD());
        System.out.println("dh to dh "+ montantDH.ConvertoDH());

        System.out.println("EUR to euro:"+montantEUR.ConvertoEUR()); 
        System.out.println("EUR to usd"+montantEUR.ConvertoUSD());
        System.out.println("EUR to DH "+ montantEUR.ConvertoDH());

        System.out.println("USD to USD:"+montantUSD.ConvertoUSD()); 
        System.out.println("USD to EUR"+montantUSD.ConvertoEUR());
        System.out.println("USD to DH "+ String.format("%.2f", montantUSD.ConvertoDH()));
    }
    
}*/


