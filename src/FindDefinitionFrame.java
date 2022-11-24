import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class FindDefinitionFrame extends JFrame implements ActionListener{
    private SlangWordList list;
    private JButton backButton;
    private JButton findButton;
    private JTable table;
    private JTextField textField;
    private String[][] results;
    private DefaultTableModel model;

    public FindDefinitionFrame() {
        list = new SlangWordList();
        Container container = this.getContentPane();
        container.setBackground(new Color(81,80,106));

        //Title
        JLabel header = new JLabel();
        header.setText("Find Slang By Definition");
        header.setForeground(Color.ORANGE);
        header.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
        header.setAlignmentX(CENTER_ALIGNMENT);

        // Form
        JPanel form = new JPanel();
        JLabel formLabel = new JLabel("Input Definition");
        textField = new JTextField();
        findButton = new JButton("Find");
        findButton.addActionListener(this);
        findButton.setMnemonic(KeyEvent.VK_ENTER);
        findButton.setFocusable(false);
        findButton.setAlignmentX(CENTER_ALIGNMENT);
        findButton.setForeground(Color.RED);
        findButton.setFont(new Font(Font.SERIF, Font.BOLD, 15));
        findButton.setBackground(Color.WHITE);

        form.setLayout(new BorderLayout(10, 10));
        form.add(formLabel, BorderLayout.LINE_START);
        form.add(textField, BorderLayout.CENTER);
        form.add(findButton, BorderLayout.LINE_END);
        Dimension size = new Dimension(700, 30);
        form.setMaximumSize(size);
        form.setPreferredSize(size);
        form.setMinimumSize(size);

        // Table
        JPanel panelTable = new JPanel();
        panelTable.setBackground(Color.black);
        String column[] = { "NUMBER", "SLANG", "MEANING" };

        table = new JTable(new DefaultTableModel(column, 0));
        table.setRowHeight(50);
        model = (DefaultTableModel) table.getModel();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getModel().addTableModelListener(table);

        // Scroll
        JScrollPane sp = new JScrollPane(table);
        panelTable.setLayout(new GridLayout(1, 1));
        panelTable.add(sp);

        // Button Back
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(81,80,106));
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        backButton.setFocusable(false);
        backButton.setAlignmentX(CENTER_ALIGNMENT);
        Font font = new Font(Font.SERIF, Font.BOLD, 15);
        backButton.setForeground(Color.RED);
        backButton.setFont(font);
        backButton.setBackground(Color.WHITE);
        bottomPanel.add(backButton);

        // Add elements to container
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(header);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(form);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(panelTable);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(backButton);

        // Setting JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Slang Words Application");
        this.setVisible(true);
        this.setSize(700, 700);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == findButton) {
            this.clearTable();
            String key = textField.getText();
            if(key.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please input definition you want to find its slang", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            results = list.searchByDefinition(key);
            if(results == null) {
                JOptionPane.showMessageDialog(this, "The input definition does not exists!", "Message", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            for(int i = 0; i < results.length; i++) {
                String s[] = results[i];
                model.addRow(s);
            }

        }
        if(e.getSource() == backButton) {
            this.dispose();
            new SlangApplication();
        }
    }

    void clearTable() {
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }
}
