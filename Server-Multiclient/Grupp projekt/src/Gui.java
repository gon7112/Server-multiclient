import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Jag har ännu inte kopplat GUI till Client eller Server (working on it...)

public class Gui extends JPanel{
    private JPanel jpn = new JPanel();
    private JButton btnON = new JButton("ON");
    private JButton btnOFF = new JButton("OFF");
    public Gui() {
        setPreferredSize(new Dimension(300,120));
        add(jpn);
        jpn.add(btnON);
        jpn.add(btnOFF);
        btnON.setPreferredSize(new Dimension(100, 50));
        btnOFF.setPreferredSize(new Dimension(100, 50));
        btnON.addActionListener(new ON());
        btnOFF.addActionListener(new OFF());
    }
    static class ON implements ActionListener{
    public void actionPerformed(ActionEvent e) {
        System.out.println(1);
    }}
    static class OFF implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            System.out.println(0);
        }}
    public static void main(String[]args){
        Gui gui = new Gui();
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(300,120));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        frame.getContentPane().add(gui);
    }
}
