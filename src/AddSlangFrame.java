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
    private String[] res;

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
        JPanel slangPanel = new JPanel();
        SpringLayout layout = new SpringLayout();
        slangPanel.setLayout(layout);
        JLabel slangLabel = new JLabel("Slang: ");
        slangLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
        slang = new JTextField("");
        slang.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
        slangPanel.add(slangLabel, BorderLayout.LINE_START);
        slangPanel.add(slang, BorderLayout.CENTER);
        layout.putConstraint(SpringLayout.WEST, slangLabel, 6, SpringLayout.WEST, slangPanel);
        layout.putConstraint(SpringLayout.NORTH, slangLabel, 6, SpringLayout.NORTH, slangPanel);
        layout.putConstraint(SpringLayout.WEST, slang, 6, SpringLayout.EAST, slangLabel);
        layout.putConstraint(SpringLayout.NORTH, slang, 6, SpringLayout.NORTH, slangPanel);
        layout.putConstraint(SpringLayout.EAST, slangPanel, 6, SpringLayout.EAST, slang);
        layout.putConstraint(SpringLayout.SOUTH, slangPanel, 6, SpringLayout.SOUTH, slang);

        JPanel meaningPanel = new JPanel();
        SpringLayout layout1 = new SpringLayout();
        meaningPanel.setLayout(layout1);
        JLabel meaningLabel = new JLabel("Meaning: ");
        meaningLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
        meaning = new JTextField("");
        meaning.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
        meaningPanel.add(meaningLabel, BorderLayout.LINE_START);
        meaningPanel.add(meaning, BorderLayout.CENTER);
        layout1.putConstraint(SpringLayout.WEST, meaningLabel, 6, SpringLayout.WEST, meaningPanel);
        layout1.putConstraint(SpringLayout.NORTH, meaningLabel, 6, SpringLayout.NORTH, meaningPanel);
        layout1.putConstraint(SpringLayout.WEST, meaning, 6, SpringLayout.EAST, meaningLabel);
        layout1.putConstraint(SpringLayout.NORTH, meaning, 6, SpringLayout.NORTH, meaningPanel);
        layout1.putConstraint(SpringLayout.EAST, meaningPanel, 6, SpringLayout.EAST, meaning);
        layout1.putConstraint(SpringLayout.SOUTH, meaningPanel, 6, SpringLayout.SOUTH, meaning);

        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        form.add(slangPanel);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
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
        this.setSize(500, 400);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton) {
            new SlangApplication();
            this.dispose();
        }
        if(e.getSource() == addButton) {

        }
    }
}
