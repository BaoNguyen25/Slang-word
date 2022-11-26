import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddSlangFrame extends JFrame implements ActionListener {
    private SlangWordList list;
    private JButton backButton;
    private JButton addButton;
    private JTextField slang;
    private JTextField meaning;

    public AddSlangFrame() {
        list = new SlangWordList();
        Container container = this.getContentPane();
        container.setBackground(new Color(81,80,106));

        //Title
        JLabel header = new JLabel();
        header.setText("Add Slang Word");
        header.setForeground(Color.ORANGE);
        header.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
        header.setAlignmentX(CENTER_ALIGNMENT);

        JPanel form = new JPanel();
        form.setPreferredSize(new Dimension(120,150));
        form.setLayout(new BoxLayout(form, BoxLayout.X_AXIS));

        JPanel textPanel = new JPanel();
        textPanel.setPreferredSize(new Dimension(-120, 80));
        textPanel.setLayout(null);
        JLabel slangLabel = new JLabel("Slang:");
        slangLabel.setBounds(80,40,50,50);
        JLabel meaningLabel = new JLabel("Meaning:");
        meaningLabel.setBounds(60,80,100,50);
        slangLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
        meaningLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
        slangLabel.setPreferredSize(new Dimension(70, 30));
        meaningLabel.setPreferredSize(new Dimension(70, 30));
        textPanel.add(slangLabel);
        textPanel.add(meaningLabel);

        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setPreferredSize(new Dimension(100, 70));
        slang = new JTextField("");
        textFieldPanel.setLayout(null);
        slang.setBounds(0,53,300,30);
        slang.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
        slang.setPreferredSize(new Dimension(100, 30));
        meaning = new JTextField("");
        meaning.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
        meaning.setBounds(0,93,300,30);
        meaning.setPreferredSize(new Dimension(100, 30));
        textFieldPanel.add(slang);
        textFieldPanel.add(meaning);

        form.add(textPanel);
        form.add(textFieldPanel);

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
        addButton = new JButton("Add");
        addButton.addActionListener(this);
        addButton.setFocusable(false);
        addButton.setAlignmentX(CENTER_ALIGNMENT);
        addButton.setForeground(Color.BLUE);
        addButton.setFont(font);
        addButton.setBackground(Color.WHITE);
        bottomPanel.add(addButton);
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
        this.setSize(500, 350);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton) {
            new SlangApplication();
            this.dispose();
        }
        if(e.getSource() == addButton) {
            String word = slang.getText();
            String mean = meaning.getText();
            if (word.isEmpty() || mean.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Slag and Meaning maybe empty", "Error", JOptionPane.ERROR_MESSAGE);
            }

            if(list.checkSlangExists(word)) {
                Object[] options = { "Overwrite", "Duplicate" };
                int n = JOptionPane.showOptionDialog(this,
                        "Slang '" +  word + "' have already existed on Slang Word List", "Message",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
                if (n == 0) {
                    // Overwrite
                    new OverwriteSlangFrame(word, mean);
                    this.dispose();
                } else if (n == 1) {
                    // Duplicate
                    list.addSlang(word, mean);
                    JOptionPane.showMessageDialog(this, "Duplicated Successfully!");
                }
            }
            else {
                list.addSlang(word, mean);
                JOptionPane.showMessageDialog(this, "Added Successfully!");
            }
            slang.setText("");
            meaning.setText("");
        }
    }
}
