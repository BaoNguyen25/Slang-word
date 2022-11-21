import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class FindWordFrame extends JFrame implements ActionListener {


    public FindWordFrame(){

        Container container = this.getContentPane();
        container.setBackground(new Color(81,80,106));


        //Setting JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Slang Words Application");
        this.setVisible(true);
        this.setSize(700, 700);
        this.setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
