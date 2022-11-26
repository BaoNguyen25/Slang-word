import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OverwriteSlangFrame extends JFrame implements ActionListener, ListSelectionListener {
    private SlangWordList list;
    private JButton backButton;
    private JTable table;
    private String[][] results;
    private DefaultTableModel model;
    private String definition;
    public OverwriteSlangFrame(String word, String mean) {
        list = new SlangWordList();
        Container container = this.getContentPane();
        container.setBackground(new Color(81,80,106));
        definition = mean;
        //Title
        JLabel header = new JLabel();
        header.setText("Choose slang word you want to overwrite");
        header.setForeground(Color.ORANGE);
        header.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
        header.setAlignmentX(CENTER_ALIGNMENT);

        // Table
        JPanel panelTable = new JPanel();
        panelTable.setBackground(Color.black);
        String column[] = {"NUMBER", "SLANG", "MEANING"};
        results = list.searchBySlang(word);
        table = new JTable(new DefaultTableModel(column, 0));
        model = (DefaultTableModel) table.getModel();
        table.setRowHeight(50);

        // Add results to table;
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
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.addListSelectionListener(this);

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
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int row = table.getSelectedRow();
        int col = table.getSelectedColumn();
        if (row == -1 || col == -1)
            return;
        String slang = (String) table.getValueAt(row, 1);
        int n = JOptionPane.showConfirmDialog(this, "Do you want to overwrite this slang word?", "Message", JOptionPane.YES_NO_OPTION);
        if (n == 0) {
            list.overwriteSlang(slang, (String) table.getValueAt(row,2), definition);
            JOptionPane.showMessageDialog(this, "Overwritten Successfully!");
            new SlangApplication();
            this.dispose();
        }
    }
}