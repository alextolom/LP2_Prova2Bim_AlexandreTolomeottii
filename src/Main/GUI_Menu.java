/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Alexandre
 */
public class GUI_Menu extends JFrame{
    
    private Container cp;
    
    private JPanel pnNorte = new JPanel();
    private JPanel pnSul = new JPanel();
    
    private JLabel lbIcone = new JLabel();
    
    private JButton btGUICarros = new JButton();
    private JButton btGUIMotos = new JButton();
    private JButton btGUIDiversos = new JButton();
    private JButton btContato = new JButton();
    
    private JLabel lbCarros = new JLabel("<html><FONT Color=WHITE>Sessão de carros</FONT></html>");
    private JLabel lbMotos = new JLabel("<html><FONT Color=WHITE>Sessão de motos</FONT></html>");
    private JLabel lbDiversos = new JLabel("<html><FONT Color=WHITE>Sessão de diversos</FONT></html>");
    private JLabel lbContato = new JLabel("<html><FONT Color=WHITE>Página de contato</FONT></html>");
    
    
    
    
    
    private String getDefaultToolTip2(String message){
   return "<html><p style='background:#555;color:yellow'>" + message + "</p></html>"; 
}
    
    public GUI_Menu(){
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        
        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnSul, BorderLayout.CENTER);
        pnSul.setLayout(new GridLayout(7,5));
        
        //1
        pnSul.add(new JLabel(""));
        pnSul.add(new JLabel(""));
        pnSul.add(new JLabel(""));
        pnSul.add(new JLabel(""));
        pnSul.add(new JLabel(""));
        
        //2
        pnSul.add(new JLabel(""));
        pnSul.add(lbCarros);
        pnSul.add(new JLabel(""));
        pnSul.add(lbMotos);
        pnSul.add(new JLabel(""));
        
        //3
        pnSul.add(new JLabel(""));
        pnSul.add(btGUICarros);
        pnSul.add(new JLabel(""));
        pnSul.add(btGUIMotos);
        pnSul.add(new JLabel(""));
        
        //4
        pnSul.add(new JLabel(""));
        pnSul.add(new JLabel(""));
        pnSul.add(new JLabel(""));
        pnSul.add(new JLabel(""));
        pnSul.add(new JLabel(""));
        
        //5
        pnSul.add(new JLabel(""));
        pnSul.add(lbDiversos);
        pnSul.add(new JLabel(""));
        pnSul.add(lbContato);
        pnSul.add(new JLabel(""));
        
        //6
        pnSul.add(new JLabel(""));
        pnSul.add(btGUIDiversos);
        pnSul.add(new JLabel(""));
        pnSul.add(btContato);
        pnSul.add(new JLabel(""));
        
        //7
        pnSul.add(new JLabel(""));
        pnSul.add(new JLabel(""));
        pnSul.add(new JLabel(""));
        pnSul.add(new JLabel(""));
        pnSul.add(new JLabel(""));
        
        pnNorte.add(lbIcone);
        lbIcone.setIcon(new ImageIcon("Images/background.png"));
        lbIcone.setToolTipText(getDefaultToolTip2("Rent a car!"));
        
        
        btGUICarros.setBackground(Color.WHITE);
        btGUIMotos.setBackground(Color.WHITE);
        btGUIDiversos.setBackground(Color.WHITE);
        btContato.setBackground(Color.WHITE);
        pnSul.setBackground(Color.BLACK);
        
        btGUICarros.setToolTipText(getDefaultToolTip2("Um botão que leva até a sessão de carros"));
        btGUIMotos.setToolTipText(getDefaultToolTip2("Um botão que leva até a sessão de motos"));
        btGUIDiversos.setToolTipText(getDefaultToolTip2("Um botão que leva até a sessão de diversos veículos"));
        btContato.setToolTipText(getDefaultToolTip2("Um botão que leva até uma página de contato do desenvolvedor"));
        
        btGUICarros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GUI_Carros gui = new GUI_Carros();
                dispose();
            }
        });
        
        btGUIMotos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GUI_Motos gui = new GUI_Motos();
                dispose();
            }
        });
        
        btGUIDiversos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GUI_Diversos gui = new GUI_Diversos();
                dispose();
            }
        });
        
        btContato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String url="https://docs.google.com/forms/d/e/1FAIpQLSfCB6ZEuuUZkl4u3gZtc78KEvqycF4ThwmWjS6Prd02C2zHQw/viewform?c=0&w=1";
                try {
                            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
                        } catch (IOException ex) {
                            Logger.getLogger(GUI_Carros.class.getName()).log(Level.SEVERE, null, ex);
                        }
            }
        });
        
        
        
        
        setSize(700,600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Locadora de automóveis");
        
    }
                                
}
