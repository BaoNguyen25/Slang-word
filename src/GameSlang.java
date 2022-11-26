import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GameSlang extends JFrame implements ActionListener {

    private JButton A,B,C,D,backButton;
    private SlangWordList list;
    private String[] res;
    private String[] random1, random2, random3;

    public GameSlang() {
        list = new SlangWordList();
        Container container = this.getContentPane();
        container.setBackground(new Color(81,80,106));

        // Title
        JLabel header = new JLabel();
        header.setText("Choose the correct definition of this slang");
        header.setForeground(Color.ORANGE);
        header.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
        header.setAlignmentX(CENTER_ALIGNMENT);

        // Word
        JLabel word = new JLabel();
        word.setForeground(Color.RED);
        word.setFont(new Font("Gill Sans MT", Font.BOLD, 30));
        word.setAlignmentX(CENTER_ALIGNMENT);
        res = list.randomSlang();
        word.setText(res[0]);

        // Choices
        JPanel choices = new JPanel();
        A = new JButton("");
        B = new JButton("");
        C = new JButton("");
        D = new JButton("");
        A.addActionListener(this);
        B.addActionListener(this);
        C.addActionListener(this);
        D.addActionListener(this);
        A.setBackground(Color.WHITE);
        B.setBackground(Color.WHITE);
        C.setBackground(Color.WHITE);
        D.setBackground(Color.WHITE);
        random1 = list.randomSlang();
        random2 = list.randomSlang();
        random3 = list.randomSlang();
        ArrayList<String> defs = new ArrayList<>(Arrays.asList(res[1], random1[1], random2[1], random3[1]));
        Collections.shuffle(defs);
        A.setText(defs.get(0));
        B.setText(defs.get(1));
        C.setText(defs.get(2));
        D.setText(defs.get(3));
        choices.add(A);
        choices.add(B);
        choices.add(C);
        choices.add(D);
        choices.setLayout(new GridLayout(2,2));
        choices.setPreferredSize(new Dimension(700,200));

        // Button Back
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(81,80,106));

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        backButton.setFocusable(false);
        backButton.setAlignmentX(CENTER_ALIGNMENT);
        backButton.setForeground(Color.RED);
        Font font = new Font(Font.SERIF, Font.BOLD, 15);
        backButton.setFont(font);
        backButton.setBackground(Color.WHITE);
        bottomPanel.add(backButton);

        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(header);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(word);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(choices);
        container.add(Box.createRigidArea(new Dimension(0, 40)));
        container.add(bottomPanel);

        // Setting JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Slang Words Application");
        this.setVisible(true);
        this.setSize(700, 500);
        this.setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton) {
            new SlangApplication();
            this.dispose();
        }

        if(e.getSource() == A) {
            if(A.getText() == res[1]) {
                JOptionPane.showMessageDialog(this, "Correct!");
                new SlangApplication();
                this.dispose();
            }else {
                JOptionPane.showMessageDialog(this, "Wrong! Choose again.");
            }
        }

        if(e.getSource() == B) {
            if(B.getText() == res[1]) {
                JOptionPane.showMessageDialog(this, "Correct!");
                new SlangApplication();
                this.dispose();
            }else {
                JOptionPane.showMessageDialog(this, "Wrong! Choose again.");
            }
        }

        if(e.getSource() == C) {
            if(C.getText() == res[1]) {
                JOptionPane.showMessageDialog(this, "Correct!");
                new SlangApplication();
                this.dispose();
            }else {
                JOptionPane.showMessageDialog(this, "Wrong! Choose again.");
            }
        }

        if(e.getSource() == D) {
            if(D.getText() == res[1]) {
                JOptionPane.showMessageDialog(this, "Correct!");
                new SlangApplication();
                this.dispose();
            }else {
                JOptionPane.showMessageDialog(this, "Wrong! Choose again.");
            }
        }
    }
}
