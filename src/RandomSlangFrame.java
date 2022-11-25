import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RandomSlangFrame extends JFrame implements ActionListener {
    private SlangWordList list;
    private JButton backButton;
    private JButton randButton;
    private JLabel slang;
    private JLabel meaning;
    private String[] res;

    public RandomSlangFrame() {
        list = new SlangWordList();
        Container container = this.getContentPane();
        container.setBackground(new Color(81,80,106));

        //Title
        JLabel header = new JLabel();
        header.setText("Random Slang Word");
        header.setForeground(Color.ORANGE);
        header.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
        header.setAlignmentX(CENTER_ALIGNMENT);

        JPanel form = new JPanel();
        JPanel slangPanel = new JPanel();
        slangPanel.setLayout(new BorderLayout(10,10));
        JLabel slangLabel = new JLabel("Slang: ");
        slangLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
        slang = new JLabel();
        slang.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
        slangPanel.add(slangLabel, BorderLayout.LINE_START);
        slangPanel.add(slang, BorderLayout.CENTER);

        JPanel meaningPanel = new JPanel();
        meaningPanel.setLayout(new BorderLayout(10,10));
        JLabel meaningLabel = new JLabel("Meaning: ");
        meaningLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
        meaning = new JLabel();
        meaning.setMaximumSize(new Dimension(20,20));
        meaning.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
        meaningPanel.add(meaningLabel, BorderLayout.LINE_START);
        meaningPanel.add(meaning, BorderLayout.CENTER);

        res = list.randomSlang();
        slang.setText(res[0]);
        meaning.setText(res[1]);

        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.add(slangPanel);
        form.add(meaningPanel);

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
        randButton = new JButton("Random");
        randButton.addActionListener(this);
        randButton.setFocusable(false);
        randButton.setAlignmentX(CENTER_ALIGNMENT);
        randButton.setForeground(Color.RED);
        randButton.setFont(font);
        randButton.setBackground(Color.WHITE);
        bottomPanel.add(randButton);
        bottomPanel.add(backButton);

        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(header);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(form);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(bottomPanel);

        // Setting JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Slang Words Application");
        this.setVisible(true);
        this.setSize(500, 400);
        this.setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton) {
            new SlangApplication();
            this.dispose();
        }
        if(e.getSource() == randButton) {
            res = list.randomSlang();
            slang.setText(res[0]);
            meaning.setText(res[1]);
        }
    }
}
