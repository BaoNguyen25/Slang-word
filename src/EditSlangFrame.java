import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class EditSlangFrame extends JFrame implements ActionListener {

    private SlangWordList list;
    private JButton backButton;
    private JButton findButton;
    private JTable table;
    private String[][] results;
    private DefaultTableModel model;
    private JTextField textField;
    private JButton saveButton;

    public EditSlangFrame() {
        list = new SlangWordList();
        Container container = this.getContentPane();
        container.setBackground(new Color(81,80,106));

        //Title
        JLabel header = new JLabel();
        header.setText("Choose slang word you want to edit");
        header.setForeground(Color.ORANGE);
        header.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
        header.setAlignmentX(CENTER_ALIGNMENT);

        // Form
        JPanel form = new JPanel();
        JLabel formLabel = new JLabel("Input Slang Word");
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
        String column[] = {"NUMBER", "SLANG", "MEANING"};
        results = list.getList();
        table = new JTable(new DefaultTableModel(column, 0));
        model = (DefaultTableModel) table.getModel();
        table.setRowHeight(50);

        // add history to table;
        for(int i = 0; i < results.length; i++) {
            String s[] = results[i];
            model.addRow(s);
        }

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

        saveButton = new JButton("Save Change");
        saveButton.addActionListener(this);
        saveButton.setFocusable(false);
        saveButton.setAlignmentX(CENTER_ALIGNMENT);
        saveButton.setForeground(Color.RED);
        saveButton.setFont(font);
        saveButton.setBackground(Color.WHITE);

        bottomPanel.add(saveButton);
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
        container.add(bottomPanel);

        // Setting JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Slang Words Application");
        this.setVisible(true);
        this.setSize(700, 700);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton) {
            this.dispose();
            new SlangApplication();
        }
        if(e.getSource() == findButton) {
            this.clearTable();
            String key = textField.getText();
            if(key.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please input slang word you want to find", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            results = list.searchBySlang(key);
            if(results == null) {
                JOptionPane.showMessageDialog(this, "The input slang word does not exists!", "Message", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            for(int i = 0; i < results.length; i++) {
                String s[] = results[i];
                model.addRow(s);
            }
        }
        if(e.getSource() == saveButton) {
            int row = table.getSelectedRow();
            int col = table.getSelectedColumn();
            if (row == -1 || col == -1)
                return;
            list.editSlang(results[row][1], (String) table.getValueAt(row,2), (String) table.getValueAt(row,1));
            list.editDefinition((String) table.getValueAt(row, 1), results[row][2], (String) table.getValueAt(row, 2));
            JOptionPane.showMessageDialog(this, "Updated successfully");
        }
    }

    void clearTable() {
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }
}
