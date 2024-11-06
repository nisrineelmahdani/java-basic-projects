import java.util.List;

import javax.swing.*;
import java.awt.*;
// interface simple gui:
import java.awt.event.ActionEvent;
 
public class MaFenetre extends JFrame{
    // les compostants
    JLabel nombreAconvertit = new JLabel ("Montant a convertit:"); // label avec text NOM
    JTextField jT1= new JTextField(12);// text field where user can enter text
    JButton converto = new JButton("Convert");
    JList<String> liste1 = new JList<>();

    
    public MaFenetre(){
        // this est la fentere
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());// mise en page de la fenetre
        JPanel jp1= new JPanel();// un panneau
        jp1.setLayout(new FlowLayout());// utilise flowlayout pour organiser ses composants ( button label) de gauche a droite
        jp1.add(nombreAconvertit);
        jp1.add(jT1);
        JPanel jp2 = new JPanel(); // panneau2
        jp2.setLayout(new GridLayout(1,2)); // panneau va utiliser Gridlayout
        jp2.add(liste1);// gridlayout  ajoute les listes au panneau2 
        // dimension dun fentre
        this.setBounds(10,10,500,500);
        // dimension= fentre
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setVisible(true);
        }
           public Void actionPerformed(ActionEvent e){
     if(e.getSource()==nombreAconvertit){// recuperer le bouuton 1 de add{
        String s = jT1.getText();
        liste1.add(s);
        
       jT1.setText("");
     }
    

    }
     
        public static void main(String[] args){
            new MaFenetre();
        }
}