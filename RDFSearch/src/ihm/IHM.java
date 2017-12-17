package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.sparqlUtil;

public class IHM extends JFrame implements ActionListener{

  private JPanel container = new JPanel();
  private JTextField jtf;   
  private JLabel label = new JLabel("Vous souhaitez les informations de :");
  private JButton b = new JButton ("OK");

  public IHM(){      
    this.setTitle("DBpedia");
    this.setSize(300, 150);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);

    container.setBackground(Color.white);
    container.setLayout(new BorderLayout());

    jtf = new JTextField();
    JPanel top = new JPanel();      

    Font police = new Font("Arial", Font.BOLD, 14);
    jtf.setFont(police);
    jtf.setPreferredSize(new Dimension(150, 30));
    jtf.setForeground(Color.BLUE);
    //On ajoute l'écouteur à notre composant
    //jtf.addKeyListener(new ClavierListener());
    b.addActionListener(this);

    top.add(label);
    top.add(jtf);
    top.add(b);

    this.setContentPane(top);
    this.setVisible(true);         
  }      
  
  public void actionPerformed(ActionEvent arg0) {
	    //Lorsque l'on clique sur le bouton, on met à jour le JLabel
	    String input = jtf.getText();
	    if(input.length() != 0) {
	    		sparqlUtil.creerFiche(input);
	    		System.out.println("Terminated");
	    }
	    else {
	    	System.out.println("Requete Vide");
	    }
	  } 

  /*class ClavierListener implements KeyListener{

    public void keyPressed(KeyEvent event) {
    	
      System.out.println("Code touche pressée : " + event.getKeyCode() + " - caractère touche pressée : " + event.getKeyChar());
      pause();
    }

    public void keyReleased(KeyEvent event) {
      System.out.println("Code touche relâchée : " + event.getKeyCode() + " - caractère touche relâchée : " + event.getKeyChar());         
      pause();                  
    }

    public void keyTyped(KeyEvent event) {
      System.out.println("Code touche tapée : " + event.getKeyCode() + " - caractère touche tapée : " + event.getKeyChar());
      pause();
    }       
  }*/   

 /* private void pause(){
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  } */  

  public static void main(String[] args){
    new IHM();
  }
}